CREATE TABLE Estadia(
	est_pk_codigo NUMBER PRIMARY KEY,
	
	qua_fk_numero NUMBER NOT NULL,
	hot_fk_codigo NUMBER NOT NULL,
	cli_fk_codigo NUMBER NOT NULL,
	res_fk_codigo NUMBER,
	
	est_dt_checkin TIMESTAMP NOT NULL,
	est_dt_checkout TIMESTAMP NOT NULL,
	
	
	FOREIGN KEY (qua_fk_numero,hot_fk_codigo)
		REFERENCES Quarto(qua_pk_numero,hot_pfk_codigo),
	FOREIGN KEY (cli_fk_codigo)
		REFERENCES Cliente(cli_pk_codigo),
	FOREIGN KEY (res_fk_codigo)
		REFERENCES Estadia(est_pk_codigo)
);

CREATE SEQUENCE est_pk
	START WITH 1
	INCREMENT BY 1
	NOMAXVALUE
	NOCACHE
	NOCYCLE;


CREATE OR REPLACE TRIGGER Estadia_pk
BEFORE INSERT ON Estadia
FOR EACH ROW

BEGIN
	SELECT est_pk.NEXTVAL
	INTO :new.est_pk_codigo
	FROM DUAL;
END;

/*
DROP TRIGGER Estadia_pk;
DROP SEQUENCE est_pk;
DROP TABLE Estadia;
*/