package it.unisannio.ingegneriaDelSoftware.Util;
import it.unisannio.ingegneriaDelSoftware.CcsRestApplication;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTT;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import java.io.*;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;


public class Settings {

    private static Settings settings = new Settings();
    public static Map<CTT, String> ip = new HashMap<>();
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
            CcsRestApplication.logger.error("File properties invalido");
        } catch (FileNotFoundException e) {
            CcsRestApplication.logger.error("Impossibile trovare i file dei settings");
        } catch (IOException e) {
            CcsRestApplication.logger.error("Errore durante l'apertura dei file del settings");
        }
        //carico impostazioni DB
        DB_NAME = loadDatabaseProps.getProperty("DB_NAME");
        COLLECTION_DIPENDENTI = loadDatabaseProps.getProperty("COLLECTION_DIPENDENTI");
        COLLECTION_SACCHE = loadDatabaseProps.getProperty("COLLECTION_SACCHE");
        COLLECTION_CTT = loadDatabaseProps.getProperty("COLLECTION_CTT");


        //carico indirizzi ip
        for (Object key :loadNetworkProps.keySet()) {
            String keyS = (String) key;
            if(!keyS.equals("PORT")) {
                try {
                    ip.put(MongoDataManager.getInstance().getCTT(CTTName.getCttName((String)key)),loadNetworkProps.getProperty((String)key));
                } catch (EntityNotFoundException e) {
                    //do nothing
                }
            }
        }
        PORTA = loadNetworkProps.getProperty("PORT");
    }
}



