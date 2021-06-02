package it.unisannio.ingegneriaDelSoftware.Util;

import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;


public class Settings {

    private static Settings settings;

    public static Settings getInstance(){
        return settings;
    }

    static {
        Properties loadProps = new Properties();
        try {
            loadProps.loadFromXML(new FileInputStream("localsettings/general_settings.xml"));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ccsIp = loadProps.getProperty("CCSIP");
        ccsWebSocket = loadProps.getProperty("CCSWebSocketIP");
        retry = Integer.valueOf(loadProps.getProperty("CCS_retry_connection"));
    }

    public   static String ccsIp;
    public   static int retry ;
    public   static String ccsWebSocket;









}
