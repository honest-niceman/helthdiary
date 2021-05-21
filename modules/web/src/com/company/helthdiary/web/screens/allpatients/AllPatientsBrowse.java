package com.company.helthdiary.web.screens.allpatients;

import com.company.helthdiary.entity.Visit;
import com.company.helthdiary.web.screenoptions.SelectedPatient;
import com.company.helthdiary.web.screens.graphs.GraphScreen;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Patient;

import javax.inject.Inject;

@UiController("helthdiary_All_Patients.browse")
@UiDescriptor("all-patients-browse.xml")
@LookupComponent("patientsTable")
@LoadDataBeforeShow
public class AllPatientsBrowse extends StandardLookup<Patient> {
    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe("patientsTable")
    public void onPatientsTableSelection(Table.SelectionEvent<Patient> event) {
        if(!event.getSelected().isEmpty()){
            screenBuilders.screen(this)
                    .withScreenClass(GraphScreen.class)
                    .withOptions(new SelectedPatient(event.getSelected().iterator().next()))
                    .build()
                    .show();
        }
    }
}