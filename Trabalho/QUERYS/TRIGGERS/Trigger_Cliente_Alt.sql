CREATE OR REPLACE TRIGGER  CLIENTE_ALT
BEFORE INSERT OR UPDATE ON Cliente
FOR EACH ROW
DECLARE
	vfound NUMBER;
BEGIN
	vfound := 0;
	IF INSERTING THEN
		-- inicio select
		SELECT COUNT(1) INTO vfound 
		FROM CLIENTE C 
		WHERE 
			( C.CLI_ST_NOME = :NEW.CLI_ST_NOME 
			  or C.CLI_ST_CPF = :NEW.CLI_ST_CPF)
			and ROWNUM < 2;
		-- fim select
		
	END IF;
	
	IF UPDATING THEN
		-- não pode alterar o codigo do cliente! 
		IF (:NEW.cli_pk_codigo != :OLD.cli_pk_codigo) THEN
			RAISE_APPLICATION_ERROR(-20001,' Não é possivel alterar o código do cliente !! ');
		END IF;
		
		-- inicio select
		SELECT 1 INTO vfound
			FROM CLIENTE C
			WHERE
				( C.CLI_ST_NOME = :NEW.CLI_ST_NOME
				  or C.CLI_ST_CPF = :NEW.CLI_ST_CPF)
				and C.cli_pk_codigo != :NEW.cli_pk_codigo
				and ROWNUM < 2;
		-- fim select
	END IF;
	
	IF vfound = 1 THEN
		RAISE_APPLICATION_ERROR(-20001,' Cadastro duplicado !! ');
	END IF;
	
	IF VALIDA_CPF( :NEW.CLI_ST_CPF) = 0 THEN
		RAISE_APPLICATION_ERROR(-20001, 'CPF invalido!');
	END IF;
END;