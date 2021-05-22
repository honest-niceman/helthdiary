package com.company.helthdiary.entity.visit;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VisitStyles implements EnumClass<String> {

    RED("event-red"),
    YELLOW("event-yellow"),
    BLUE("event-blue"),
    GREEN("event-green");

    private String id;

    VisitStyles(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static VisitStyles fromId(String id) {
        for (VisitStyles at : VisitStyles.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}