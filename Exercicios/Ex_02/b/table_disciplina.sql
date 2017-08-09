create table Disciplina (
	sigla_disc char(10) not null,
	nome varchar(100) not null,
	carga_horaria int,
	primary key (sigla_disc)
);