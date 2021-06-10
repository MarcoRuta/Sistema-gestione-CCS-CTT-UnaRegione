package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;

import javax.ws.rs.core.Response;
import java.util.List;

public interface ResponseHandler {
    public  Response makeResearchResponse(int numSacche,
                                          List<Seriale> serialiDaEvadere,
                                          String enteRichiedente,
                                          String indirizzoEnte,
                                          String dataArrivoMassima,
                                          String priorita, String gruppoSanguigno);

}
