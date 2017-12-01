create or replace function "VALIDA_CNPJ"
(pcnpj in CHAR)
return NUMBER
is
	vdig CHAR(1) default '0';
	vi NUMBER default 1;
	vsoma NUMBER default 0;
	vreturn NUMBER default 1;
begin
	
	-- Verifica o primeiro digito
	
	while ( vi < 5) loop
		vsoma := vsoma + (6-vi) * TO_NUMBER( SUBSTR(pcnpj, vi, 1));
		vi := vi + 1;
	end loop;
	
	while ( vi < 13) loop
		vsoma := vsoma + (14-vi) * TO_NUMBER( SUBSTR(pcnpj, vi, 1));
		vi := vi + 1;
	end loop;
	
	vsoma := 11 - MOD( vsoma, 11);
	
	if vsoma < 10 then
		vdig := TO_CHAR( vsoma);
	end if;
	
	if vdig != SUBSTR( pcnpj, 13, 1) then
		vreturn := 0;
		return vreturn;
	end if;
	
	vsoma := 0;
	vi := 0;
	
	
	-- Verifica o segundo digito
	
	while ( vi < 6) loop
		vsoma := vsoma + (7-vi) * TO_NUMBER( SUBSTR(pcnpj, vi, 1));
		vi := vi + 1;
	end loop;
	
	while ( vi < 14) loop
		vsoma := vsoma + (15-vi) * TO_NUMBER( SUBSTR(pcnpj, vi, 1));
		vi := vi + 1;
	end loop;
	
	vsoma := 11 - MOD( vsoma, 11);
	
	if vsoma < 10 then
		vdig := TO_CHAR( vsoma);
	end if;
	
	if vdig != SUBSTR( pcnpj, 14, 1) then
		vreturn := 0;
		return vreturn;
	end if;
	
	return vreturn;
	
end;
