/* Tela de Cadastro de Quartos*/

SELECT 	q.QUA_PK_NUMERO as NUMERO,
		q.HOT_PFK_CODIGO as HOTEL,
		tq.TQU_ST_TIPO as TIPO,
		q.QUA_IN_LOCAL as LOCAL,
		(	SELECT 'VAGO'
			FROM Estadia e
			WHERE 	e.QUA_FK_NUMERO = q.QUA_PK_NUMERO AND e.HOT_FK_CODIGO = q.HOT_PFK_CODIGO
					AND SYSDATE BETWEEN e.est_dt_checkin and e.est_dt_checkout
			GROUP BY q.QUA_PK_NUMERO, q.HOT_PFK_CODIGO) AS Estado
FROM Quarto q join TIPO_QUARTO tq ON q.TQU_FK_CODIGO = tq.TQU_PK_CODIGO;
/*
SELECT 1
FROM Estadia e JOIN Quarto q ON e.QUA_FK_NUMERO = q.QUA_PK_NUMERO and e.HOT_FK_CODIGO = q.HOT_PFK_CODIGO
GROUP BY q.QUA_PK_NUMERO, q.HOT_PFK_CODIGO;
*/
/* Inserção de Quarto */
/* Deleção de Quarto */
/* Alteração de Quartos */

/* Testes */
/*
INSERT INTO QUARTO VALUES (101, 'Primeiro andar', 1, 7);
INSERT INTO QUARTO VALUES (102, 'Primeiro andar', 1, 7);
INSERT INTO QUARTO VALUES (103, 'Primeiro andar', 1, 7);
INSERT INTO QUARTO VALUES (104, 'Primeiro andar', 1, 7);
INSERT INTO QUARTO VALUES (105, 'Primeiro andar', 2, 7);
INSERT INTO QUARTO VALUES (106, 'Primeiro andar', 2, 7);
INSERT INTO QUARTO VALUES (107, 'Primeiro andar', 2, 7);
INSERT INTO QUARTO VALUES (108, 'Primeiro andar', 2, 7);
INSERT INTO QUARTO VALUES (109, 'Primeiro andar', 1, 7);
INSERT INTO QUARTO VALUES (110, 'Primeiro andar', 1, 7);
INSERT INTO QUARTO VALUES (111, 'Primeiro andar', 3, 7);
INSERT INTO QUARTO VALUES (112, 'Primeiro andar', 3, 7);
INSERT INTO QUARTO VALUES (113, 'Primeiro andar', 3, 7);
INSERT INTO QUARTO VALUES (114, 'Primeiro andar', 3, 7);
INSERT INTO QUARTO VALUES (115, 'Primeiro andar', 4, 7);
INSERT INTO QUARTO VALUES (116, 'Primeiro andar', 4, 7);

INSERT INTO QUARTO VALUES (201, 'Segundo andar', 1, 7);
INSERT INTO QUARTO VALUES (202, 'Segundo andar', 1, 7);
INSERT INTO QUARTO VALUES (203, 'Segundo andar', 1, 7);
INSERT INTO QUARTO VALUES (204, 'Segundo andar', 1, 7);
INSERT INTO QUARTO VALUES (205, 'Segundo andar', 2, 7);
INSERT INTO QUARTO VALUES (206, 'Segundo andar', 2, 7);
INSERT INTO QUARTO VALUES (207, 'Segundo andar', 2, 7);
INSERT INTO QUARTO VALUES (208, 'Segundo andar', 2, 7);
INSERT INTO QUARTO VALUES (209, 'Segundo andar', 1, 7);
INSERT INTO QUARTO VALUES (210, 'Segundo andar', 1, 7);
INSERT INTO QUARTO VALUES (211, 'Segundo andar', 3, 7);
INSERT INTO QUARTO VALUES (212, 'Segundo andar', 3, 7);
INSERT INTO QUARTO VALUES (213, 'Segundo andar', 3, 7);
INSERT INTO QUARTO VALUES (214, 'Segundo andar', 3, 7);
INSERT INTO QUARTO VALUES (215, 'Segundo andar', 4, 7);
INSERT INTO QUARTO VALUES (216, 'Segundo andar', 4, 7);

INSERT INTO QUARTO VALUES (301, 'Terceiro andar', 1, 7);
INSERT INTO QUARTO VALUES (302, 'Terceiro andar', 1, 7);
INSERT INTO QUARTO VALUES (303, 'Terceiro andar', 1, 7);
INSERT INTO QUARTO VALUES (304, 'Terceiro andar', 1, 7);
INSERT INTO QUARTO VALUES (305, 'Terceiro andar', 2, 7);
INSERT INTO QUARTO VALUES (306, 'Terceiro andar', 2, 7);
INSERT INTO QUARTO VALUES (307, 'Terceiro andar', 2, 7);
INSERT INTO QUARTO VALUES (308, 'Terceiro andar', 2, 7);
INSERT INTO QUARTO VALUES (309, 'Terceiro andar', 1, 7);
INSERT INTO QUARTO VALUES (310, 'Terceiro andar', 1, 7);
INSERT INTO QUARTO VALUES (311, 'Terceiro andar', 3, 7);
INSERT INTO QUARTO VALUES (312, 'Terceiro andar', 3, 7);
INSERT INTO QUARTO VALUES (313, 'Terceiro andar', 3, 7);
INSERT INTO QUARTO VALUES (314, 'Terceiro andar', 3, 7);
INSERT INTO QUARTO VALUES (315, 'Terceiro andar', 4, 7);
INSERT INTO QUARTO VALUES (316, 'Terceiro andar', 4, 7);

INSERT INTO QUARTO VALUES (401, 'Quarto andar', 1, 7);
INSERT INTO QUARTO VALUES (402, 'Quarto andar', 1, 7);
INSERT INTO QUARTO VALUES (403, 'Quarto andar', 1, 7);
INSERT INTO QUARTO VALUES (404, 'Quarto andar', 1, 7);
INSERT INTO QUARTO VALUES (405, 'Quarto andar', 2, 7);
INSERT INTO QUARTO VALUES (406, 'Quarto andar', 2, 7);
INSERT INTO QUARTO VALUES (407, 'Quarto andar', 2, 7);
INSERT INTO QUARTO VALUES (408, 'Quarto andar', 2, 7);
INSERT INTO QUARTO VALUES (409, 'Quarto andar', 1, 7);
INSERT INTO QUARTO VALUES (410, 'Quarto andar', 1, 7);
INSERT INTO QUARTO VALUES (411, 'Quarto andar', 3, 7);
INSERT INTO QUARTO VALUES (412, 'Quarto andar', 3, 7);
INSERT INTO QUARTO VALUES (413, 'Quarto andar', 3, 7);
INSERT INTO QUARTO VALUES (414, 'Quarto andar', 3, 7);
INSERT INTO QUARTO VALUES (415, 'Quarto andar', 4, 7);
INSERT INTO QUARTO VALUES (416, 'Quarto andar', 4, 7);

INSERT INTO QUARTO VALUES (101, 'Primeiro andar', 1, 8);
INSERT INTO QUARTO VALUES (102, 'Primeiro andar', 1, 8);
INSERT INTO QUARTO VALUES (103, 'Primeiro andar', 1, 8);
INSERT INTO QUARTO VALUES (104, 'Primeiro andar', 1, 8);
INSERT INTO QUARTO VALUES (105, 'Primeiro andar', 2, 8);
INSERT INTO QUARTO VALUES (106, 'Primeiro andar', 2, 8);
INSERT INTO QUARTO VALUES (107, 'Primeiro andar', 2, 8);
INSERT INTO QUARTO VALUES (108, 'Primeiro andar', 2, 8);
INSERT INTO QUARTO VALUES (109, 'Primeiro andar', 1, 8);
INSERT INTO QUARTO VALUES (110, 'Primeiro andar', 1, 8);
INSERT INTO QUARTO VALUES (111, 'Primeiro andar', 3, 8);
INSERT INTO QUARTO VALUES (112, 'Primeiro andar', 3, 8);
INSERT INTO QUARTO VALUES (113, 'Primeiro andar', 3, 8);
INSERT INTO QUARTO VALUES (114, 'Primeiro andar', 3, 8);
INSERT INTO QUARTO VALUES (115, 'Primeiro andar', 4, 8);
INSERT INTO QUARTO VALUES (116, 'Primeiro andar', 4, 8);

INSERT INTO QUARTO VALUES (201, 'Segundo andar', 1, 8);
INSERT INTO QUARTO VALUES (202, 'Segundo andar', 1, 8);
INSERT INTO QUARTO VALUES (203, 'Segundo andar', 1, 8);
INSERT INTO QUARTO VALUES (204, 'Segundo andar', 1, 8);
INSERT INTO QUARTO VALUES (205, 'Segundo andar', 2, 8);
INSERT INTO QUARTO VALUES (206, 'Segundo andar', 2, 8);
INSERT INTO QUARTO VALUES (207, 'Segundo andar', 2, 8);
INSERT INTO QUARTO VALUES (208, 'Segundo andar', 2, 8);
INSERT INTO QUARTO VALUES (209, 'Segundo andar', 1, 8);
INSERT INTO QUARTO VALUES (210, 'Segundo andar', 1, 8);
INSERT INTO QUARTO VALUES (211, 'Segundo andar', 3, 8);
INSERT INTO QUARTO VALUES (212, 'Segundo andar', 3, 8);
INSERT INTO QUARTO VALUES (213, 'Segundo andar', 3, 8);
INSERT INTO QUARTO VALUES (214, 'Segundo andar', 3, 8);
INSERT INTO QUARTO VALUES (215, 'Segundo andar', 4, 8);
INSERT INTO QUARTO VALUES (216, 'Segundo andar', 4, 8);

INSERT INTO QUARTO VALUES (301, 'Terceiro andar', 1, 8);
INSERT INTO QUARTO VALUES (302, 'Terceiro andar', 1, 8);
INSERT INTO QUARTO VALUES (303, 'Terceiro andar', 1, 8);
INSERT INTO QUARTO VALUES (304, 'Terceiro andar', 1, 8);
INSERT INTO QUARTO VALUES (305, 'Terceiro andar', 2, 8);
INSERT INTO QUARTO VALUES (306, 'Terceiro andar', 2, 8);
INSERT INTO QUARTO VALUES (307, 'Terceiro andar', 2, 8);
INSERT INTO QUARTO VALUES (308, 'Terceiro andar', 2, 8);
INSERT INTO QUARTO VALUES (309, 'Terceiro andar', 1, 8);
INSERT INTO QUARTO VALUES (310, 'Terceiro andar', 1, 8);
INSERT INTO QUARTO VALUES (311, 'Terceiro andar', 3, 8);
INSERT INTO QUARTO VALUES (312, 'Terceiro andar', 3, 8);
INSERT INTO QUARTO VALUES (313, 'Terceiro andar', 3, 8);
INSERT INTO QUARTO VALUES (314, 'Terceiro andar', 3, 8);
INSERT INTO QUARTO VALUES (315, 'Terceiro andar', 4, 8);
INSERT INTO QUARTO VALUES (316, 'Terceiro andar', 4, 8);

INSERT INTO QUARTO VALUES (401, 'Quarto andar', 1, 8);
INSERT INTO QUARTO VALUES (402, 'Quarto andar', 1, 8);
INSERT INTO QUARTO VALUES (403, 'Quarto andar', 1, 8);
INSERT INTO QUARTO VALUES (404, 'Quarto andar', 1, 8);
INSERT INTO QUARTO VALUES (405, 'Quarto andar', 2, 8);
INSERT INTO QUARTO VALUES (406, 'Quarto andar', 2, 8);
INSERT INTO QUARTO VALUES (407, 'Quarto andar', 2, 8);
INSERT INTO QUARTO VALUES (408, 'Quarto andar', 2, 8);
INSERT INTO QUARTO VALUES (409, 'Quarto andar', 1, 8);
INSERT INTO QUARTO VALUES (410, 'Quarto andar', 1, 8);
INSERT INTO QUARTO VALUES (411, 'Quarto andar', 3, 8);
INSERT INTO QUARTO VALUES (412, 'Quarto andar', 3, 8);
INSERT INTO QUARTO VALUES (413, 'Quarto andar', 3, 8);
INSERT INTO QUARTO VALUES (414, 'Quarto andar', 3, 8);
INSERT INTO QUARTO VALUES (415, 'Quarto andar', 4, 8);
INSERT INTO QUARTO VALUES (416, 'Quarto andar', 4, 8);

INSERT INTO QUARTO VALUES (101, 'Primeiro andar', 1, 9);
INSERT INTO QUARTO VALUES (102, 'Primeiro andar', 1, 9);
INSERT INTO QUARTO VALUES (103, 'Primeiro andar', 1, 9);
INSERT INTO QUARTO VALUES (104, 'Primeiro andar', 1, 9);
INSERT INTO QUARTO VALUES (105, 'Primeiro andar', 2, 9);
INSERT INTO QUARTO VALUES (106, 'Primeiro andar', 2, 9);
INSERT INTO QUARTO VALUES (107, 'Primeiro andar', 2, 9);
INSERT INTO QUARTO VALUES (108, 'Primeiro andar', 2, 9);
INSERT INTO QUARTO VALUES (109, 'Primeiro andar', 1, 9);
INSERT INTO QUARTO VALUES (110, 'Primeiro andar', 1, 9);
INSERT INTO QUARTO VALUES (111, 'Primeiro andar', 3, 9);
INSERT INTO QUARTO VALUES (112, 'Primeiro andar', 3, 9);
INSERT INTO QUARTO VALUES (113, 'Primeiro andar', 3, 9);
INSERT INTO QUARTO VALUES (114, 'Primeiro andar', 3, 9);
INSERT INTO QUARTO VALUES (115, 'Primeiro andar', 4, 9);
INSERT INTO QUARTO VALUES (116, 'Primeiro andar', 4, 9);

INSERT INTO QUARTO VALUES (201, 'Segundo andar', 1, 9);
INSERT INTO QUARTO VALUES (202, 'Segundo andar', 1, 9);
INSERT INTO QUARTO VALUES (203, 'Segundo andar', 1, 9);
INSERT INTO QUARTO VALUES (204, 'Segundo andar', 1, 9);
INSERT INTO QUARTO VALUES (205, 'Segundo andar', 2, 9);
INSERT INTO QUARTO VALUES (206, 'Segundo andar', 2, 9);
INSERT INTO QUARTO VALUES (207, 'Segundo andar', 2, 9);
INSERT INTO QUARTO VALUES (208, 'Segundo andar', 2, 9);
INSERT INTO QUARTO VALUES (209, 'Segundo andar', 1, 9);
INSERT INTO QUARTO VALUES (210, 'Segundo andar', 1, 9);
INSERT INTO QUARTO VALUES (211, 'Segundo andar', 3, 9);
INSERT INTO QUARTO VALUES (212, 'Segundo andar', 3, 9);
INSERT INTO QUARTO VALUES (213, 'Segundo andar', 3, 9);
INSERT INTO QUARTO VALUES (214, 'Segundo andar', 3, 9);
INSERT INTO QUARTO VALUES (215, 'Segundo andar', 4, 9);
INSERT INTO QUARTO VALUES (216, 'Segundo andar', 4, 9);

INSERT INTO QUARTO VALUES (301, 'Terceiro andar', 1, 9);
INSERT INTO QUARTO VALUES (302, 'Terceiro andar', 1, 9);
INSERT INTO QUARTO VALUES (303, 'Terceiro andar', 1, 9);
INSERT INTO QUARTO VALUES (304, 'Terceiro andar', 1, 9);
INSERT INTO QUARTO VALUES (305, 'Terceiro andar', 2, 9);
INSERT INTO QUARTO VALUES (306, 'Terceiro andar', 2, 9);
INSERT INTO QUARTO VALUES (307, 'Terceiro andar', 2, 9);
INSERT INTO QUARTO VALUES (308, 'Terceiro andar', 2, 9);
INSERT INTO QUARTO VALUES (309, 'Terceiro andar', 1, 9);
INSERT INTO QUARTO VALUES (310, 'Terceiro andar', 1, 9);
INSERT INTO QUARTO VALUES (311, 'Terceiro andar', 3, 9);
INSERT INTO QUARTO VALUES (312, 'Terceiro andar', 3, 9);
INSERT INTO QUARTO VALUES (313, 'Terceiro andar', 3, 9);
INSERT INTO QUARTO VALUES (314, 'Terceiro andar', 3, 9);
INSERT INTO QUARTO VALUES (315, 'Terceiro andar', 4, 9);
INSERT INTO QUARTO VALUES (316, 'Terceiro andar', 4, 9);

INSERT INTO QUARTO VALUES (401, 'Quarto andar', 1, 9);
INSERT INTO QUARTO VALUES (402, 'Quarto andar', 1, 9);
INSERT INTO QUARTO VALUES (403, 'Quarto andar', 1, 9);
INSERT INTO QUARTO VALUES (404, 'Quarto andar', 1, 9);
INSERT INTO QUARTO VALUES (405, 'Quarto andar', 2, 9);
INSERT INTO QUARTO VALUES (406, 'Quarto andar', 2, 9);
INSERT INTO QUARTO VALUES (407, 'Quarto andar', 2, 9);
INSERT INTO QUARTO VALUES (408, 'Quarto andar', 2, 9);
INSERT INTO QUARTO VALUES (409, 'Quarto andar', 1, 9);
INSERT INTO QUARTO VALUES (410, 'Quarto andar', 1, 9);
INSERT INTO QUARTO VALUES (411, 'Quarto andar', 3, 9);
INSERT INTO QUARTO VALUES (412, 'Quarto andar', 3, 9);
INSERT INTO QUARTO VALUES (413, 'Quarto andar', 3, 9);
INSERT INTO QUARTO VALUES (414, 'Quarto andar', 3, 9);
INSERT INTO QUARTO VALUES (415, 'Quarto andar', 4, 9);
INSERT INTO QUARTO VALUES (416, 'Quarto andar', 4, 9);

INSERT INTO QUARTO VALUES (101, 'Primeiro andar', 1, 10);
INSERT INTO QUARTO VALUES (102, 'Primeiro andar', 1, 10);
INSERT INTO QUARTO VALUES (103, 'Primeiro andar', 1, 10);
INSERT INTO QUARTO VALUES (104, 'Primeiro andar', 1, 10);
INSERT INTO QUARTO VALUES (105, 'Primeiro andar', 2, 10);
INSERT INTO QUARTO VALUES (106, 'Primeiro andar', 2, 10);
INSERT INTO QUARTO VALUES (107, 'Primeiro andar', 2, 10);
INSERT INTO QUARTO VALUES (108, 'Primeiro andar', 2, 10);
INSERT INTO QUARTO VALUES (109, 'Primeiro andar', 1, 10);
INSERT INTO QUARTO VALUES (110, 'Primeiro andar', 1, 10);
INSERT INTO QUARTO VALUES (111, 'Primeiro andar', 3, 10);
INSERT INTO QUARTO VALUES (112, 'Primeiro andar', 3, 10);
INSERT INTO QUARTO VALUES (113, 'Primeiro andar', 3, 10);
INSERT INTO QUARTO VALUES (114, 'Primeiro andar', 3, 10);
INSERT INTO QUARTO VALUES (115, 'Primeiro andar', 4, 10);
INSERT INTO QUARTO VALUES (116, 'Primeiro andar', 4, 10);

INSERT INTO QUARTO VALUES (201, 'Segundo andar', 1, 10);
INSERT INTO QUARTO VALUES (202, 'Segundo andar', 1, 10);
INSERT INTO QUARTO VALUES (203, 'Segundo andar', 1, 10);
INSERT INTO QUARTO VALUES (204, 'Segundo andar', 1, 10);
INSERT INTO QUARTO VALUES (205, 'Segundo andar', 2, 10);
INSERT INTO QUARTO VALUES (206, 'Segundo andar', 2, 10);
INSERT INTO QUARTO VALUES (207, 'Segundo andar', 2, 10);
INSERT INTO QUARTO VALUES (208, 'Segundo andar', 2, 10);
INSERT INTO QUARTO VALUES (209, 'Segundo andar', 1, 10);
INSERT INTO QUARTO VALUES (210, 'Segundo andar', 1, 10);
INSERT INTO QUARTO VALUES (211, 'Segundo andar', 3, 10);
INSERT INTO QUARTO VALUES (212, 'Segundo andar', 3, 10);
INSERT INTO QUARTO VALUES (213, 'Segundo andar', 3, 10);
INSERT INTO QUARTO VALUES (214, 'Segundo andar', 3, 10);
INSERT INTO QUARTO VALUES (215, 'Segundo andar', 4, 10);
INSERT INTO QUARTO VALUES (216, 'Segundo andar', 4, 10);

INSERT INTO QUARTO VALUES (301, 'Terceiro andar', 1, 10);
INSERT INTO QUARTO VALUES (302, 'Terceiro andar', 1, 10);
INSERT INTO QUARTO VALUES (303, 'Terceiro andar', 1, 10);
INSERT INTO QUARTO VALUES (304, 'Terceiro andar', 1, 10);
INSERT INTO QUARTO VALUES (305, 'Terceiro andar', 2, 10);
INSERT INTO QUARTO VALUES (306, 'Terceiro andar', 2, 10);
INSERT INTO QUARTO VALUES (307, 'Terceiro andar', 2, 10);
INSERT INTO QUARTO VALUES (308, 'Terceiro andar', 2, 10);
INSERT INTO QUARTO VALUES (309, 'Terceiro andar', 1, 10);
INSERT INTO QUARTO VALUES (310, 'Terceiro andar', 1, 10);
INSERT INTO QUARTO VALUES (311, 'Terceiro andar', 3, 10);
INSERT INTO QUARTO VALUES (312, 'Terceiro andar', 3, 10);
INSERT INTO QUARTO VALUES (313, 'Terceiro andar', 3, 10);
INSERT INTO QUARTO VALUES (314, 'Terceiro andar', 3, 10);
INSERT INTO QUARTO VALUES (315, 'Terceiro andar', 4, 10);
INSERT INTO QUARTO VALUES (316, 'Terceiro andar', 4, 10);

INSERT INTO QUARTO VALUES (401, 'Quarto andar', 1, 10);
INSERT INTO QUARTO VALUES (402, 'Quarto andar', 1, 10);
INSERT INTO QUARTO VALUES (403, 'Quarto andar', 1, 10);
INSERT INTO QUARTO VALUES (404, 'Quarto andar', 1, 10);
INSERT INTO QUARTO VALUES (405, 'Quarto andar', 2, 10);
INSERT INTO QUARTO VALUES (406, 'Quarto andar', 2, 10);
INSERT INTO QUARTO VALUES (407, 'Quarto andar', 2, 10);
INSERT INTO QUARTO VALUES (408, 'Quarto andar', 2, 10);
INSERT INTO QUARTO VALUES (409, 'Quarto andar', 1, 10);
INSERT INTO QUARTO VALUES (410, 'Quarto andar', 1, 10);
INSERT INTO QUARTO VALUES (411, 'Quarto andar', 3, 10);
INSERT INTO QUARTO VALUES (412, 'Quarto andar', 3, 10);
INSERT INTO QUARTO VALUES (413, 'Quarto andar', 3, 10);
INSERT INTO QUARTO VALUES (414, 'Quarto andar', 3, 10);
INSERT INTO QUARTO VALUES (415, 'Quarto andar', 4, 10);
INSERT INTO QUARTO VALUES (416, 'Quarto andar', 4, 10);
*/