create table Professor (
	reg_mec int not null,
	nome varchar(100) not null,
	rua varchar(255) not null,
	numero int not null,
	cidade varchar(100) not null,
	estado char(2) not null,
	cep int not null,
	primary key (reg_mec)
);