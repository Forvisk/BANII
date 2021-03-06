CREATE OR REPLACE FUNCTION QUARTO_DISPONIVEL
( 	pCodQuarto NUMBER,
	pDataIn DATE,
	pDataOut DATE
)
RETURN NUMBER IS
	vreturn NUMBER default 1;
	vselect NUMBER DEFAULT 0;
BEGIN
	SELECT 1 INTO vselect
	FROM Reserva R
	WHERE R.qua_in_codquarto = pCodQuarto
	AND ( NOT R.res_dt_dtentrada BETWEEN pDataIn AND pDataOut)
	AND ( NOT R.res_dt_dtSaida BETWEEN pDataIn AND pDataOut);
	IF vselect = 1	THEN
		vreturn := 0;
	ELSE
		SELECT 1 INTO vselect
		FROM Estadia E
		WHERE E.qua_in_codquarto = pCodQuarto
		AND ( NOT E.est_dt_dtcheckin BETWEEN pDataIn AND pDataOut)
		AND ( NOT E.est_dt_dtcheckout BETWEEN pDataIn AND pDataOut);
	END IF;
	
	return vreturn;
END;

-- DROP FUNCTION QUARTO_DISPONIVEL