/*

ttt_xx_yyyyyy
ttt : tabela do campo
xx : tipo do campo
yyyy : nome do campo

*/

CREATE TABLE developer.Quarto(
    qua_in_codquarto NUMBER PRIMARY KEY,
    qua_in_numero NUMBER NOT NULL,
    qua_in_local VARCHAR2(50 CHAR) NOT NULL,	
    qua_fl_diaria NUMBER DEFAULT 100 NOT NULL,

    tqu_in_codigo NUMBER,
    hot_in_codhotel NUMBER NOT NULL,

	FOREIGN KEY (hot_in_codhotel)
        REFERENCES developer.hotel(hot_in_codhotel),
    FOREIGN KEY (tqu_in_codigo)
		REFERENCES developer.TipoQuarto(tqu_in_codigo)
);
-- drop table developer.Quarto;

CREATE TABLE developer.Reserva(
	res_in_codreserva NUMBER PRIMARY KEY,
	res_dt_dtReserva TIMESTAMP DEFAULT SYSDATE NOT NULL,
	res_dt_dtentrada DATE,	-- Data de check-in previsto
	res_dt_dtsaida DATE,	-- Data de check-out previsto
	res_dt_dtpagamento DATE,

	res_st_estado VARCHAR2(1 CHAR) DEFAULT 'E' NOT NULL,
	res_st_camaextra VARCHAR2(1 CHAR) DEFAULT 'N' NOT NULL,


	cli_in_codcliente NUMBER NOT NULL,
	qua_in_codquarto NUMBER NOT NULL,

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
	est_in_codestadia NUMBER PRIMARY KEY,
	est_dt_dtcheckin TIMESTAMP DEFAULT SYSDATE NOT NULL,
	est_dt_dtcheckout TIMESTAMP,

	
	cli_in_codcliente NUMBER NOT NULL,
	qua_in_codquarto NUMBER,
	res_in_codreserva NUMBER,

	FOREIGN KEY (cli_in_codcliente)
		REFERENCES developer.Cliente(cli_in_codcliente),
	FOREIGN KEY (qua_in_codquarto)
		REFERENCES developer.Quarto(qua_in_codquarto),
	FOREIGN KEY (res_in_codreserva)
		REFERENCES developer.Reserva(res_in_codreserva)
);
-- drop table developer.Estadia;


CREATE TABLE developer.Empregado(
	epg_in_codempregado NUMBER PRIMARY KEY,
	epg_st_nome VARCHAR2(40 CHAR) NOT NULL,

	epg_st_estado VARCHAR(1 CHAR) DEFAULT 'A' NOT NULL,

    hot_in_codhotel NUMBER NOT NULL,

    FOREIGN KEY (hot_in_codhotel)
    	REFERENCES developer.hotel(hot_in_codhotel)
);
-- drop table developer.Empregado;

CREATE TABLE developer.Servico_prestado( 
	svp_in_codservicopre NUMBER PRIMARY KEY,
	svp_dt_dtservico TIMESTAMP NOT NULL,
	svp_st_concluido VARCHAR2(1 CHAR) DEFAULT 'N' NOT NULL,

	svp_st_cortesia VARCHAR2(1 CHAR) DEFAULT 'N' NOT NULL,

	sev_in_codservico NUMBER NOT NULL,
	epg_in_codempregado NUMBER NOT NULL,
	est_in_codestadia NUMBER NOT NULL,

	FOREIGN KEY (sev_in_codservico)
		REFERENCES developer.Servico(sev_in_codservico),
	FOREIGN KEY (epg_in_codempregado)
		REFERENCES developer.Empregado(epg_in_codempregado),
	FOREIGN KEY (est_in_codestadia)
		REFERENCES developer.Estadia(est_in_codestadia)
);
-- drop table developer.Servico_prestado;
