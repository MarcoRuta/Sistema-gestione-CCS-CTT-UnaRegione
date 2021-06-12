package it.unisannio.ingegneriaDelSoftware.ResponseHandler;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
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

/**Response handler che gestisce l'evento di una ricerca sacche senza risultato in locale */
public class SaccheNonTrovateInLocaleHandler implements ResponseHandler {
    @Override
    public Response makeResearchResponse(int numSacche, List<Seriale> serialiDaEvadere, String enteRichiedente, String indirizzoEnte, String dataArrivoMassima, String priorita, String gruppoSanguigno) {

        //se il CCS è online inoltro la richiesta globale
        if (ConnectionVerifier.isCCSOnline()) {
            CttRestApplication.logger.info("La ricerca in locale non è completa, sto per contattare il CCS");
            CTTRestClient.estendiRicercaLocale(CTTName.getInstance(),
                    GruppoSanguigno.valueOf(gruppoSanguigno),
                    numSacche,
                    LocalDate.parse(dataArrivoMassima, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
                    enteRichiedente,
                    indirizzoEnte,
                    Boolean.valueOf(priorita));


            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Localmente non è stata trovata nessuna sacca.\n" +
                            "Inoltro la richiesta al CCS")
                    .build();
        }

        //se il ccs è offline Non posso restiture nulla
        CttRestApplication.logger.info("La ricerca in locale non è completa, non riesco a contattare il CCS");
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("Localmente non è stata trovata nessuna sacca.\n" +
                        "Non posso contattare il CCS perchè è offline")
                .build();
    }

}
