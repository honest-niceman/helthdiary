package com.company.helthdiary.web.screens.patient;

import com.company.helthdiary.entity.Gender;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Patient;

import javax.inject.Inject;
import java.util.Date;

@UiController("helthdiary_Patient.browse")
@UiDescriptor("patient-browse.xml")
@LoadDataBeforeShow
public class PatientBrowse extends StandardLookup<Patient> {
    @Inject
    private CollectionLoader<Patient> patientsDl;
    @Inject
    private TextField<String> nameFirstField;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private Button createBtn;
    @Inject
    private TextField<String> weightField;
    @Inject
    private TextField<String> heightField;
    @Inject
    private TextField<String> calorieField;
    @Inject
    private DateField<Date> dateOfBirthField;
    @Inject
    private LookupField<Gender> genderField;
    @Inject
    private TextField<String> nameLastField;
    @Inject
    private Button editBtn;

    @Subscribe
    public void onInit(InitEvent event) {
        patientsDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        patientsDl.load();
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        //todo если в патиенДл есть 0 элемент то выставляем значения для наших филдов
        if (!patientsDl.getContainer().getMutableItems().isEmpty()) {
            updateFields();
            createBtn.setVisible(false);
            editBtn.setVisible(true);
        } else {
            editBtn.setVisible(false);
            createBtn.setVisible(true);
        }
    }

    public void updateFields() {
        patientsDl.load();
        nameFirstField.setValue(patientsDl.getContainer().getMutableItems().get(0).getNameFirst());
        nameLastField.setValue(patientsDl.getContainer().getMutableItems().get(0).getNameLast());
        dateOfBirthField.setDateFormat(patientsDl.getContainer().getMutableItems().get(0).getDateOfBirth().toString());
        genderField.setValue(patientsDl.getContainer().getMutableItems().get(0).getGender());
        heightField.setValue(patientsDl.getContainer().getMutableItems().get(0).getHeight().toString());
        weightField.setValue(patientsDl.getContainer().getMutableItems().get(0).getWeight().toString());
        calorieField.setValue(patientsDl.getContainer().getMutableItems().get(0).getCalorie().toString());
    }


    @Subscribe("editBtn")
    public void onEditBtnClick(Button.ClickEvent event) {
        screenBuilders.editor(Patient.class, this)
                .editEntity(patientsDl.getContainer().getMutableItems().get(0))
                .build()
                .show()
                .addAfterCloseListener(e -> {
                    updateFields();
                });

    }

    @Subscribe("createBtn")
    public void onCreateBtnClick(Button.ClickEvent event) {
        screenBuilders.editor(Patient.class, this)
                .newEntity()
                .build()
                .show();

    }
}