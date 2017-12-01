CREATE TABLE Quarto(
    --qua_in_codquarto NUMBER PRIMARY KEY,
    qua_pk_numero NUMBER NOT NULL,
    qua_in_local VARCHAR2(50 CHAR) NOT NULL,

    tqu_fk_codigo NUMBER,
    hot_pfk_codigo NUMBER NOT NULL,
	
	PRIMARY KEY (qua_pk_numero, hot_pfk_codigo),

	FOREIGN KEY (hot_pfk_codigo)
        REFERENCES Hotel (hot_pk_codigo),
    FOREIGN KEY (tqu_fk_codigo)
		REFERENCES Tipo_Quarto (tqu_pk_codigo)
);



/*
--DROP TRIGGER Quarto_pk;
--DROP SEQUENCE qua_pk_nhumero;
DROP TABLE Quarto;
*/