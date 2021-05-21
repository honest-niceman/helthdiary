package com.company.helthdiary.web.screens.doctorscreens.doctor;

import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.doctor.Doctor;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.entity.UserRole;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.UUID;

@UiController("helthdiary_Doctor.edit")
@UiDescriptor("doctor-edit.xml")
@EditedEntityContainer("doctorDc")
@LoadDataBeforeShow
public class DoctorEdit extends StandardEditor<Doctor> {
    @Inject
    private DataManager dataManager;
    @Inject
    private TextField<String> firstnameField;
    @Inject
    private TextField<String> lastnameField;
    @Inject
    private Metadata metadata;
    @Inject
    private PasswordEncryption passwordEncryption;

    private static final String COMPANY_GROUP_ID = "0fa2b1a5-1d68-4d69-9fbd-dff348347f93";
    private static final String DOCTOR_ROLE_ID = "55dc3375-e1b0-f6f2-ff72-93a456b240ac";

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        // Load group and role to be assigned to the new user
        Group group = dataManager.load(LoadContext.create(Group.class).setId(UUID.fromString(COMPANY_GROUP_ID)));

        // Create a user instance
        User doctorUser = metadata.create(User.class);
        doctorUser.setLogin(firstnameField.getValue());
        doctorUser.setPassword(passwordEncryption.getPasswordHash(doctorUser.getId(), firstnameField.getValue()));
        doctorUser.setChangePasswordAtNextLogon(true);
        doctorUser.setFirstName(firstnameField.getValue());
        doctorUser.setLastName(lastnameField.getValue());

        //находим роль, которую создали программно: класс com/company/helthdiary/core/role/DoctorRole.java
        Role role = dataManager.load(LoadContext.create(Role.class).setId(UUID.fromString(DOCTOR_ROLE_ID)));
        UserRole userRole = dataManager.create(UserRole.class); //создаем юзерРоль

        ArrayList<UserRole> userRoles = new ArrayList<>(); // создаем список юзерролей
        userRoles.add(userRole); // добавляем ранее созданную юзер роль

        doctorUser.setUserRoles(userRoles); // присваем роли нашему доктору-юзеру

        // у юзера роль указываем роль, пользователя и имя роли которую создали в классе com/company/helthdiary/core/role/DoctorRole.java
        userRole.setRole(role);
        userRole.setUser(doctorUser);
        userRole.setRoleName("Doctor");

        // Note that the platform does not support the default group out of the box, so here we define the default group id and set it for the newly registered users.
        doctorUser.setGroup(group);

        getEditedEntity().setUser(doctorUser);

        event.getDataContext().clear();

        // Save new entities
        dataManager.commit(new CommitContext(doctorUser, getEditedEntity(),userRole));
    }

}