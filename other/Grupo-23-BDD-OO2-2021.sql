create database `Grupo-23-BDD-OO2-2021` ;
use `Grupo-23-BDD-OO2-2021`;


create table perfil(
	idPerfil int not null auto_increment primary key,
    tipo varchar(30) not null
);

create table documento(
	idDocumento int not null auto_increment primary key,
    tipo varchar(30) not null
);

create table usuario(
	idUsuario int not null auto_increment primary key ,
	nombre varchar(100) not null,
    apellido varchar(100) not null,
    email varchar(100) not null,
    nombreUsuario varchar(100) not null,
    contraseña varchar(100) not null,
    idPerfil int not null,
    idDocumento int not null,
    documento int not null,
    foreign key (idPerfil) references perfil(idPerfil),
    foreign key(idDocumento) references documento(idDocumento)
);

insert into perfil(tipo)
values
("administrador"),
("auditoría");

insert into documento(tipo)
values
("libreta cívica"),
("libreta de enrollamiento"),
("dni libreta verde"),
("dni libreta celeste"),
("dni targeta");


-- CONSULTA 

select * from perfil;
select * from documento;

-- Se necesita un administrador original para empezar a agregar usuarios
insert usuario(nombre, apellido, email, nombreUsuario, 
contraseña, idPerfil, idDocumento, documento
)
value
("Administrador", "Original","admin@gmail.com", "admin", "admin", 1,  5, 0);



