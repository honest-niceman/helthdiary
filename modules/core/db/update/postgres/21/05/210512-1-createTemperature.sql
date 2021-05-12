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
    USER_ID uuid,
    MEASURE double precision not null,
    DATE_ date not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
);