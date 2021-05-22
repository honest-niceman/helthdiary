package com.company.helthdiary.entity.visit;

import com.company.helthdiary.entity.doctor.Doctor;
import com.company.helthdiary.entity.patient.Patient;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

@Table(name = "HELTHDIARY_VISIT")
@Entity(name = "helthdiary_Visit")
@NamePattern("%s %s|doctorName,patient")
public class Visit extends StandardEntity {
    private static final long serialVersionUID = 2136051950488074008L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @Column(name = "STYLENAME")
    private String stylename;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @NotNull
    @Column(name = "TYPE_", nullable = false)
    private String type;

    @NotNull
    @Column(name = "START_", nullable = false)
    @Future
    private LocalDateTime start;

    @Transient
    @MetaProperty(related = "doctor")
    private String doctorName;

    @Transient
    @MetaProperty(related = "type")
    private String doctorType;

    public String getStylename() {
        return stylename;
    }

    public void setStylename(String stylename) {
        this.stylename = stylename;
    }

    public String getDoctorName() {
        return Optional.ofNullable(getDoctor())
                .map(Doctor::getFirstname)
                .orElse("");
    }

    public void setType(VisitType type) {
        this.type = type == null ? null : type.getId();
    }

    public VisitType getType() {
        return type == null ? null : VisitType.fromId(type);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @MetaProperty(related = {"start"})
    public LocalDateTime getEnd() {
        return (start != null) ? start.plusHours(2) : null;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }
}