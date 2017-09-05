
-- 1) Função para inserção e exclusão de um Setor.
/*create or replace function insertSetor( pCodS int, pNome varchar) returns void as
	$$
    insert into setor values ( pCodS, pNome);
	$$
language sql;*/
-- select insertSetor( 5, 'Estoque');
/*create or replace function insertSetor( pNome varchar) returns void as
	$$
    declare iCodS setor.cods%type;
    begin
    	select cods into iCods from setor order by cods desc;
    	insert into setor values ( iCodS+1, pNome);
        
	end;
    $$
language plpgsql; */
-- select insertSetor('Jo')
--  select cods from setor order by cods desc
/*
2)      Função para inserção e exclusão de um Mecânico.

3)      Função para inserção e exclusão de uma Cliente.

4)      Função para inserção e exclusão de um Veículo.

5)      Função para inserção e exclusão de um Conserto.

6)      Função para calcular a média geral de idade dos Mecânicos e Clientes.

7)      Uma única função que permita fazer exclusão de um Setor, Mecânico, Cliente ou Veículo.

8)      Considerando que na tabela Cliente apenas codc é a chave primária, faça uma função que remova clientes com CPF repetido, deixando apenas um cadastro para cada CPF. Escolha o critério que preferir para definir qual cadastro será mantido: aquele com a menor idade, que possuir mais consertos agendados, etc. Para testar a função, não se esqueça de inserir na tabela alguns clientes com este problema.

9)   Função para calcular se o CPF é válido*.

10)   Função que calcula a quantidade de horas extras de um mecânico em um mês de trabalho. O número de horas extras é calculado a partir das horas que excedam as 160 horas de trabalho mensais. O número de horas mensais trabalhadas é calculada a partir dos consertos realizados. Cada conserto tem a duração de 1 hora.

* Como calcular se o CPF é válido:

O CPF é composto por onze algarismos, onde os dois últimos são chamados de dígitos verificadores, ou seja, os dois últimos dígitos são criados a partir dos nove primeiros. O cálculo é feito em duas etapas utilizando o módulo de divisão 11. Para exemplificar melhor será usado um CPF hipotético, por exemplo, 222.333.444-XX.

O primeiro dígito é calculado com a distribuição dos dígitos colocando-se os valores 10,9,8,7,6,5,4,3,2 conforme a representação abaixo:

2 2 2 3 3 3 4 4 4

10 9 8 7 6 5 4 3 2

Na seqüência multiplica-se os valores de cada coluna:

2    2    2    3    3    3    4    4    4

10  9    8    7    6    5    4    3    2

20 18  16  21  18  15  16  12   8

Em seguida efetua-se o somatório dos resultados (20+18+...+12+8), o resultado obtido (144) deve ser divido por 11. Considere como quociente apenas o valor inteiro, o resto da divisão será responsável pelo cálculo do primeiro dígito verificador. 144 dividido por 11 tem-se 13 de quociente e 1 de resto da divisão. Caso o resto da divisão seja menor que 2, o primeiro dígito verificador se torna 0 (zero), caso contrário subtrai-se o valor obtido de 11. Como o resto é 1 então o primeiro dígito verificador é 0 (222.333.444-0X).

Para o cálculo do segundo dígito será usado o primeiro dígito verificador já calculado. Monta-se uma tabela semelhante a anterior só que desta vez é usado na segunda linha os valores 11,10,9,8,7,6,5,4,3,2, já que é incorporado mais um algarismo para esse cálculo.

2    2   2  3  3  3  4  4  4  0

11 10  9  8  7  6  5  4  3  2

Na próxima etapa é feita como na situação do cálculo do primeiro dígito verificador, multiplica-se os valores de cada coluna:

2     2    2    3    3    3    4    4    4   0

11  10   9    8    7    6    5    4    3   2

22  20  18  24  21  18  20  16  12  0

Depois efetua-se o somatório dos resultados: 22+20+18+24+21+18+20+16+12+0=171.

Agora, pega-se esse valor e divide-se por 11. Considere novamente apenas o valor inteiro do quociente, e com o resto da divisão, no caso 6, usa-se para o cálculo do segundo dígito verificador, assim como na primeira parte. Se o valor do resto da divisão for menor que 2, esse valor passa automaticamente a ser zero, caso contrário é necessário subtrair o valor obtido de 11 para se obter o dígito verificador, nesse caso 11-6=5. Portanto, chega-se ao final dos cálculos e descobre-se que os dígitos verificadores do CPF hipotético são os números 0 e 5, portanto o CPF fica:

222.333.444-05
*/