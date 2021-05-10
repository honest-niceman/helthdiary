package com.company.helthdiary.web.screens.patient;

import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Patient;

@UiController("helthdiary_Patient1.browse")
@UiDescriptor("patient-browse-1.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class PatientBrowse1 extends MasterDetailScreen<Patient> {
}