/* Lista de Hoteis */
SELECT 	hot_pk_codigo as Codigo,
		hot_st_nome as Nome,
		hot_st_cidade as Cidade,
		hot_st_uf as UF,
		hot_st_cnpj as CNPJ
FROM Hotel;

SELECT 	hot_pk_codigo,
		hot_st_nome
FROM Hotel;


/* Testes */
/*
INSERT INTO Hotel VALUES (0,'Hotel GudaGuda','00000000000000','Joinville','SC','somewhere n859',5);
INSERT INTO Hotel VALUES (0,'Hotel Roaman','00000000000000','Blumenau','SC','somewhere n639',3);
INSERT INTO Hotel VALUES (0,'Hotel Interespacial','00000000000000','Rio Branco','AC','somewhere n52',4);
INSERT INTO Hotel VALUES (0,'Hotel Hooo','00000000000000','Rio de Janeiro','RJ','somewhere n11',3);
*/

--update Hotel set HOT_ST_UF = HOT_ST_UF;