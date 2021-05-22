package com.company.helthdiary.entity.patient;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "HELTHDIARY_PULSE")
@Entity(name = "helthdiary_Pulse")
@NamePattern("%s %s|patient,rate")
public class Pulse extends StandardEntity {
    private static final long serialVersionUID = -395241012061114516L;

    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @NotNull
    @Column(name = "RATE", nullable = false)
    private Integer rate;

    public void setDescription(String description) {
        this.description = description;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDescription() {
        return description;
    }

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DATE_", nullable = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}