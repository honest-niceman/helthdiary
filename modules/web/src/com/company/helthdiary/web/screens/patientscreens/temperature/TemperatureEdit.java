package com.company.helthdiary.web.screens.patientscreens.temperature;

import com.company.helthdiary.entity.patient.Patient;
import com.company.helthdiary.service.PatientGetterService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Temperature;

import javax.inject.Inject;

@UiController("helthdiary_Temperature.edit")
@UiDescriptor("temperature-edit.xml")
@EditedEntityContainer("temperatureDc")
@LoadDataBeforeShow
public class TemperatureEdit extends StandardEditor<Temperature> {
    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setPatient(AppBeans.get(PatientGetterService.class).getPatient());
    }
}