CREATE TABLE Servico_Prestado( 
	svp_pk_codigo NUMBER PRIMARY KEY,
	
	svp_dt_dtservico TIMESTAMP NOT NULL,
	
	svp_st_concluido VARCHAR2(1 CHAR) DEFAULT 'E' NOT NULL,
	-- svp_st_conlcuido: C = Conlcuido; E = Encaminhado/Em processo;
	svp_st_cortesia VARCHAR2(1 CHAR) DEFAULT 'N' NOT NULL,
	-- svp_st_cortesia: N = Não; S = Sim;

	sev_fk_codigo NUMBER NOT NULL,
	fun_fk_codigo NUMBER NOT NULL,
	est_fk_codigo NUMBER NOT NULL,

	FOREIGN KEY (sev_fk_codigo)
		REFERENCES Servico(sev_pk_codigo),
	FOREIGN KEY (fun_fk_codigo)
		REFERENCES Funcionario(fun_pk_codigo),
	FOREIGN KEY (est_fk_codigo)
		REFERENCES Estadia(est_pk_codigo)
);

CREATE SEQUENCE svp_fk
	START WITH 1
	INCREMENT BY 1
	NOMAXVALUE
	NOCACHE
	NOCYCLE;

CREATE OR REPLACE TRIGGER Servico_Prestado_fk
BEFORE INSERT ON Servico_Prestado
FOR EACH ROW

BEGIN
	SELECT svp_fk.NEXTVAL
	INTO :new.svp_pk_codigo
	FROM DUAL;
END;

/*
DROP TRIGGER Servico_Prestado_fk;
DROP SEQUENCE svp_fk;
DROP TABLE Servico_Prestado;
*/