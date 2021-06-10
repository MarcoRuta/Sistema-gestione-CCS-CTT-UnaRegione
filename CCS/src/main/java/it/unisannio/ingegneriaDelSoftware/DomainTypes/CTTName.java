package it.unisannio.ingegneriaDelSoftware.DomainTypes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

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

    /**Costruisco il seriale a partire da configurazioni presenti in /localsettings/serial_settings.xml
     * @throws InvalidPropertiesFormatException, FileNotFoundException, IOException*/
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
     * @param cttname Nome del CTT nel formato "CTT"+ 3 interi
     */
    public static CTTName getCttName(String cttname){
        assert cttname!= null:"il nome del CTT non puo essere null";
        if (cttNameMap.containsKey(cttname))
            return cttNameMap.get(cttname);
        CTTName unCttName = new CTTName(cttname);
        CTTName.cttNameMap.put(cttname,unCttName);
        return unCttName;
    }

    /**Restituisce il nome del CTT
	 * @return cttname Il nome del CTT
	 */
    public String getCttname() {
        return cttname;
    }

    /**Costruisce un seriale, di volta in volta il seriale creato sarà diverso*/
    public CTTName() {
        this.cttname = radice+(new DecimalFormat("000")).format(++lastAssigned);
        CTTName.cttNameMap.put(this.cttname,this);
    }

    /**Costruisce il seriale a partire da una stringa
     * @param cttname Nome del CTT nel formato "CTT"+ 3 interi
     */
    private CTTName(String cttname) {
        assert  cttname != null: "Il seriale non puo essere null";
        assert 	cttname.length()==6 &&
                cttname.substring(3).matches("^[0-9]*$"): "Formato del cttName non valido";
        this.cttname = cttname;
    }

    /**Aggiorna il file xml di settings per la generazione del seriale. Il prossimo seriale partirà da lastAssigned+1*/
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

    /**Aggiorno il file xml di settings per la generazione del cttname alla sua versione originaria. Il seriale ripartirà da 0*/
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CTTName cttName = (CTTName) o;
        return cttname.equals(cttName.cttname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cttname);
    }
}