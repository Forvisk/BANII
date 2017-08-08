create table Setor (
	codS serial not null,
    nomeS varchar(30) not null,
    primary key (codS),
    unique (nomeS)
);

