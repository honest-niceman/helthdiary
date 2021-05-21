package com.company.helthdiary.web.screens.visit.doctor;

import com.company.helthdiary.entity.Doctor;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Visit;

import javax.inject.Inject;

@UiController("helthdiary_DoctorVisit.browse")
@UiDescriptor("doctor-visit-browse.xml")
@LookupComponent("visitsTable")
@LoadDataBeforeShow
public class DoctorVisitBrowse extends StandardLookup<Visit> {
    @Inject
    private CollectionLoader<Visit> visitsDl;
    @Inject
    private CollectionLoader<Doctor> doctorLoader;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        doctorLoader.setParameter("user", AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        doctorLoader.load();
        visitsDl.setParameter("doctor", doctorLoader.getContainer().getItems().get(0));
        visitsDl.load();
    }

}