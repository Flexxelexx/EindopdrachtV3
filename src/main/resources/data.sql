insert into roles(id,rolename) values (1,'ROLE_USER'), (2,'ROLE_ADMIN');
insert into users(id,username,password) values (1001, 'Test', 'test123');
insert into users_roles(users_id,roles_id) values (1001, 2);

insert into fishingspots (id,city)
values (1001,'Alblasserdam'),
       (1002,'Bodegraven'),
       (1003,'Boskoop'),
       (1004,'Gorinchem'),
       (1005,'Gouda'),
       (1006,'Haastrecht'),
       (1007,'Oudewater'),
       (1008,'Rotterdam'),
       (1009,'Waddinxveen'),
       (1010,'Zevenhuizen');

insert into uploads (id,speciesfish)
values (1001,'Aal'),
       (1002,'Baars'),
       (1003,'Barbeel'),
       (1004,'Brasem'),
       (1005,'Brasemblei'),
       (1006,'Kolblei'),
       (1007,'Karper'),
       (1008,'Sneep'),
       (1009,'Snoek'),
       (1010,'Zeelt');