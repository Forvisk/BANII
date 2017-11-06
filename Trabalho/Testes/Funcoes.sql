/*
Function: Confirmar reserva
Function: Disponibilidade de um quarto
--Trigger: Anula reserva--
Trigger: Verifica cama extra
*/

CREATE OR REPLACE FUNCTION developer.ConfirmaReserva ( pres_in_codreserva VARCHAR2, pdata DATE, pconfirma INT) 
RETURN INT AS	
BEGIN
	-- confirma Reserva e pagamento
	IF pconfirma = 1 THEN
		UPDATE Reserva SET res_st_confirma = 'S', res_dt_dtpagamento = pdata
		WHERE res_in_codreserva = pres_in_codreserva;
	-- cancela a reserva
	ELSIF pconfirma = 2 THEN
		UPDATE Reserva SET res_st_confirma = 'C', res_dt_dtpagamento = pdata
		WHERE res_in_codreserva = pres_in_codreserva;
	-- anula a reserva
	ELSIF pconfirma = 3 THEN
		UPDATE Reserva SET res_st_confirma = 'A', res_dt_dtpagamento = pdata
		WHERE res_in_codreserva = pres_in_codreserva;
	ELSE
		RETURN 0;
	END IF;
	RETURN 1;
END;

-- DROP FUNCTION developer.ConfirmaReserva;


CREATE OR REPLACE FUNCTION Disponibilidade( pqua_in_codquarto NUMBER(9,9)) RETURN INT AS
BEGIN
	
END;