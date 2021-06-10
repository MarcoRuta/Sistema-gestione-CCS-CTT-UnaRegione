package it.unisannio.ingegneriaDelSoftware.ClientRest;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

/**Client REST che viene utilizzato dal CTT per ritirare l'alert di una Sacca in scadenza*/
public class CTTRestClient {

	/**Contatta il CCS per avvisarlo di aver consumato in locale una Sacca per cui era stato inviato un alert
	 * @param sacca, la Sacca per la quale si vuole ritirare l'alert
	 */
    public static void notifyEvasioneSaccaToCCS(Sacca sacca){
        Client client = ClientBuilder.newClient();
        WebTarget evasioneSacca = client
                .target("http://"+Settings.ccsIp+":"+Settings.ccsIpPort+"/rest/CCS/ritiroAlertCTT")
                .path(sacca.getSeriale().getSeriale());
        evasioneSacca.request().delete();
        CttRestApplication.logger.info("Avviso il CCS che ho consumato una delle Sacche in Scadenza");
    }


    public static void notifySaccaInScadenzaToCCS(List<Sacca> saccheInScadenza){
        Client client = ClientBuilder.newClient();
        WebTarget gestioneSaccheInscadenza = client
                .target("http://"+Settings.ccsIp+":"+Settings.ccsIpPort+"/rest/CCS/saccheInScadenza");
        gestioneSaccheInscadenza.request().post(Entity.json(saccheInScadenza));
        CttRestApplication.logger.info("Ho inviato un alert al CCS con la lista delle sacche in scadenza");
    }
    
    public static Response estendiRicercaLocale(CTTName nome, GruppoSanguigno gs, int numeroSacche, LocalDate dataArrivoMassima, String enteRichiedente, String indirizzoEnte, boolean priorità) {
    	 Client client = ClientBuilder.newClient();
    	 CttRestApplication.logger.info("Sto per estendere la ricerca globale al CCS: "+"http://"+Settings.ccsIp+":"+Settings.ccsIpPort+"/rest/CCS/ricercaGlobale");

    	 WebTarget ricercaGlobale = client
                 .target("http://"+Settings.ccsIp+":"+Settings.ccsIpPort+"/rest/CCS/ricercaGlobale")
                 .queryParam("nome", nome.getCttname())
                 .queryParam("gruppo", gs.toString())
                 .queryParam("numero", String.valueOf(numeroSacche))
                 .queryParam("dataArrivoMassima", dataArrivoMassima.toString())
                 .queryParam("enteRichiedente", enteRichiedente)
                 .queryParam("indirizzoEnte", indirizzoEnte)
                 .queryParam("priorità", priorità);

        Response r = ricercaGlobale.request().get();
        CttRestApplication.logger.info("Ho contattato il CCS per estendere la ricerca locale");
        return r;
    }
}