package it.unisannio.ingegneriaDelSoftware.Util;
import java.text.SimpleDateFormat;

public class Constants {

	public static final String DB_NAME = "CTT";

	public static final String COLLECTION_SACCHE = "SACCHE";
	public static final String ELEMENT_SERIALE = "seriale";
	public static final String ELEMENT_GRUPPO = "gruppo";
	public static final String ELEMENT_DATAPRODUZIONE = "dataProduzione";
	public static final String ELEMENT_DATASCADENZA = "dataScadenza";
	public static final String ELEMENT_PRENOTATO = "prenotato";

	public static final String COLLECTION_DATISACCHE = "DATISACCHE";
	public static final String ELEMENT_DATAARRIVO = "dataArrivo";
	public static final String ELEMENT_DATAAFFIDAMENTO = "dataAffidamento";
	public static final String ELEMENT_ENTEDONATORE = "enteDonatore";
	public static final String ELEMENT_ENTERICHIEDENTE = "enteRichiedente";
	public static final String ELEMENT_INDIRIZZOENTE = "indirizzoEnte";

	public static final String COLLECTION_DIPENDENTI = "DIPENDENTI";
	public static final String ELEMENT_CDF = "cdf";
	public static final String ELEMENT_NOME = "nome";
	public static final String ELEMENT_COGNOME = "cognome";
	public static final String ELEMENT_DATADINASCITA = "dataDiNascita";
	public static final String ELEMENT_RUOLO = "ruolo";
	public static final String ELEMENT_USERNAME = "username";
	public static final String ELEMENT_PASSWORD = "password";

	//indirizzi ip
    public static final String CCSIP = "http://192.168.193.220:8080";
    public static final String CCSWebSocket = "ws://192.168.193.220:8080/ws/saccheInScadenza";

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	public static final String DATEFORMAT = "yyyy-MM-dd";
}