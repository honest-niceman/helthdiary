package com.company.helthdiary.web.screenoptions;

import com.company.helthdiary.entity.patient.Patient;
import com.haulmont.cuba.gui.screen.ScreenOptions;

public class SelectedPatient implements ScreenOptions {
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public SelectedPatient(Patient patient) {
        this.patient = patient;
    }
}
