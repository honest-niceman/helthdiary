package com.company.helthdiary.web.screens.patientscreens.graphs;

import com.company.helthdiary.entity.patient.*;
import com.company.helthdiary.web.screenoptions.SelectedPatient;
import com.haulmont.charts.gui.components.charts.SerialChart;
import com.haulmont.charts.gui.data.DataProvider;
import com.haulmont.charts.gui.data.ListDataProvider;
import com.haulmont.charts.gui.data.MapDataItem;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@UiController("helthdiary_GraphScreen")
@UiDescriptor("graph-screen.xml")
public class GraphScreen extends Screen {
    @Inject
    private CollectionLoader<com.company.helthdiary.entity.patient.Pulse> pulsesDl;
    @Inject
    private CollectionLoader<Glucose> glucoseDl;
    @Inject
    private CollectionLoader<Pressure> pressuresDl;
    @Inject
    private CollectionContainer<Pulse> pulsesDc;
    @Inject
    private DateField<Date> startDate;
    @Inject
    private SerialChart pulseSerialChart;
    @Inject
    private CollectionLoader<Patient> patientsDl;
    @Inject
    protected TimeSource timeSource;
    @Inject
    private DateField<Date> endDate;
    @Inject
    private Notifications notifications;
    @Inject
    private CollectionLoader<Temperature> temperaturesDl;

    private Patient patient;
    private User user;

    @Subscribe
    public void onInit(InitEvent event) {
        initPatientAndUser(event);

        initLoaders();

        highlightCols();
    }

    @Subscribe("filterBtn")
    public void onFilterBtnClick(Button.ClickEvent event) {
        if (startDate.getValue() != null || endDate.getValue() != null) {
            if (!startDate.getValue().before(endDate.getValue())) {
                notifications.create().withCaption("Start date should be greate than end date").show();
            } else {
                Date start = startDate.getValue();
                Date end = endDate.getValue();

                loadGlucoses(start, end);
                loadPressuress(start, end);
                loadPulses(start, end);
                loadTemperatures(start, end);

                highlightCols();
            }
        } else {
            notifications.create().withCaption("Input fields are empty").show();
        }
    }

    private void initPatientAndUser(InitEvent event){
        ScreenOptions options = event.getOptions();
        if (options instanceof SelectedPatient) {
            patient = ((SelectedPatient) options).getPatient();
            user = patient.getUser();
        } else {
            patientsDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
            patientsDl.load();
            patient = patientsDl.getContainer().getItems().get(0);
            user = AppBeans.get(UserSessionSource.class).getUserSession().getUser();
        }
    }

    private void initLoaders(){
        pulsesDl.setParameter("user", user);
        pulsesDl.load();

        glucoseDl.setParameter("user", user);
        glucoseDl.load();

        pressuresDl.setParameter("user", user);
        pressuresDl.load();

        temperaturesDl.setParameter("user", user);
        temperaturesDl.load();
    }

    private void loadGlucoses(Date start, Date end) {
        glucoseDl.setQuery("select e from helthdiary_Glucose e where e.user = :user and e.date between :dateFirst and :dateLast");
        glucoseDl.setParameter("user", user);
        glucoseDl.setParameter("dateFirst", start);
        glucoseDl.setParameter("dateLast", end);
        glucoseDl.load();
    }

    private void loadPressuress(Date start, Date end) {
        pressuresDl.setQuery("select e from helthdiary_Pressure e where e.user = :user and e.date between :dateFirst and :dateLast");
        pressuresDl.setParameter("user", user);
        pressuresDl.setParameter("dateFirst", start);
        pressuresDl.setParameter("dateLast", end);
        pressuresDl.load();
    }

    private void loadPulses(Date start, Date end) {
        pulsesDl.setQuery("select e from helthdiary_Pulse e where e.user = :user and e.date between :dateFirst and :dateLast");
        pulsesDl.setParameter("user", user);
        pulsesDl.setParameter("dateFirst", start);
        pulsesDl.setParameter("dateLast", end);
        pulsesDl.load();
    }

    private void loadTemperatures(Date start, Date end) {
        temperaturesDl.setQuery("select e from helthdiary_Temperature e where e.user = :user and e.date between :dateFirst and :dateLast");
        temperaturesDl.setParameter("user", user);
        temperaturesDl.setParameter("dateFirst", start);
        temperaturesDl.setParameter("dateLast", end);
        temperaturesDl.load();
    }

    private void highlightCols() {
        DataProvider dataProvider = new ListDataProvider();

        for (Pulse pulse : pulsesDc.getItems()) {
            Map<String, Object> dataItem = new HashMap<>();
            dataItem.put("rate", pulse.getRate());
            dataItem.put("date", pulse.getDate());

            Date patientDateOfBirth = patient.getDateOfBirth();

            LocalDate now = convertToLocalDateViaInstant(timeSource.currentTimestamp());
            LocalDate localDateOfBirth = convertToLocalDateViaInstant(patientDateOfBirth);

            int age = Period.between(localDateOfBirth, now).getYears();
            int normalRateMin = calculateNormalRateMin(age);
            int normalRateMax = calculateNormalRateMax(age);

            if (pulse.getRate() >= normalRateMax) {
                String color = pulse.getRate() >= normalRateMax ? "#FF0F00" : "#A9DEFF";
                dataItem.put("color", color);
            } else if (pulse.getRate() <= normalRateMin) {
                String color = pulse.getRate() <= normalRateMin ? "#FFFF00" : "#A9DEFF";
                dataItem.put("color", color);
            }

            dataProvider.addItem(new MapDataItem(dataItem));
        }
        pulseSerialChart.setDataProvider(dataProvider);
    }

    private int calculateNormalRateMax(int age) {
        int normalRateMax = 10;

        if (age >= 12 && age <= 15) {
            normalRateMax = 86 + 10;
        } else if (age >= 16 && age <= 17) {
            normalRateMax = 80 + 10;
        } else if (age >= 18 && age <= 50 && patient.getGender().equals(Gender.MALE)) {
            normalRateMax = 80 + 10;
        } else if (age >= 18 && age <= 50 && patient.getGender().equals(Gender.FEMALE)) {
            normalRateMax = 70 + 10;
        } else if (age >= 51) {
            normalRateMax = 90 + 10;
        }
        return normalRateMax;
    }

    private int calculateNormalRateMin(int age){
        int normalRateMin = 0;

        if (age >= 12 && age <= 15) {
            normalRateMin = 68 - 10;
        } else if (age >= 16 && age <= 17) {
            normalRateMin = 64 - 10;
        } else if (age >= 18 && age <= 50 && patient.getGender().equals(Gender.MALE)) {
            normalRateMin = 60 - 10;
        } else if (age >= 18 && age <= 50 && patient.getGender().equals(Gender.FEMALE)) {
            normalRateMin = 60 - 10;
        } else if (age >= 51) {
            normalRateMin = 75 - 20;
        }
        return normalRateMin;
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}