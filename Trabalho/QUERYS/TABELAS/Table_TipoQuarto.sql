create table Tipo_Quarto(
	tqu_pk_codigo NUMBER PRIMARY KEY,
	tqu_st_tipo VARCHAR2(20 CHAR) NOT NULL
	
);

CREATE SEQUENCE tqu_pk
	START WITH 1
	INCREMENT BY 1
	NOMAXVALUE
	NOCACHE
	NOCYCLE;
	
CREATE OR REPLACE TRIGGER tipo_quarto_pk
BEFORE INSERT ON Tipo_Quarto
FOR EACH ROW

BEGIN
	SELECT tqu_pk.NEXTVAL
	INTO :new.tqu_pk_codigo
	FROM DUAL;
END;

/*
DROP TRIGGER tipo_quarto_pk;
DROP SEQUENCE tqu_pk;
DROP TABLE Tipo_Quarto
*/