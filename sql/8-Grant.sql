-- Datentabellen/DB freigeben --

GRANT CONNECT to public


GRANT SELECT ON dbo.FILIALE to public
GRANT SELECT ON dbo.KASSENBON to public
GRANT SELECT ON dbo.BONPOSITION to public
GRANT SELECT ON dbo.PRODUKT to public
GRANT SELECT ON dbo.FACHBEREICH to public
GRANT SELECT ON dbo.HANDELSMARKE to public

GRANT SELECT ON dbo.DIM_TIME to public
GRANT SELECT ON dbo.DIM_DATE to public
GRANT SELECT ON dbo.DIM_ABTEILUNG to public
GRANT SELECT ON dbo.DIM_PRODUKT to public
GRANT SELECT ON dbo.DIM_FILIALE to public
GRANT SELECT ON dbo.DIM_HANDELSMARKE to public
GRANT SELECT ON dbo.KASSENBON_FAKTEN to public
GRANT SELECT ON dbo.KASSENBON_FAKTEN_WOCHEN to public
GRANT SELECT ON dbo.DW_REPORT_TABLE to public

GRANT EXECUTE ON dbo.DW_REPORT to public
