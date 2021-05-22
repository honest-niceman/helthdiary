package com.company.helthdiary.web.screens.patientscreens.visit;

import com.company.helthdiary.entity.patient.Patient;
import com.company.helthdiary.service.PatientGetterService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.visit.Visit;

import javax.inject.Inject;
import java.util.UUID;

@UiController("helthdiary_Visit.edit")
@UiDescriptor("visit-edit.xml")
@EditedEntityContainer("visitDc")
@LoadDataBeforeShow
public class VisitEdit extends StandardEditor<Visit> {
    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setPatient(AppBeans.get(PatientGetterService.class).getPatient());
    }
}