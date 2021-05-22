package com.company.helthdiary.web.screens.doctorscreens.visit;

import com.company.helthdiary.entity.doctor.Doctor;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.visit.Visit;

import javax.inject.Inject;

@UiController("helthdiary_DoctorVisit.browse")
@UiDescriptor("doctor-visit-browse.xml")
@LookupComponent("visitsTable")
@LoadDataBeforeShow
public class DoctorVisitBrowse extends StandardLookup<Visit> {
    @Inject
    private CollectionLoader<Visit> visitsDl;
    @Inject
    private Notifications notifications;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        visitsDl.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        visitsDl.load();
        if(visitsDl.getContainer().getItems().isEmpty()){
            notifications.create().withCaption("Good news! You have not visits for now. Take a rest.").show();
        }
    }

}