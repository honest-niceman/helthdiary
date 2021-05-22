package com.company.helthdiary.web.screens.patientscreens.pressure;

import com.company.helthdiary.entity.patient.Patient;
import com.company.helthdiary.service.PatientGetterService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Pressure;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@UiController("helthdiary_Pressure.browse")
@UiDescriptor("pressure-browse.xml")
@LookupComponent("pressuresTable")
@LoadDataBeforeShow
public class PressureBrowse extends StandardLookup<Pressure> {
    @Inject
    private CollectionLoader<Pressure> pressuresDl;

    @Subscribe
    public void onInit(InitEvent event) {
        pressuresDl.setParameter("patient", AppBeans.get(PatientGetterService.class).getPatient());
        pressuresDl.load();
    }

    @Inject
    private DataManager dataManager;
    @Inject
    private TextArea<String> textArea;

    @Subscribe("btn")
    public void onBtnClick(Button.ClickEvent event) {
        generate();
        pressuresDl.load();
    }

    private Random random = new Random();

    private void generate() {
        CommitContext commitContext = new CommitContext();
        long date = 1612137600000L;
        long plusDay = 86400000L;
        for (int i = 0; i < 89; i++) {
            Pressure pressure = dataManager.create(Pressure.class);
            pressure.setDate(new Date(date));
            pressure.setLowerBp(60 + random.nextInt(40));
            pressure.setUpperBp(110 + random.nextInt(60));
            pressure.setPatient(AppBeans.get(PatientGetterService.class).getPatient());
            date = date + plusDay;
            commitContext.addInstanceToCommit(pressure);
        }
        dataManager.commit(commitContext);
    }

    private String displayInsert() {
        pressuresDl.load();
        StringBuilder stringBuilder = new StringBuilder();
        int size = pressuresDl.getContainer().getItems().size();
        for (int i = 0; i < size; i++) {
            Date date1 = pressuresDl.getContainer().getItems().get(i).getDate();

            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = outputFormatter.format(date1);

            stringBuilder.append("insert into HELTHDIARY_PRESSURE (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PATIENT_ID, UPPER_BP, LOWER_BP, DESCRIPTION, DATE_) values (")
                    .append("'").append(pressuresDl.getContainer().getItems().get(i).getId()).append("'").append(",")
                    .append(1).append(",")
                    .append("'").append(date).append("'").append(",")
                    .append("'admin'").append(",")
                    .append("'").append(date).append("'").append(",")
                    .append("null").append(",")
                    .append("null").append(",")
                    .append("null").append(",")
                    .append("'").append(pressuresDl.getContainer().getItems().get(i).getPatient().getId()).append("'").append(",")
                    .append(pressuresDl.getContainer().getItems().get(i).getLowerBp()).append(",")
                    .append(pressuresDl.getContainer().getItems().get(i).getUpperBp()).append(",")
                    .append("null").append(",")
                    .append("'").append(date).append("'")
                    .append(");").append("\n");
        }
        return stringBuilder.toString();
    }

    @Subscribe("showScript")
    public void onShowScriptClick(Button.ClickEvent event) {
        textArea.setValue(displayInsert());
    }
}