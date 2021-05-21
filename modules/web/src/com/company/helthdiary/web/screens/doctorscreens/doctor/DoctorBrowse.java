package com.company.helthdiary.web.screens.doctorscreens.doctor;

import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.doctor.Doctor;

@UiController("helthdiary_Doctor.browse")
@UiDescriptor("doctor-browse.xml")
@LookupComponent("doctorsTable")
@LoadDataBeforeShow
public class DoctorBrowse extends StandardLookup<Doctor> {
}