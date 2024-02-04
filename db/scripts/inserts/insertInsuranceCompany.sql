START TRANSACTION;

BEGIN;

insert into InsuranceCompany(name,shortcut,country_of_origin)
values('Česká průmyslová zdravotní pojišťovna','ČPZP','Czech Republic');
insert into InsuranceCompany(name,shortcut,country_of_origin)
values('Všeobecná zdravotní pojišťovna ČR','VZP','Czech Republic');
insert into InsuranceCompany(name,shortcut,country_of_origin)
values('Vojenská Zdravotní Pojišťovna','VoZP','Czech Republic');
insert into InsuranceCompany(name,shortcut,country_of_origin)
values('Techniker Krankenkasse','TK ','Germany');

commit;
