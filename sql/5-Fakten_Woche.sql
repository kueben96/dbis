-- Faktentabelle auf Wochenbasis für aggregierte Werte --
select * from KASSENBON_FAKTEN
select * from KASSENBON_FAKTEN_WOCHEN


select * from KASSENBON_FAKTEN_WOCHEN
	order by JAHR, WOCHE


		

create table KASSENBON_FAKTEN_WOCHEN (
	ABTEILUNGNR int not null references DIM_ABTEILUNG (ABTEILUNGNR),
	PRODUKTNR int not null references DIM_PRODUKT (PRODUKTNR),
	HANDELSMARKENR int not null references DIM_HANDELSMARKE (HANDELSMARKENR),
	JAHR int not null, 
	WOCHE int not null,
    TIMEID time not null, 
	FILIALENR char(3) not null references DIM_FILIALE (FILIALENR),
	Umsatz float not null,
	ABGABEMWST float not null,
	NETTO float not null,
	MENGE int not null,
	AKTIONSARTIKEL char(1) not null,
	RABATTIERT char(1) not null,
	constraint PK_FAKTEN_WOCHEN primary key (ABTEILUNGNR,PRODUKTNR,HANDELSMARKENR, JAHR, TIMEID, WOCHE,FILIALENR))



	INSERT INTO KASSENBON_FAKTEN_WOCHEN
	select
	FB.FACHBEREICHNR,
	P.PRODUKTNR,
	H.HANDELSMARKEID,
	DD.JAHR,
	DD.KW,
    convert(time(0),TIMEID),
	F.FILIALENR,
	sum(BP.Verkaufspreis),
	sum(BP.MWST),
	sum(BP.Verkaufspreis)-sum(BP.MWST),
	sum(BP.Verkaufsmenge),
	BP.AKTIONSWARE,
	BP.RABATTWARE
	from
	FACHBEREICH as FB 
	join Produkt as P
		on FB.FACHBEREICHNR= P.FachbereichNr 
	join
		HANDELSMARKE as H 
		on P.HandelsmarkeId = H.HANDELSMARKEID 
	join 
		Bonposition as BP
		on P.ProduktNr = BP.ProduktNr 
	join 
		Kassenbon as KB
		on BP.BonNr = KB.BonNr 
	join 
		FILIALE as F
		on KB.FilialeNr = F.FILIALENR 
	join 
		Dim_Date as DD 
		on DATEPART(yyyy,KB.Datum) = DD.JAHR and
			DATEPART(wk,KB.DATUM) = DD.KW
	join 
		Dim_Time as DT
		on convert(time(0),KB.Datum) = DT.TIMEID  
	group by 
	FB.FACHBEREICHNR,
	P.PRODUKTNR,
	H.HANDELSMARKEID,
	DD.JAHR,
	DD.KW,
    DT.TIMEID,
	F.FILIALENR,
	BP.AKTIONSWARE,
	BP.RABATTWARE