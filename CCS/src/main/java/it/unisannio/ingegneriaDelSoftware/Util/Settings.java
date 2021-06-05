package it.unisannio.ingegneriaDelSoftware.Util;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;

import java.io.*;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;


public class Settings {

    private static Settings settings = new Settings();
    public static Map<CTTName, String> ip = new HashMap<>();
    public static final String DB_NAME;
    public static final String COLLECTION_DIPENDENTI;
    public static final String COLLECTION_SACCHE;
    public static final String COLLECTION_CTT;
    public static final String PORTA;

    static{
        Properties loadNetworkProps = new Properties();
        Properties loadDatabaseProps = new Properties();

        try {
            loadNetworkProps.loadFromXML(new FileInputStream("localsettings/network_settings.xml"));
            loadDatabaseProps.loadFromXML(new FileInputStream("localsettings/database_settings.xml"));
        } catch (InvalidPropertiesFormatException e) {
            CcsDataBaseRestApplication.logger.error("File properties invalido");
        } catch (FileNotFoundException e) {
            CcsDataBaseRestApplication.logger.error("Impossibile trovare i file dei settings");
        } catch (IOException e) {
            CcsDataBaseRestApplication.logger.error("Errore durante l'apertura dei file del settings");
        }
        //carico indirizzi ip
        for (Object key :loadNetworkProps.keySet()) {
        	String keyS = (String) key;
        	if(!keyS.equals("PORT"))
        		ip.put(CTTName.getCttName((String)key),loadNetworkProps.getProperty((String)key));
        }
        PORTA = loadNetworkProps.getProperty("PORT");
        	
        //carico impostazioni DB
        DB_NAME = loadDatabaseProps.getProperty("DB_NAME");
        COLLECTION_DIPENDENTI = loadDatabaseProps.getProperty("COLLECTION_DIPENDENTI");
        COLLECTION_SACCHE = loadDatabaseProps.getProperty("COLLECTION_SACCHE");
        COLLECTION_CTT = loadDatabaseProps.getProperty("COLLECTION_CTT");
    }


}
