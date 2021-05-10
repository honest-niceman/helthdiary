create table HELTHDIARY_VISIT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PATIENT_ID uuid not null,
    DOCTOR_ID uuid not null,
    START_ timestamp not null,
    END_ timestamp not null,
    TYPE_ integer not null,
    --
    primary key (ID)
);