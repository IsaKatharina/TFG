use BestDupeDB

select * from Productos

delete from Productos where idProducto=3

drop table Productos

create table Usuarios (
    idUsuario INT identity (1,1) PRIMARY KEY not null,
    nombreUsu VARCHAR(50) not null,
    correo VARCHAR(50) not null,
	password varchar(8) not null,
	foto varchar(max)
	)

create table Productos (
	idProducto int identity(1,1) primary key not null,
	idUsuario int not null,
	nombre varchar(200) not null,
	marca varchar(200) not null,
	nombreOG varchar(200),
	marcaOG varchar(200),
	original varchar(2) not null,
	comentario varchar(1000),
	imagen varchar(max),
	CONSTRAINT FK_IdUsuarioProductos FOREIGN KEY (idUsuario) REFERENCES Usuarios (idUsuario) 
	--en caso de que se borre un usuario, se borren todos los productos creados por �l
	on delete cascade 
)

insert into Usuarios values
('isaka','iklo9@macromedia.com','uW4qE2?','http://dummyimage.com/100x100.png/dddddd/000000')

insert into Productos values 
(1, 'Soft Glam Filter Fluid', 'Catrice', 'Hollywood Flawless Filter', 'Charlotte Tilbury', 'No',null, 'https://www.dropbox.com/scl/fi/mssln3q50lvqjxilgr8gv/Soft-Glam-Filter-Fluid-Catrice.png?rlkey=fd9yhm1woa16djinm8z3i6zkn&st=smwiqojb&dl=0'),
(1, 'Hollywood Flawless Filter', 'Charlotte Tilbury',null, null, 'Si',null,'https://www.dropbox.com/scl/fi/6rffgnbt1l6x2qj1wxorf/Hollywood-Flawless-Filter.jpeg?rlkey=6v4q4yxo0eulgm5dpnawkrwv8&st=8rtvm3dv&dl=0'),
(1, 'What a tint','Essence','Benetint', 'Benefit', 'no', null, 'https://www.dropbox.com/scl/fi/y7azfiibpnb0cdmb1cxf7/wha_a_tint.jpg?rlkey=2318205ey12s2dthurgnfueu9&st=gtg1e7f9&dl=0'),
(1,'Benetint','Benefit',null,null, 'si',null, 'https://www.dropbox.com/scl/fi/scz6qdaldf6vygksih1dt/benetint.jpg?rlkey=ixu5qsenbs36765942nw7m99h&st=lj9satff&dl=0')
