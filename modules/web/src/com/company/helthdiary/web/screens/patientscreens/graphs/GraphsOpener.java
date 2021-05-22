package com.company.helthdiary.web.screens.patientscreens.graphs;

import com.company.helthdiary.service.PatientGetterService;
import com.company.helthdiary.web.screens.patientscreens.aboutme.AboutMePatientEdit;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.web.AppUI;

public class GraphsOpener implements Runnable{
    @Override
    public void run() {
        Screens screens = AppUI.getCurrent().getScreens();

        if(AppBeans.get(PatientGetterService.class).getPatient().getDateOfBirth()==null){
            AboutMePatientEdit aboutMePatientEdit = screens.create(AboutMePatientEdit.class, OpenMode.DIALOG);
            aboutMePatientEdit.setEntityToEdit(AppBeans.get(PatientGetterService.class).getPatient());

            aboutMePatientEdit.show();
        } else {
            GraphScreen graphScreen = screens.create(GraphScreen.class, OpenMode.NEW_TAB);
            graphScreen.show();
        }
    }
}
