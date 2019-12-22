package model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class FileLineParser {
	
	private static final int SPALTENANZAHL = 4;

	/**
	 * Erzeugt aus der �bergebenen Zeile ein Object, welches die ausgelesene
	 * Zeile repr�sentiert. 
	 * 
	 * KUNDENNUMMER; HERSTELLER; PRODUKTNAME; VERPACKUNGSEINHEIT; PRODUKTKATEGORIECODE;
	 * VERKAUFSDATUM; MENGE; RECHNUNGSBETRAG
	 * @throws SQLException 
	 */
	protected static FileLine getExpFileLine(String line)
			throws NoSuchElementException, SQLException {

		String	bezeichnung = getValue(line, 1).trim();
		String	trockensortiment = getValue(line, 2).trim();
		String	warengruppe = getValue(line, 3).trim();
		String	preis = getValue(line, 4).trim();
		String fb_id;
		
		//Versuch: Handelsmarke und Fachbereich integrieren
		//Hardcoded Idee für Fachbereich: Methode getFachbereich wie getWarengruppe --> Fachbereichnummer ist der Foreign Key
		
		// Gewürze sind nicht als Fachbereich vorhanden
		
		//Schwieriger: Handelsmarke -> Bezeichnung nochmal Tokenizen (1. Stelle oder mit Regex wegen Dr Oetker und Kellogs)
		//Muss auch ein insert für Handelsmarke geben, wenn nicht vorhanden
		//Bisher Vorhanden: Dr. Oetker (zum Glück)
		// Neue Handelsmarken: Weber, Kellog's, Nestle, Basic, Schär. Nick
		// Regex für Kellogs und Doktor Oetker:  (\w*'\w|Dr\.\s\w*)
		
		
		
		if(bezeichnung.length() >500) {
			bezeichnung.substring(0, 500);
		}
		if(trockensortiment.length() >1) {
			trockensortiment.substring(0, 1);
		}
		
		
		
		warengruppe = FileLineParser.getWarengruppe(warengruppe);
		fb_id = FileLineParser.getFachbereichsNummer(warengruppe);
		
		if(warengruppe.length() >3) {
			warengruppe.substring(0,3);
		}
		FileLine fileLineObj = null;
		try {
			
			fileLineObj = new FileLine(fb_id, 9,bezeichnung,trockensortiment,warengruppe,Float.parseFloat(preis));
		} catch (Exception e) {
			System.out
					.println("Parsefehler: Bitte �berpr�fen Sie das Format in dieser Zeile:"
							+ line);
			throw new NoSuchElementException();
		}
		return fileLineObj;
	}

	/**
	 * Liefert einen Wert, f�r den entsprechenden Index
	 */
	
	private static String getValue(String line, int keyIndex)
			throws NoSuchElementException {
		String value = null;
		boolean isDub = false;
		
		StringTokenizer lineValues = new StringTokenizer(line, ";");
		int tokens = lineValues.countTokens();

		// �berpr�fung der Spaltenanzahl
		if (tokens < SPALTENANZAHL) {
			// Falls mehr Tokens in Zeile als notwendige Spaltenzahl, Throw NoSuchElementException
			System.out.println("Falsches Format von Testdaten!"
					+ "Zu wenige Parameter gefunden: "
					+ tokens + " von " + SPALTENANZAHL + "Spalten.");
			throw new NoSuchElementException("Falsches Format der Daten!\n");
		} else if (tokens > SPALTENANZAHL) {
			System.out.println("Falsches Format von Testdaten!"
					+ " Zu viele Parameter gefunden: " + tokens + " von "
					+ SPALTENANZAHL + " Spalten.");
			throw new NoSuchElementException("Falsches Format der Daten!\n");
		}

		for (int i = 0; i < keyIndex; i++) {
			value = lineValues.nextToken();
		}
		
		// Check if Value has dublicate Contents
		
		isDub = isDub(value);
		if(isDub == true) {
			System.out.println("Falsches Format von Testdaten!"
					+ "Redundante Daten");
			throw new NoSuchElementException("Falsches Format der Daten!\n");		
		}
		
		return value;
	}
	
	// Weitere Probleme: 
	// Bezeichnung min doppelt oder zu lang  z.B. weber fisch 
	// Warengruppe wird nicht übertragen
	
	public static String getWarengruppe(String s_warengruppe) {
		
		if (s_warengruppe == null)
			return null;
		
		String warengruppe_id = null;
		
		if (s_warengruppe.equalsIgnoreCase("Milch"))
			warengruppe_id = "002";
		else if (s_warengruppe.equalsIgnoreCase("Müsli & Cerealien"))
			warengruppe_id = "003";
		else if (s_warengruppe.equalsIgnoreCase("Saucen"))
			warengruppe_id = "004";
		else if (s_warengruppe.equalsIgnoreCase("Gewürze"))
			warengruppe_id = "005";
		else
			warengruppe_id = "006";
		
		return warengruppe_id;
	}
	
	public static String getFachbereichsNummer(String warengruppe_id) throws SQLException {
		
		if (warengruppe_id==null) {
			return null;
		}
		String fb_id= null;
		if (warengruppe_id=="002") {
			fb_id= "1014";
		}else if(warengruppe_id == "003") {
			fb_id= "1023";
		}else if(warengruppe_id == "004") {
			fb_id= "1015";
		//hardcoding von FB für Gewürze
		}else if(warengruppe_id == "005") {
			ImportRoutine.createNewFB("Gewürze", "1039");
			fb_id="1039";
		}
		
		return fb_id;
	}
	
	// Methode zum Prüfen von Redundanten Einträgen
	
	public static boolean isDub(String bezeichnung) {
		
		String[] words = bezeichnung.split("[\\s+]");
		
		Map<String, Integer> occurrences = new HashMap<String, Integer>();

		boolean isdub =false;
		Integer oldCount=0;
		for ( String word : words ) {
			oldCount = occurrences.get(word);
			if ( oldCount == null ) {
				oldCount = 0;
			}
			occurrences.put(word, oldCount + 1);
			if(oldCount>2) isdub = true;
			System.out.println("oldcount : "+ oldCount+ "word"+ word + "isdub" + isdub);
		}
		System.out.println("IS DUB? :"+ isdub);
		return isdub;
	}

}
