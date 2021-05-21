package com.company.helthdiary.web.screens.patientscreens.visit;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.visit.Visit;

import javax.inject.Inject;

@UiController("helthdiary_Visit.browse")
@UiDescriptor("visit-browse.xml")
@LookupComponent("visitsTable")
@LoadDataBeforeShow
public class VisitBrowse extends StandardLookup<Visit> {
    @Inject
    private CollectionLoader<Visit> visitsDl;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        visitsDl.setParameter("patientUser", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
    }

}