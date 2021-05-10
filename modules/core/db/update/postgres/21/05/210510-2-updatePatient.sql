alter table HELTHDIARY_PATIENT rename column gender to gender__u91830 ;
alter table HELTHDIARY_PATIENT alter column gender__u91830 drop not null ;
alter table HELTHDIARY_PATIENT add column GENDER integer ^
update HELTHDIARY_PATIENT set GENDER = 10 where GENDER is null ;
alter table HELTHDIARY_PATIENT alter column GENDER set not null ;
