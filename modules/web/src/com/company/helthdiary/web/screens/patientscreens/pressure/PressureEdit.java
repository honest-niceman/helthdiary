package com.company.helthdiary.web.screens.patientscreens.pressure;

import com.company.helthdiary.entity.patient.Patient;
import com.company.helthdiary.service.PatientGetterService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Pressure;

import javax.inject.Inject;

@UiController("helthdiary_Pressure.edit")
@UiDescriptor("pressure-edit.xml")
@EditedEntityContainer("pressureDc")
@LoadDataBeforeShow
public class PressureEdit extends StandardEditor<Pressure> {
    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setPatient(AppBeans.get(PatientGetterService.class).getPatient());
    }
}