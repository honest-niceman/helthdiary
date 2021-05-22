package com.company.helthdiary.entity.visit;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VisitType implements EnumClass<String> {


    REGULAR_CHECKUP("Regular checkup"),
    DISEASE("Disease"),
    TESTS("Tests"),
    OTHER("Other");


    private String id;

    VisitType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static VisitType fromId(String id) {
        for (VisitType at : VisitType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}