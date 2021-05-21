package com.company.helthdiary.service;

import com.company.helthdiary.entity.patient.Patient;

public interface PatientGetterService {
    String NAME = "helthdiary_PatientGetterService";

    Patient getPatient();
}