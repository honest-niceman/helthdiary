package com.company.helthdiary.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.security.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.util.Date;

@Table(name = "HELTHDIARY_PATIENT")
@Entity(name = "helthdiary_Patient")
public class Patient extends StandardEntity {
    private static final long serialVersionUID = 5508022192149477734L;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open", "clear"})

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", unique = true)
    private User user;

    @NotNull
    @Column(name = "NAME_FIRST", nullable = false, length = 50)
    @Length(min = 1, max = 50)
    private String nameFirst;

    @NotNull
    @Column(name = "NAME_LAST", nullable = false, length = 50)
    private String nameLast;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    @PastOrPresent(message = "Дата рождения не должна превышать текущую дату")
    private Date dateOfBirth;

    @NotNull
    @Column(name = "GENDER", nullable = false)
    private Integer gender;

    @Column(name = "HEIGHT")
    @Positive
    @NotNull
    private Double height;

    @Column(name = "WEIGHT")
    @Positive
    @NotNull
    private Double weight;

    @Column(name = "CALORIE")
    private Double calorie;

    public void setGender(Gender gender) {
        this.gender = gender == null ? null : gender.getId();
    }

    public Gender getGender() {
        return gender == null ? null : Gender.fromId(gender);
    }

    public Double getCalorie() {
        return calorie;
    }

    public void setCalorie(Double calorie) {
        this.calorie = calorie;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}