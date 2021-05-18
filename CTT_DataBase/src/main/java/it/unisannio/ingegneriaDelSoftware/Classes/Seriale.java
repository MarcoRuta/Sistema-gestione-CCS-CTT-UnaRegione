package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


/**FlyWeight*/
public class Seriale {

	/** tag usati per i settings all'interno del file XML*/
	private static final String TAG_RADICE = "radice";
	private static final String TAG_LAST_ASSIGNED = "last_assigned";

	private final static String radice;
	private static int lastAssigned;
	private final String seriale;

	private static Map<String,Seriale> seriali = new HashMap<String,Seriale>();

	/**Costruisco il seriale a partire da configurazioni presenti in /localsettings/serial_settings.xml*/
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



	/**Ricavo il Seriale se è stato gia creato
	 * Si evita di creare piu volte lo stesso seriale
	 * @param seriale deve essere una stringa di 15 caratteri con '-' in 6 posizione*/
	public static Seriale getSeriale(String seriale){
		assert seriale!= null:"il seriale non puo essere null";
		if (seriali.containsKey(seriale))
			return seriali.get(seriale);
		Seriale aSeriale = new Seriale(seriale);
		Seriale.seriali.put(seriale,aSeriale);
		return aSeriale;
	}

	
	/**
	 * costruisco un seriale, di volta in volta il seriale creato sarà diverso*/
	public Seriale() {
		this.seriale = radice+"-"+(new DecimalFormat("00000000")).format(++lastAssigned);
		Seriale.seriali.put(this.seriale,this);
	}

	/**costruisco il seriale a partire da una stringa
	 * @param seriale deve essere una stringa di 15 caratteri con '-' in 6 posizione
	 * */
	private Seriale(String seriale) {
		assert  seriale != null: "Il seriale non puo essere null";
		assert 	seriale.length()==15 &&
				seriale.charAt(6)=='-' &&
				seriale.substring(7).matches("^[0-9]*$"): "Formato del seriale non valido";
		this.seriale = seriale;
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