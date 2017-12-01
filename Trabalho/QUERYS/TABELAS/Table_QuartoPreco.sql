CREATE TABLE Quarto_Preco(
	tqu_pfk_codigo NUMBER,
	hot_pfk_codigo NUMBER,
	
	qpr_in_diaria FLOAT(2) DEFAULT 0 NOT NULL,
	
	PRIMARY KEY (tqu_pfk_codigo,hot_pfk_codigo),
	
	FOREIGN KEY (tqu_pfk_codigo)
		REFERENCES Tipo_Quarto (tqu_pk_codigo),
		
	FOREIGN KEY (hot_pfk_codigo)
		REFERENCES Hotel (hot_pk_codigo)
);