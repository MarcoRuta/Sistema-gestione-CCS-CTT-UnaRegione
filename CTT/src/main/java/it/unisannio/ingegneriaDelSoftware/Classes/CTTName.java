package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;


/**Sinlgeton*/
public class CTTName {

    /** tag usati per i settings all'interno del file XML*/
    private static final String TAG_RADICE = "radice";
    private static final String NUMBER = "number";

    private final static String radice;
    private static int number;
    private final String cttname;
    private static CTTName cttNameInstance;

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
        number = Integer.valueOf(loadProps.getProperty(NUMBER));
        cttNameInstance = new CTTName();
    }

    public String getCttname() {
        return cttname;
    }

    /**
     * costruisco un seriale, di volta in volta il seriale creato sarà diverso*/
    private CTTName() {
        this.cttname = radice+(new DecimalFormat("000")).format(number);
    }

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
