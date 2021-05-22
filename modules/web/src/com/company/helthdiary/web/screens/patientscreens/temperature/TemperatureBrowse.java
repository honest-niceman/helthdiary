package com.company.helthdiary.web.screens.patientscreens.temperature;

import com.company.helthdiary.service.PatientGetterService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Temperature;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@UiController("helthdiary_Temperature.browse")
@UiDescriptor("temperature-browse.xml")
@LookupComponent("temperaturesTable")
@LoadDataBeforeShow
public class TemperatureBrowse extends StandardLookup<Temperature> {
    @Inject
    private CollectionLoader<Temperature> temperaturesDl;

    @Subscribe
    public void onInit(InitEvent event) {
        temperaturesDl.setParameter("patient", AppBeans.get(PatientGetterService.class).getPatient());
        temperaturesDl.load();
    }

    @Inject
    private DataManager dataManager;
    @Inject
    private TextArea<String> textArea;

    @Subscribe("btn")
    public void onBtnClick(Button.ClickEvent event) {
        generate();
        temperaturesDl.load();
    }

    private Random random = new Random();

    private void generate() {
        CommitContext commitContext = new CommitContext();
        long date = 1612137600000L;
        long plusDay = 86400000L;
        for (int i = 0; i < 89; i++) {
            Temperature temperature = dataManager.create(Temperature.class);
            temperature.setDate(new Date(date));
            temperature.setMeasure(
                    BigDecimal.valueOf(
                            35 + (40 - 35) * random.nextDouble()
                    ).setScale(1, RoundingMode.HALF_UP).doubleValue()
            );
            temperature.setPatient(AppBeans.get(PatientGetterService.class).getPatient());
            date = date + plusDay;
            commitContext.addInstanceToCommit(temperature);
        }
        dataManager.commit(commitContext);
    }

    private String displayInsert() {
        temperaturesDl.load();
        StringBuilder stringBuilder = new StringBuilder();
        int size = temperaturesDl.getContainer().getItems().size();
        for (int i = 0; i < size; i++) {
            Date date1 = temperaturesDl.getContainer().getItems().get(i).getDate();

            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = outputFormatter.format(date1);

            stringBuilder.append("insert into HELTHDIARY_TEMPERATURE(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PATIENT_ID, MEASURE, DATE_, DESCRIPTION) values (")
                    .append("'").append(temperaturesDl.getContainer().getItems().get(i).getId()).append("'").append(",")
                    .append(1).append(",")
                    .append("'").append(date).append("'").append(",")
                    .append("'admin'").append(",")
                    .append("'").append(date).append("'").append(",")
                    .append("null").append(",")
                    .append("null").append(",")
                    .append("null").append(",")
                    .append("'").append(temperaturesDl.getContainer().getItems().get(i).getPatient().getId()).append("'").append(",")
                    .append(temperaturesDl.getContainer().getItems().get(i).getMeasure()).append(",")
                    .append("'").append(date).append("'").append(",")
                    .append("null")
                    .append(");").append("\n");
        }
        return stringBuilder.toString();
    }

    @Subscribe("showScript")
    public void onShowScriptClick(Button.ClickEvent event) {
        textArea.setValue(displayInsert());
    }
}