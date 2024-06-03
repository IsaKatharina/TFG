use BDIsaKatha
go

drop table Productos
drop table Usuarios

create table Usuarios (
    idUsuario INT identity (1,1) PRIMARY KEY not null,
    nombre VARCHAR(50) not null,
    apellidos VARCHAR(50) not null,
	correo varchar(200) not null,
	password varchar(8) not null,
    fechaNac date not null,
	foto varchar(max)
	)

create table Productos (
	idProducto int identity(1,1) primary key not null,
	idUsuario int not null,
	nombre varchar(200) not null,
	marca varchar(200) not null,
	nombreOG varchar(200) not null,
	marcaOG varchar(200) not null,
	original varchar(2) not null,
	comentario varchar(1000),
	imagen varchar(max),
	CONSTRAINT FK_IdUsuarioProductos FOREIGN KEY (idUsuario) REFERENCES Usuarios (idUsuario) 
	--en caso de que se borre un usuario, se borren todos los productos creados por él
	on delete cascade 
)

create table Favs (
	idFav int identity(1,1) primary key not null,
	idUsuario int not null,
	idProducto int not null,
	CONSTRAINT FK_IdUsuarioFavs FOREIGN KEY (idUsuario) references Usuarios(idUsuario) on delete cascade,
	CONSTRAINT FK_IdProducto FOREIGN KEY (idProducto) references Productos(idProducto) on delete cascade
)

insert into Usuarios values
('isaka','loerzer','iklo9@macromedia.com','uW4qE2?','1998/12/05','http://dummyimage.com/100x100.png/dddddd/000000')


select * from Usuarios

select * from Productos

insert into Productos (idUsuario, nombre, marca, nombreOG, marcaOG, original) values 
(1, 'Soft Glam Filter Fluid', 'Catrice', 'Hollywood Flawless Filter', 'Charlotte Tilbury', 'No' )



