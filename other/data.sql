CREATE DATABASE  IF NOT EXISTS `grupo-23-bdd-oo2-2021`;
USE `grupo-23-bdd-oo2-2021`;
insert into perfil(createat, tipo, updateat)
values('2021-05-20', "ROLE_ADMIN",'2021-05-20');

insert into perfil(createat, tipo, updateat)
values('2021-05-20', "ROLE_AUDITOR",'2021-05-20');

-- password=admin
insert into usuario(apellido, createat, dni, email, nombre, password, tipodocumento, updateat, username, perfil_id_perfil )
values("Ruler", '2008-11-11', 00000000, "admin@admin.com", "ADMIN","$2a$10$KbD5zlop6D/W06fvqtHzLejYw3evJ3U3fWP/ffDpiiaWVQmFVhLZq" ,1,'2008-11-11', "admin", 1);

-- password=auditor
insert into usuario(apellido, createat, dni, email, nombre, password, tipodocumento, updateat, username, perfil_id_perfil )
values("Fernandez", '2008-11-11', 36500548, "analiafc@live.com.ar", "Analia","$2a$10$elSl7./mcAsipLvw/Qk/MO56HiAHxF0kdYwrZVWlYbGV4KCtHO6.G" ,1,'2008-11-11', "AnaFc", 2);

insert into usuario(apellido, createat, dni, email, nombre, password, tipodocumento, updateat, username, perfil_id_perfil )
values("De la Fuente", '2008-11-11', 43200624, "ezedelafuente@gmail.com", "Ezequiel","$2a$10$elSl7./mcAsipLvw/Qk/MO56HiAHxF0kdYwrZVWlYbGV4KCtHO6.G" ,1,'2008-11-11', "EzeFuente", 2);

insert into usuario(apellido, createat, dni, email, nombre, password, tipodocumento, updateat, username, perfil_id_perfil )
values("Frutos Gonzalez", '2008-11-11', 95122296, "danielfrutos@yahoo.com", "Daniel","$2a$10$elSl7./mcAsipLvw/Qk/MO56HiAHxF0kdYwrZVWlYbGV4KCtHO6.G" ,1,'2008-11-11', "Dani95", 2);
