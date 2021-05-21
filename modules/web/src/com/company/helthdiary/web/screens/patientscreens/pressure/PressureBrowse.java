package com.company.helthdiary.web.screens.patientscreens.pressure;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Pressure;

import javax.inject.Inject;

@UiController("helthdiary_Pressure.browse")
@UiDescriptor("pressure-browse.xml")
@LookupComponent("pressuresTable")
@LoadDataBeforeShow
public class PressureBrowse extends StandardLookup<Pressure> {
    @Inject
    private CollectionLoader<Pressure> pressuresDl;

    @Subscribe
    public void onInit(InitEvent event) {
        pressuresDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        pressuresDl.load();
    }
}