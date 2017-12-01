CREATE OR REPLACE TRIGGER tipo_quarto_lock
BEFORE DELETE OR UPDATE ON Tipo_Quarto
FOR EACH ROW

BEGIN
	RAISE_APPLICATION_ERROR(-20001,' Não é possivel alterar ou excluir tipos de quartos');
END;