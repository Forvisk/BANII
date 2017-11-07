/*Descrição: Considerando o BD da oficina mecânica, faça a especificação dos comandos SQL para criação de índices conforme descrito abaixo.
*/
-- 1) Crie um índice para cada uma das chaves estrangeiras presentes do esquema de dados.
/*
CREATE INDEX idx_mecanico_setor ON Mecanico USING BTREE(cods);
CREATE INDEX idx_veiculo_cliente ON Veiculo USING BTREE(codc);
CREATE INDEX idx_conserto_mecanico ON Conserto USING BTREE(codm);
CREATE INDEX idx_conserto_veiculo ON Conserto USING BTREE(codv);
*/

-- 2) Crie um índice para acelerar a busca dos mecânicos pela função.
/*
CREATE INDEX idx_mecanico_funcao ON Mecanico USING hash(funcao);
*/

-- 3) Crie um índice para acelerar a ordenação dos consertos pela data e hora.
/*
CREATE INDEX idx_conserto_data_hora ON Conserto (data,hora);
*/

-- 4) Crie um índice para acelerar a busca de clientes pelo cpf.CREATE INDEX
/*
CREATE INDEX idx_cliente_cpf ON Cliente USING btree (cpf);
*/

-- 5) Crie um índice para acelerar a busca pelas primeiras 5 letras do nome dos clientes.
/*
CREATE INDEX idx_cliente_f3nome ON Cliente USING btree (substring( nome, 1, 5));
*/
-- 6) Crie um índice para acelerar a busca dos clientes com CPF com final XXX.XXX.XXX-55.
/*
CREATE INDEX idx_cliente_cpf_last55 ON using btree (cpf) WHERE  cpf LIKE '___.___.___-55';
*/