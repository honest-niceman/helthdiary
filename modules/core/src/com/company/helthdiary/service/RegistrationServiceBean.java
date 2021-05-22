package com.company.helthdiary.service;

import com.company.helthdiary.core.role.PatientRole;
import com.company.helthdiary.entity.patient.Patient;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.entity.UserRole;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.UUID;

@Service(RegistrationService.NAME)
public class RegistrationServiceBean implements RegistrationService {
    /**
     * ID of the Group for self-registered users.
     */
    private static final String COMPANY_GROUP_ID = "0fa2b1a5-1d68-4d69-9fbd-dff348347f93";

    @Inject
    private DataManager dataManager;

    @Inject
    private Metadata metadata;

    @Inject
    private PasswordEncryption passwordEncryption;


    @Override
    public RegistrationResult registerUser(String login, String password) {

        // Load group and role to be assigned to the new user
        Group group = dataManager.load(LoadContext.create(Group.class).setId(UUID.fromString(COMPANY_GROUP_ID)));

        // Create a user instance
        User user = metadata.create(User.class);
        user.setLogin(login);
        user.setPassword(passwordEncryption.getPasswordHash(user.getId(), password));

        UserRole patientRole = metadata.create(UserRole.class);
        patientRole.setUser(user);
        patientRole.setRoleName(PatientRole.NAME);

        ArrayList<UserRole> patientRoles = new ArrayList<>();
        patientRoles.add(patientRole);

        user.setUserRoles(patientRoles);

        Patient patient = metadata.create(Patient.class);
        patient.setUser(user);

        // Note that the platform does not support the default group out of the box, so here we define the default group id and set it for the newly registered users.
        user.setGroup(group);

        // Save new entities
        dataManager.commit(new CommitContext(user, patient, patientRole));

        return new RegistrationResult(user);
    }
}