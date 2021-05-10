package com.company.helthdiary.web.screens.visit;

import com.company.helthdiary.entity.Patient;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Visit;

import javax.inject.Inject;
import java.util.UUID;

@UiController("helthdiary_Visit.edit")
@UiDescriptor("visit-edit.xml")
@EditedEntityContainer("visitDc")
@LoadDataBeforeShow
public class VisitEdit extends StandardEditor<Visit> {
    @Inject
    private DataManager dataManager;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        UUID id = AppBeans.get(UserSessionSource.class).getUserSession().getUser().getId();

        Patient patient = dataManager.load(Patient.class)
                .query("select e from helthdiary_Patient e where e.user.id = :id")
                .parameter("id", id)
                .one();

        getEditedEntity().setPatient(patient);
    }
}