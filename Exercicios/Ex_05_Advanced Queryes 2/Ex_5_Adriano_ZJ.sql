-- 1)   Recupere todos os atributos dos professores do departamento Ciência da Computação.
select prof.* from Professor prof join Departamento dep on prof.codigoDepartamento = dep.codigo
	where dep.nome = 'CIÊNCIA DA COMPUTAÇÃO';

-- 2)   Recupere o CPF, nome e endereço dos professores do departamento Administração.
-- select * from Professor;
select prof.cpf, prof.nome, concat( prof.logradouro,', ',prof.bairro,', ',prof.cidade,'-',prof.estado) as Endereço
	from Professor prof join Departamento dep on prof.codigoDepartamento = dep.codigo
    where upper(dep.nome) = upper('administração');

-- 3)   Recupere as disciplinas dos cursos de Ciência da Computação e Física.
select disc.*
	from ( Disciplina disc join Curso cur on disc.codigoCurso = cur.codigo) 
    			join Departamento dep on cur.codigoDepartamento = dep.codigo
    where upper(dep.nome) in (upper('Ciência da computação'), upper('matemática e física'));

-- 4)	Recupere os alunos matriculados na disciplina Engenharia de Software do curso Ciência da Computação no semestre 2012-2. 
-- 		O resultado deve conter: ano, semestre, matrícula do aluno, nome do aluno, código da disciplina, nome da disciplina e 
-- 		o código do curso.
select mat.anoSemestre, mat.numeroSemestre, al.matricula, al.nome, disp.codigo, disp.nome, cur.codigo
	from 	((Aluno al join Matricula mat on al.matricula = matriculaAluno)
    			join Curso cur on mat.codigoCurso = cur.codigo)
                	join Disciplina disp on mat.codigoDisciplina = disp.codigo
	where upper(disp.nome) = upper('engenharia de software') and upper(cur.nome) = upper('ciência da computação')
    	and mat.anoSemestre = 2012 and mat.numeroSemestre = 2;

-- 5)   Recupere a quantidade de alunos matriculados na disciplina Banco de Dados II do curso Ciência da Computação
-- 		em cada semestre letivo.
select mat.anoSemestre, mat.numeroSemestre, count(1)
	from 	( Matricula mat join Disciplina disc on mat.codigoDisciplina = disc.codigo)
    			join Curso cur on mat.codigoCurso = cur.codigo
    where	upper(cur.nome) = upper('ciência da computação') and upper(disc.nome) = upper('banco de dados II')
    group by mat.anoSemestre, mat.numeroSemestre;
		
-- 6)   Recupere o CPF e nome dos professores que reprovaram mais de 2 alunos em um semestre 
-- 		(reprovação => nota < 7.0). Mostrar também a ano e semestre das reprovações.
select prof.cpf, prof.nome, mat.anoSemestre, mat.numeroSemestre
	from 	( Professor prof join Vinculo vin on prof.cpf = vin.cpfProfessor)
    			join Matricula mat on (mat.codigoCurso = vin.codigoCurso and mat.codigoDisciplina = vin.codigoDisciplina)
    where mat.notaFinal < 7
    group by prof.cpf, prof.nome , mat.anoSemestre, mat.numeroSemestre
	having count(1) > 2;
   	

-- 7)   Recupere o código da disciplina, nome da disciplina, código do curso, número de alunos matriculados na disciplina 
-- 		e a média das notas dos alunos matriculados na disciplina.
select disc.codigo, disc.nome, cur.codigo , count(1) as matriculas, ROUND(avg(mat.notaFinal), 2) as media
	from 	( Matricula mat join  Disciplina disc on mat.codigoDisciplina = disc.codigo)
    			join Curso cur on mat.codigoCurso = cur.codigo
	group by disc.codigo, disc.nome, cur.codigo;

-- 8)   Recupere os professores do curso de Ciência da Computação, em ordem alfabética. A lista deve conter todos os atributos 
-- 		de Professor e o código do departamento onde ele está vinculado.
select distinct disc.codigo as Cod_Departamento, prof.*
	from Professor prof join Departamento dep on prof.codigoDepartamento = dep.codigo
    		join Curso cur on cur.codigoDepartamento = dep.codigo
    			join Disciplina disc on cur.codigo = disc.codigoCurso				
	where upper(cur.nome) = upper('Ciência da computação')
    order by prof.nome;

-- 9)   Recupere os alunos que tiveram faltas. A lista deve conter nome do curso, nome da disciplina, nome do aluno e 
-- 		número de faltas. Ordenar a relação de forma decrescente, de acordo com número de faltas.

select al.nome, cur.nome, disc.nome, count(1) as Faltas
	from Aluno al join Matricula mat on al.matricula = mat.matriculaAluno
    		join Frequencia freq 
            		on freq.codigoCurso = mat.codigoCurso and freq.codigoDisciplina = mat.codigoDisciplina
                    	and freq.numeroTurma = mat.numeroTurma and freq.numeroSubTurma = mat.numeroSubTurma
                        and	freq.matriculaAluno = mat.matriculaAluno and freq.dataMatricula = mat.dataMatricula
            	join Curso cur on mat.codigoCurso = cur.codigo
                	join Disciplina disc on mat.codigoDisciplina = disc.codigo
    where freq.indPresenca = 'N'       
	group by al.nome, cur.nome, disc.nome, mat.dataMatricula
    order by faltas desc;
/*
select freq.codigoCurso, freq.codigoDisciplina, freq.matriculaAluno, count(1) as Faltas
	from Frequencia freq
    where freq.indPresenca = 'N'
    group by freq.codigoCurso, freq.codigoDisciplina, freq.matriculaAluno;
*/