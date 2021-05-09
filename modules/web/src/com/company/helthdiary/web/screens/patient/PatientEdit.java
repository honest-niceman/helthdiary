package com.company.helthdiary.web.screens.patient;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Patient;

@UiController("helthdiary_Patient.edit")
@UiDescriptor("patient-edit.xml")
@EditedEntityContainer("patientDc")
@LoadDataBeforeShow
public class PatientEdit extends StandardEditor<Patient> {
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setUser(AppBeans.get(UserSessionSource.class).getUserSession().getUser());
    }
}