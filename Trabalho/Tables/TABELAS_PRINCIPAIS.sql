/*

ttt_xx_yyyyyy
ttt : tabela do campo
xx : tipo do campo
yyyy : nome do campo

*/

CREATE TABLE developer.Quarto(
    qua_in_codquarto NUMBER(9,9) PRIMARY KEY,
    qua_in_numero NUMBER(4,4) NOT NULL,
    hot_in_codhotel NUMBER(6,6) NOT NULL,
    qua_in_local VARCHAR2(50 CHAR) NOT NULL,
    tqu_in_codigo NUMBER(6,6),	
    qua_fl_diaria NUMBER DEFAULT 100 NOT NULL,
    /*FOREIGN KEY (hot_in_codhotel)
        REFERENCES developer.hotel(hot_in_codhotel) 
        ON DELETE CASCADE,
    FOREIGN KEY (tqu_in_codigo)
		REFERENCES developer.TipoQuarto(tqu_in_codigo)
		ON DELETE SET NULL*/
	FOREIGN KEY (hot_in_codhotel)
        REFERENCES developer.hotel(hot_in_codhotel),
    FOREIGN KEY (tqu_in_codigo)
		REFERENCES developer.TipoQuarto(tqu_in_codigo)
);
-- drop table developer.Quarto;

CREATE TABLE developer.Reserva(
	res_in_codreserva NUMBER(9,9) PRIMARY KEY,
	cli_in_codcliente NUMBER(9,9) NOT NULL,
	qua_in_codquarto NUMBER(9,9),
	res_dt_dtReserva DATE,
	res_dt_dtentrada DATE,	-- Data de check-in previsto
	res_dt_dtsaida DATE,	-- Data de check-out previsto
	res_dt_dtpagamento DATE,
	--res_st_confirma CHAR(1 CHAR) DEFAULT 'E',
	res_st_estado CHAR(1 CHAR) DEFAULT 'E',
	res_st_camaextra CHAR(1 CHAR),
	/*FOREIGN KEY (cli_in_codcliente)
		REFERENCES developer.Cliente(cli_in_codcliente)
		ON DELETE CASCADE,
	FOREIGN KEY (qua_in_codquarto)
		REFERENCES developer.Quarto(qua_in_codquarto)
		ON DELETE SET NULL*/
		FOREIGN KEY (cli_in_codcliente)
		REFERENCES developer.Cliente(cli_in_codcliente),
	FOREIGN KEY (qua_in_codquarto)
		REFERENCES developer.Quarto(qua_in_codquarto)
);
-- drop table developer.Reserva;
/*
	res_st_estado:
	E = em espera
	S = confirmado
	C = cancelado pelo cliente
	A = anulada por pagamento
*/


CREATE TABLE developer.Estadia(
	est_in_codestadia NUMBER(9,9) PRIMARY KEY,
	cli_in_codcliente NUMBER(9,9) DEFAULT '000000000' NOT NULL,
	qua_in_codquarto NUMBER(9,9),
	est_dt_dtcheckin DATE,
	est_dt_dtcheckout DATE,
	res_in_codreserva NUMBER(9,9),
	/*FOREIGN KEY (cli_in_codcliente)
		REFERENCES developer.Cliente(cli_in_codcliente)
		ON DELETE CASCADE,*/
	FOREIGN KEY (cli_in_codcliente)
		REFERENCES developer.Cliente(cli_in_codcliente),
	/*FOREIGN KEY (qua_in_codquarto)
		REFERENCES developer.Quarto(qua_in_codquarto)
		ON DELETE SET NULL,*/
	FOREIGN KEY (qua_in_codquarto)
		REFERENCES developer.Quarto(qua_in_codquarto),
	/*FOREIGN KEY (res_in_codreserva)
		REFERENCES developer.Reserva(res_in_codreserva)
		ON DELETE SET NULL*/
	FOREIGN KEY (res_in_codreserva)
		REFERENCES developer.Reserva(res_in_codreserva)
);
-- drop table developer.Estadia;

CREATE TABLE developer.Servico_prestado( 
	svp_in_codservicopre NUMBER(9,9) PRIMARY KEY,
	sev_in_codservico NUMBER(6,6) NOT NULL,
	epg_in_codempregado NUMBER(9,9) NOT NULL,
	svp_dt_dtservico DATE NOT NULL,
	svp_dt_hrservico DATE NOT NULL,
	svp_st_concluido CHAR(1 CHAR) DEFAULT 'N' NOT NULL,
	/*FOREIGN KEY (sev_in_codservico)
		REFERENCES developer.Servico(sev_in_codservico)
		ON DELETE CASCADE,
	FOREIGN KEY (epg_in_codempregado)
		REFERENCES developer.Empregado(epg_in_codempregado)
		ON DELETE CASCADE*/
	FOREIGN KEY (sev_in_codservico)
		REFERENCES developer.Servico(sev_in_codservico),
	FOREIGN KEY (epg_in_codempregado)
		REFERENCES developer.Empregado(epg_in_codempregado)
);
-- drop table developer.Servico_prestado;
