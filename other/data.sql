use `grupo-23-bdd-oo2-2021`;
insert into perfil(createat, tipo, updateat)
values('2021-05-20', "ROLE_ADMIN",'2021-05-20');

insert into perfil(createat, tipo, updateat)
values('2021-05-20', "ROLE_AUDITOR",'2021-05-20');

-- password=user
insert into usuario(apellido, createat, dni, email, nombre, password, tipodocumento, updateat, username, perfil_id_perfil )
values("de la fuente", '2008-11-11', 43200624, "pepe@hotmail.com", "pepe","$2a$10$JNdnIXsWwVb1jLzpyFv0zeevVIW94gJmQ40flwWRgy5ggy4/J52.K" ,3,'2008-11-11', "admin", 1);
insert into usuario(apellido, createat, dni, email, nombre, password, tipodocumento, updateat, username, perfil_id_perfil )
values("de la fuente", '2008-11-11', 43200625, "pepe@hotmail.com", "pepe","$2a$10$JNdnIXsWwVb1jLzpyFv0zeevVIW94gJmQ40flwWRgy5ggy4/J52.K" ,3,'2008-11-11', "auditor", 2);

