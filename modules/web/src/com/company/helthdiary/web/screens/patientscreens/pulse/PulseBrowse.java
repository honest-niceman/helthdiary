package com.company.helthdiary.web.screens.patientscreens.pulse;

import com.company.helthdiary.service.PatientGetterService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Pulse;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@UiController("helthdiary_Pulse.browse")
@UiDescriptor("pulse-browse.xml")
@LookupComponent("pulsesTable")
@LoadDataBeforeShow
public class PulseBrowse extends StandardLookup<Pulse> {
    @Inject
    private CollectionLoader<Pulse> pulsesDl;

    @Subscribe
    public void onInit(InitEvent event) {
        pulsesDl.setParameter("patient", AppBeans.get(PatientGetterService.class).getPatient());
        pulsesDl.load();
    }

    @Inject
    private DataManager dataManager;
    @Inject
    private TextArea<String> textArea;

    @Subscribe("btn")
    public void onBtnClick(Button.ClickEvent event) {
        generate();
        pulsesDl.load();
    }

    private Random random = new Random();

    private void generate() {
        CommitContext commitContext = new CommitContext();
        long date = 1612137600000L;
        long plusDay = 86400000L;
        for (int i = 0; i < 89; i++) {
            Pulse pulse = dataManager.create(Pulse.class);
            pulse.setDate(new Date(date));
            pulse.setRate(60 + random.nextInt(90));
            pulse.setPatient(AppBeans.get(PatientGetterService.class).getPatient());
            date = date + plusDay;
            commitContext.addInstanceToCommit(pulse);
        }
        dataManager.commit(commitContext);
    }

    private String displayInsert() {
        pulsesDl.load();
        StringBuilder stringBuilder = new StringBuilder();
        int size = pulsesDl.getContainer().getItems().size();
        for (int i = 0; i < size; i++) {
            Date date1 = pulsesDl.getContainer().getItems().get(i).getDate();

            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = outputFormatter.format(date1);

            stringBuilder.append("insert into HELTHDIARY_PULSE (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PATIENT_ID, RATE, DESCRIPTION, DATE_) values (")
                    .append("'").append(pulsesDl.getContainer().getItems().get(i).getId()).append("'").append(",")
                    .append(1).append(",")
                    .append("'").append(date).append("'").append(",")
                    .append("'admin'").append(",")
                    .append("'").append(date).append("'").append(",")
                    .append("null").append(",")
                    .append("null").append(",")
                    .append("null").append(",")
                    .append("'").append(pulsesDl.getContainer().getItems().get(i).getPatient().getId()).append("'").append(",")
                    .append(pulsesDl.getContainer().getItems().get(i).getRate()).append(",")
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