
CREATE OR REPLACE EDITIONABLE TRIGGER  "LIMITA_SERVICO_FUNCIONARIO" 
BEFORE INSERT OR UPDATE ON Servico_prestado 
FOR EACH ROW 
 
DECLARE 
	 
	vN_Servicos NUMBER; 
	vfun_st_nome VARCHAR2(40 CHAR); 
 
BEGIN 
	 
	SELECT F.fun_st_nome into vfun_st_nome 
	FROM FUNCIONARIO F 
	WHERE F.fun_pk_codfuncionario = :NEW.fun_fk_codfuncionario; 
	 
	SELECT SUM(1) into vN_Servicos 
	FROM Servico_prestado SP 
	WHERE SP.fun_fk_codfuncionario = :NEW.fun_fk_codfuncionario 
		AND SP.svp_st_estado != 'C'; 
		 
	IF vN_Servicos >= 3 THEN 
		RAISE_APPLICATION_ERROR(-20001,' Funcionario '+ vfun_st_nome +' já possui 3 serviços ainda não terminados!! '); 
	END IF; 
	 
END;

/
ALTER TRIGGER  "LIMITA_SERVICO_FUNCIONARIO" ENABLE
/


