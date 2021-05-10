alter table HELTHDIARY_VISIT add constraint FK_HELTHDIARY_VISIT_ON_PATIENT foreign key (PATIENT_ID) references HELTHDIARY_PATIENT(ID);
alter table HELTHDIARY_VISIT add constraint FK_HELTHDIARY_VISIT_ON_DOCTOR foreign key (DOCTOR_ID) references HELTHDIARY_DOCTOR(ID);
create index IDX_HELTHDIARY_VISIT_ON_PATIENT on HELTHDIARY_VISIT (PATIENT_ID);
create index IDX_HELTHDIARY_VISIT_ON_DOCTOR on HELTHDIARY_VISIT (DOCTOR_ID);
