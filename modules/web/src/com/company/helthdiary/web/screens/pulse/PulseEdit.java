package com.company.helthdiary.web.screens.pulse;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Pulse;

@UiController("helthdiary_Pulse.edit")
@UiDescriptor("pulse-edit.xml")
@EditedEntityContainer("pulseDc")
@LoadDataBeforeShow
public class PulseEdit extends StandardEditor<Pulse> {

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setUser(AppBeans.get(UserSessionSource.class).getUserSession().getUser());
    }
}