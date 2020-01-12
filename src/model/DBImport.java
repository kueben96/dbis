package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

// Klasse zum Importieren der CSV-Zeilen in die Datenbank

public class DBImport{

	/**
	 * Speichert Daten der ubergebenen Zeile in der Datenbank.
	 * 
	 * @throws ClassNotFoundException
	 */
	
	 // Schreiben der Daten in die Datenbank 
	
	public static int insert(FileLine fileLine, Connection con)
			throws SQLException, ClassNotFoundException, NoSuchElementException {
		PreparedStatement stmnt = null;


		int ergebnisZeilen = 0;


		if (fileLine.getFachbereichNr() != null) {
			stmnt = con
					.prepareStatement("INSERT INTO "
							+ "dbo.PRODUKT"
							+"(fachbereichNr,handelsmarkeID,bezeichnung,trockensortiment,warengruppe,preis)"
							+"VALUES (?,?,?,?,?,?)");


			// Werte setzen:
			stmnt.setString(1, fileLine.getFachbereichNr());
			stmnt.setInt(2,fileLine.getHandelsmarkeId());
			stmnt.setString(3, fileLine.getBezeichnung());
			stmnt.setString(4, fileLine.getTrockensortiment());
			stmnt.setString(5,fileLine.getWarengruppe());
			stmnt.setDouble(6,fileLine.getPreis());

			System.out.println(fileLine.toString());
			// Insert Ausfuhren
			ergebnisZeilen = stmnt.executeUpdate();

			// Transaktion beenden:
			con.commit();
			con.setAutoCommit(true);
		} else 
			throw new NoSuchElementException();
		
		if (stmnt != null)
			try {
				stmnt.close();
			} catch (Exception ex) {
			}
		stmnt = null;



		return ergebnisZeilen;
	}
	
	// Method for creating new Fachbereich

	@SuppressWarnings("null")
	public static void insertFB (String name, String fb_nr, Connection con) throws SQLException{
		
	
		int ergebnisZeilen = 0;


		String query = "INSERT INTO dbo.FACHBEREICH"
					+ "(FACHBEREICHNR, NAME)" 
					+"VALUES (?,?)" ;

		PreparedStatement statement = con.prepareStatement(query);
		
		// Fachbereich will be created with number and name
		
		statement.setString(1,fb_nr);
		statement.setString(2,name);
		ergebnisZeilen = statement.executeUpdate();
		
		con.commit();
		con.setAutoCommit(true);
		if (statement != null)
			try {
				System.out.println("Inserting FB");
				statement.close();
			} catch (Exception ex) {
			}
		statement = null;


	}
}