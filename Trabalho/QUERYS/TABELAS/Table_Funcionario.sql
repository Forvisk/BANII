CREATE TABLE Funcionario(
	fun_pk_codigo NUMBER PRIMARY KEY,
	fun_st_nome VARCHAR2(100 CHAR) NOT NULL,
	fun_st_telefone VARCHAR2(12 CHAR),
	fun_st_cpf VARCHAR2(11 CHAR) NOT NULL,
	fun_st_endereco VARCHAR2(150 CHAR),
	fun_st_cargo VARCHAR2(20 CHAR),
	fun_st_estado CHAR(1 CHAR) DEFAULT 'A' NOT NULL,
	-- fun_st_estado: A = Ativo; L = Licença; D = Demitido/Inativo;
	hot_fk_hotel NUMBER NOT NULL,
	
	FOREIGN KEY (hot_fk_hotel)
		REFERENCES Hotel(hot_pk_codigo)
	
);

CREATE SEQUENCE fun_pk
	START WITH 1
	INCREMENT BY 1
	NOMAXVALUE
	NOCACHE
	NOCYCLE;
	
CREATE OR REPLACE TRIGGER Funcionario_pk
BEFORE INSERT ON Funcionario
FOR EACH ROW

BEGIN
	SELECT fun_pk.NEXTVAL
	INTO :new.fun_pk_codigo
	FROM DUAL;
END;

/*
DROP TRIGGER Funcionario_pk;
DROP SEQUENCE fun_pk;
DROP TABLE Funcionario;
*/