-- begin HELTHDIARY_PATIENT
create table HELTHDIARY_PATIENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID uuid not null,
    NAME_FIRST varchar(50),
    NAME_LAST varchar(50),
    DATE_OF_BIRTH date,
    GENDER integer,
    HEIGHT double precision,
    WEIGHT double precision,
    CALORIE double precision,
    --
    primary key (ID)
)^
-- end HELTHDIARY_PATIENT
-- begin HELTHDIARY_PRESSURE
create table HELTHDIARY_PRESSURE (
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
    UPPER_BP integer not null,
    LOWER_BP integer not null,
    DESCRIPTION text,
    DATE_ date not null,
    --
    primary key (ID)
)^
-- end HELTHDIARY_PRESSURE
-- begin HELTHDIARY_PULSE
create table HELTHDIARY_PULSE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PATIENT_ID uuid,
    RATE integer not null,
    DESCRIPTION text,
    DATE_ date not null,
    --
    primary key (ID)
)^
-- end HELTHDIARY_PULSE
-- begin HELTHDIARY_TEMPERATURE
create table HELTHDIARY_TEMPERATURE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PATIENT_ID uuid,
    MEASURE double precision not null,
    DATE_ date not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end HELTHDIARY_TEMPERATURE
-- begin HELTHDIARY_DOCTOR
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
)^
-- end HELTHDIARY_DOCTOR
-- begin HELTHDIARY_GLUCOSE
create table HELTHDIARY_GLUCOSE (
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
    BEFORE_FOOD boolean,
    LEVEL integer not null,
    DESCRIPTION text,
    DATE_ date not null,
    --
    primary key (ID)
)^
-- end HELTHDIARY_GLUCOSE
-- begin HELTHDIARY_VISIT
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
    STYLENAME varchar(255),
    DOCTOR_ID uuid not null,
    TYPE_ varchar(50) not null,
    START_ timestamp not null,
    --
    primary key (ID)
)^
-- end HELTHDIARY_VISIT
