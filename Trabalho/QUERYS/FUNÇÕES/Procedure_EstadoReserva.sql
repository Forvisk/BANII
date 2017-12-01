create or replace PROCEDURE ALT_ESTADORESERVA
(
  PCODRESERVA IN NUMBER,
  POPERACAO IN VARCHAR2,
  PRES_ST_ESTADO IN VARCHAR2 
) IS
	vOpeFim VARCHAR(1);
BEGIN
	IF POPERACAO = 'S' THEN
		-- inicio update
		UPDATE Reserva 
		SET res_st_estado = 'S', res_dt_pagamento = SYSTIMESTAMP 
		WHERE PCODRESERVA = res_pk_codigo;
		-- fim update
	ELSIF POPERACAO = 'C' THEN
		-- inicio update
		UPDATE Reserva 
		SET res_st_estado = 'C'
		WHERE PCODRESERVA = res_pk_codigo;
		-- fim update
	ELSIF POPERACAO = 'A' THEN
		-- inicio update
		UPDATE Reserva 
		SET res_st_estado = 'A' 
		WHERE PCODRESERVA = res_pk_codigo;
		-- fim update
	END IF;
	--inicio select
	SELECT R.res_st_estado INTO vOpeFim
	FROM Reserva R 
	WHERE PCODRESERVA = R.res_pk_codigo;
	-- fim select
END;
