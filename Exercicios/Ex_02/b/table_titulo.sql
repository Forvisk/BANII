create table Titulo(
	reg_mec int not null,
	cod_area int not null,
	data_de_titulacao date not null,
	responsavel char(1) check( responsavel like '%S%' or responsavel like '%N%')
	primary key( reg_mec, cod_area),
	foreign key (reg_mec) references professor,
	foreign key (cod_area) references Area
);