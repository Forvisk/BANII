/*

ttt_xx_yyyyyy
ttt : tabela do campo
xx : tipo do campo
yyyy : nome do campo

*/

CREATE TABLE developer.Cliente(
    cli_in_codcliente NUMBER PRIMARY KEY,
    cli_st_nome VARCHAR2(40 CHAR) NOT NULL,
    cli_st_endereco VARCHAR2(100 CHAR) NOT NULL,
    cli_st_telefone VARCHAR2(10 CHAR) NOT NULL
);
-- drop table developer.Cliente;

CREATE TABLE developer.Hotel(
    hot_in_codhotel NUMBER PRIMARY KEY,
    hot_st_nome VARCHAR2(40 CHAR) NOT NULL,
    hot_st_endereco VARCHAR2(100 CHAR) NOT NULL,
    hot_st_telefone VARCHAR2(10 CHAR) NOT NULL,

    hot_st_cidade VARCHAR2(20 CHAR) NOT NULL,
    hot_st_uf VARCHAR(2 CHAR) NOT NULL
);
-- drop table developer.Hotel;

-- Single, Casal, Suite, Duplo
CREATE TABLE developer.TipoQuarto(
    tqu_in_codigo NUMBER PRIMARY KEY,
    tqu_st_tipo VARCHAR2(20 CHAR) NOT NULL
);
-- drop table developer.TipoQuarto;

CREATE TABLE developer.Servico(
	sev_in_codservico NUMBER PRIMARY KEY,
	sev_fl_preco NUMBER DEFAULT 0 NOT NULL,
	sev_st_descricao VARCHAR2(100 CHAR) NOT NULL,
	sev_st_tipo CHAR(1 CHAR) DEFAULT 'N' NOT NULL
	-- N = n�o de limpeza, L = sevi�o de limpeza
);
-- drop table developer.Servico;