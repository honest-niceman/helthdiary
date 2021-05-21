package com.company.helthdiary.entity.doctor;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Education implements EnumClass<Integer> {

    DOCTOR_OF_MEDICINE(10),
    MASTER_DEGREE(20),
    DOCTOR_OF_OSTEOPATHIC(30),
    BACHELOR(40);

    private Integer id;

    Education(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static Education fromId(Integer id) {
        for (Education at : Education.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}