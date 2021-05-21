package com.company.helthdiary.web.screens.patientscreens.glucose;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Glucose;

@UiController("helthdiary_Glucose.edit")
@UiDescriptor("glucose-edit.xml")
@EditedEntityContainer("glucoseDc")
@LoadDataBeforeShow
public class GlucoseEdit extends StandardEditor<Glucose> {
    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setUser(AppBeans.get(UserSessionSource.class).getUserSession().getUser());
    }
}