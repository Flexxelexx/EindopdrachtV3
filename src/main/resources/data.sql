insert into roles(id,rolename) values (1,'USER'), (2,'ADMIN');
insert into users(id,username, is_verified, password) values (1001, 'Test', true, '$2a$10$ciVxaLFKHxcLpScb0MxQduFX5/wkfKP2QYMNPb4fxhjwU85TjNF0S');
insert into users_roles(users_id,roles_id) values (1001, 2);