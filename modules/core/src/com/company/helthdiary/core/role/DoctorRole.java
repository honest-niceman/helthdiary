package com.company.helthdiary.core.role;

import com.company.helthdiary.entity.doctor.Doctor;
import com.company.helthdiary.entity.patient.*;
import com.company.helthdiary.entity.visit.Visit;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.*;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;

@Role(name = DoctorRole.NAME)
public class DoctorRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Doctor";

    @EntityAccess(entityClass = Patient.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = Pressure.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = Pulse.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = Glucose.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = Temperature.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = Visit.class, operations = {EntityOp.UPDATE, EntityOp.READ})
    @EntityAccess(entityClass = Doctor.class, operations = {EntityOp.READ, EntityOp.UPDATE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Patient.class, modify = "*")
    @EntityAttributeAccess(entityClass = Pressure.class, modify = "*")
    @EntityAttributeAccess(entityClass = Glucose.class, modify = "*")
    @EntityAttributeAccess(entityClass = Temperature.class, modify = "*")
    @EntityAttributeAccess(entityClass = Visit.class, modify = "*")
    @EntityAttributeAccess(entityClass = Doctor.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @ScreenAccess(screenIds = {
            "application-helthdiary",
            "helthdiary_All_Patients.browse",
            "helthdiary_Doctor.browse",
            "helthdiary_Doctor.edit",
            "helthdiary_DoctorVisit.browse",
            "helthdiary_GraphScreen",
            "new-login",
            "aboutWindow",
            "help",
            "settings",
            "main",
            "mainWindow"
    })
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @SpecificAccess(permissions = {
            "cuba.gui.filter.customConditions",
            "cuba.gui.filter.edit",
            "cuba.gui.filter.global",
            "cuba.gui.filter.maxResults"
    })
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }
}
