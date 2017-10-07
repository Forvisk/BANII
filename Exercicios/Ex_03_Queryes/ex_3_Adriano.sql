
-- 1)	Recupere o nome e o endereço de cada cliente.
select 	nome, 
		endereco 
	from cliente

-- 2)	Recupere o nome e a função dos mecânicos que trabalham no setor número 2 (cods 2).
select 	nome, 
		funcao 
	from mecanico 
    where 	cods = 2

-- 3)	Recupere o CPF e o nome de todos os mecânicos que são clientes da oficina (utilize operação de conjuntos).
select 	cpf,
		nome 
	from mecanico
intersect
select 	cpf, 
		nome 
	from cliente

-- 4)	Recupere as cidades das quais os mecânicos e clientes são oriundos.
select 	cidade 
	from cliente
union
select 	cidade 
	from mecanico

-- 5)   Recupere as marcas distintas dos veículos dos clientes que moram em Joinville.
select 	distinct ve.marca 
	from veiculo ve, 
    	 cliente cl 
	where 	ve.codc = cl.codc 
    		and cl.cidade = 'Joinville'

-- 6)   Recupere as funções distintas dos mecânicos da oficina.
select 	distinct funcao 
	from mecanico

-- 7)   Recupere todas as informações dos clientes que têm idade maior que 25 anos.
select 	* 
	from cliente 
	where 	idade > 25

-- 8)   Recupere o CPF e o nome dos mecânicos que trabalham no setor de mecânica.
select 	mec.cpf, 
		mec.nome 
	from mecanico mec, 
    	 setor se 
    where 	se.nome = 'Mecânica' 
    		and se.cods = mec.cods

-- 9)   Recupere o CPF e nome dos mecânicos que trabalharam no dia 13/06/2014.
select distinct mec.cpf, 
				mec.nome
	from mecanico mec, 
    	 conserto con 
    where 	con.codm = mec.codm 
    		and con.data = '13/06/2014'

-- 10)	Recupere o nome do cliente, o modelo do seu veículo, o nome do mecânico 
--  	e sua função para todos os consertos realizados (utilize join para realizar a junção).
select 	cl.nome  as "Cliente", 
		ve.modelo, 
        mec.nome  as "Mecanico", 
        mec.funcao
	from cliente cl join veiculo ve on cl.codc = ve.codc
    		join conserto con on ve.codv = con.codv
            	join mecanico mec on mec.codm = con.codm

-- 11)  Recupere o nome do mecânico, o nome do cliente 
-- 		e a hora do conserto para as serviços realizados no dia 19/06/2014 (utilize join para realizar a junção).
select 	mec.nome as "Mecanico", 
		cl.nome as "Cliente", 
        con.hora
	from mecanico mec join conserto con on mec.codm = con.codm, 
    	 veiculo ve join cliente cl on ve.codc = cl.codc
    where 	con.data = '19/06/2014' 
    		and con.codv = ve.codv

-- 12)  Recupere o código e o nome dos setores que foram utilizados 
-- 		entre os dias 12/06/2014 e 14/06/2014 (utilize join para realizar a junção).
select distinct se.* 
	from setor se join mecanico mec on mec.cods = se.cods
			join conserto con on con.codm = mec.codm
    where 	con.data between '12/06/2014' and '14/06/2014'
