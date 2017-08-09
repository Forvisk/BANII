create table Inscricao (
	matricula int not null,
	sigla_curso char(10) not null,
	cpf char(11) check( char_length(cpf) = 11),
	primary key ( matricula),
	foreign key (sigla_curso) references Curso,
	foreign key (cpf) references Aluno
);