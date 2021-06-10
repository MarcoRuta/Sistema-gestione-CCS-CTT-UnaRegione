package it.unisannio.ingegneriaDelSoftware.DomainTypes;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


/**FlyWeight*/
public class Seriale {

	/** tag usati per i settings all'interno del file XML*/
	private static final String TAG_LAST_ASSIGNED = "last_assigned";

	/**Radice, prima parte del Seriale comune a tutte le Sacche prodotte dallo stesso CTT */
	private final static String radice;
	/**lastAssigned, seconda parte del Seriale diversa per ogni Sacca, valore incrementale*/
	private static int lastAssigned;
	/**Rappresentazione come stringa del Seriale*/
	private final String seriale;

	/**Mappa statica che mantiene tutte le istanze del Seriale*/
	private static Map<String,Seriale> seriali = new HashMap<String,Seriale>();

	/**Costruisce il seriale a partire da configurazioni presenti in /localsettings/serial_settings.xml*/
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
	    radice = CTTName.getInstance().getCttname();
	    lastAssigned = Integer.valueOf(loadProps.getProperty(TAG_LAST_ASSIGNED));
	}



	/**Restituisce il Seriale se è stato già creato
	 * Si evita di creare piu volte lo stesso Seriale
	 * @param seriale deve essere una stringa di 15 caratteri con '-' in 6 posizione*/
	public static Seriale getSeriale(String seriale){
		assert seriale!= null:"il seriale non puo essere null";
		if (seriali.containsKey(seriale))
			return seriali.get(seriale);
		Seriale aSeriale = new Seriale(seriale);
		Seriale.seriali.put(seriale,aSeriale);
		return aSeriale;
	}

	
	/**Costruisce un Seriale, di volta in volta il Seriale creato sarà diverso*/
	public Seriale() {
		this.seriale = radice+"-"+(new DecimalFormat("00000000")).format(++lastAssigned);
		Seriale.seriali.put(this.seriale,this);
	}

	
	/**Costruisce il seriale a partire da una stringa
	 * @param seriale deve essere una stringa di 15 caratteri con '-' in 6 posizione
	 */
	private Seriale(String seriale) {
		assert  seriale != null: "Il seriale non può essere null";
		assert 	seriale.length()==15 &&
				seriale.charAt(6)=='-' &&
				seriale.substring(7).matches("^[0-9]*$"): "Formato del seriale non valido";
		this.seriale = seriale;
	}


	/**Aggiorna il file xml di settings per la generazione del seriale
	 */
	public static void updateSettings() {
		Properties saveProps = new Properties();
		saveProps.setProperty(TAG_LAST_ASSIGNED, Integer.toString(lastAssigned));
		try {
			FileOutputStream fos = new FileOutputStream("localsettings/serial_settings.xml");
			saveProps.storeToXML(fos, "");
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**Aggiorna il file xml di settings per la generazione del seriale alla sua versione originaria
	 */
	public static void restartSettings() {
		Properties saveProps = new Properties();
		saveProps.setProperty(TAG_LAST_ASSIGNED, Integer.toString(0));
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

	/**Restituisce il valore del Seriale come stringa
	 * @return seriale Il valore del Seriale come stringa*/
	public String getSeriale(){
		return this.seriale;
	}
}