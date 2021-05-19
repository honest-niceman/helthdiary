package com.company.helthdiary.core.role;

import com.company.helthdiary.entity.*;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = SelfUserRegistratedRole.NAME, isDefault = true)
public class SelfUserRegistratedRole extends AnnotatedRoleDefinition {
    public final static String NAME = "self-user-registrated";

    @EntityAccess(entityClass = Patient.class, operations = {EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE, EntityOp.READ})
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

    @ScreenAccess(screenIds = {"helthdiary_RegisterScreen", "application-helthdiary", "helthdiary_Pressure.browse", "helthdiary_Pressure.edit", "helthdiary_RestorePasswordScreen", "helthdiary_Glucose.browse", "helthdiary_Pulse.browse", "helthdiary_Patient.browse", "helthdiary_Visit.browse", "helthdiary_GraphScreen", "helthdiary_Temperature.browse", "helthdiary_Doctor.browse1", "helthdiary_Doctor.browse"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }
}
