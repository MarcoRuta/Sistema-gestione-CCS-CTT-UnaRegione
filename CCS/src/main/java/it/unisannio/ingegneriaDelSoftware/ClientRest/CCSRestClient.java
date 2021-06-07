package it.unisannio.ingegneriaDelSoftware.ClientRest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Classes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Beans.Seriale;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**Client REST che viene utilizzato dal CCS per contattare gli EndPointRest dei CTT*/
public class CCSRestClient {


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

    public static List<Dipendente> makeReportOperatoriRequest(String ip, String ruolo){

        Client client = ClientBuilder.newClient();
        WebTarget dipendenti = client
                .target("http://"+ip+":"+Settings.PORTA+"/rest/amministratore/reportOperatoriCtt")
                .queryParam("ruolo",ruolo);
        List<Dipendente> dipendentiCtt = dipendenti.request().get(ArrayList.class);
        return dipendentiCtt;
    }

    public static Map<GruppoSanguigno,Integer> makeDisponibilitàSaccheRequest(String ip){

        Client client = ClientBuilder.newClient();
        WebTarget sacche = client
                .target("http://"+ip+":"+Settings.PORTA+"/rest/amministratore/reportStatisticoSacche");

        Map<GruppoSanguigno,Integer> saccheCtt = sacche.request().get(Map.class);

        return saccheCtt;
    }


    public static List<DatiSacca> makeReportSaccheInviate(String ip, String dataInizio, String dataFine){
        Client client = ClientBuilder.newClient();
        WebTarget saccheInviateCtt = client
                .target("http://"+ip+":"+Settings.PORTA+"/rest/amministratore/reportLocaleSaccheInviate")
                .queryParam("dataInizio",dataInizio)
                .queryParam("dataFine",dataFine);
        List<DatiSacca> datiSaccheInviate = saccheInviateCtt.request().get(ArrayList.class);
        return datiSaccheInviate;
    }

    public static List<DatiSacca> makeReportSaccheRicevute(String ip, String dataInizio, String dataFine){
        Client client = ClientBuilder.newClient();
        WebTarget saccheRicevuteCtt = client
                .target("http://"+ip+":"+Settings.PORTA+"/rest/amministratore/reportLocaleSaccheRicevute")
                .queryParam("dataInizio",dataInizio)
                .queryParam("dataFine",dataFine);
        List<DatiSacca> datiSaccheRicevute = saccheRicevuteCtt.request().get(ArrayList.class);
        return datiSaccheRicevute;
    }


}
