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
create or replace function insere_cod_setor() return trigger as
$$
declare
	proxCods int;
begin
end;
$$
language plpgsql

-- 4) Gatilho para impedir a inserção de um mecânico ou cliente com CPF inválido.

-- 5) Gatilho para impedir que um mecânico seja removido caso não exista outro mecânico com a mesma função.

-- 6) Gatilho que ao inserir, atualizar ou remover um mecânico, reflita as mesmas modificações na tabela de Cliente. Em caso de atualização, se o mecânico ainda não existir na tabela de Cliente, deve ser inserido.

-- 7) Gatilho para impedir que um conserto seja inserido na tabela Conserto se o mecânico já realizou mais de 20 horas extras no mês.