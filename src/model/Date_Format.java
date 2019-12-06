package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Date_Format {
	
	/**
	   * Liefert das Datum im Format "dd.MM.yyyy" zur�ck.
	   * @param time long
	   * @return String
	   */
	  public static String getDdMMyyyyHHMi(long time) {
	    java.util.Date d = new java.util.Date(time);
	    TimeZone z = TimeZone.getDefault();

	    Calendar c = Calendar.getInstance(z, Locale.GERMANY);
	    SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	    sd.setCalendar(c);
	    return sd.format(d);
	  }

	  /**
	   * Wandelt Datum als String in einen Longwert.
	   */
	  public static long getDdMMyyyy(String sDate) throws ParseException {
	    if ( (sDate == null) || sDate.trim().equals("")) {
	      return 0;
	    }
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	    // Pr�ft, dass es sich tats�chlicht um die richtigen Datumsangaben handelt.
	    dateFormat.setLenient(false);
	    GregorianCalendar date = new GregorianCalendar();
	    date.setTime(dateFormat.parse(sDate));
	    return date.getTime().getTime();
	  }

	  /**
	   * Wandelt Datum als String in ein GregorianCalendar-Objekttyp.
	   */
	  public static GregorianCalendar getDayAsGregorianCalendar(String sDate) throws ParseException {
	    if ( (sDate == null) || sDate.trim().equals("")) {
	      return null;
	    }
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	    // Pr�ft, dass es sich tats�chlicht um die richtigen Datumsangaben handelt.
	    dateFormat.setLenient(false);
	    GregorianCalendar date = new GregorianCalendar();
	    date.setTime(dateFormat.parse(sDate));
	    return date;
	  }

	  /**
	   * Wandel Date-Objekt in einen String im Format 'YYYY-MM-dd'
	   */
	    public static String getYyyyMMdd(java.sql.Date time) {
	      TimeZone z = TimeZone.getDefault();

	      Calendar c = Calendar.getInstance(z, Locale.GERMANY);
	      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	      sd.setCalendar(c);
	      return sd.format(time);
	    }

	    /**
	     * Wandel Long-Objekt in einen Date-Objekt
	     */
	    public static java.sql.Date getDate(long time) {
	      return new java.sql.Date(time);
	    }
}
