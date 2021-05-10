package com.company.helthdiary.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Specialization implements EnumClass<Integer> {

    DERMATOLOGY(10),
    PEDIATRICS(20),
    IMMUNOLOGY(30),
    NEUROLOGY(40),
    PSYCHIATRY(50),
    OPHTHALMOLOGY(60);

    private Integer id;

    Specialization(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static Specialization fromId(Integer id) {
        for (Specialization at : Specialization.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}