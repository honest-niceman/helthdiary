package com.company.helthdiary.web.screens.patientscreens.aboutme;

import com.company.helthdiary.entity.patient.Gender;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.patient.Patient;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@UiController("helthdiary_Patient.editAboutMe")
@UiDescriptor("patient-edit-about-me.xml")
@EditedEntityContainer("patientDc")
@LoadDataBeforeShow
public class AboutMePatientEdit extends StandardEditor<Patient> {
    @Inject
    private TextField<Double> heightField;
    @Inject
    private LookupField<Gender> genderField;
    @Inject
    private TextField<Double> weightField;
    @Inject
    private DateField<Date> dateOfBirthField;
    @Inject
    private TextField<Double> calorieField;

    private void recalculateCalorie() {
        if (heightField.getValue() == null || weightField.getValue() == null ||
                genderField.getValue() == null || dateOfBirthField.getValue() == null) {
            return;
        } else {
            calorieField.setValue(calculateCalorieValue(heightField.getValue(),
                    weightField.getValue(),
                    genderField.getValue(),
                    calculateAge(dateOfBirthField.getValue())));
        }
    }

    private int calculateAge(Date birthDate) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(AppBeans.get(TimeSource.class).currentTimestamp()));
        return (d2 - d1) / 10000;
    }

    private double calculateCalorieValue(double height, double weight, Gender gender, int age) {
        double cal = 0.0;
        if (gender.equals(Gender.MALE)) {
            cal = 66.5 + (13.75 * weight) + (5.003 * height) - (6.775 * age);
        } else if (gender.equals(Gender.FEMALE)) {
            cal = 655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age);
        }
        return cal;
    }

    @Subscribe("dateOfBirthField")
    public void onDateOfBirthFieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        recalculateCalorie();
    }

    @Subscribe("genderField")
    public void onGenderFieldValueChange(HasValue.ValueChangeEvent<Gender> event) {
        recalculateCalorie();
    }

    @Subscribe("heightField")
    public void onHeightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        recalculateCalorie();
    }

    @Subscribe("weightField")
    public void onWeightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        recalculateCalorie();
    }
}