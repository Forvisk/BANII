Create table DEVELOPER.teste (
    empno      NUMBER(5) PRIMARY KEY,
         ename      VARCHAR2(15) NOT NULL,
         job        VARCHAR2(10) DEFAULT 'String',
         mgr        NUMBER(5) DEFAULT 5,
         hiredate   DATE DEFAULT (sysdate)
    );

--	drop table DEVELOPER.teste;
    
    -- comentario 

    /* comentario em bloco */


create or replace function developer.minino( v1 int, v2 int) return int as
begin
	if v1 < v2 then
		return v1;
	else
		return v2;
	end if;
end;

-- drop function developer.minino;