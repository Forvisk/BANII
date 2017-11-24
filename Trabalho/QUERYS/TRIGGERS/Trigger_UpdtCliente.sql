
CREATE OR REPLACE EDITIONABLE TRIGGER  "CLIENTE_REPET" 
BEFORE
insert or update on "CLIENTE"
for each row
begin
DECLARE
	vfound NUMBER;
BEGIN
	vfound := 0;
	IF INSERTING THEN
	
		-- inicio select
		SELECT 1 INTO vfound 
		FROM CLIENTE C 
		WHERE 
			( C.CLI_PK_CODCLIENTE = :NEW.CLI_PK_CODCLIENTE 
		      or C.CLI_ST_NOME = :NEW.CLI_ST_NOME 
			  or C.CLI_ST_CPF = :NEW.CLI_ST_CPF)
			and ROWNUM < 2;
		-- fim select
		
	END IF;
	
	IF UPDATING THEN
		-- não pode alterar o codigo do cliente! 
		IF (:NEW.CLI_PK_CODCLIENTE != :OLD.CLI_PK_CODCLIENTE) THEN
			RAISE_APPLICATION_ERROR(-20001,' Não é possivel alterar o código do cliente !! ');
		END IF;
		
		-- inicio select
		SELECT 1 INTO vfound
			FROM CLIENTE C
			WHERE
				( C.CLI_ST_NOME = :NEW.CLI_ST_NOME
				  or C.CLI_ST_CPF = :NEW.CLI_ST_CPF)
				and C.CLI_PK_CODCLIENTE != :NEW.CLI_PK_CODCLIENTE
				and ROWNUM < 2;
		-- fim select
	END IF;
	
	IF vfound = 1 THEN
		RAISE_APPLICATION_ERROR(-20001,' Cadastro duplicado !! ');
	END IF;
	
END;
end;

/
ALTER TRIGGER  "CLIENTE_REPET" ENABLE
/


