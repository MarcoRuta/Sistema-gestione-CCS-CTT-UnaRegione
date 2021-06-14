package it.unisannio.ingegneriaDelSoftware.ClientRest;

import it.unisannio.ingegneriaDelSoftware.CcsRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Seriale;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Wrapper.SaccaWrapper;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
        if(sacche.size()!=0) CcsRestApplication.logger.info("Sacche compatibili trovate presso un CTT");

            return sacche;
    }

    public static void makeEvasioneRequest(String ip, List<Seriale> serialiDaEvadere, String indirizzoEnte, String enteRichiedente) {
        Client client = ClientBuilder.newClient();
        WebTarget saccheDaEvadere = client
                .target("http://"+ip+":"+ Settings.PORTA +"/rest/notifica/notificaEvasione");

        CcsRestApplication.logger.info("Sto per mandare richiesta evasione presso url: "+saccheDaEvadere.getUri());
        Response r = saccheDaEvadere.request().post(Entity.json(new NotificaEvasione(
                serialiDaEvadere,enteRichiedente,indirizzoEnte,"Sacche richieste da un altro CTT")));
        CcsRestApplication.logger.info("Richiesta evasione mandata");
    }

    public static void sendRisultatiRicerca(String ip, NotificaRisultatiRicerca notificaRisultatiRicerca) {
        CcsRestApplication.logger.info("Sto per mandare il risultato della ricerca");
        Client client = ClientBuilder.newClient();
        WebTarget risultati = client
                .target("http://"+ip+":"+ Settings.PORTA +"/rest/notificheOperatore/risultatiRicercaGlobale");

        //prenoto la sacca e mando la notifica evasione al magazzinere
        Response r = risultati.request().post(Entity.json(notificaRisultatiRicerca));
        CcsRestApplication.logger.info("Risultati mandati all'uri: "+risultati.getUri());
        CcsRestApplication.logger.info("Ecco la risposta: "+r.getStatus()+r.readEntity(String.class));
    }

    public static List<Dipendente> makeReportDipendenti(String ip, String ruolo){

        Client client = ClientBuilder.newClient();
        WebTarget dipendenti = client
                .target("http://"+ip+":"+Settings.PORTA+"/rest/amministratore/reportDipendentiCtt")
                .queryParam("ruolo",ruolo);
        List<Dipendente> dipendentiCtt = dipendenti.request().get(ArrayList.class);
        return dipendentiCtt;
    }

    public static Map<GruppoSanguigno,Integer> makeReportDisponibilitàSacche(String ip){

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

    public static Map<GruppoSanguigno, Double> makeReportGiacenzaMediaSacche(String ip) {
        Client client = ClientBuilder.newClient();
        WebTarget permanenza = client
                .target("http://"+ip+":"+Settings.PORTA+"/rest/amministratore/giacenzaMediaSacche");

        Map<GruppoSanguigno,Double> permanenzaSacche = permanenza.request().get(Map.class);

        return permanenzaSacche;
    }
}