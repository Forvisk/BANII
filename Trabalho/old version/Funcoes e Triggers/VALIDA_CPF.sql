create or replace function "VALIDA_CPF"
(pcpf in VARCHAR2)
return NUMBER
is
	vc char;
    vi number default 1;
    vsuma1 number default 0;
    vresto number;
    vdig char(1);
    vresult number default 1;
begin
	-- verifica primeiro digito
	while (vi < 10) loop	-- primeira verificação
		vsuma1 := vsuma1 + (11 - vi) * TO_NUMBER( substr( pcpf, vi, 1));
		vi := vi +1;
	end loop;
	
	vresto := MOD(vsuma1, 11);
	
	if( vresto < 2) then
		vdig := '0';
	else
		-- vdig = cast( 11 - vresto as char(1));
		vdig := TO_CHAR( 11 - vresto);
	end if;
	
	if(substr(pcpf, 10, 1) != vdig) then
		vresult := 0;
		return vresult;
	end if;

	vi := 1;
	vsuma1 := 0;

	-- Verifica segundo digito
	while ( vi < 11) loop
		vsuma1 := vsuma1 + ( 12 - vi) * TO_NUMBER( substr( pcpf, vi, 1));
		vi := vi +1;
	end loop;
	
	vresto := MOD(vsuma1, 11);
	
	if( vresto < 2) then
		vdig := '0';
	else
		-- vdig = cast( 11 - vresto as char(1));
		vdig := TO_CHAR( 11 - vresto);
	end if;
	
	if(substr(pcpf, 11, 1) != vdig) then
		vresult := 0;
	end if;

	return vresult;
end;
