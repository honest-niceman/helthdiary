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
);