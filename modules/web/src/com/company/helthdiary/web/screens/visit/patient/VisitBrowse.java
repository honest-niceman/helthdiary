package com.company.helthdiary.web.screens.visit.patient;

import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Visit;

@UiController("helthdiary_Visit.browse")
@UiDescriptor("visit-browse.xml")
@LookupComponent("visitsTable")
@LoadDataBeforeShow
public class VisitBrowse extends StandardLookup<Visit> {
}