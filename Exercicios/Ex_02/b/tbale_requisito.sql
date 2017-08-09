create table Requisito (
	cod_requerido char(10) not null,
	cod_grade char(10) not null,
	primary key ( cod_requerido, cod_grade),
	foreign key ( cod_requerido) references Grade,
	foreign key ( cod_grade) references Grade
);