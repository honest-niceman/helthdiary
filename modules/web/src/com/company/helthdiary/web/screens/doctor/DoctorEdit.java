package com.company.helthdiary.web.screens.doctor;

import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Doctor;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.entity.UserRole;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UiController("helthdiary_Doctor.edit")
@UiDescriptor("doctor-edit.xml")
@EditedEntityContainer("doctorDc")
@LoadDataBeforeShow
public class DoctorEdit extends StandardEditor<Doctor> {
    @Inject
    private DataManager dataManager;
    private static final String COMPANY_GROUP_ID = "0fa2b1a5-1d68-4d69-9fbd-dff348347f93";
    @Inject
    private TextField<String> firstnameField;
    @Inject
    private TextField<String> lastnameField;
    @Inject
    private Metadata metadata;
    @Inject
    private PasswordEncryption passwordEncryption;


    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        // Load group and role to be assigned to the new user
        Group group = dataManager.load(LoadContext.create(Group.class).setId(UUID.fromString(COMPANY_GROUP_ID)));

        // Create a user instance
        User user = metadata.create(User.class);
        user.setLogin(firstnameField.getValue());
        user.setPassword(passwordEncryption.getPasswordHash(user.getId(), firstnameField.getValue()));
        user.setChangePasswordAtNextLogon(true);
        user.setFirstName(firstnameField.getValue());
        user.setLastName(lastnameField.getValue());

        // Note that the platform does not support the default group out of the box, so here we define the default group id and set it for the newly registered users.
        user.setGroup(group);

        getEditedEntity().setUser(user);

        event.getDataContext().clear();

        // Save new entities
        dataManager.commit(new CommitContext(user, getEditedEntity()));
    }

}