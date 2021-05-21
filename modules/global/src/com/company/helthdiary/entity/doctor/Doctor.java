package com.company.helthdiary.entity.doctor;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "HELTHDIARY_DOCTOR")
@Entity(name = "helthdiary_Doctor")
@NamePattern("%s|firstname")
public class Doctor extends StandardEntity {
    private static final long serialVersionUID = -919053709125039542L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Column(name = "FIRSTNAME", nullable = false, length = 50)
    private String firstname;

    @NotNull
    @Column(name = "LASTNAME", nullable = false, length = 50)
    private String lastname;

    @NotNull
    @Column(name = "SPECIALITY", nullable = false)
    private Integer speciality;

    @NotNull
    @Column(name = "EDUCATION", nullable = false)
    private Integer education;

    public Education getEducation() {
        return education == null ? null : Education.fromId(education);
    }

    public void setEducation(Education education) {
        this.education = education == null ? null : education.getId();
    }

    public Specialization getSpeciality() {
        return speciality == null ? null : Specialization.fromId(speciality);
    }

    public void setSpeciality(Specialization speciality) {
        this.speciality = speciality == null ? null : speciality.getId();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}