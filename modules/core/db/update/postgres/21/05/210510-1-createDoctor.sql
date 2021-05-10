create table HELTHDIARY_DOCTOR (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID uuid,
    FIRSTNAME varchar(50) not null,
    LASTNAME varchar(50) not null,
    SPECIALITY integer not null,
    EDUCATION integer not null,
    --
    primary key (ID)
);