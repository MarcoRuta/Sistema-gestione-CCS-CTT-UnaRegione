package it.unisannio.ingegneriaDelSoftware.ResponseHandler;

import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.ClientRest.CTTRestClient;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Functional.ConnectionVerifier;
import it.unisannio.ingegneriaDelSoftware.Interfaces.ResponseHandler;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SaccheNonTrovateInLocaleHandler implements ResponseHandler {
    @Override
    public Response makeResearchResponse(int numSacche, List<Seriale> serialiDaEvadere, String enteRichiedente, String indirizzoEnte, String dataArrivoMassima, String priorita, String gruppoSanguigno) {

        //se il CCS è online inoltro la richiesta globale
        if (ConnectionVerifier.isCCSOnline()) {
            CttDataBaseRestApplication.logger.info("La ricerca in locale non è completa, sto per contattare il CCS");
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
        CttDataBaseRestApplication.logger.info("La ricerca in locale non è completa, non riesco a contattare il CCS");
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("Localmente non è stata trovata nessuna sacca.\n" +
                        "Non posso contattare il CCS perchè è offline")
                .build();
    }

}
