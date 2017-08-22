-- 1)      Recupere o CPF e o nome dos mecânicos que trabalham nos setores número 1 e 2 (faça a consulta utilizado a cláusula IN).
select mec.cpf, mec.nome 
	from mecanico mec 
    where cods in (1,2);

-- 2)      Recupere o CPF e o nome dos mecânicos que trabalham nos setores 'Funilaria' e 'Pintura' (faça a consulta utilizando sub-consultas aninhadas).
select mec.cpf, mec.nome 
	from mecanico mec 
    where cods in (select cods from setor se where nome = 'Funilaria' or nome = 'Pintura');

-- 3)      Recupere o CPF e nome dos mecânicos que atenderam no dia 13/06/2014 (faça a consulta usando INNER JOIN).
select mec.cpf, mec.nome 
	from mecanico mec inner join conserto con on con.codm = mec.codm 
    where con.data = '13/06/2014';

-- 4)      Recupere o nome do mecânico, o nome do cliente e a hora do conserto para os consertos realizados no dia 12/06/2014 (faça a consulta usando INNER JOIN).
select mec.nome, cli.nome, con.data 
	from (mecanico mec inner join conserto con on con.codm = mec.codm)
    		inner join 
       	(veiculo vei inner join cliente cli on vei.codc = cli.codc)
            	on vei.codv = con.codv
	where con.data = '12/06/2014';

-- 5)	Recupere o nome e a função de todos os mecânicos, e o número e o nome dos setores para os mecânicos que tenham 
-- 		essa informação.
select mec.nome, mec.funcao, se.cods, se.nome 
-- from mecanico mec left join setor se on mec.cods = se.cods;
    from mecanico mec left join setor se using (cods);

-- 6)   Recupere o nome de todos os mecânicos, e as datas dos consertos para os mecânicos que têm consertos feitos 
-- 		(deve aparecer apenas um registro de nome de mecânico para cada data de conserto).
select distinct mec.nome, con.data
-- 	from mecanico mec left join conserto con on mec.codm = con.codm;
		from mecanico mec left join conserto con using (codm);

-- 7)      Recupere a média da quilometragem de todos os veículos dos clientes.
select avg(ve.quilometragem) 
	from veiculo ve;

-- 8)      Recupere a soma da quilometragem dos veículos de cada cidade onde residem seus proprietários.
select cli.cidade, sum(ve.quilometragem)
	from veiculo ve join cliente cli on ve.codc = cli.codc
    group by cli.cidade;

-- 9)      Recupere a quantidade de consertos feitos por cada mecânico durante o período de 12/06/2014 até 19/06/2014
select mec.nome, count(1)
	from mecanico mec join conserto con on mec.codm = con.codm
    where con.data between '12/06/2014' and '19/06/2014' 
    group by mec.codm, mec.nome;

-- 10)   Recupere a quantidade de consertos feitos agrupada pela marca do veículo.
select ve.marca, count(1)
	from veiculo ve join conserto con on ve.codv = con.codv
    group by ve.marca;

-- 11)   Recupere o modelo, a marca e o ano dos veículos que têm quilometragem maior que a média de quilometragem de todos os veículos.
select ve.modelo, ve.marca, ve.ano
	from veiculo ve
    where ve.quilometragem > (select avg(vee.quilometragem) from veiculo vee);

-- 12)   Recupere o nome dos mecânicos que têm mais de um conserto marcado para o mesmo dia.
select distinct mec.nome 
	from mecanico mec join conserto con using(codm)
    group by mec.codm, con.data
    having count(1) > 1;
