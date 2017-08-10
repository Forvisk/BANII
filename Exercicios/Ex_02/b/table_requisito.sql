create table Requisito (
	cod_requerido int not null,
	cod_grade int not null,
	primary key ( cod_requerido, cod_grade),
	foreign key ( cod_requerido) references Grade,
	foreign key ( cod_grade) references Grade
);