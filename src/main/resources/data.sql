insert into roles(id,rolename) values (1,'ROLE_USER'), (2,'ROLE_ADMIN');
insert into users(id,username,password) values (1001, 'TestAdmin', '$2a$10$6BxOtu.iIzfregVi8rXXWutgGMIL3yPJ2gWJFv3gHAckELvK9DIOi');
insert into users_roles(users_id,roles_id) values (1001, 2);
