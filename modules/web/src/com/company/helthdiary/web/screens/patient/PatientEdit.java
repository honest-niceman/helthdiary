package com.company.helthdiary.web.screens.patient;

import com.company.helthdiary.entity.Gender;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.company.helthdiary.entity.Patient;

import javax.inject.Inject;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@UiController("helthdiary_Patient.edit")
@UiDescriptor("patient-edit.xml")
@EditedEntityContainer("patientDc")
@LoadDataBeforeShow
public class PatientEdit extends StandardEditor<Patient> {
    @Inject
    private LookupField<Gender> genderField;
    @Inject
    private DateField<Date> dateOfBirthField;
    @Inject
    protected TimeSource timeSource;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setUser(AppBeans.get(UserSessionSource.class).getUserSession().getUser());
    }

    @Subscribe("heightField")
    public void onHeightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        LocalDate dob;
        LocalDate now;
        if (checkFields()==true){
            now=convertToLocalDateViaInstant(timeSource.currentTimestamp());
            try {
                dob = convertToLocalDateViaInstant(dateOfBirthField.getValue());
            }
            catch(Exception ex){
                dob=((java.sql.Date) dateOfBirthField.getValue()).toLocalDate();
            }
            int age=Period.between(dob, now).getYears();

            int cal=(int)getCal(Double.parseDouble(heightField.getRawValue()),Double.parseDouble(weightField.getRawValue()),age);
            calorieField.setValue(Double.valueOf(cal));
        }
    }

    @Subscribe("weightField")
    public void onWeightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        LocalDate dob;
        LocalDate now;
        if (checkFields()==true){
            now = convertToLocalDateViaInstant(timeSource.currentTimestamp());
            try {
                dob = convertToLocalDateViaInstant(dateOfBirthField.getValue());
            }
            catch(Exception ex){
                dob=((java.sql.Date) dateOfBirthField.getValue()).toLocalDate();
            }
            int age=Period.between(dob, now).getYears();
            int cal=(int)getCal(Double.parseDouble(heightField.getRawValue()),Double.parseDouble(weightField.getRawValue()),age);
            calorieField.setValue(Double.valueOf(cal));

        }
    }

    @Subscribe("dateOfBirthField")
    public void onDateOfBirthFieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        LocalDate dob;
        LocalDate now;
        if (checkFields()==true){
            now=convertToLocalDateViaInstant(timeSource.currentTimestamp());
            dob=((java.sql.Date) dateOfBirthField.getValue()).toLocalDate();
            int age=Period.between(dob, now).getYears();

            int cal=(int)getCal(Double.parseDouble(heightField.getRawValue()),Double.parseDouble(weightField.getRawValue()),age);
            calorieField.setValue(Double.valueOf(cal));
        }

    }

    @Inject
    private TextField<Double> calorieField;


    public double getCal(double h, double w, int age ){
        double cal=0.0;
        if(genderField.getValue().equals(Gender.MALE)){
            cal=66.5+(13.75*w)+(5.003*h)-(6.775*age);
        }
        else if(genderField.getValue().equals(Gender.FEMALE)){
            cal=655.1+(9.563*w)+(1.85*h)-(4.676*age);
        }
        else{
//            notifications.create().withCaption(
//                    "Please fill all fields"
//            ).show();
            return cal;
        }
        return cal;
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Inject
    private TextField<Double> heightField;

    public boolean checkFields(){
        if(heightField.getValue() == null || weightField.getValue()==null || dateOfBirthField.getValue()==null){
//            notifications.create().withCaption(
//                    "Please fill all fields"
//            ).show();
            return false;
        }
        else{
            return true;
        }
    }

    @Inject
    private TextField<Double> weightField;
}