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
    USER_ID uuid not null,
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
    RATE integer not null,
    USER_ID uuid,
    DESCRIPTION text,
    DATE_ date not null,
    --
    primary key (ID)
)^
-- end HELTHDIARY_PULSE
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
    USER_ID uuid not null,
    BEFORE_FOOD boolean,
    LEVEL integer not null,
    DESCRIPTION text,
    DATE_ date not null,
    --
    primary key (ID)
)^
-- end HELTHDIARY_GLUCOSE
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
    NAME_FIRST varchar(50) not null,
    NAME_LAST varchar(50) not null,
    DATE_OF_BIRTH date not null,
    GENDER varchar(50) not null,
    HEIGHT double precision,
    WEIGHT double precision,
    CALORIE double precision,
    --
    primary key (ID)
)^
-- end HELTHDIARY_PATIENT
