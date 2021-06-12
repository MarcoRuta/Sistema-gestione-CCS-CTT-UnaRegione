package it.unisannio.ingegneriaDelSoftware.ResponseHandler;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Interfaces.ResponseHandler;

import javax.ws.rs.core.Response;
import java.util.List;
/**Response handler che gestisce l'evento di una ricerca sacche con risultato completo in locale */
public class SaccheTrovateInLocaleHandler implements ResponseHandler {
    public  Response makeResearchResponse(int numSacche, List<Seriale> serialiDaEvadere, String enteRichiedente, String indirizzoEnte, String dataArrivoMassima, String priorita, String gruppoSanguigno) {
        return Response
                .status(Response.Status.OK)
                .entity(new NotificaEvasione(serialiDaEvadere, enteRichiedente, indirizzoEnte,"La richiesta è stata soddisfatta completamente in locale.\n" +
                        "Una volta confermata l'evasione il magazziniere sarà notificato."))
                .build();
    }
}
