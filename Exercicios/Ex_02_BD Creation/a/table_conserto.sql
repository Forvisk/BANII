create table Conserto (
	codM int not null,
    codV int not null,
    entrada date not null,
    horario time not null,
    primary key (codM, codV, entrada),
    foreign key (codM) references Mecanico on update cascade,
    foreign key (codV) references Veiculo on update cascade
);