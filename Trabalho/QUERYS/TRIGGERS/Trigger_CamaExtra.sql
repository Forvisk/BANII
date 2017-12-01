
CREATE OR REPLACE EDITIONABLE TRIGGER  "CAMA_EXTRA_OK" 
BEFORE
insert or update on "RESERVA"
for each row
begin
DECLARE
    vTipo_Quarto NUMBER;
BEGIN
    SELECT Q.TQU_FK_CODIGO INTO vTipo_Quarto
    FROM quarto Q
    WHERE Q.QUA_PK_CODQUARTO = :NEW.QUA_FK_CODQUARTO;
    
    IF vTipo_Quarto = 1 THEN
        RAISE_APPLICATION_ERROR(-20001,' Quarto não pode ser utilizada cama extra !! ');
    END IF;
END;
end;

/
ALTER TRIGGER  "CAMA_EXTRA_OK" ENABLE
/


