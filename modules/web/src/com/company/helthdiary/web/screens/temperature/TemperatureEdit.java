package com.company.helthdiary.web.screens.temperature;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Temperature;

@UiController("helthdiary_Temperature.edit")
@UiDescriptor("temperature-edit.xml")
@EditedEntityContainer("temperatureDc")
@LoadDataBeforeShow
public class TemperatureEdit extends StandardEditor<Temperature> {
    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setUser(AppBeans.get(UserSessionSource.class).getUserSession().getUser());
    }
}