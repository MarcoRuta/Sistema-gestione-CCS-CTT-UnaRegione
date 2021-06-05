package it.unisannio.ingegneriaDelSoftware.ClientRest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Classes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Beans.Seriale;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.Classes.Wrapper.SaccaWrapper;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**Client REST che viene utilizzato dal CCS per contattare gli EndPointRest dei CTT*/
public class CCSRestClient {

	/**Contatta il CCS per avvisarlo di aver consumato il locale una sacca per cui era stato inviato un alert
	 * , la Sacca per la quale si vuole ritirare l'alert
	 */
    public static List<Sacca> RicercaGlobaleSaccheCompatibili(String gs, String dataArrivoMassima, String CTTip, String CTTport){
        Client client = ClientBuilder.newClient();
        WebTarget saccheCompatibili = client
                .target("http://"+CTTip+":"+CTTport+"/rest/operatore/listaSaccheCompatibili")
        		.path(gs)
        		.path(dataArrivoMassima);

        SaccaWrapper saccaWrapper = saccheCompatibili.request().get(SaccaWrapper.class);
        List<Sacca> sacche = saccaWrapper.getSacche();
        if(sacche.size()!=0)CcsDataBaseRestApplication.logger.info("Sacche compatibili trovate presso un CTT");
            return sacche;
    }



    public static void makeEvasioneRequest(String ip, List<Seriale> serialiDaEvadere, String indirizzoEnte, String enteRichiedente) {
        Client client = ClientBuilder.newClient();
        WebTarget saccheDaEvadere = client
                .target("http://"+ip+":"+ Settings.PORTA +"/rest/notifica/notificaEvasione");

        CcsDataBaseRestApplication.logger.info("Sto per mandare richiesta evasione presso url: "+saccheDaEvadere.getUri());



        //prenoto la sacca e mando la notifica evasione al magazzinere
        Response r = saccheDaEvadere.request().post(Entity.json(new NotificaEvasione(
                serialiDaEvadere,enteRichiedente,indirizzoEnte,"Sacche richieste da un altro CTT")));
        CcsDataBaseRestApplication.logger.info("Richiesta evasione mandata");
    }

    public static void sendRisultatiRicerca(String ip, NotificaRisultatiRicerca notificaRisultatiRicerca) {
        CcsDataBaseRestApplication.logger.info("Sto per mandare il risultato della ricerca");
        Client client = ClientBuilder.newClient();
        WebTarget risultati = client
                .target("http://"+ip+":"+ Settings.PORTA +"/rest/notificheOperatore/risultatiRicercaGlobale");

        //prenoto la sacca e mando la notifica evasione al magazzinere
        Response r = risultati.request().post(Entity.json(notificaRisultatiRicerca));
        CcsDataBaseRestApplication.logger.info("Risultati mandati all'uri: "+risultati.getUri());
        CcsDataBaseRestApplication.logger.info("ecco la risposta: "+r.getStatus()+r.readEntity(String.class));
    }
}
