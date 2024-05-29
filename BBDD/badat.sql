use BDIsaKatha
go

drop table Producto

create table Usuario (
    idUsuario INT PRIMARY KEY not null,
    nombre VARCHAR(50) not null,
    apellidos VARCHAR(50) not null,
	correo varchar(200) not null,
	password varchar(8) not null,
    fechaNac date not null,
	foto image
)

create table Producto (
	idProducto int primary key not null,
	idUsuario int not null,
	nombre varchar(200) not null,
	marca varchar(200) not null,
	nombreOG varchar(200) not null,
	marcaOG varchar(200) not null,
	original varchar(2) not null,
	comentario varchar(1000),
	imagen image,
	CONSTRAINT FK_IdUsuario FOREIGN KEY (idUsuario)
    REFERENCES Usuario (idUsuario) 
	--en caso de que se borre un usuario, se borren todos los productos creados por él
	on delete cascade 
)

insert into Usuario values
(10,'Wells','Dulanty','wdulanty9@macromedia.com','uW4qE2?','11/01/1921','http://dummyimage.com/100x100.png/dddddd/000000')


select * from Usuario

select * from Producto

insert into Producto (idProducto, idUsuario, nombre, marca, nombreOG, marcaOG, original) values 
(2, 1, 'Soft Glam Filter Fluid', 'Catrice', 'Hollywood Flawless Filter', 'Charlotte Tilbury', 'No' )



