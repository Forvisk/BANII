CREATE OR REPLACE TRIGGER  CAMA_EXTRA_OK
BEFORE INSERT OR UPDATE ON Reserva
FOR EACH ROW

DECLARE

	vTipo_Quarto NUMBER;

BEGIN

	SELECT Q.tqu_in_codigo INTO vTipo_Quarto
	FROM Quarto Q
	WHERE Q.qua_in_codquarto = :NEW.qua_in_codquarto;
	
	IF vTipo_Quarto = 1 THEN
		RAISE_APPLICATION_ERROR(-20001,' Quarto não pode ser utilizada cama extra !! ');
	END IF;
	
END;

-- DROP TRIGGER CAMA_EXTRA_OK
