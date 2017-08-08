create table Mecanico (
	codM serial not null,
	cpf varchar(11) not null 
    	check ( char_length(cpf) = 11),
    nome varchar(30) not null,
    idade smallint not null,
    endereco varchar(100) not null,
    cidade varchar(30) not null,
    funcao varchar(30) not null,
    codS int,
    primary key (codM),
    unique (cpf),
    unique (nome),
    foreign key (codS) references Setor
    	on delete set null on update cascade
);
