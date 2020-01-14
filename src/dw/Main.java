package dw;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import view.ImportWindow;

public class Main {

	private final static Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {

		//Aufruf des Programms ueber den Konstruktor der ImportWindow-Klasse

				try {
					ImportWindow win = new ImportWindow();
				}catch(Exception e) {
					LOG.log(Level.SEVERE, "Window Cant'open", e);


				}
				
//  	Versuche, Um Dr. Oetker mir Regex als Handelsmarke zu identifizieren				

//		String s = "Weber Fisch Barbecue Gewürz Weber Fisch Barbecue Gewürz Weber Fisch Barbecue Gewürz Weber Fisch Barbecue Gewürz Weber Fisch Barbecue Gewürz Weber Fisch Barbecue Gewürz Weber Fisch Barbecue Gewürz Weber Fisch Barbecue Gewürz Weber Fisch Barbecue Gewürz Weber Fisch Barbecue Gewürz Weber Fisch Barbecue Gewürz Weber Fisch Barbecue Gewürz (18 g)";
//		byte[] b = s.getBytes(StandardCharsets.UTF_8);
//		System.out.println(Arrays.toString(b));
//
//
//
//		String[] words = s.split("\\s+");
//		for (int i = 0; i < words.length; i++) {
//			// You may want to check for a non-word character before blindly
//			// performing a replacement
//			// It may also be necessary to adjust the character class
//			words[i] = words[i].replaceAll("ü", "ü");
//			System.out.println(words[i]);
//		}
//		System.out.println(Arrays.toString(words));
//
//		String s2= "Dr. Oetker Vitalis Knusper Pop Müsli Schoko (450 g)";
//		String s3 = "Kellogg's Star Wars (350 g)";
//
//		String[] words2 = s2.split("[^.$][\\s+]");
//		String[] words3 = s3.split("[\\s+]");
//
//
//		for (int i = 0; i < words2.length; i++) {
//			// You may want to check for a non-word character before blindly
//			// performing a replacement
//			// It may also be necessary to adjust the character class
//			words2[i] = words2[i].replaceAll("^.[^\\w]]", "");
//			System.out.println(words2[i]);
//		}
//		System.out.println(Arrays.toString(words2));
//		System.out.println(words2.length);
//		System.out.println(Arrays.toString(words3));
//
//
//		Map<String, Integer> occurrences = new HashMap<String, Integer>();
//
//		boolean isdub =false;
//		Integer oldCount=0;
//		for ( String word : words ) {
//			oldCount = occurrences.get(word);
//			if ( oldCount == null ) {
//				oldCount = 0;
//			}
//			occurrences.put(word, oldCount + 1);
//			if(oldCount>2) isdub = true;
//			System.out.println("oldcount2 : "+ oldCount+ "word"+ word + "isdub" + isdub);
//		}
//		System.out.println("ISDUUUB"+ isdub);



	}


}
