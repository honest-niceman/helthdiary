package com.company.helthdiary.service;

import com.company.helthdiary.entity.patient.Patient;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(PatientGetterService.NAME)
public class PatientGetterServiceBean implements PatientGetterService {

    @Inject
    private DataManager dataManager;

    @Override
    public Patient getPatient() {
        return dataManager.load(Patient.class)
                .query("select e from helthdiary_Patient e where e.user= :user")
                .parameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser())
                .one();
    }
}