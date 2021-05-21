package com.company.helthdiary.web.screens.patientscreens.glucose;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Glucose;

import javax.inject.Inject;

@UiController("helthdiary_Glucose.browse")
@UiDescriptor("glucose-browse.xml")
@LookupComponent("glucosesTable")
@LoadDataBeforeShow
public class GlucoseBrowse extends StandardLookup<Glucose> {

    @Inject
    private CollectionLoader<Glucose> glucoseDl;

    @Subscribe
    public void onInit(InitEvent event) {
        glucoseDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        glucoseDl.load();
    }
}