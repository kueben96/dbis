//package model;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.util.NoSuchElementException;
//
//
//
//public class DBImport {
//
//	/**
//	 * Save transferred data to database
//	 * 
//	 * @throws ClassNotFoundException
//	 */
//	public static int insert(FileLine fileLine, DBConnection dbConnection)
//			throws SQLException, ClassNotFoundException, NoSuchElementException {
//		PreparedStatement stmnt = null;
//		int res_rows = 0;
//		Connection connect = null;
//		try {
//			connect = dbConnection.getConnection();
//			// start transaction;
//			connect.setAutoCommit(false);
//
//			// Zuerst wird die Artikelnummer gefunden
//			long artikelNummer = getArtikelNummer(connect, dbZentrale
//					.getSchemaname(), fileLine.getArtikelName());
//			
//			// Als n�chstes wird versucht, die Zeit hinzuzuf�gen.
//			// Wenn Zeit nicht vorhanden ist, wird sie eingef�gt.
//			insertZeit(connect, dbZentrale.getSchemaname(), 
//					fileLine.getBestelldatum().getTime().getTime());
//			// Jetzt wird die Zeitnummer gefunden
//			long zeitNummer = getZeitNummer(connect, dbZentrale.getSchemaname(),
//					fileLine.getBestelldatum().getTime().getTime());
//
//			// Nur wenn die Eintr�ge gefunden wurden,
//			// wird INSERT durchgef�hrt.
//			if (artikelNummer > 0 && zeitNummer > 0) {
//				stmnt = connect
//						.prepareStatement("INSERT INTO "
//								+ dbZentrale.getSchemaname()
//								+ ".FACT_VERKAUF"
//								+ "(KUNDENNUMMER, ARTIKELNUMMER, ZEITNUMMER, MENGE, BETRAG)"
//								+ " VALUES (?,?,?,?,?)");
//				// Werte setzen:
//				stmnt.setLong(1, fileLine.getKundenNummer());
//				stmnt.setLong(2, artikelNummer);
//				stmnt.setLong(3, zeitNummer);
//				stmnt.setInt(4, fileLine.getMenge());
//				stmnt.setDouble(5, fileLine.getBetrag());
//
//				// Insert Ausf�hren
//				ergebnisZeilen = stmnt.executeUpdate();
//
//				// Transaktion beenden:
//				connect.commit();
//				connect.setAutoCommit(true);
//			} else 
//				throw new NoSuchElementException();
//		} catch (SQLException e) {
//			connect.rollback();
//			throw e;
//
//		}
//
//		finally {
//			if (stmnt != null)
//				try {
//					stmnt.close();
//				} catch (Exception ex) {
//				}
//			stmnt = null;
//
//		}
//
//		return ergebnisZeilen;
//	}
//
//	/**
//	 * Liefert die Nummer des Artikels zur�ck
//	 */
//	public static long getArtikelNummer(Connection connect, String schema,
//			String artikelName) {
//		PreparedStatement stmnt = null;
//		ResultSet rs = null;
//
//		try {
//
//			stmnt = connect.prepareStatement("SELECT ARTIKELNUMMER" + " FROM "
//					+ schema + ".DIM_ARTIKEL" + " WHERE UPPER(NAME)=?");
//
//			stmnt.setString(1, artikelName.toUpperCase());
//
//			rs = stmnt.executeQuery();
//			if (rs.next()) {
//				// Das Ergebnis wird sofort geliefert
//				return rs.getLong("ARTIKELNUMMER");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (rs != null)
//				try {
//					rs.close();
//				} catch (Exception ex) {
//				}
//			if (stmnt != null)
//				try {
//					stmnt.close();
//				} catch (Exception ex) {
//				}
//			stmnt = null;
//
//		}
//
//		// Wenn die Methode hier angekommen ist, dann hei�t es, dass
//		// keine Daten ermittelt werden konnten.
//		return 0;
//
//	}
//
//	/**
//	 * Liefert die Nummer des Tages zur�ck
//	 */
//	public static long getZeitNummer(Connection connect, String schema,
//			long datum) {
//		PreparedStatement stmnt = null;
//		ResultSet rs = null;
//
//		try {
//
//			stmnt = connect.prepareStatement("SELECT ZEITNUMMER" + " FROM "
//					+ schema + ".DIM_ZEIT" + " WHERE DATUM=?");
//
//			stmnt.setDate(1, Datumformatierung.getDate(datum));
//
//			rs = stmnt.executeQuery();
//			if (rs.next()) {
//				// Das Ergebnis wird sofort geliefert
//				return rs.getLong("ZEITNUMMER");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (rs != null)
//				try {
//					rs.close();
//				} catch (Exception ex) {
//				}
//			if (stmnt != null)
//				try {
//					stmnt.close();
//				} catch (Exception ex) {
//				}
//			stmnt = null;
//
//		}
//		return 0;
//	}
//
//	/**
//	 * Hinzuf�gt ein neues Datum
//	 */
//	public static void insertZeit(Connection connect, String schema,
//			long datum) {
//		PreparedStatement stmnt = null;
//
//		try {
//
//			stmnt = connect.prepareStatement("INSERT INTO "
//					+ schema + ".DIM_ZEIT (DATUM, MONAT, QUARTAL, JAHR)" 
//					+ " VALUES(?,MONTH(?),DATEPART(quarter , ?),YEAR(?))");
//
//			stmnt.setDate(1, Datumformatierung.getDate(datum));
//			stmnt.setDate(2, Datumformatierung.getDate(datum));
//			stmnt.setDate(3, Datumformatierung.getDate(datum));
//			stmnt.setDate(4, Datumformatierung.getDate(datum));
//
//			stmnt.executeUpdate();
//
//		} catch (Exception e) {
//			// Wenn hier Fehler auftritt, wird davon ausgegange, dass die
//			// Zeit bereits existiert.
////			e.printStackTrace();
//		} finally {
//			if (stmnt != null)
//				try {
//					stmnt.close();
//				} catch (Exception ex) {
//				}
//			stmnt = null;
//
//		}
//	}
//
//	/**
//	 * In dieser Methode wird gezeigt, wie Datumsfelder in einer SQL-Abfrage in
//	 * der WHERE-Klausel verwendet werden k�nnen.
//	 */
//	public static void whereBeispielMitDatum(DbConnectionZentrale dbZentrale,
//			String date) throws SQLException, ParseException,
//			ClassNotFoundException {
//
//		PreparedStatement stmnt = null;
//		ResultSet rs = null;
//		Connection connect = null;
//		try {
//			connect = dbZentrale.getConnection();
//			stmnt = connect.prepareStatement("SELECT * FROM "
//					+ dbZentrale.getSchemaname() + ".BESTELLUNG" + " WHERE"
//					+ " BESTELLDATUM=?" + " ORDER BY BESTELLUNGID");
//
//			// Datumumwandlung im Format 'YYYY-MM-dd' in long-Wert
//			long dateAsLong = Datumformatierung.getDdMMyyyy(date);
//			stmnt.setDate(1, new Date(dateAsLong));
//			rs = stmnt.executeQuery();
//			while (rs.next()) {
//				// Objekte erzeugen ...
//				System.out.println("Select lieferte diese Zeile.");
//			}
//		} finally {
//			if (rs != null)
//				try {
//					rs.close();
//				} catch (Exception ex) {
//				}
//			if (stmnt != null)
//				try {
//					stmnt.close();
//				} catch (Exception ex) {
//				}
//			stmnt = null;
//
//		}
//
//	}
//
//
//	
//}
