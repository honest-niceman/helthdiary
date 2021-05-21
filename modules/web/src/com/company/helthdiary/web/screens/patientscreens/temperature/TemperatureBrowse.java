package com.company.helthdiary.web.screens.patientscreens.temperature;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Temperature;

import javax.inject.Inject;

@UiController("helthdiary_Temperature.browse")
@UiDescriptor("temperature-browse.xml")
@LookupComponent("temperaturesTable")
@LoadDataBeforeShow
public class TemperatureBrowse extends StandardLookup<Temperature> {
    @Inject
    private CollectionLoader<Temperature> temperaturesDl;

    @Subscribe
    public void onInit(InitEvent event) {
        temperaturesDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        temperaturesDl.load();
    }
}