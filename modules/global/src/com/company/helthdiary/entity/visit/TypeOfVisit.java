package com.company.helthdiary.entity.visit;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum TypeOfVisit implements EnumClass<Integer> {

    CHECKUP(10),
    DISEASE(20),
    TESTS(30);

    private Integer id;

    TypeOfVisit(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static TypeOfVisit fromId(Integer id) {
        for (TypeOfVisit at : TypeOfVisit.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}