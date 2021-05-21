package com.company.helthdiary.web.screens.patientscreens.doctor;

import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.doctor.Doctor;

@UiController("helthdiary_Doctor.browse1")
@UiDescriptor("doctor-browse1.xml")
@LookupComponent("doctorsTable")
@LoadDataBeforeShow
public class DoctorBrowse1 extends StandardLookup<Doctor> {
}