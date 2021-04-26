package com.company.helthdiary.web.screens.restorepassword;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.components.ValidationErrors;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.app.UserManagementService;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Objects;

@UiController("helthdiary_RestorePasswordScreen")
@UiDescriptor("restore-password-screen.xml")
public class RestorePasswordScreen extends Screen {

    /**
     * Injected service for user management operations.
     */
    @Inject
    private UserManagementService userManagementService;

    /**
     * Injected DataManager for loading data from middleware
     */
    @Inject
    private DataManager dataManager;

    @Inject
    private Notifications notifications;

    @Inject
    private TextField<String> loginField;

    @Inject
    private Label<String> warningLabel;

    @Inject
    private Messages messages;

    public String getLogin() {
        return loginField.getValue();
    }

    /**
     * "Send new password" button click handler.
     */
    @Subscribe("okBtn")
    public void onOkBtnClick(Button.ClickEvent event) {
        // validate required fields first
        if (validateScreen()) {
            LoadContext<User> lc = LoadContext.create(User.class);
            lc.setView(View.MINIMAL);
            lc.setQueryString("select u from sec$User u where u.loginLowerCase = :login")
                    .setParameter("login", Objects.requireNonNull(loginField.getValue()).toLowerCase());

            User targetUser = dataManager.load(lc);
            if (targetUser == null) {
                warningLabel.setVisible(true);

                loginField.focus();
            } else {
                // generate new temporary password and send email
                // user must have specified e-mail in the database
                userManagementService.changePasswordsAtLogonAndSendEmails(Collections.singletonList(targetUser.getId()));

                notifications.create(Notifications.NotificationType.TRAY)
                        .withCaption(
                                messages.getMessage(
                                        "com/company/helthdiary/web/screens/restorepassword/",
                                        "success")
                        )
                        .withDescription(
                                messages.getMessage(
                                        "com/company/helthdiary/web/screens/restorepassword/",
                                        "description")
                        )
                        .show();

                close(WINDOW_COMMIT_AND_CLOSE_ACTION);
            }
        }
    }

    /**
     * "Cancel" button click handler.
     */
    @Subscribe("cancelBtn")
    public void onCancelBtnClick1(Button.ClickEvent event) {
        close(WINDOW_CLOSE_ACTION);
    }

    private boolean validateScreen() {
        ScreenValidation screenValidation = getBeanLocator().get(ScreenValidation.NAME);
        ValidationErrors errors = screenValidation.validateUiComponents(getWindow());

        if (errors.isEmpty())
            return true;

        screenValidation.showValidationErrors(this, errors);

        return false;
    }
}