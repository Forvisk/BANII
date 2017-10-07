Create table DEVELOPER.teste (
    empno      NUMBER(5) PRIMARY KEY,
         ename      VARCHAR2(15) NOT NULL,
         job        VARCHAR2(10),
         mgr        NUMBER(5),
         hiredate   DATE DEFAULT (sysdate)
    );
    
    drop table DEVELOPER.teste;
    
    