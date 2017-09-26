-- 1) Gatilho para impedir a inserção ou atualização de Clientes com o mesmo CPF.
/*
create or replace function check_cpf_cliente() returns trigger as 
$$
declare
begin
	if (TG_OP = 'INSERT') then
		if (select 1 from cliente cli where NEW.cpf = cli.cpf) then
        	RAISE EXCEPTION 'Não pode haver cpf repetidos';
        end if;
    else
    	if (select 1 from cliente cli where NEW.cpf = cli.cpf and NEW.codc != cli.codc) then
        	RAISE EXCEPTION 'Não pode haver cpf repetidos';
        end if;
    end if;
    return new;
end;
$$
language plpgsql;

create trigger check_cpf_cliente before insert or update on cliente
for each row execute procedure check_cpf_cliente()

select * from cliente

insert into cliente values (8, '10000110000', 'Batata', 85, '', '')

update cliente set CPF = '10000110000' where codc = 2 	-- não permite
update cliente set CPF = '10000110000' where codc = 7	-- permite
update cliente set nome = 'Luiza' where codc = 7 -- não permite --agora permite
update cliente set idade = 36 where codc = 3
*/
-- 2) Gatilho para impedir a inserção ou atualização de Mecânicos com idade menor que 20 anos.
/*
create or replace function idade_minima_mecanico() returns trigger as
$$
declare
begin
	if (new.idade < 20) then
    	RAISE EXCEPTION 'Idade minima de contratação é 20 anos!';
    end if;
    return new;
end;
$$
language plpgsql;

create trigger idade_minima_mecanico before insert or update on mecanico
for each row execute procedure idade_minima_mecanico();

select * from mecanico

insert into mecanico values (8, '13434344142', 'Loli', 12, 'Somewhere', 'Somewhere big', 'kawai', 1);	-- not legal
insert into mecanico values (7, '12131511232', 'Legal Loli', 23, 'Dreamland', 'Somewhere big', 'kawai2', 1); -- legal
insert into mecanico values (6, '12131512342', 'Sua mãe', 45, 'Somewhere', 'Somewhere big', 'Sua mãe', 2); -- permite

update mecanico set idade = 12 where codm = 7 -- not legal
*/
-- 3) Gatilho para atribuir um cods (sequencial) para um novo setor inserido.
/*
create or replace function insere_cod_setor() returns trigger as
$$
declare
	vproxCods int;
begin    
    new.cods = nextval('setor_cods_seq');
    return new;
end;
$$
language plpgsql

create trigger insere_cod_setor before insert on setor for each row execute procedure insere_cod_setor();
insert into setor values (null, 'Gambiarra'),
							(null,'Joça');
insert into setor (nome) values ('KKK');
insert into setor values (5, 'Groud'); -- sobrescreve valor de cods
select * from setor
*/
-- pode ser feito criando uma sequencia
-- create sequence setor_cods_seq start 5

-- 4) Gatilho para impedir a inserção de um mecânico ou cliente com CPF inválido.
/*
create or replace function valida_cpf() returns trigger as
$$
declare
	pcpf char(11);
	vc char;
    vi int default 1;
    vsuma1 int default 0;
    vresto int;
    vdig char(1);
    -- vresult boolean default true;
begin
	pcpf := new.cpf;
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
    	return null;
    	-- vresult := false;
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
    	return null;
    	-- vresult := false;
    end if;
    return new;
    -- return vresult;
end;
$$
language plpgsql;

create trigger valida_cpf before insert on cliente for each row execute procedure valida_cpf();
create trigger valida_cpf before insert on mecanico for each row execute procedure valida_cpf(); 
insert into cliente values (8, '09502422929', 'Adriano Zanella Junior', 21, 'Battleship', 'Nether');
insert into cliente (codc, cpf, nome) values (9, '12345678910', 'Joscleiton');
insert into mecanico values (6, '05260001452', 'Edinelzon',56, 'Somewhere', 'baixada', 2);
select * from cliente
select * from mecanico
*/
-- 5) Gatilho para impedir que um mecânico seja removido caso não exista outro mecânico com a mesma função.
/*
create or replace function impede_delecao_mecanico() returns trigger as
$$
declare
begin
    if (select count(1) from mecanico where funcao = old.funcao group by funcao) < 2 then
        RAISE EXCEPTION 'Unico mecanico na funcao';
    end if;
    return old;
end;
$$
language plpgsql;

create trigger impede_delecao_mecanico before delete on mecanico for each row execute procedure impede_delecao_mecanico();
select * from mecanico 
delete from mecanico where codm = 6
/*
insert into mecanico (codm, cods, nome) values (6, 1, 'Joelson');
insert into mecanico values (4, '11000110000', 'Carlos', 28, 'Trindade', 'Florianópolis', 'estofado', null);
insert into mecanico (codm, cods, nome) values (8, 3, 'jocleison');
insert into mecanico (codm, cods, nome) values (9, 2, 'joilson');
*//*
select codm, funcao from mecanico
select count(1), funcao from mecanico group by funcao
*/
-- 6) Gatilho que ao inserir, atualizar ou remover um mecânico, reflita as mesmas modificações na tabela de Cliente. 
-- Em caso de atualização, se o mecânico ainda não existir na tabela de Cliente, deve ser inserido.
-- criamos um auto incremento para a tabela cliente
/*
create sequence cliente_codc_seq start 8;
-- select max(codc) from cliente
create or replace function insere_cod_cliente() returns trigger as
$$
declare
begin    
    new.codc = nextval('setor_cods_seq');
    return new;
end;
$$
language plpgsql;

create trigger insere_cod_cliente before insert on cliente for each row execute procedure insere_cod_cliente();
*//*
create or replace function espelha_mecanico_to_cliente() returns trigger as
$$
declare
begin
	if TG_OP = 'INSERT' then
    	if( select 1 from cliente where new.cpf = cpf) then
        	update cliente set idade = new.idade, endereco = new.endereco, cidade = new.cidade where cpf = new.cpf;
        else
        	insert into cliente (nome, cpf, idade, cidade, endereco) values 
            						(new.nome, new.cpf, new.idade, new.endereco, new.cidade);
        end if;
        return new;
    end if;
    if TG_OP = 'UPDATE' then
    	if( new.cpf = old.cpf) then
            if( select 1 from cliente where new.cpf = cpf) then
                update cliente set nome = new.nome, idade = new.idade, endereco = new.endereco,
                					cidade = new.cidade where cpf = new.cpf;
            else
                insert into cliente (nome, cpf, idade, cidade, endereco) values 
                	(new.nome, new.cpf, new.idade, new.endereco, new.cidade);
            end if;
        else
        	if( select 1 from cliente where old.cpf = cpf) then
            	if( select 1 from cliente where new.cpf = cpf) then
                	delete from cliente where cpf = new.cpf;
                    insert into cliente (nome, cpf, idade, cidade, endereco) values 
                		(new.nome, new.cpf, new.idade, new.endereco, new.cidade);
                else
                	update cliente set cpf = new.cpf, nome = new.nome, idade = new.idade, endereco = new.endereco,
                					cidade = new.cidade where cpf = new.cpf;
                end if;
            else
            	if( select 1 from cliente where new.cpf = cpf) then
                	delete from cliente where cpf = new.cpf;
                    insert into cliente (nome, cpf, idade, cidade, endereco) values 
                		(new.nome, new.cpf, new.idade, new.endereco, new.cidade);
                else
                	insert into cliente (nome, cpf, idade, cidade, endereco) values 
                		(new.nome, new.cpf, new.idade, new.endereco, new.cidade);
                end if;
            end if;
        end if;
        return old;
    end if;
    if TG_OP = 'DELETE' then
    	delete from cliente where cpf = old.cpf;
        return old;
    end if;
end;
$$
language plpgsql;

create trigger espelha_mecanico_to_cliente after insert or delete or update on mecanico 
	for each row execute procedure espelha_mecanico_to_cliente();
*//*
insert into mecanico values (9, '12345678910', 'Josilcieton', 56, '', '', 'encanador', 4);
update mecanico set cpf = '10000110000' where codm = 9;
update mecanico set nome = 'Garibaldo' where codm = 5;
select max(codm) from mecanico;
select * from mecanico;
select * from cliente;
*/

-- 7) Gatilho para impedir que um conserto seja inserido na tabela Conserto se o mecânico já realizou mais de 20 horas 
-- 	extras no mês.
/*
create or replace function bloqueia_hora_extra() returns trigger as
$$
declare
	vquanthr int default 0;
begin
	select count(1) into vquanthr from conserto 
        where codm = new.codm and date_part('month', data) = date_part('month', new.data) 
        group by codm, date_part('month', data);
    If (vquanthr > 5) then
        raise EXCEPTION 'Limite de horas atingido';
    else
    	return new;
    end if;
end;
$$
language plpgsql;

create trigger bloqueia_hora_extra before insert on conserto 
	for each row execute procedure bloqueia_hora_extra();
select * from conserto
select codm, count(1), date_part('month', data) from conserto group by codm, date_part('month', data)
insert into conserto values(4,2,'01/06/2017', '15:14')
*/

-- Extra -- 26/09/2017
-- Gatilho para impedir que mais de um conserto seja agendado no mesmo setor na mesma hora.

create or replace function bloqueia_conserto_mesma_hora() returns trigger as
$$
declare
	vcods int;
begin
	select cods into vcods from mecanico where new.codm = codm;
    if( select 1 from conserto join mecanico using(codm) 
    		where data = new.data and date_part('hour', hora) = date_part('hour',new.hora) and (cods = vcods or codm = new.codm) group by 1) then
    	raise exception 'Setor já alocado para as %h', date_part('hour', new.hora);
    else
    	return new;
    end if;
end;
$$
language plpgsql;

create trigger bloqueia_conserto_mesma_hora before insert on conserto
	for each row execute procedure bloqueia_conserto_mesma_hora();

select codm, cods from mecanico
select cods, con.* from conserto con join mecanico using(codm)
select 1 from conserto join mecanico using(codm) 
	where data = '14/06/2014' and date_part('hour', hora) = '17' and cods = 2

insert into conserto values (4, 3, '20/06/2014', '13:00')
insert into conserto values (4, 2, '20/06/2014', '13:00')

delete from conserto where codm = 4 and data != '20/06/2014'
