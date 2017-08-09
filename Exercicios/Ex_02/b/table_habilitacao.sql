create table Habilitacao (
	sigla_disc char(10) not null,
	reg_mec int not null,
	primary key (sigla_disc, reg_mec),
	foreign key (sigla_disc) references Disciplina,
	foreign key (reg_mec) references Professor
);