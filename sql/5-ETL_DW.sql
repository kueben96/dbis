-- Dim-Tabellen mit Werten aus der ODB befüllen -- 
--DIM_Abteilung--
insert into DIM_Abteilung(AbteilungNr,Name)
select FB.FachbereichNr,FB.Name
from Fachbereich as FB

--DIM_Produkt--
insert into DIM_PRODUKT(PRODUKTNR,BEZEICHNUNG,TROCKENSORTIMENT,WARENGRUPPE)
select P.ProduktNr, P.Bezeichnung, P.Trockensortiment, substring(P.Warengruppe,3,1)
from Produkt as P 

--DIM_Filiale--
insert into DIM_FILIALE(FILIALENR, UST_ID_NR,STRASSE,STADT,PLZ)
select F.FILIALENR,F.UST_ID_NR, F.STRASSE,F.STADT, F.PLZ
from FILIALE as F

--DIM_Handelsmarke--
insert into DIM_HANDELSMARKE(HANDELSMARKENR,NAME, LAND)
select HM.HANDELSMARKEID, HM.NAME, HM.LAND
from HANDELSMARKE AS HM


--DIM_Date Parameter für die Berechnung der Attribute--
declare @vonDatum date,
		@bisDatum date,
		@Jahr integer, -- Attribut "Jahr"
		@Monat integer, -- Attribut "Monat"
		@Tag integer, -- Attribut "Tag"
		@Quartal integer, -- Attribut "Quartal"
		@KW integer -- Attribut "Kalenderwoche"

	select @vonDatum=min(CONVERT(date,datum)) from KASSENBON
	select @bisDatum=max(CONVERT(date,datum)) from KASSENBON

-- Zeitspanne: Schleife, bis Enddatum Erreicht wurde -- 

	while @vonDatum <= @bisDatum
		begin
			set @Jahr		=year(@vonDatum)
			set @Monat		=month(@vonDatum)
			set @Tag		=day(@vonDatum)
			set @Quartal	=datepart(Quarter,@vonDatum)
			set @KW			=datepart(WK,@vonDatum)

			insert into DIM_DATE(DATEID, JAHR, MONAT, TAG, QUARTAL, KW)
			values (@vonDatum, @jahr, @monat, @tag, @Quartal, @KW)

		set @vonDatum = DATEADD(DD, 1,@vonDatum)
	end

-- DIM_Time aud den Tag bezogen -- 
declare 
	@vonZeit time,
	@bisZeit time,
	@stunde int,
	@minute int,
	@tageszeit int,
	@flag int
    --Tag --
	set @vonZeit = '00:00'
	set @bisZeit = '23:59'
	set @flag	= 0 --unterbricht die Schleife -- 

while @flag = 0
	begin
    -- Tageszeiten bestimmen (1,2,3,4)
    -- Schleife, bis Tagesende 
		set @stunde = DATEPART(HOUR, @vonZeit)
		set @minute = DATEPART(MINUTE, @vonZeit)

		if @stunde > 5 and @stunde < 12
			set @tageszeit = 1
		else if @stunde > 11 and @stunde < 18
			set @tageszeit = 2
		else if @stunde > 17 and @stunde <= 24
			set @tageszeit = 3
		else
			set @tageszeit = 4

		insert into DIM_TIME(TIMEID,STUNDE,MINUTE,TAGESZEIT)
			values (@vonZeit, @stunde, @minute, @tageszeit)

			if @vonZeit = @bisZeit 
				set @flag = 1

			set @vonZeit = DATEADD(MINUTE, 1, @vonZeit)
	end

-- Kassenbon_Fakten mit Zeiten verbinden --

	INSERT INTO KASSENBON_FAKTEN
	select fbr.FACHBEREICHNR, P.PRODUKTNR, hm.HANDELSMARKEID,
	convert(date,DATEID), convert(time(0),TIMEID),
    F.FILIALENR, sum(BP.Verkaufspreis), sum(BP.MWST),
	sum(BP.Verkaufspreis)  - sum(BP.MWST),
	sum(BP.Verkaufsmenge),
	bp.AKTIONSWARE,
	bp.RABATTWARE

	from
	FACHBEREICH as fbr 
	join Produkt as P
		on fbr.FACHBEREICHNR= P.FachbereichNr 
	join
		HANDELSMARKE as hm
		on P.HandelsmarkeId = hm.HANDELSMARKEID 
	join 
		Bonposition as bp
		on P.ProduktNr = bp.ProduktNr 
	join 
		Kassenbon as KB
		on bp.BonNr = KB.BonNr 
	join 
		FILIALE as F
		on KB.FilialeNr = F.FILIALENR 
	join 
		Dim_Date as DD 
		on convert(date,KB.Datum) = DD.DateID 
	join 
		Dim_Time as DT
		on convert(time(0),KB.Datum) = DT.TimeID 
	group by 
	fbr.FACHBEREICHNR, P.PRODUKTNR, hm.HANDELSMARKEID,
	DD.DATEID, DT.TIMEID, F.FILIALENR,
	BP.AKTIONSWARE, BP.RABATTWARE,BP.BONNR
