package it.unisannio.ingegneriaDelSoftware.ResponseHandler;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Interfaces.ResponseHandler;

import java.util.List;

/**Factory Method che restituisce un ResponseHandler in base all'esito di una ricerca sacche */
public class ResponseBuilderFactory {

    public static ResponseHandler GetResponseHandler(List<Seriale> serialiDaEvadere, int numSacche) {
        int saccheMancanti = numSacche - serialiDaEvadere.size();
        //Se la richiesta è stata soddisfatta completamente in locale
        if (saccheMancanti == 0)
            return new SaccheTrovateInLocaleHandler();
        //Se non è stata trovata nessuna sacca in locale
        if (serialiDaEvadere.size() == 0)
            return new SaccheNonTrovateInLocaleHandler();
        //Se la richiesta è stata soddisfatta parzialmente in locale
        if (saccheMancanti > 0)
            return new SaccheTrovateParzialmenteInLocaleHandler();
        return null;
    }

}
