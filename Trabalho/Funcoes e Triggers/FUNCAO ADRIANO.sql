create or replace function retorna_ADRIANO(pCDRESERVA number, pOPERACAO varchar2, pRES_ST_CONFIRMA VARCHAR2)
return number
is
   retorna varchar2(100); -- variavel de retorno
   VOPERAFIM VARCHAR2(1);

begin

--CONFIRMA
if pOPERACAO = 'S' THEN
    UPDATE ADRRESERVA SET res_st_confirma = 'S', res_dt_dtpagamento = SYSDATE WHERE res_in_codreserva = pCDRESERVA;
END IF;

--CANCELA
if pOPERACAO = 'C' THEN
    UPDATE ADRRESERVA SET res_st_confirma = 'C' WHERE res_in_codreserva = pCDRESERVA;
END IF;

--ANULA
if pOPERACAO = 'A' THEN
    UPDATE ADRRESERVA SET res_st_confirma = 'A' WHERE res_in_codreserva = pCDRESERVA;
END IF;


SELECT res_st_confirma 
INTO VOPERAFIM
FROM ADRRESERVA R
WHERE R.res_in_codreserva = pCDRESERVA;


 if VOPERAFIM != pRES_ST_CONFIRMA then
  retorna := 1;
 else
  retorna := 0;
 end if;

   return (retorna);

end;