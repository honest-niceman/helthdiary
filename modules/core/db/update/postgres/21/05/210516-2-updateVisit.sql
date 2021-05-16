alter table HELTHDIARY_VISIT rename column type_ to type___u64054 ;
alter table HELTHDIARY_VISIT alter column type___u64054 drop not null ;
alter table HELTHDIARY_VISIT add column TYPE_ varchar(50) ^
alter table HELTHDIARY_VISIT alter column TYPE_ set not null ;
