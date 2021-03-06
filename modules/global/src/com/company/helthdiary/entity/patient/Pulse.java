package com.company.helthdiary.entity.patient;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "HELTHDIARY_PULSE")
@Entity(name = "helthdiary_Pulse")
@NamePattern("%s %s|user,rate")
public class Pulse extends StandardEntity {
    private static final long serialVersionUID = -395241012061114516L;

    @NotNull
    @Column(name = "RATE", nullable = false)
    private Integer rate;

    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

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

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}