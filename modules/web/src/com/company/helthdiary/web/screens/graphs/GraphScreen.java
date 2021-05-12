package com.company.helthdiary.web.screens.graphs;

import com.company.helthdiary.entity.*;
import com.haulmont.charts.gui.components.charts.SerialChart;
import com.haulmont.charts.gui.data.DataProvider;
import com.haulmont.charts.gui.data.ListDataProvider;
import com.haulmont.charts.gui.data.MapDataItem;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

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
    private CollectionContainer<Patient> patientsDc;
    @Inject
    protected TimeSource timeSource;
    @Inject
    private DataManager dataManager;
    @Inject
    private CollectionContainer<Pressure> pressuresDc;
    @Inject
    private DateField<Date> endDate;
    @Inject
    private Notifications notifications;
    @Inject
    private CollectionLoader<Temperature> temperaturesDl;

    @Subscribe
    public void onInit(InitEvent event) {
        pulsesDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        pulsesDl.load();
        glucoseDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        glucoseDl.load();
        pressuresDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        pressuresDl.load();
        patientsDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        patientsDl.load();
        temperaturesDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        temperaturesDl.load();


        highliteCols();

    }



    @Subscribe("filterBtn")
    public void onFilterBtnClick(Button.ClickEvent event) {
        try {
            if (startDate.getValue().before(endDate.getValue()) == false || startDate.getValue().after(endDate.getValue()) == true) {
                notifications.create().withCaption("Check input dates").show();
            } else if (startDate.getValue() != null && endDate.getValue() != null) {

                Date start=startDate.getValue();
                Date end=endDate.getValue();

                glucoseDl.setQuery("select e from helthdiary_Glucose e where e.user = :user and e.date between :dateFirst and :dateLast");
                glucoseDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
                glucoseDl.setParameter("dateFirst", start);
                glucoseDl.setParameter("dateLast", end);
                glucoseDl.load();

                pressuresDl.setQuery("select e from helthdiary_Pressure e where e.user = :user and e.date between :dateFirst and :dateLast");
                pressuresDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
                pressuresDl.setParameter("dateFirst", start);
                pressuresDl.setParameter("dateLast", end);
                pressuresDl.load();

                pulsesDl.setQuery("select e from helthdiary_Pulse e where e.user = :user and e.date between :dateFirst and :dateLast");
                pulsesDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
                pulsesDl.setParameter("dateFirst", start);
                pulsesDl.setParameter("dateLast", end);
                pulsesDl.load();
              //  highliteCols();

                temperaturesDl.setQuery("select e from helthdiary_Temperature e where e.user = :user and e.date between :dateFirst and :dateLast");
                temperaturesDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
                temperaturesDl.setParameter("dateFirst", start);
                temperaturesDl.setParameter("dateLast", end);
                temperaturesDl.load();

            }
        }
        catch (NullPointerException ex){
            notifications.create().withCaption("Input fields are empty").show();
        }
    }

    @Inject
    private DateField<Date> startDate;

    private void highliteCols() {
        DataProvider dataProvider = new ListDataProvider();

        Patient patient = patientsDl.getContainer().getItems().get(0);

        for (Pulse pulse : pulsesDc.getItems()) {
            Map<String, Object> dataItem = new HashMap<>();
            dataItem.put("rate",pulse.getRate());
            dataItem.put("date",pulse.getDate());

            LocalDate dob;
            LocalDate now=convertToLocalDateViaInstant(timeSource.currentTimestamp());

            Date rawDOB=patient.getDateOfBirth();
            dob = convertToLocalDateViaInstant(rawDOB);

            int age=Period.between(dob, now).getYears();
            int normalRateMin=0;
            int normalRateMax=10;

            if(age>=12 && age<=15 ){
                normalRateMin=68-10;
                normalRateMax=86+10;
            }
            else if(age>=16 && age<=17 ){
                normalRateMin=64-10;
                normalRateMax=80+10;
            }
            else if(age>=18 && age<=50 && patient.getGender().equals(Gender.MALE)){
                normalRateMin=60-10;
                normalRateMax=80+10;
            }
            else if(age>=18 && age<=50 && patient.getGender().equals(Gender.FEMALE)){
                normalRateMin=60-10;
                normalRateMax=70+10;
            }
            else if(age>=51){
                normalRateMin=75-20;
                normalRateMax=90+10;
            }

            //todo добавь переменную final наверх и там укажи значение 70 и цвета назови
            if(pulse.getRate()>=normalRateMax){
                String color = pulse.getRate() >= normalRateMax  ? "#FF0F00" : "#A9DEFF";
                dataItem.put("color", color);
            }
            else if(pulse.getRate()<=normalRateMin){
                String color = pulse.getRate() <= normalRateMin  ? "#FFFF00" : "#A9DEFF";
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

//<chart:serialChart
//        dataContainer="temperaturesDc"
//        width="100%"
//        height="100%"
//        theme="NONE"
//        marginRight="70"
//        autoMarginOffset="20"
//        categoryField="date"
//        align="TOP_RIGHT">
//<chart:balloon cornerRadius="6"/>
//<chart:valueAxes>
//<chart:axis axisAlpha="0"/>
//</chart:valueAxes>
//<chart:graphs>
//<chart:graph
//        bullet="ROUND"
//        bulletSize="6"
//        connect="false"
//        lineColor="#b6d278"
//        lineThickness="2"
//        negativeLineColor="#487dac"
//        valueField="measure"/>
//</chart:graphs>
//<chart:chartCursor categoryBalloonDateFormat="DD"
//        cursorAlpha="0.1"
//        cursorColor="#000000"
//        fullWidth="true"
//        graphBulletSize="2"/>
//<chart:categoryAxis minPeriod="DAYS"
//        minorGridEnabled="true"/>
//<chart:export/>
//</chart:serialChart>