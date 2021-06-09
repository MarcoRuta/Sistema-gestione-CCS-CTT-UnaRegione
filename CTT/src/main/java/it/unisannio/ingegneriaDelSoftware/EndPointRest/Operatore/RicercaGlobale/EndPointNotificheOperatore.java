package it.unisannio.ingegneriaDelSoftware.EndPointRest.Operatore.RicercaGlobale;

import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;

import javax.annotation.security.RolesAllowed;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Secured
@RolesAllowed("CCS")
@Path("/notificheOperatore")
public class EndPointNotificheOperatore {

    protected static NotificaRisultatiRicerca notificaRisultatiRicerca;


    @POST
    @Path("/risultatiRicercaGlobale")
    @Consumes(MediaType.APPLICATION_JSON)
    public void notifyOperatore(NotificaRisultatiRicerca risultatiRicerca){
        CttDataBaseRestApplication.logger.info("Mi è arrivato il risultato di una ricerca globale: "+risultatiRicerca);
        notificaRisultatiRicerca= risultatiRicerca;
        for (Session s : WebSocketEndPointResultRicercaGlobale.sessions) {
            try {
                s.getBasicRemote().sendObject(risultatiRicerca);
                CttDataBaseRestApplication.logger.info("Notifica risultati ricerca inoltrata correttamente al terminale operatore con sessione"+s.getId());
            } catch (IOException e) {
                CttDataBaseRestApplication.logger.error("Impossibile inoltrare all'operatore con sessione "+s.getId()+" i risultati della ricerca");
            } catch (EncodeException e) {
                CttDataBaseRestApplication.logger.error("Impossibile serializzare la lista delle sacche trovate online con sessione "+s.getId());
            }
        }
    }
}
