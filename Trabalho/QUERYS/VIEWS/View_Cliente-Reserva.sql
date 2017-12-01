
CREATE OR REPLACE FORCE EDITIONABLE VIEW  "CLIENTE_RESERVA" ("CLI_PK_CODCLIENTE", "CLI_ST_NOME", "RES_PK_CODRESERVA", "RES_DT_DTRESERVA", "RES_ST_ESTADO") AS 
  SELECT 	C.CLI_PK_CODCLIENTE,
		C.CLI_ST_NOME,
		R.RES_PK_CODRESERVA,
		R.RES_DT_DTRESERVA,
		R.RES_ST_ESTADO
	FROM Cliente C join Reserva R 
						ON C.CLI_PK_CODCLIENTE = R.CLI_FK_CODCLIENTE;