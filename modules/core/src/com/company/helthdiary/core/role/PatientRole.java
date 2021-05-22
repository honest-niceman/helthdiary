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

@Role(name = PatientRole.NAME)
public class PatientRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Patient";

    @EntityAccess(entityClass = Patient.class, operations = {EntityOp.READ, EntityOp.UPDATE, EntityOp.CREATE})
    @EntityAccess(entityClass = Pressure.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE, EntityOp.READ})
    @EntityAccess(entityClass = Pulse.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE, EntityOp.READ})
    @EntityAccess(entityClass = Glucose.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE, EntityOp.READ})
    @EntityAccess(entityClass = Temperature.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE, EntityOp.READ})
    @EntityAccess(entityClass = Visit.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE, EntityOp.READ})
    @EntityAccess(entityClass = Doctor.class, operations = {EntityOp.READ})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Patient.class, modify = "*")
    @EntityAttributeAccess(entityClass = Pressure.class, modify = "*")
    @EntityAttributeAccess(entityClass = Pulse.class, modify = "*")
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
            "new-login",
            "helthdiary_RegisterScreen",
            "helthdiary_RestorePasswordScreen",
            "health_indicators",
            "helthdiary_Patient.editAboutMe",
            "helthdiary_Pressure.browse",
            "helthdiary_Pressure.edit",
            "helthdiary_Glucose.browse",
            "helthdiary_Glucose.edit",
            "helthdiary_Pulse.browse",
            "helthdiary_Pulse.edit",
            "helthdiary_Temperature.browse",
            "helthdiary_Temperature.edit",
            "helthdiary_Visit.browse",
            "helthdiary_Visit.edit",
            "helthdiary_GraphScreen",
            "helthdiary_Doctor.browse1",
            "aboutWindow",
            "graphs_opener",
            "about_me_opener",
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
