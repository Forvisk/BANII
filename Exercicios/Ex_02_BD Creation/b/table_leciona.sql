create table Leciona (
	cod_lec serial not null,
	sigla_disc char(10) not null,
	reg_mec int not null,
	ano date not null,
	semestre int not null,
    -- primary key (sigla_disc, reg_mec, ano, semestre),
	primary key (cod_lec),
	foreign key (sigla_disc) references Disciplina,
	foreign key (reg_mec) references Professor,
    unique(sigla_disc, reg_mec, ano, semestre)
);