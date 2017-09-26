create table Curso (
	sigla_curso char(10) not null,
	nome varchar(100) not null,
	titulacao varchar(100),
	primary key (sigla_curso)
);