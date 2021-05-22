package com.company.helthdiary.web.screens.patientscreens.visit;

import com.company.helthdiary.entity.doctor.Doctor;
import com.company.helthdiary.entity.visit.VisitStyles;
import com.company.helthdiary.entity.visit.VisitType;
import com.company.helthdiary.service.PatientGetterService;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.visit.Visit;
import org.checkerframework.checker.units.qual.C;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;

@UiController("helthdiary_Visit.browse")
@UiDescriptor("visit-browse.xml")
@LookupComponent("visitsTable")
@LoadDataBeforeShow
public class VisitBrowse extends StandardLookup<Visit> {
    @Inject
    private CollectionLoader<Visit> visitsDl;
    @Inject
    private DataManager dataManager;
    @Inject
    private CollectionLoader<Doctor> doctorsDl;
    @Inject
    private Calendar<LocalDateTime> calendar;

    private LocalDateTime currentStartCalendar;
    private LocalDateTime currentEndCalendar;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        visitsDl.setParameter("patient", AppBeans.get(PatientGetterService.class).getPatient());
        visitsDl.load();
        if (!visitsDl.getContainer().getItems().isEmpty()) {
            currentStartCalendar = visitsDl.getContainer().getItems().get(0).getStart();
            calendar.setStartDate(currentStartCalendar);
            currentEndCalendar = visitsDl.getContainer().getItems().get(0).getStart().plusWeeks(1);
            calendar.setEndDate(currentEndCalendar);
        } else {
            currentStartCalendar = convertToLocalDateTimeViaInstant(AppBeans.get(TimeSource.class).currentTimestamp());
            currentEndCalendar = currentStartCalendar.plusWeeks(1);
        }
    }

    @Subscribe("btn")
    public void onBtnClick(Button.ClickEvent event) {
        generateVisits();
        visitsDl.load();
    }

    private Random random = new Random();

    private void generateVisits() {
        CommitContext commitContext = new CommitContext();
        long visitStart;
        if (visitsDl.getContainer().getItems().isEmpty()) {
            Date now = AppBeans.get(TimeSource.class).currentTimestamp();
            visitStart = now.getTime();
        } else {
            ZonedDateTime zdt = ZonedDateTime.of(visitsDl.getContainer().getItems().get(visitsDl.getContainer().getItems().size() - 1).getEnd(), ZoneId.systemDefault());
            visitStart = zdt.toInstant().toEpochMilli();
        }

        long one_hour = 3600000L;
        for (int i = 0; i < 1 + random.nextInt(10); i++) {
            Visit visit = dataManager.create(Visit.class);
            visit.setDoctor(doctorsDl.getContainer().getItems().get(random.nextInt(doctorsDl.getContainer().getItems().size() - 1)));
            visit.setPatient(AppBeans.get(PatientGetterService.class).getPatient());
            if (i < 2) {
                visit.setType(VisitType.DISEASE);
                visit.setStylename(VisitStyles.RED.getId());
            } else if (i < 4) {
                visit.setType(VisitType.REGULAR_CHECKUP);
                visit.setStylename(VisitStyles.BLUE.getId());
            } else if (i < 7) {
                visit.setType(VisitType.TESTS);
                visit.setStylename(VisitStyles.GREEN.getId());
            } else {
                visit.setType(VisitType.OTHER);
                visit.setStylename(VisitStyles.YELLOW.getId());
            }
            visitStart = visitStart + random.nextInt(24) * one_hour;
            visit.setStart(convertToLocalDateTimeViaInstant(new Date(visitStart)));
            commitContext.addInstanceToCommit(visit);
        }
        dataManager.commit(commitContext);
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @Subscribe("nextWeek")
    public void onNextWeekClick(Button.ClickEvent event) {
        currentStartCalendar = currentStartCalendar.plusWeeks(1);
        currentEndCalendar = currentEndCalendar.plusWeeks(1);
        calendar.setStartDate(currentStartCalendar);
        calendar.setEndDate(currentEndCalendar);
    }

    @Subscribe("prevWeek")
    public void onPrevWeekClick(Button.ClickEvent event) {
        currentStartCalendar = currentStartCalendar.minusWeeks(1);
        currentEndCalendar = currentEndCalendar.minusWeeks(1);
        calendar.setStartDate(currentStartCalendar);
        calendar.setEndDate(currentEndCalendar);
    }
}