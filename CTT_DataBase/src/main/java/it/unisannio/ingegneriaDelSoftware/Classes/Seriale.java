package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.*;
import java.text.DecimalFormat;
import java.util.InvalidPropertiesFormatException;
import java.util.Objects;
import java.util.Properties;



public class Seriale {

	/** tag usati per i settings all'interno del file XML*/
	private static final String TAG_RADICE = "radice";
	private static final String TAG_LAST_ASSIGNED = "last_assigned";

	private final static String radice;
	private static int lastAssigned;
	private final String seriale;

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
	
	
	public Seriale() {

		seriale = radice+"-"+(new DecimalFormat("00000000")).format(++lastAssigned);
	}
	
	/*
	 * @pre ser deve essere un seriale corretto
	 * !null
	 * 17 caratteri
	 * carattere "-" in quinta posizione
	 * un intero dalla sesta posizione alla fine
	 */	
	public Seriale(String ser) {
		assert  ser != null: "Il seriale non puo essere null";
		assert 	ser.length()==17 &&
				ser.charAt(4)=='-' &&
				ser.substring(5).matches("^[0-9]*$"): "Formato del seriale non valido";
		this.seriale = ser;
	}

	/**aggiorno il file xml di settings per la generazione del seriale*/
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

	@Override
	public String toString() {
		return "Seriale{" +
				"seriale='" + seriale + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Seriale seriale1 = (Seriale) o;
		return seriale.equals(seriale1.seriale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(seriale);
	}

	/**@return  il valore del seriale come stringa*/
	public String getSeriale(){
		return this.seriale;
	}


}