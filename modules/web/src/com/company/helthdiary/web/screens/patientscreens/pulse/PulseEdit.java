package com.company.helthdiary.web.screens.patientscreens.pulse;

import com.company.helthdiary.entity.patient.Patient;
import com.company.helthdiary.service.PatientGetterService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Pulse;

import javax.inject.Inject;

@UiController("helthdiary_Pulse.edit")
@UiDescriptor("pulse-edit.xml")
@EditedEntityContainer("pulseDc")
@LoadDataBeforeShow
public class PulseEdit extends StandardEditor<Pulse> {
    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setPatient(AppBeans.get(PatientGetterService.class).getPatient());
    }
}