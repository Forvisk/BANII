CREATE TABLE Reserva(
	res_pk_codigo NUMBER PRIMARY KEY,
	
	qua_fk_numero NUMBER NOT NULL,
	hot_fk_codigo NUMBER NOT NULL,
	cli_fk_codigo NUMBER NOT NULL,
	
	res_dt_dtcria TIMESTAMP DEFAULT SYSDATE,
	res_dt_pagamento TIMESTAMP,
	
	res_dt_checkin DATE NOT NULL,
	res_dt_checkout DATE NOT NULL,
	
	FOREIGN KEY (qua_fk_numero,hot_fk_codigo)
		REFERENCES Quarto(qua_pk_numero,hot_fk_codigo),
	FOREIGN KEY (cli_fk_codigo)
		REFERENCES Cliente(cli_pk_codigo)
);

ALTER TABLE Reserva ADD(
	res_st_camaextra CHAR(1),
	res_st_estado CHAR(1)
);

CREATE SEQUENCE res_pk
	START WITH 1
	INCREMENT BY 1
	NOMAXVALUE
	NOCACHE
	NOCYCLE;


CREATE OR REPLACE TRIGGER Reserva_pk
BEFORE INSERT ON Reserva
FOR EACH ROW

BEGIN
	SELECT res_pk.NEXTVAL
	INTO :new.res_pk_codigo
	FROM DUAL;
END;

/*
DROP TRIGGER Reserva_pk;
DROP SEQUENCE res_pk;
DROP TABLE Reserva;
*/