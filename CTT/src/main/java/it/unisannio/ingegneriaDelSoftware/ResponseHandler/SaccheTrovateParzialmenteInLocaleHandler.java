package it.unisannio.ingegneriaDelSoftware.ResponseHandler;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;
import it.unisannio.ingegneriaDelSoftware.ClientRest.CTTRestClient;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.Functional.ConnectionVerifier;
import it.unisannio.ingegneriaDelSoftware.Interfaces.ResponseHandler;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**Response handler che gestisce l'evento di una ricerca sacche con risultato parziale in locale */
public class SaccheTrovateParzialmenteInLocaleHandler implements ResponseHandler {
    @Override
    public Response makeResearchResponse(int numSacche, List<Seriale> serialiDaEvadere, String enteRichiedente, String indirizzoEnte, String dataArrivoMassima, String priorita, String gruppoSanguigno) {
        int num = numSacche - serialiDaEvadere.size();

        //se il CCS è online inoltro la richiesta globale
        if (ConnectionVerifier.isCCSOnline()) {
            CttRestApplication.logger.info("La ricerca in locale non è completa, sto per contattare il CCS");
            CTTRestClient.estendiRicercaLocale(CTTName.getInstance(),
                    GruppoSanguigno.valueOf(gruppoSanguigno),
                    num,
                    LocalDate.parse(dataArrivoMassima, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
                    enteRichiedente,
                    indirizzoEnte,
                    Boolean.valueOf(priorita));


            return Response
                    .status(Response.Status.PARTIAL_CONTENT)
                    .entity(new NotificaEvasione(serialiDaEvadere, enteRichiedente, indirizzoEnte, "La richiesta non è stata soddisfatta completamente in locale.\n" +
                            "Verrà contattato il CCS"))
                    .build();
        }

        //se il ccs è offline inoltro solo le sacche che ho in locale
        CttRestApplication.logger.info("La ricerca in locale non è completa, non riesco a contattare il CCS");
        return Response
                .status(Response.Status.PARTIAL_CONTENT)
                .entity(new NotificaEvasione(serialiDaEvadere, enteRichiedente, indirizzoEnte, "La richiesta non è stata soddisfatta completamente in locale.\n" +
                        "CCS è offline quindi non è possibile effettuare una ricerca GLOBALE.\n" +
                        "Se conferma l'evasione verrà notificato il magazziniere di evadere comunque le sacche trovate in locale"))
                .build();
    }
}
