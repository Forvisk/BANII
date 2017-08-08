
create table Cliente (
	codC serial not null,
    cpf varchar(11) not null
    	check ( char_length(cpf) = 11),
    nome varchar(30) not null,
    idade smallint not null,
    endereco varchar(100) not null,
    cidade varchar(30) not null,
    primary key (codC),
    unique (cpf),
    unique (nome)
);