insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('d2205e6c-7023-1e68-90df-aa6f1f174ed6', 1, '2021-05-21 19:21:35', 'admin', '2021-05-21 19:21:35', null, null, null, 'a405db59-e674-4f63-8afe-269dda788fe8', null, 'system-minimal');
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('7734ec0a-8d53-7a16-7cd0-db36cfc06bc7', 1, '2021-05-22 16:49:41', 'admin', '2021-05-22 16:49:41', null, null, null, 'a405db59-e674-4f63-8afe-269dda788fe8', null, 'Patient');

--First patient lucy lucy
insert into SEC_USER
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, LOGIN, LOGIN_LC, PASSWORD, PASSWORD_ENCRYPTION, NAME, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSITION_, EMAIL, LANGUAGE_, TIME_ZONE, TIME_ZONE_AUTO, ACTIVE, CHANGE_PASSWORD_AT_LOGON, GROUP_ID, GROUP_NAMES, IP_MASK, SYS_TENANT_ID)
values ('0424f99e-5228-2234-da46-5be917541a49', 1, '2021-05-22 17:36:11', 'anonymous', '2021-05-22 17:36:11', null, null, null, 'lucy', 'lucy', '$2a$10$y7XizyqjZE2OdX8iLw12G.OQmrc25B2ZLnFhUt1ywRvMep1LAtp4O', 'bcrypt', null, null, null, null, null, null, null, null, null, true, false, '0fa2b1a5-1d68-4d69-9fbd-dff348347f93', null, null, null);
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('68d9db51-28f7-fc12-3227-1b6a96e9df99', 1, '2021-05-22 17:36:11', 'anonymous', '2021-05-22 17:36:11', null, null, null, '0424f99e-5228-2234-da46-5be917541a49', null, 'system-minimal');
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('da1df890-c1ea-11ba-654a-3189626fd94b', 1, '2021-05-22 17:36:11', 'anonymous', '2021-05-22 17:36:11', null, null, null, '0424f99e-5228-2234-da46-5be917541a49', null, 'Patient');

--Lucy associated patient entity

insert into HELTHDIARY_PATIENT
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, NAME_FIRST, NAME_LAST, DATE_OF_BIRTH, GENDER, HEIGHT, WEIGHT, CALORIE)
values ('a43456ee-9ad2-fa8f-b0be-7801eb46cd6b', 2, '2021-05-22 17:36:11', 'anonymous', '2021-05-22 17:40:52', 'lucy', null, null, '0424f99e-5228-2234-da46-5be917541a49', 'Lucy', 'Williams', '1972-05-02', 20, 165.0, 85.0, 1544.081);

--Second patient marpha marpha
insert into SEC_USER
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, LOGIN, LOGIN_LC, PASSWORD, PASSWORD_ENCRYPTION, NAME, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSITION_, EMAIL, LANGUAGE_, TIME_ZONE, TIME_ZONE_AUTO, ACTIVE, CHANGE_PASSWORD_AT_LOGON, GROUP_ID, GROUP_NAMES, IP_MASK, SYS_TENANT_ID)
values ('da029f16-9dca-a0ef-f88f-e11b7c0cfb3b', 1, '2021-05-22 17:36:19', 'anonymous', '2021-05-22 17:36:19', null, null, null, 'marpha', 'marpha', '$2a$10$fCr5j00BsTk3MuM.FgeQKuKoy60plVInHYIRiHYg23xLI57/df0BC', 'bcrypt', null, null, null, null, null, null, null, null, null, true, false, '0fa2b1a5-1d68-4d69-9fbd-dff348347f93', null, null, null);
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('525b7f3d-c3a8-7592-091f-923c1a793bfa', 1, '2021-05-22 17:36:19', 'anonymous', '2021-05-22 17:36:19', null, null, null, 'da029f16-9dca-a0ef-f88f-e11b7c0cfb3b', null, 'system-minimal');
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('fd56425b-3151-2e2f-ec66-bfb544cf5150', 1, '2021-05-22 17:36:19', 'anonymous', '2021-05-22 17:36:19', null, null, null, 'da029f16-9dca-a0ef-f88f-e11b7c0cfb3b', null, 'Patient');

--Marpha associated patient entity
insert into HELTHDIARY_PATIENT
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, NAME_FIRST, NAME_LAST, DATE_OF_BIRTH, GENDER, HEIGHT, WEIGHT, CALORIE)
values ('1cefab41-1cde-6aa8-ce6d-42a1760e035b', 2, '2021-05-22 17:36:19', 'anonymous', '2021-05-22 17:41:41', 'marpha', null, null, 'da029f16-9dca-a0ef-f88f-e11b7c0cfb3b', 'Marpha', 'Brawn', '1999-12-22', 20, 174.0, 53.0, 1385.6430000000003);

--admin patient for tests
insert into HELTHDIARY_PATIENT
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, NAME_FIRST, NAME_LAST, DATE_OF_BIRTH, GENDER, HEIGHT, WEIGHT, CALORIE)
values ('a43456ee-9ad2-fa8f-b0be-78018b46cd6b', 2, '2021-05-22 17:36:11', 'anonymous', '2021-05-22 17:40:52', 'admin', null, null, '60885987-1b61-4247-94c7-dff348347f93', 'Admin', 'Admin', '1972-05-02', 20, 165.0, 85.0, 1544.081);

--User for doctor john
insert into SEC_USER
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, LOGIN, LOGIN_LC, PASSWORD, PASSWORD_ENCRYPTION, NAME, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSITION_, EMAIL, LANGUAGE_, TIME_ZONE, TIME_ZONE_AUTO, ACTIVE, CHANGE_PASSWORD_AT_LOGON, GROUP_ID, GROUP_NAMES, IP_MASK, SYS_TENANT_ID)
values ('60b7a2d0-38ab-9954-e854-e771b58c08b7', 4, '2021-05-22 19:28:59', 'admin', '2021-05-22 21:01:21', 'admin', null, null, 'john', 'john', '$2a$10$iAaZXluSeCl.tw4FBRphDO7Gp2fwmjA9cEmWUmi2I9.TL8tHkBJoC', 'bcrypt', null, 'John', 'Doe', null, null, null, null, null, null, true, false, '0fa2b1a5-1d68-4d69-9fbd-dff348347f93', null, null, null);
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('109ff6ec-0c5e-ac04-4c64-1fd076a46e49', 1, '2021-05-22 19:28:59', 'admin', '2021-05-22 19:28:59', null, null, null, '60b7a2d0-38ab-9954-e854-e771b58c08b7', null, 'Doctor');
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('4dc142b7-f0d2-7dec-31e4-5e383493024b', 1, '2021-05-22 19:28:59', 'admin', '2021-05-22 19:28:59', null, null, null, '60b7a2d0-38ab-9954-e854-e771b58c08b7', null, 'system-minimal');

--Doctor entity for John
insert into HELTHDIARY_DOCTOR
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, FIRSTNAME, LASTNAME, SPECIALITY, EDUCATION)
values ('c58b82d8-c393-8651-c906-fb99c4a6da83', 1, '2021-05-22 19:28:59', 'admin', '2021-05-22 19:28:59', null, null, null, '60b7a2d0-38ab-9954-e854-e771b58c08b7', 'John', 'Doe', 30, 20);

--User for doctor man
insert into SEC_USER
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, LOGIN, LOGIN_LC, PASSWORD, PASSWORD_ENCRYPTION, NAME, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSITION_, EMAIL, LANGUAGE_, TIME_ZONE, TIME_ZONE_AUTO, ACTIVE, CHANGE_PASSWORD_AT_LOGON, GROUP_ID, GROUP_NAMES, IP_MASK, SYS_TENANT_ID)
values ('4176dbc0-75fb-d65d-f5e1-358688d79ad2', 4, '2021-05-22 19:29:16', 'admin', '2021-05-22 21:02:48', 'admin', null, null, 'man', 'man', '$2a$10$ypIWmLJPvDG9B0M33XlO5elxfL8OipiIFb1ObmluCBIcnK4JxnHDG', 'bcrypt', null, 'Man', 'Turing', null, null, null, null, null, null, true, false, '0fa2b1a5-1d68-4d69-9fbd-dff348347f93', null, null, null);
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('e513b1ba-7924-d9f4-f8f8-90e702676414', 1, '2021-05-22 19:29:16', 'admin', '2021-05-22 19:29:16', null, null, null, '4176dbc0-75fb-d65d-f5e1-358688d79ad2', null, 'Doctor');
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('9aa5d554-8920-6b6b-277e-b1f2d540e53d', 1, '2021-05-22 19:29:16', 'admin', '2021-05-22 19:29:16', null, null, null, '4176dbc0-75fb-d65d-f5e1-358688d79ad2', null, 'system-minimal');

--Doctor entity for Man
insert into HELTHDIARY_DOCTOR
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, FIRSTNAME, LASTNAME, SPECIALITY, EDUCATION)
values ('f5a7535b-81b6-bd58-beb4-7f4781d9c8b2', 1, '2021-05-22 19:29:16', 'admin', '2021-05-22 19:29:16', null, null, null, '4176dbc0-75fb-d65d-f5e1-358688d79ad2', 'Man', 'Turing', 50, 40);

--User for doctor alisa
insert into SEC_USER
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, LOGIN, LOGIN_LC, PASSWORD, PASSWORD_ENCRYPTION, NAME, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSITION_, EMAIL, LANGUAGE_, TIME_ZONE, TIME_ZONE_AUTO, ACTIVE, CHANGE_PASSWORD_AT_LOGON, GROUP_ID, GROUP_NAMES, IP_MASK, SYS_TENANT_ID)
values ('03e396aa-e1e0-cb8e-1c50-fda397f7e4b8', 4, '2021-05-22 19:29:32', 'admin', '2021-05-22 21:02:41', 'admin', null, null, 'alisa', 'alisa', '$2a$10$e4C3kp6tOy.14O409/kwX.vtj0M1fxmjT3l53Tu6OOgY3bSuxCVyK', 'bcrypt', null, 'Alisa', 'Down', null, null, null, null, null, null, true, false, '0fa2b1a5-1d68-4d69-9fbd-dff348347f93', null, null, null);
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('4a818aa1-3950-36cd-e56f-b7a15e3d2c1b', 1, '2021-05-22 19:29:32', 'admin', '2021-05-22 19:29:32', null, null, null, '03e396aa-e1e0-cb8e-1c50-fda397f7e4b8', null, 'Doctor');
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('0c8fd15d-f19d-f55d-12b0-3561b17f408c', 1, '2021-05-22 19:29:32', 'admin', '2021-05-22 19:29:32', null, null, null, '03e396aa-e1e0-cb8e-1c50-fda397f7e4b8', null, 'system-minimal');

--Doctor entity for Alisa
insert into HELTHDIARY_DOCTOR
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, FIRSTNAME, LASTNAME, SPECIALITY, EDUCATION)
values ('c4d94a77-7536-a6b9-c911-732087db73b9', 1, '2021-05-22 19:29:32', 'admin', '2021-05-22 19:29:32', null, null, null, '03e396aa-e1e0-cb8e-1c50-fda397f7e4b8', 'Alisa', 'Down', 30, 30);
