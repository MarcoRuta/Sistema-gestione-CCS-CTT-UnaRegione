package it.unisannio.ingegneriaDelSoftware.DomainTypes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**Singleton*/
public class CTTName {

    /**Tag usati per i settings all'interno del file XML*/
    private static final String TAG_RADICE = "radice";
    private static final String NUMBER = "number";

    /**Radice, parte iniziale del nome CTT */
    private final static String radice;
    /**Numero, parte finale del nome CTT */
    private static int number;
    /**Stringa che rappresenta il nome del CTT*/
    private final String cttname;
    /**Istanza statica di CTTName*/
    private static CTTName cttNameInstance;

    /**Costruisco il nome del CTT a partire da configurazioni presenti in localsettings/cttname_settings.xmll*/
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
        number = Integer.valueOf(loadProps.getProperty(NUMBER));
        cttNameInstance = new CTTName();
    }

    
    /**Restituisce il nome del CTT come stringa
     * @return cttname il nome del CTT come stringa */
    public String getCttname() {
        return cttname;
    }

    
    /**Costruttore privato di CTTName*/
    private CTTName() {
        this.cttname = radice+(new DecimalFormat("000")).format(number);
    }

    
    /**Restituisce l'istanza caricata in memoria di CTTName
     * @return cttNameInstance */
    public static CTTName getInstance(){
        return cttNameInstance;
    }

    
    @Override
    public String toString() {
        return "CTTName{" +
                "cttname='" + cttname + '\'' +
                '}';
    }
}