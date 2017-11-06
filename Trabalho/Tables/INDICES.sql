--Nada Funciona

CREATE UNIQUE INDEX CLIENTE_I1 ON Cliente(cli_in_codcliente);
CREATE UNIQUE INDEX HOTEL_I2 ON Hotel(hot_in_codhotel);
CREATE UNIQUE INDEX EMPREGADO_I3 ON Empregado(epg_in_codempregado);
CREATE UNIQUE INDEX SERVICO_I4 ON  Servico(sev_in_codservico);

CREATE UNIQUE INDEX QUARTO_I5 ON Quarto(qua_in_codquarto);
CREATE UNIQUE INDEX RESERVA_I6 ON Reserva(res_in_codreserva);
CREATE UNIQUE INDEX ESTADIA_I7 ON Estadia(est_in_codestadia);
CREATE UNIQUE INDEX SERVICO_PRESTADO_I8 ON Servico_prestado(svp_in_codservicopre);