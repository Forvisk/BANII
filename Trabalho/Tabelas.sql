/*

ttt_xx_yyyyyy
ttt : tabela do campo
xx : tipo do campo
yyyy : nome do campo

*/

CREATE TABLE developer.Cliente(
    cli_in_codcliente NUMBER(9,9) PRIMARY KEY,
    cli_st_nome VARCHAR2(40 CHAR) NOT NULL,
    cli_st_endereco VARCHAR2(100 CHAR) NOT NULL,
    cli_st_telefone VARCHAR2(10 CHAR) NOT NULL
);
-- drop table developer.Cliente;

CREATE TABLE developer.Hotel(
    hot_in_codhotel NUMBER(6,6) PRIMARY KEY,
    hot_st_nome VARCHAR2(40 CHAR) NOT NULL,
    hot_st_endereco VARCHAR2(100 CHAR) NOT NULL,
    hot_st_telefone VARCHAR2(10 CHAR) NOT NULL
);
-- drop table developer.Hotel;

-- Single, Casal, Suite, Duplo
CREATE TABLE developer.TipoQuarto(
    tqu_in_codigo NUMBER(6,6) PRIMARY KEY,
    tqu_st_tipo VARCHAR(20 CHAR) NOT NULL
);
-- drop table developer.TipoQuarto;

CREATE TABLE developer.Quarto(
    qua_in_codquarto NUMBER(9,9) PRIMARY KEY,
    qua_in_numero NUMBER(4,4) NOT NULL,
    hot_in_codhotel NUMBER(6,6) NOT NULL,
    qua_in_local VARCHAR2(50 CHAR) NOT NULL,
    tqu_in_codigo NUMBER(6,6),	
    qua_fl_diaria NUMBER DEFAULT 100 NOT NULL,
    FOREIGN KEY (hot_in_codhotel)
        REFERENCES developer.hotel(hot_in_codhotel) 
        ON DELETE CASCADE,
    FOREIGN KEY (tqu_in_codigo)
		REFERENCES developer.TipoQuarto(tqu_in_codigo)
		ON DELETE SET NULL
	ON DELETE CASCADE
);
-- drop table developer.Quarto;

CREATE TABLE developer.Reserva(
	res_in_codreserva NUMBER(9,9) PRIMARY KEY,
	cli_in_codcliente NUMBER(9,9) NOT NULL,
	qua_in_codquarto NUMBER(9,9),
	res_dt_dtreserva DATE,
	res_dt_dtentrada DATE,
	res_dt_dtpagamento DATE,
	res_st_confirma CHAR(1 CHAR),
	res_st_camaextra CHAR(1 CHAR),
	FOREIGN KEY (cli_in_codcliente)
		REFERENCES developer.Cliente(cli_in_codcliente)
		ON DELETE CASCADE,
	FOREIGN KEY (qua_in_codquarto)
		REFERENCES developer.Quarto(qua_in_codquarto)
		ON DELETE SET NULL
);
-- drop table developer.Reserva;

CREATE TABLE developer.Estadia(
	est_in_codestadia NUMBER(9,9) PRIMARY KEY,
	cli_in_codcliente NUMBER(9,9) DEFAULT '000000000' NOT NULL,
	qua_in_codquarto NUMBER(9,9),
	est_dt_dtcheckin DATE,
	est_dt_dtcheckout DATE,
	res_in_codreserva NUMBER(9,9),
	FOREIGN KEY (cli_in_codcliente)
		REFERENCES developer.Cliente(cli_in_codcliente)
		ON DELETE CASCADE,
	FOREIGN KEY (qua_in_codquarto)
		REFERENCES developer.Quarto(qua_in_codquarto)
		ON DELETE SET NULL,
	FOREIGN KEY (res_in_codreserva)
		REFERENCES developer.Reserva(res_in_codreserva)
		ON DELETE SET NULL
);
-- drop table developer.Estadia;

CREATE TABLE developer.Empregado(
	epg_in_codempregado NUMBER(9,9) PRIMARY KEY,
	epg_st_nome VARCHAR2(40 CHAR) NOT NULL
);
-- drop table developer.Empregado;

CREATE TABLE developer.Servico(
	sev_in_codservico NUMBER(6,6) PRIMARY KEY,
	sev_fl_preco NUMBER DEFAULT 0 NOT NULL,
	sev_st_descricao VARCHAR2(100 CHAR) NOT NULL,
	sev_st_tipo CHAR(1 CHAR) DEFAULT 'N' NOT NULL
	-- N = não de limpeza, L = seviço de limpeza
);
-- drop table developer.Servico;
CREATE TABLE developer.Servico_prestado( 
	svp_in_codservicopre NUMBER(9,9) PRIMARY KEY,
	sev_in_codservico NUMBER(6,6) NOT NULL,
	epg_in_codempregado NUMBER(9,9) NOT NULL,
	svp_dt_dtservico DATE NOT NULL,
	svp_dt_hrservico DATE NOT NULL,
	FOREIGN KEY (sev_in_codservico)
		REFERENCES developer.Servico(sev_in_codservico)
		ON DELETE CASCADE,
	FOREIGN KEY (epg_in_codempregado)
		REFERENCES developer.Empregado(epg_in_codempregado)
		ON DELETE CASCADE
);
-- drop table developer.Servico_prestado;


/*
drop table developer.Servico_prestado;
drop table developer.Estadia;
drop table developer.Reserva;
drop table developer.Cliente;
drop table developer.Quarto;
drop table developer.Hotel;
drop table developer.Empregado;
drop table developer.Servico;
*/
