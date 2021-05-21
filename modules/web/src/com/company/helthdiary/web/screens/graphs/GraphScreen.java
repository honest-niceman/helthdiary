package com.company.helthdiary.web.screens.graphs;

import com.company.helthdiary.entity.*;
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
    private CollectionLoader<Pulse> pulsesDl;
    @Inject
    private CollectionLoader<Glucose> glucoseDl;
    @Inject
    private CollectionLoader<Pressure> pressuresDl;
    @Inject
    private CollectionContainer<Pulse> pulsesDc;

    //todo переименуй чтобы был pulseSerialChart
    @Inject
    private SerialChart Pulse;
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

        pulsesDl.setParameter("user", user);
        pulsesDl.load();

        glucoseDl.setParameter("user", user);
        glucoseDl.load();

        pressuresDl.setParameter("user", user);
        pressuresDl.load();

        temperaturesDl.setParameter("user", user);
        temperaturesDl.load();

        highliteCols();
    }


    @Subscribe("filterBtn")
    public void onFilterBtnClick(Button.ClickEvent event) {
        try {
            if (startDate.getValue().before(endDate.getValue()) == false || startDate.getValue().after(endDate.getValue()) == true) {
                notifications.create().withCaption("Check input dates").show();
            } else if (startDate.getValue() != null && endDate.getValue() != null) {

                Date start = startDate.getValue();
                Date end = endDate.getValue();

                glucoseDl.setQuery("select e from helthdiary_Glucose e where e.user = :user and e.date between :dateFirst and :dateLast");
                glucoseDl.setParameter("user", user);
                glucoseDl.setParameter("dateFirst", start);
                glucoseDl.setParameter("dateLast", end);
                glucoseDl.load();

                pressuresDl.setQuery("select e from helthdiary_Pressure e where e.user = :user and e.date between :dateFirst and :dateLast");
                pressuresDl.setParameter("user", user);
                pressuresDl.setParameter("dateFirst", start);
                pressuresDl.setParameter("dateLast", end);
                pressuresDl.load();

                pulsesDl.setQuery("select e from helthdiary_Pulse e where e.user = :user and e.date between :dateFirst and :dateLast");
                pulsesDl.setParameter("user", user);
                pulsesDl.setParameter("dateFirst", start);
                pulsesDl.setParameter("dateLast", end);
                pulsesDl.load();
                highliteCols();

                temperaturesDl.setQuery("select e from helthdiary_Temperature e where e.user = :user and e.date between :dateFirst and :dateLast");
                temperaturesDl.setParameter("user", user);
                temperaturesDl.setParameter("dateFirst", start);
                temperaturesDl.setParameter("dateLast", end);
                temperaturesDl.load();

            }
        } catch (NullPointerException ex) {
            notifications.create().withCaption("Input fields are empty").show();
        }
    }

    @Inject
    private DateField<Date> startDate;

    private void highliteCols() {
        DataProvider dataProvider = new ListDataProvider();

        for (Pulse pulse : pulsesDc.getItems()) {
            Map<String, Object> dataItem = new HashMap<>();
            dataItem.put("rate", pulse.getRate());
            dataItem.put("date", pulse.getDate());

            LocalDate dob;
            LocalDate now = convertToLocalDateViaInstant(timeSource.currentTimestamp());

            Date rawDOB = patient.getDateOfBirth();
            dob = convertToLocalDateViaInstant(rawDOB);

            int age = Period.between(dob, now).getYears();
            int normalRateMin = 0;
            int normalRateMax = 10;

            if (age >= 12 && age <= 15) {
                normalRateMin = 68 - 10;
                normalRateMax = 86 + 10;
            } else if (age >= 16 && age <= 17) {
                normalRateMin = 64 - 10;
                normalRateMax = 80 + 10;
            } else if (age >= 18 && age <= 50 && patient.getGender().equals(Gender.MALE)) {
                normalRateMin = 60 - 10;
                normalRateMax = 80 + 10;
            } else if (age >= 18 && age <= 50 && patient.getGender().equals(Gender.FEMALE)) {
                normalRateMin = 60 - 10;
                normalRateMax = 70 + 10;
            } else if (age >= 51) {
                normalRateMin = 75 - 20;
                normalRateMax = 90 + 10;
            }

            //todo добавь переменную final наверх и там укажи значение 70 и цвета назови
            if (pulse.getRate() >= normalRateMax) {
                String color = pulse.getRate() >= normalRateMax ? "#FF0F00" : "#A9DEFF";
                dataItem.put("color", color);
            } else if (pulse.getRate() <= normalRateMin) {
                String color = pulse.getRate() <= normalRateMin ? "#FFFF00" : "#A9DEFF";
                dataItem.put("color", color);
            }


            dataProvider.addItem(new MapDataItem(dataItem));
        }
        Pulse.setDataProvider(dataProvider);
    }


    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}