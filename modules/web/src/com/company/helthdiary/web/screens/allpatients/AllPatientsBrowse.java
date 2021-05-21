package com.company.helthdiary.web.screens.allpatients;

import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Patient;

@UiController("helthdiary_All_Patients.browse")
@UiDescriptor("all-patients-browse.xml")
@LookupComponent("patientsTable")
@LoadDataBeforeShow
public class AllPatientsBrowse extends StandardLookup<Patient> {
}