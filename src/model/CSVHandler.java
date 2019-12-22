package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class CSVHandler {

	private static final Logger LOG = Logger.getGlobal();

	// needs opencsv private CSVReader reader; also possible but not used yet


	private int handelsmarke;
	private String bezeichnung;
	private String trockensort;
	private String Warengruppe;
	private double preis;

	private StringTokenizer stoken;

	//private String csvFilename;

	private String line = "";
	private String cvsSplitBy = ";";

	private String anystring = "Dr. Oetker am Stizz";


	public CSVHandler(String csvFilename) {




		try (BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {

			while ((line = br.readLine()) != null) {


				// use comma as separator
				String[] produkt = line.split(cvsSplitBy);

				for (int i = 0; i < produkt.length; i++) {
					System.out.println("Got into foor loop");
					stoken = new StringTokenizer(produkt[i], " ");
					while (stoken.hasMoreTokens()) 
						System.out.println(stoken.nextToken()); 
				}

				System.out.println("Produkt [Bezeichnung= " + produkt[0] + " , Trockensortiment=" + produkt[1] + " , Warengruppe= "+ produkt[2] +  " , Preis= "+ produkt[3] +"]");


				//Drop line if produkt [1] is not Y or N



			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
