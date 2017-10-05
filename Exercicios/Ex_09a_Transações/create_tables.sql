-- Table: setor

-- DROP TABLE setor;

CREATE TABLE setor
(
  cods integer NOT NULL,
  nome character varying(50),
  CONSTRAINT setor_pkey PRIMARY KEY (cods)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE setor OWNER TO postgres;

-- Table: mecanico

-- DROP TABLE mecanico;

CREATE TABLE mecanico
(
  codm serial NOT NULL,
  cpf char(11),
  nome character varying(50),
  idade integer,
  endereco character varying(500),
  cidade character varying(500),
  funcao character varying(50),
  cods integer,
  CONSTRAINT mecanico_pkey PRIMARY KEY (codm),
  CONSTRAINT mecanico_cods_fkey FOREIGN KEY (cods)
      REFERENCES setor (cods) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE mecanico OWNER TO postgres;


-- Table: cliente

-- DROP TABLE cliente;

CREATE TABLE cliente
(
  codc integer NOT NULL,
  cpf char(11),
  nome character varying(50),
  idade integer,
  endereco character varying(500),
  cidade character varying(500),
  CONSTRAINT cliente_pkey PRIMARY KEY (codc) 
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente OWNER TO postgres;

-- Table: veiculo

-- DROP TABLE veiculo;

CREATE TABLE veiculo
(
  codv integer NOT NULL,
  renavam char(10),
  modelo character varying(50),
  marca character varying(50),
  ano integer,
  quilometragem float,
  codc integer,
  CONSTRAINT veiculo_pkey PRIMARY KEY (codv),
  CONSTRAINT veiculo_codc_fkey FOREIGN KEY (codc)
      REFERENCES cliente (codc) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE veiculo OWNER TO postgres;

-- Table: conserto

-- DROP TABLE conserto;

CREATE TABLE conserto
(
  codm integer NOT NULL,
  codv integer NOT NULL,
  data date NOT NULL,
  hora time without time zone,
  CONSTRAINT consulta_pkey PRIMARY KEY (codm, codv, data),
  CONSTRAINT consulta_codm_fkey FOREIGN KEY (codm)
      REFERENCES mecanico (codm) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT consulta_codv_fkey FOREIGN KEY (codv)
      REFERENCES veiculo (codv) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conserto OWNER TO postgres;