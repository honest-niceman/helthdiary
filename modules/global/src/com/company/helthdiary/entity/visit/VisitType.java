package com.company.helthdiary.entity.visit;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VisitType implements EnumClass<String> {


    REGULAR_CHECKUP("REGULAR_CHECKUP", "event-blue", "BLUE"),
    DISEASE("DISEASE", "event-green", "GREEN"),
    TESTS("TESTS", "event-yellow", "YELLOW"),
    OTHER("OTHER", "event-purple", "PURPLE");


    private String id;
    private String styleName;
    private final String icon;

    VisitType(String value, String styleName, String icon) {
        this.id = value;
        this.styleName = styleName;
        this.icon = icon;
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

    public String getStyleName() {
        return id;
    }

    public String getIcon() {
        return icon;
    }
}