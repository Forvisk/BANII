
-- 1) Mostre o nome e a função dos mecânicos.

Create View mecanico_nome_funcao (nome, funcao) as
	select m.nome, m.funcao
    	from mecanico m
        
select * from mecanico_nome_funcao

-- 2) Mostre o modelo e a marca dos veículos dos clientes.

Create View veiculo_modelo_marca ( modelo, marca) as
	select distinct v.modelo, v.marca
    	from veiculo v

select * from veiculo_modelo_marca


-- 3) Mostre o nome dos mecânicos, o nome dos clientes, o modelo dos veículos e a data e hora dos consertos realizados.

Create View mecanico_cliente_veiculo_conserto ( mecanico, cliente, modelo, data, hora) as
	select m.nome, c.nome, v.modelo, con.data, con.hora
    	from veiculo v join cliente c using(codc)
        		join conserto con using(codv)
                join mecanico m using(codm)
                
select * from mecanico_cliente_veiculo_conserto

-- 4) Mostre o ano dos veículos e a média de quilometragem para cada ano.

Create View ano_quilom_media (ano, media_km) as 
    select v.ano, avg(v.quilometragem)
        from veiculo v
        group by v.ano
     
select * from ano_quilom_media


-- 5) Mostre o nome dos mecânicos e o total de consertos feitos por um mecânico em cada dia.

Create View mecanico_con_dia (mecanico, data, quant) as
    select m.nome, con.data, count(1)
        from mecanico m join conserto con using(codm)
        group by m.nome, con.data
        
select * from mecanico_con_dia


-- 6) Mostre o nome dos setores e o total de consertos feitos em um setor em cada dia.

Create View setor_conserto_dia (setor, data, quant) as
    select se.nome, con.data, count(1)
        from setor se join mecanico m using(cods)
                join conserto con using(codm)
        group by se.cods, con.data
select * from setor_conserto_dia

-- 7) Mostre o nome das funções e o número de mecânicos que têm uma destas funções.

Create View funcao (nome, quant_mec) as
    select m.funcao, count(1)
        from mecanico m
        group by m.funcao

select * from funcao

-- 8) Mostre o nome dos mecânicos e suas funções e, para os mecânicos que estejam alocados a um setor, 
-- 		informe também o número e nome do setor.

Create View mecanico_setor (nome, funcao, setor, codsetor) as
    select m.nome, m.funcao, se.nome, se.cods
        from mecanico m left join setor se using(cods)

select * from mecanico_setor

-- 9) Mostre o nome das funções dos mecânicos e a quantidade de consertos feitos agrupado por cada função.

create view quant_conserto_funcao (funcao, quant) as
	select m.funcao, count(1)
		from mecanico m join conserto c using (codm)
		group by m.funcao

select * from quant_conserto_funcao
	where quant > 2
	order by quant
