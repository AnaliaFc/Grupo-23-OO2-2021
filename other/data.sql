CREATE DATABASE  IF NOT EXISTS `grupo-23-bdd-oo2-2021`;
USE `grupo-23-bdd-oo2-2021`;
insert into perfil(createat, tipo, updateat)
values('2021-05-20', "ROLE_ADMIN",'2021-05-20');

insert into perfil(createat, tipo, updateat)
values('2021-05-20', "ROLE_AUDITOR",'2021-05-20');

-- persona

insert into persona(apellido, createat, dni, nombre, updateat)
values("de la Fuente", '2020-11-11', 43200624, "Ezequiel", '2020-11-11');

insert into persona(apellido, createat, dni, nombre, updateat)
values("de la Fuente", '2020-11-11', 43200625, "Pepe", '2020-11-11');

-- usuario=root password=admin

insert into usuario(email, password, tipodocumento, username, id_persona, perfil_id_perfil)
values("eze@pepe.com","$2a$10$KbD5zlop6D/W06fvqtHzLejYw3evJ3U3fWP/ffDpiiaWVQmFVhLZq", 1, "root", 1, 1 );

-- usuario=user password=auditor

insert into usuario(email, password, tipodocumento, username, id_persona, perfil_id_perfil)
values("eze@pepe.com.ar","$2a$10$ePhKPkFp.Uti3gy3HI5PcupVWrckG3cEhKgbma62mjulO/K.GoalS", 2, "user", 3, 2 );
