create table Leciona (
	cod_lec serial not null,
	sigla_disc char(10) not null,
	reg_mec int not null,
	ano date not null,
	semestre int not null,
	primary key (cod_lec),
	foreign key (sigla_disc) references Disciplina,
	foreign key (reg_mec) references Professor
);