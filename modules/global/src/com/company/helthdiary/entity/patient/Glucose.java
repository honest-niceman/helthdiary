package com.company.helthdiary.entity.patient;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "HELTHDIARY_GLUCOSE")
@Entity(name = "helthdiary_Glucose")
@NamePattern("%s %s|level,date")
public class Glucose extends StandardEntity {
    private static final long serialVersionUID = 8302173468187410980L;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open", "clear"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PATIENT_ID")
    @NotNull
    private Patient patient;

    @Column(name = "BEFORE_FOOD")
    private Boolean beforeFood;

    @NotNull
    @Column(name = "LEVEL", nullable = false)
    private Integer level;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "DATE_", nullable = false)
    private Date date;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public Boolean getBeforeFood() {
        return beforeFood;
    }

    public void setBeforeFood(Boolean beforeFood) {
        this.beforeFood = beforeFood;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

}