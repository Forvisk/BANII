
-- 1) Função para inserção e exclusão de um Setor.
create or replace function insertSetor( pCodS int, pNome varchar) returns void as
	$$
    insert into setor values ( pCodS, pNome);
	$$
language sql;
-- select insertSetor( 5, 'Estoque');
create or replace function insertSetor( pNome varchar) returns void as
	$$
    declare iCodS setor.cods%type;
    begin
    	select cods into iCods from setor order by cods desc;
    	insert into setor values ( iCodS+1, pNome);
        
	end;
    $$
language plpgsql;

    select insertSetor('Jo');
    select cods from setor order by cods desc;

-- 2) Função para inserção e exclusão de um Mecânico.

create or replace function delete_Mecanico( pcodm integer) returns void as
	$$
    declare
    begin
    	delete from mecanico where codm = pcodm;
	end;
    $$
language plpgsql; 

-- 3) Função para inserção e exclusão de uma Cliente.

create or replace function insere_exclui_cliente( popcao int,
    											 pcodc integer, 
                                                 pnome char varying(50), 
                                                 pidade int, 
                                                 pendereco char varying(500), 
                                                 pcidade char varying(500))
returns void as
$$
declare
begin
	if( popcao = 1) then -- insere
    	insert into cliente values ( pcodc, pnome, pidade, pendereco, pcidade);
    elseif( popcao = 2) then -- delete
    	delete from cliente where pcodc = codc;
    else
    	raise notice 'Acao desconhecida';
    end if;
end;
$$
language plpgsql;


-- 4) Função para inserção e exclusão de um Veículo.

create or replace function insere_exclui_veiculo( popcao int,
    											 pcodv integer, 
                                                 prenavan char(50),
                                                 pmodelo char varying(50),
                                                 pmarca char varying(50),
                                                 pano int, 
                                                 pkilometragem float,
                                                 pcodc integer )
                                                returns void as
$$
declare
begin
	if( popcao = 1) then -- insere
    	insert into veiculo values ( pcodv, prenavan, pmodelo, pmarca, pano, pkilometragem, pcodc);
    elseif( popcao = 2) then -- delete
    	delete from veiculo where pcodv = codv;
    else
    	raise notice 'Acao desconhecida';
    end if;
end;
$$
language plpgsql;


-- 5) Função para inserção e exclusão de um Conserto.

create or replace function insere_exclui_conserto( pacao int, pcodm integer, pcodv integer,
                                                  pdata date, phora time) returns int as
$$
declare
	vlinhas int default 0;
begin
	if (pacao = 1) then -- insert
       	insert into conserto values( pcodm, pcodv, pdata, phora);
    elseif (pacao = 2) then -- delete
       	delete from conserto where codm = pcodm and codv = pcodv and data = pdata;
    else
    	raise notice 'Acao desconhecida';
    end if;
    get diagnostics vlinhas = row_count;
    return vlinhas;
end;
$$
language plpgsql;

/*
select * from conserto;
select insere_exclui_conserto( 1, 3, 4, '12/02/2017', '16:15:20');
select insere_exclui_conserto( 1, 1, 3, '12/05/2017', '16:15:20');
select insere_exclui_conserto( 2, 3, 4, '12/03/2017', '16:15:20');
select insere_exclui_conserto( 55, 3, 4, '12/03/2017', '16:15:20');
*/

-- 6) Função para calcular a média geral de idade dos Mecânicos e Clientes.

create or replace function media_Idade() returns int as
	$$
    declare
    	vidadeC float default 0;
        vidadeM float default 0;
    begin
    	select avg(idade) into vidadeC from cliente;
    	select avg(idade) into  vidadeM from mecanico;
        return (vidadeC + vidadeM) /  2;
	end;
    $$
language plpgsql;

select media_Idade();

-- 7) Uma única função que permita fazer exclusão de um Setor, Mecânico, Cliente ou Veículo.

create or replace function exclusao( pacao int, pcod integer) returns int as
$$
declare
	vlinhas int default 0;
begin
	if( pacao = 1) then -- setor
    	delete from setor where cods = pcod;
    elseif (pacao = 2) then -- mecanico
    	delete from mecanico where codm = pcod;
    elseif (pacao = 3) then -- cliente
    	delete from cliente where codc = pcod;
    elseif (pacao = 4) then -- veiculo
    	delete from veiculo where codv = pcodv;
    else
    	raise notice 'Acao desconhecida';
    end if;
    get diagnostics vlinhas = row_count;
    return vlinhas;
end;
$$
language plpgsql;

select * from mecanico
select * from 
select exclusao(2, 1)

-- 8) Considerando que na tabela Cliente apenas codc é a chave primária, faça uma função que remova clientes com 
-- CPF repetido, deixando apenas um cadastro para cada CPF. Escolha o critério que preferir para definir qual cadastro 
-- será mantido: aquele com a menor idade, que possuir mais consertos agendados, etc. Para testar a função, 
-- não se esqueça de inserir na tabela alguns clientes com este problema.

create or replace function remove_cliente_cpf_repetido() returns int as
$$
declare
	vcliente cliente%rowtype;
    vcpf char(11);
    vkeepcodc integer default -1;
    vcount int default 0;
begin
	for vcpf in select cpf from cliente group by cpf having count(1) > 1 loop
    	vkeepcodc := -1;
    	for vcliente in select * from cliente where cpf = vcpf order by idade loop
        	if( vkeepcodc = -1) then
            	vkeepcodc := vcliente.codc;
            else
            	delete from cliente where cpf = vcpf and codc = vcliente.codc;
                vcount := vcount + 1;
            end if;
        end loop;
    end loop;
    return vcount;
end;
$$
language plpgsql;

-- select * from cliente;
-- select remove_cliente_cpf_repetido();

insert into cliente values
(8, '20000200000', 'An Ga', 20, 'América', 'Joinville'),
(9, '20000220000', 'Paulo G', 24, 'Saguaçú', 'Joinville'),
(10, '22000200000', 'Lucia G', 30, 'Vila Nova', 'Joinville'),
(11, '11000110000', 'Carlos G', 28, 'Trindade', 'Florianópolis'),
(12, '51000110000', 'Carlos G', 44, 'Centro', 'Florianópolis'),
(13, '71000111000', 'João G', 38, 'Praia Comprida', 'São José'),
(14, '10000110000', 'Luiz G', 42, 'Vila Nova', 'Joinville'),
(15, '20000200000', 'An GaH', 20, 'América', 'Joinville'),
(16, '20000220000', 'Paulo GH', 24, 'Saguaçú', 'Joinville'),
(17, '22000200000', 'Lucia GH', 30, 'Vila Nova', 'Joinville'),
(18, '11000110000', 'Carlos GH', 28, 'Trindade', 'Florianópolis'),
(19, '51000110000', 'Carlos GH', 44, 'Centro', 'Florianópolis'),
(20, '71000111000', 'João GH', 38, 'Praia Comprida', 'São José'),
(21, '10000110000', 'Luiz GH', 42, 'Vila Nova', 'Joinville');

update cliente set idade = idade + 5 where codc >= 8;


-- 9) Função para calcular se o CPF é válido*.

create or replace function valida_cpf( pcpf char(11)) returns boolean as
$$
declare
	vc char;
    vi int default 1;
    vsuma1 int default 0;
    vresto int;
    vdig char(1);
    vresult boolean default true;
begin
	-- Verifica primeiro digito
	while (vi < 10) loop	-- primeira verificação
        -- raise notice '1>>pcpf[%] é %', vi, cast( substr(pcpf, vi, 1) as integer);
        vsuma1 := vsuma1 + (11 - vi) * cast( substr(pcpf, vi, 1) as integer);
        vi := vi +1;
    end loop;
    vresto := vsuma1 % 11;
    if( vresto < 2) then
    	vdig = '0';
    else
    	vdig = cast( 11 - vresto as char(1));
    end if;
    raise notice '1>>suma: %, resto: %, digito: %', vsuma1, vresto, vdig;
    if(substr(pcpf, 10, 1) != vdig) then
    	vresult := false;
    end if;
    
    vi := 1;
    vsuma1 := 0;
    
    -- Verifica segundo digito
    while ( vi < 11) loop
    	-- raise notice '2>>pcpf[%] é %', vi, cast( substr(pcpf, vi, 1) as integer);
        vsuma1 := vsuma1 + (12 - vi) * cast( substr(pcpf, vi, 1) as integer);
        vi := vi +1;
    end loop;
    vresto := vsuma1 % 11;
    if( vresto < 2) then
    	vdig = '0';
    else
    	vdig = cast( 11 - vresto as char(1));
    end if;
    raise notice '1>>suma: %, resto: %, digito: %', vsuma1, vresto, vdig;
    if(substr(pcpf, 11, 1) != vdig) then
    	vresult := false;
    end if;
    
    return vresult;
end;
$$
language plpgsql;

-- select valida_cpf( cpf) from cliente -- where codc = 8;
-- insert into cliente values(8, '09502422929', 'Adriano Zanella Junior', 21, 'Nether', 'Nehter');

-- 10) Função que calcula a quantidade de horas extras de um mecânico em um mês de trabalho. O número de horas extras é 
-- 		calculado a partir das horas que excedam as 160 horas de trabalho mensais. O número de horas mensais trabalhadas 
-- 		é calculada a partir dos consertos realizados. Cada conserto tem a duração de 1 hora.

create or replace function calcula_hora_extra( pcodm integer, pmes int) returns int as
$$
declare
	-- vhoratrab time without time zone default '00:00';
    -- vhoramax time without time zone default '160:00';
    vhoratrab int default 0;
begin
	select count(1) into vhoratrab from conserto where codm = pcodm and cast(date_part('month', data) as integer) = pmes group by codm, date_part('month', data);
    if( vhoratrab > 160) then
    	return vhoratrab - 160;
    end if;
    return 0;
end;
$$
language plpgsql;

-- select codm, date_part('month', data), sum(hora) from conserto group by codm, date_part('month', data);
select calcula_hora_extra( codm, 6) from mecanico


/*
* Como calcular se o CPF é válido:
O CPF é composto por onze algarismos, onde os dois últimos são chamados de dígitos verificadores, ou seja, 
	os dois últimos dígitos são criados a partir dos nove primeiros. O cálculo é feito em duas etapas utilizando 
    o módulo de divisão 11. Para exemplificar melhor será usado um CPF hipotético, por exemplo, 222.333.444-XX.
O primeiro dígito é calculado com a distribuição dos dígitos colocando-se os valores 10,9,8,7,6,5,4,3,2 conforme 
	a representação abaixo:

2 2 2 3 3 3 4 4 4

10 9 8 7 6 5 4 3 2

Na seqüência multiplica-se os valores de cada coluna:

2    2    2    3    3    3    4    4    4

10  9    8    7    6    5    4    3    2

20 18  16  21  18  15  16  12   8

Em seguida efetua-se o somatório dos resultados (20+18+...+12+8), o resultado obtido (144) deve ser divido por 11. 
	Considere como quociente apenas o valor inteiro, o resto da divisão será responsável pelo cálculo do primeiro dígito 
    verificador. 144 dividido por 11 tem-se 13 de quociente e 1 de resto da divisão. Caso o resto da divisão seja 
    menor que 2, o primeiro dígito verificador se torna 0 (zero), caso contrário subtrai-se o valor obtido de 11. 
    Como o resto é 1 então o primeiro dígito verificador é 0 (222.333.444-0X).

Para o cálculo do segundo dígito será usado o primeiro dígito verificador já calculado. Monta-se uma tabela semelhante 
	a anterior só que desta vez é usado na segunda linha os valores 11,10,9,8,7,6,5,4,3,2, já que é incorporado mais 
    um algarismo para esse cálculo.

2    2   2  3  3  3  4  4  4  0

11 10  9  8  7  6  5  4  3  2

Na próxima etapa é feita como na situação do cálculo do primeiro dígito verificador, multiplica-se os valores de cada coluna:

2     2    2    3    3    3    4    4    4   0

11  10   9    8    7    6    5    4    3   2

22  20  18  24  21  18  20  16  12  0

Depois efetua-se o somatório dos resultados: 22+20+18+24+21+18+20+16+12+0=171.

Agora, pega-se esse valor e divide-se por 11. Considere novamente apenas o valor inteiro do quociente, e com o resto da divisão,
	no caso 6, usa-se para o cálculo do segundo dígito verificador, assim como na primeira parte. Se o valor do resto da divisão 
    for menor que 2, esse valor passa automaticamente a ser zero, caso contrário é necessário subtrair o valor obtido de 11 
    para se obter o dígito verificador, nesse caso 11-6=5. Portanto, chega-se ao final dos cálculos e descobre-se que os 
    dígitos verificadores do CPF hipotético são os números 0 e 5, portanto o CPF fica:

222.333.444-05
*/
