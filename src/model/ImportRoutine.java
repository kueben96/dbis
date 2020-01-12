package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ImportRoutine {

	//Schnittstelle zum Importieren von Daten 

		protected BufferedReader in; 
		protected final int DOPPELTER_PRIMARY_KEY_FEHLER = 2601;
		protected final int DOPPELTER_UNIQUE_SCHLUSSEL_FEHLER = 2627;
		protected String fileIn;
		protected static Connection con;
		private boolean istImportOk = true;

		public ImportRoutine(Connection con, String fileIn) {
			this.con = con;
			this.fileIn = fileIn;
		}

		/**
		 * Liest Datei aus und speichert sie in die Datenbank.
		 */
		public String startImport() throws IOException {
			String protocol = "";
			if (con == null) {
				System.out
						.println("Datenbankverbindung muss initialisiert werden!");
			}
			try {
				try {
					// Buffer fur Zeilen
				in = new BufferedReader(new FileReader(fileIn));
				}
				catch (Exception e) {e.printStackTrace();}
				
				String line = null;
				int lineCounter = 0;
				int objCounter = 0;

				protocol += "Datenimport wird gestartet...\n\n";
				
				// Abbruch des Exports bei falschen Zeilen
				
				try {
					line = in.readLine();
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				while (line != null && istImportOk) {
					lineCounter++;
					
					// Konmentare ausschliessen
					if (!line.trim().equals("") && !line.substring(0, 1).equals("#")) {
						try {
							// Zeile ueberspringen
							objCounter++;
							FileLine fileLine = FileLineParser.getExpFileLine(line);
							DBImport.insert(fileLine, con);

						} catch (SQLException e) {

							// Fehler ignorieren, wenn Zeile existiert
							
							if (e.getErrorCode() != DOPPELTER_PRIMARY_KEY_FEHLER
									&& e.getErrorCode() != DOPPELTER_UNIQUE_SCHLUSSEL_FEHLER) {
								// Der Import soll weiter laufen, wenn der Datensatz
								// existiert.
								// istImportOk = false;
								e.printStackTrace();
								protocol += "SQL-Exception in der Zeile: "
										+ lineCounter + "\n";
								protocol += line + "\n\n";
								JOptionPane
										.showMessageDialog(
												new JFrame(),
												"Daten aus der Zeile "
														+ lineCounter
														+ " verursachten beim Ausfuhren der SQL-Anweisung Fehler."
														+ "\nBitte überprufen Sie die SQL-Anweisung oder zu importierenden Daten!",
												"Datenimport",
												JOptionPane.ERROR_MESSAGE);
							} else {
								protocol += "Datensatz in der Zeile " + lineCounter + " ist bereits vorhanden:\n";
								protocol += line + "\n\n";
							}
						} catch (ClassNotFoundException e) {
							// istImportOk = false;
							JOptionPane
									.showMessageDialog(
											new JFrame(),
											"Keine Verbindung zur Datenbank!"
													+ "\nBitte uberprufen Sie Ihre Angaben!",
											"Datenimport",
											JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
							protocol += "Keine Verbindung zur Datenbank!\n\n";
							break;
						} catch (NoSuchElementException e) {
							protocol += "Parse-Exception in der Zeile: "
									+ lineCounter + "\n";
							protocol += line + "\n\n";
							JOptionPane
									.showMessageDialog(
											new JFrame(),
											"Daten aus der Zeile "
													+ lineCounter
													+ " konnten nicht importiert werden."
													+ "\nBitte uberprufen Sie die zu importierenden Daten!",
											"Datenimport",
											JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
							// break;
						}
					}
					try {
						line = in.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				protocol += "Anzahl der verarbeiteten Datensatze: " + objCounter + "\n\n";
				protocol += "Datenimport ist abgeschlossen.\n\n";
			} finally {
				if (in != null)
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			return protocol;
		}
		
		// Neuer Fachbereich für Gewurze erstellen, bevor der Import vollzogen wird
		
		public static void createNewFB(String name, String fb_nr) throws SQLException {

			DBImport.insertFB(name, fb_nr, con);
		
		}

		/**
		 * Liefert true, wenn der Import ohne Fehler durchgef�hrt wurde.
		 */
		public boolean isImportOk() {
			return istImportOk;
		}
	
}
