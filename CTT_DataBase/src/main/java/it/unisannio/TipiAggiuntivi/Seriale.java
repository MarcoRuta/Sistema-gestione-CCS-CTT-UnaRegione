package it.unisannio.TipiAggiuntivi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Seriale {
	
	private static final String TAG_RADICE = "radice";
	private static final String TAG_LAST_ASSIGNED = "last_assigned";
	
	private final static String radice;
	private static int lastAssigned;
	static {
		Properties loadProps = new Properties();
	    try {
			loadProps.loadFromXML(new FileInputStream("localsettings/serial_settings.xml"));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    radice = loadProps.getProperty(TAG_RADICE);
	    lastAssigned = Integer.valueOf(loadProps.getProperty(TAG_LAST_ASSIGNED));
	}
	
	private final String val;
	
	public Seriale() {
		val = radice+"-"+(new DecimalFormat("000000000000")).format(++lastAssigned);
	}
	
	/*
	 * @pre ser deve essere un seriale corretto
	 * !null
	 * 17 caratteri
	 * carattere "-" in quinta posizione
	 * un intero dalla sesta posizione alla fine
	 */	
	public Seriale(String ser) {
		assert  ser != null &&
				ser.length()==17 &&
				ser.charAt(4)=='-' &&
				ser.substring(5).matches("^[0-9]*$");
		val = ser;
	}
	

	public boolean equals (Object o) {
		return ((Seriale) o ).val.equals(this.val);
	}
	public String toString() {
		return val;
	}
		
	public static void updateSettings() {
	    Properties saveProps = new Properties();
	    saveProps.setProperty(TAG_RADICE, radice);
	    saveProps.setProperty(TAG_LAST_ASSIGNED, Integer.toString(lastAssigned));
	    try {
	    	FileOutputStream fos = new FileOutputStream("localsettings/serial_settings.xml");
			saveProps.storeToXML(fos, "");
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}