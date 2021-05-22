package com.company.helthdiary.web.screens.patientscreens.glucose;

import com.company.helthdiary.service.PatientGetterService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Glucose;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@UiController("helthdiary_Glucose.browse")
@UiDescriptor("glucose-browse.xml")
@LookupComponent("glucosesTable")
@LoadDataBeforeShow
public class GlucoseBrowse extends StandardLookup<Glucose> {

    @Inject
    private CollectionLoader<Glucose> glucoseDl;

    @Subscribe
    public void onInit(InitEvent event) {
        glucoseDl.setParameter("patient", AppBeans.get(PatientGetterService.class).getPatient());
        glucoseDl.load();
    }

    @Inject
    private DataManager dataManager;
    @Inject
    private TextArea<String> textArea;

    @Subscribe("btn")
    public void onBtnClick(Button.ClickEvent event) {
        generate();
        glucoseDl.load();
    }

    private Random random = new Random();

    private void generate() {
        CommitContext commitContext = new CommitContext();
        long date = 1612137600000L;
        long plusDay = 86400000L;
        for (int i = 0; i < 89; i++) {
            Glucose glucose = dataManager.create(Glucose.class);
            glucose.setDate(new Date(date));
            glucose.setLevel(70 + random.nextInt(130));
            glucose.setPatient(AppBeans.get(PatientGetterService.class).getPatient());
            if (glucose.getLevel() < 150) {
                glucose.setBeforeFood(true);
            } else {
                glucose.setBeforeFood(false);
            }
            date = date + plusDay;
            commitContext.addInstanceToCommit(glucose);
        }
        dataManager.commit(commitContext);
    }

    private String displayInsert() {
        glucoseDl.load();
        StringBuilder stringBuilder = new StringBuilder();
        int size = glucoseDl.getContainer().getItems().size();
        for (int i = 0; i < size; i++) {
            Date date1 = glucoseDl.getContainer().getItems().get(i).getDate();

            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = outputFormatter.format(date1);

            stringBuilder.append("insert into HELTHDIARY_GLUCOSE (ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PATIENT_ID, BEFORE_FOOD, LEVEL, DESCRIPTION, DATE_) values (")
                    .append("'").append(glucoseDl.getContainer().getItems().get(i).getId()).append("'").append(",")
                    .append(1).append(",")
                    .append("'").append(date).append("'").append(",")
                    .append("'admin'").append(",")
                    .append("'").append(date).append("'").append(",")
                    .append("null").append(",")
                    .append("null").append(",")
                    .append("null").append(",")
                    .append("'").append(glucoseDl.getContainer().getItems().get(i).getPatient().getId()).append("'").append(",")
                    .append(glucoseDl.getContainer().getItems().get(i).getBeforeFood()).append(",")
                    .append(glucoseDl.getContainer().getItems().get(i).getLevel()).append(",")
                    .append("null").append(",")
                    .append("'").append(date).append("'").append(",")
                    .append(");").append("\n");
        }
        return stringBuilder.toString();
    }

    @Subscribe("showScript")
    public void onShowScriptClick(Button.ClickEvent event) {
        textArea.setValue(displayInsert());
    }
}