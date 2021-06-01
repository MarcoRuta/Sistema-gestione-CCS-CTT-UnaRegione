package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;


/**FlyWeight*/
public class CTTName {

    /** tag usati per i settings all'interno del file XML*/
    private static final String TAG_RADICE = "radice";
    private static final String TAG_LAST_ASSIGNED = "last_assigned";

    private final static String radice;
    private static int lastAssigned;
    private final String cttname;

    /**Mappa statica che mantiene tutte le istanze di CTTName*/
    private static Map<String,CTTName> cttNameMap = new HashMap<String,CTTName>();

    /**Costruisco il seriale a partire da configurazioni presenti in /localsettings/serial_settings.xml*/
    static {
        Properties loadProps = new Properties();
        try {
            loadProps.loadFromXML(new FileInputStream("localsettings/cttname_settings.xml"));
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



    /**Restituisce il nome del CTT partendo da una stringa
     * @param cttname deve iniziare con CTT ed essere seguito da un intero di massimo 3 cifre*/
    public static CTTName getCttName(String cttname){
        assert cttname!= null:"il nome del CTT non puo essere null";
        if (cttNameMap.containsKey(cttname))
            return cttNameMap.get(cttname);
        CTTName unCttName = new CTTName(cttname);
        CTTName.cttNameMap.put(cttname,unCttName);
        return unCttName;
    }

    /**Restituisce il nome del CTT
	 * @return cttname
	 */
    public String getCttname() {
        return cttname;
    }

    
    public static String getTagLastAssigned() {
        return TAG_LAST_ASSIGNED;
    }

    
    /**Costruisce un seriale, di volta in volta il seriale creato sarà diverso*/
    public CTTName() {
        this.cttname = radice+(new DecimalFormat("000")).format(++lastAssigned);
        CTTName.cttNameMap.put(this.cttname,this);
        CTTName.updateSettings();
    }

    /**Costruisce il seriale a partire da una stringa
     * @param cttname deve essere una stringa di 6 caratteri
     * */
    private CTTName(String cttname) {
        assert  cttname != null: "Il seriale non puo essere null";
        assert 	cttname.length()==6 &&
                cttname.substring(3).matches("^[0-9]*$"): "Formato del cttName non valido";
        this.cttname = cttname;
    }


    /**Aggiorna il file xml di settings per la generazione del seriale
    */
    public static void updateSettings() {
        Properties saveProps = new Properties();
        saveProps.setProperty(TAG_RADICE, radice);
        saveProps.setProperty(TAG_LAST_ASSIGNED, Integer.toString(lastAssigned));
        try {
            FileOutputStream fos = new FileOutputStream("localsettings/cttname_settings.xml");
            saveProps.storeToXML(fos, "");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Aggiorno il file xml di settings per la generazione del cttname alla sua versione originaria*/
    public static void restartSettings() {
        Properties saveProps = new Properties();
        saveProps.setProperty(TAG_RADICE, radice);
        saveProps.setProperty(TAG_LAST_ASSIGNED, Integer.toString(0));
        try {
            FileOutputStream fos = new FileOutputStream("localsettings/cttname_settings.xml");
            saveProps.storeToXML(fos, "");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CTTName{" +
                "cttname='" + cttname + '\'' +
                '}';
    }
}