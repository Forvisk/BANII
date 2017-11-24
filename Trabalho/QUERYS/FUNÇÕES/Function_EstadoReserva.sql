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
		UPDATE Reserva 
		SET res_st_estado = 'S', res_dt_dtpagamento = SYSDATE 
		WHERE PCODRESERVA = res_pk_codreserva;
	ELSIF POPERACAO = 'C' THEN
		UPDATE Reserva 
		SET res_st_estado = 'C'
		WHERE PCODRESERVA = res_pk_codreserva;
	ELSIF POPERACAO = 'A' THEN
		UPDATE Reserva 
		SET res_st_estado = 'A' 
		WHERE PCODRESERVA = res_pk_codreserva;
	END IF;
	
	SELECT R.res_st_estado INTO vOpeFim
	FROM Reserva R 
	WHERE PCODRESERVA = R.res_pk_codreserva;
	
	IF vOpeFim != POPERACAO THEN
		vreturn := 1;
	END IF;
	
	RETURN vreturn;
END ESTADO_RESERVA;
