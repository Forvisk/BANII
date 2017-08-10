create table Matricula (
	cod_matr serial not null,
	ano date not null,
	matricula int not null,
	sigla_disc char(10) not null,
	semestre int not null,
	primary key (cod_matr),
  	-- primary key (sigla_disc, matricula, ano, semestre),
	foreign key (sigla_disc) references Disciplina,
	foreign key (matricula) references Inscricao,
    unique (sigla_disc, matricula, ano, semestre)
);