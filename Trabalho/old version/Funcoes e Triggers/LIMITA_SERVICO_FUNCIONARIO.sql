CREATE OR REPLACE TRIGGER LIMITA_SERVICO_FUNCIONARIO
BEFORE INSERT OR UPDATE ON Servico_prestado
FOR EACH ROW

DECLARE
	
	vN_Servicos NUMBER;
	vepg_st_nome VARCHAR2(40 CHAR);

BEGIN
	
	SELECT E.epg_st_nome into vepg_st_nome
	FROM Empregado E
	WHERE E.epg_in_codempregado = :NEW.epg_in_codempregado;
	
	SELECT SUM(1) into vN_Servicos
	FROM Servico_prestado SP
	WHERE SP.epg_in_codempregado = :NEW.epg_in_codempregado
		AND SP.svp_st_concluido = 'N';
		
	IF vN_Servicos >= 3 THEN
		RAISE_APPLICATION_ERROR(-20001,' Funcionario '+ vepg_st_nome +' já possui 3 serviços ainda não terminados!! ');
	END IF;
	
END;

--DROP TRIGGER LIMITA_SERVICO_FUNCIONARIO