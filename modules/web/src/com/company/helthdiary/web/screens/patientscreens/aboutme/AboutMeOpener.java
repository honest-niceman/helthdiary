package com.company.helthdiary.web.screens.patientscreens.aboutme;

import com.company.helthdiary.service.PatientGetterService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.web.AppUI;

public class AboutMeOpener implements Runnable{
    @Override
    public void run() {
        Screens screens = AppUI.getCurrent().getScreens();

        AboutMePatientEdit aboutMePatientEdit = screens.create(AboutMePatientEdit.class, OpenMode.NEW_TAB);
        aboutMePatientEdit.setEntityToEdit(AppBeans.get(PatientGetterService.class).getPatient());

        aboutMePatientEdit.show();
    }
}
