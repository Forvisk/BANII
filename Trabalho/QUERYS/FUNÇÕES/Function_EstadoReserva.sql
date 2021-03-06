create or replace FUNCTION ESTADO_RESERVA 
(
  PCODRESERVA IN NUMBER,
  POPERACAO IN VARCHAR2,
  PRES_ST_ESTADO IN VARCHAR2 
) RETURN NUMBER IS

	vreturn NUMBER default 0;
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
	
	IF vOpeFim != POPERACAO THEN
		vreturn := 1;
	END IF;
	
	RETURN vreturn;
END ESTADO_RESERVA;

drop function ESTADO_RESERVA;