package com.company.helthdiary.web.screens.patient;

import com.company.helthdiary.entity.Glucose;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Patient;

import javax.inject.Inject;

@UiController("helthdiary_Patient.browse")
@UiDescriptor("patient-browse.xml")
@LookupComponent("patientsTable")
@LoadDataBeforeShow
public class PatientBrowse extends StandardLookup<Patient> {
    @Inject
    private CollectionLoader<Patient> patientsDl;

    @Subscribe
    public void onInit(InitEvent event) {
        patientsDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        patientsDl.load();
    }
}