create table Aluno (
	cpf char(11) check( char_length(cpf) = 11),
	nome varchar(100) not null,
	rua varchar(100) not null,
	numero int not null,
	estado char(2) not null,
	cidade varchar(100) not null,
	cep int not null,
	primary key (cpf)
);