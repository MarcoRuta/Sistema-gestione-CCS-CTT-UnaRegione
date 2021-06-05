package it.unisannio.ingegneriaDelSoftware.ResponseHandler;

import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Interfaces.ResponseHandler;

import javax.ws.rs.core.Response;
import java.util.List;

public class SacceTrovateInLocaleHandler implements ResponseHandler {
    public  Response makeResearchResponse(int numSacche, List<Seriale> serialiDaEvadere, String enteRichiedente, String indirizzoEnte, String dataArrivoMassima, String priorita, String gruppoSanguigno) {
        return Response
                .status(Response.Status.OK)
                .entity(new NotificaEvasione(serialiDaEvadere, enteRichiedente, indirizzoEnte,"La richiesta è stata soddisfatta completamente in locale.\n" +
                        "Una volta confermata l'evasione il magazziniere sarà notificato."))
                .build();
    }
}
