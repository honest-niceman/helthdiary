package com.company.helthdiary.web.screens.patientscreens.pulse;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Pulse;

import javax.inject.Inject;

@UiController("helthdiary_Pulse.browse")
@UiDescriptor("pulse-browse.xml")
@LookupComponent("pulsesTable")
@LoadDataBeforeShow
public class PulseBrowse extends StandardLookup<Pulse> {
    @Inject
    private CollectionLoader<Pulse> pulsesDl;

    @Subscribe
    public void onInit(InitEvent event) {
        pulsesDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        pulsesDl.load();
    }
}