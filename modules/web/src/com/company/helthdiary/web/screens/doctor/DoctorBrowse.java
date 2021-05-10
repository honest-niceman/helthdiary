package com.company.helthdiary.web.screens.doctor;

import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Doctor;

@UiController("helthdiary_Doctor.browse")
@UiDescriptor("doctor-browse.xml")
@LookupComponent("doctorsTable")
@LoadDataBeforeShow
public class DoctorBrowse extends StandardLookup<Doctor> {
}