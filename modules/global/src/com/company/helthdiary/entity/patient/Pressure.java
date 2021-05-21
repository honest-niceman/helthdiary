package com.company.helthdiary.entity.patient;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "HELTHDIARY_PRESSURE")
@Entity(name = "helthdiary_Pressure")
@NamePattern("%s|description")
public class Pressure extends StandardEntity {
    private static final long serialVersionUID = -3841916254072302190L;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open", "clear"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Column(name = "UPPER_BP", nullable = false)
    private Integer upperBp;

    @Column(name = "LOWER_BP", nullable = false)
    @NotNull
    private Integer lowerBp;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "DATE_", nullable = false)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLowerBp() {
        return lowerBp;
    }

    public void setLowerBp(Integer lowerBp) {
        this.lowerBp = lowerBp;
    }

    public Integer getUpperBp() {
        return upperBp;
    }

    public void setUpperBp(Integer upperBp) {
        this.upperBp = upperBp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}