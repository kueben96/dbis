select * from PRODUKT;
select * from HANDELSMARKE;

-- *** Zuordnung der Handelsmarken *** --

update PRODUKT
set HANDELSMARKEID = 22
where BEZEICHNUNG LIKE '%Landliebe%';

update PRODUKT
set HANDELSMARKEID = 40
where BEZEICHNUNG LIKE '%Kellog%';

update PRODUKT
set HANDELSMARKEID = 42
where BEZEICHNUNG LIKE '%Goutess%';

update PRODUKT
set HANDELSMARKEID = 46
where BEZEICHNUNG LIKE '%Basic%';

update PRODUKT
set HANDELSMARKEID = 39
where BEZEICHNUNG LIKE '%Nestlé%';

update PRODUKT
set HANDELSMARKEID = 38
where BEZEICHNUNG LIKE '%Schär%';

update PRODUKT
set HANDELSMARKEID = 45
where BEZEICHNUNG LIKE '%Nick%';

update PRODUKT
set HANDELSMARKEID = 13
where BEZEICHNUNG LIKE '%Oetker%';

-- ** Update der Summen in Kassenbons ** --

UPDATE KASSENBON
SET SUMME = t.summe_einkauf
FROM KASSENBON AS kb
INNER JOIN
    (
        SELECT BONNR, SUM(VERKAUFSPREIS) summe_einkauf
        FROM BONPOSITION
        GROUP BY BONNR 
    ) t
    ON t.BONNR = kb.BONNR

-- ** Bargeld an Summe anpassen, um Deckung zu gewährleisten ** --

update KASSENBON
	set BARGELD = Round(SUMME, 0)

-- ** Bargeld standardmäßig erhöhen, um Differenz zu erzeugen ** --

update KASSENBON
	set BARGELD = BARGELD + 50

-- ** runden ** --

update KASSENBON
	set BARGELD = Round(Bargeld,-1)


