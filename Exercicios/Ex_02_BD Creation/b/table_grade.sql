-- drop table Grade;

create table Grade(
	cod_grade serial not null,
	sigla_curso char(10) not null,
	sigla_disc char not null,
	-- primary key (sigla_curso, sigla_disc),
	primary key (cod_grade),
	foreign key (sigla_curso) references Curso,
	foreign key (sigla_disc) references Disciplina,
    unique( sigla_curso, sigla_disc)
);