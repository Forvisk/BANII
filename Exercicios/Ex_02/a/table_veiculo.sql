create table Veiculo (
	codV serial not null,
    renavan varchar(7)
    	check (char_length( renavan) = 7),
	marca varchar(30) not null,
    ano smallint not null,
    quilometragem int not null,
    codC int not null default 0,
    primary key (codV),
    unique (renavan),
    foreign key (codC) references Cliente on update cascade
);

/*
CREATE TABLE DEPT (
DNOME         VARCHAR(10)   NOT NULL,
DNUMERO       INTEGER       NOT NULL,
GERSSN        CHAR(9),
GERDATAINICIO CHAR(9),
PRIMARY KEY (DNUMERO),
UNIQUE (DNOME),
FOREIGN KEY (GERSSN) REFERENCES EMP
ON DELETE SET DEFAULT ON UPDATE 
CASCADE);
*/