--- *** PROZEDUR mit Cursor für das Datum ** ---

--- *** Parameter zum Aufruf der Prozedur: Produktnummer und Zeitspanne ** --

-- *** drop procedure DW_REPORT

CREATE PROCEDURE DW_REPORT  (@produkt int ,@vonDatum date, @bisDatum date) AS
BEGIN
	BEGIN TRANSACTION
			if @vonDatum > @bisDatum
			print 'ERROR. Eingabe überprüfen'
		else
insert into DW_REPORT_TABLE
select PRODUKTNR, DATEID, sum(UMSATZ) as umsatz, sum(MENGE) as menge from KASSENBON_FAKTEN
	WHERE
	DATEID BETWEEN @vonDatum AND @bisDatum
	and Produktnr =@produkt
	group by  DATEID, PRODUKTNR

	declare @ProduktNr char(2), @von date, @bis date, @umsatz char(15), @menge char(4), @gesums char(10);
	set @von = @vonDatum
	set @bis = @bisDatum

	declare KB_Cursor CURSOR FOR
	SELECT Datum as Datum FROM DW_REPORT_TABLE
	OPEN KB_Cursor
	FETCH NEXT FROM KB_Cursor into @von
	print 'ProduktNr    Menge in St.    Datum                     Umsatz in €'
	while @@FETCH_STATUS = 0
	begin
		select @umsatz = cast(umsatz as char) from DW_Report_TABLE where Datum = @von
		select @menge= cast(menge as char) from DW_Report_TABLE where Datum = @von
		select @ProduktNr = cast(ProduktNr as char) from DW_Report_TABLE where Datum = @von
		print @ProduktNr +'         |      '+@menge+'            |      '+cast(@von as char)+'|    '+@umsatz+'|'
		print '-------------------------------------------------------------------------------------------|'
		FETCH NEXT FROM KB_Cursor into @von
	end
	select @gesums = cast(sum(umsatz) as char) from DW_REPORT_TABLE
	print'                                                        |  Gesamtumsatz (€) :   '+@gesums+'      |'
	close KB_Cursor
	deallocate KB_Cursor
	delete from DW_REPORT_TABLE
	commit transaction
END


-- ** für den Aufruf der Prozedur Beispielparameter auswählen ** --


declare  @produktnummer int, @von date, @bis date
execute DW_REPORT 15,'2019-02-01','2019-08-01'
