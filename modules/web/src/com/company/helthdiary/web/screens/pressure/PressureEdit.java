package com.company.helthdiary.web.screens.pressure;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Pressure;

import javax.inject.Inject;

@UiController("helthdiary_Pressure.edit")
@UiDescriptor("pressure-edit.xml")
@EditedEntityContainer("pressureDc")
@LoadDataBeforeShow
public class PressureEdit extends StandardEditor<Pressure> {
    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setUser(AppBeans.get(UserSessionSource.class).getUserSession().getUser());
    }
}