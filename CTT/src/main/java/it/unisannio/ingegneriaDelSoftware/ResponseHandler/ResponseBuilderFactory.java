package it.unisannio.ingegneriaDelSoftware.ResponseHandler;

import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Interfaces.ResponseHandler;

import java.util.List;

public class ResponseBuilderFactory {


    public static ResponseHandler GetResponseHandler(List<Seriale> serialiDaEvadere, int numSacche) {
        int saccheMancanti = numSacche - serialiDaEvadere.size();
        if (saccheMancanti == 0)
            return new SaccheTrovateInLocaleHandler();
        if (serialiDaEvadere.size() == 0)
            return new SaccheNonTrovateInLocaleHandler();
        if (saccheMancanti > 0)
            return new SaccheTrovateParzialmenteInLocaleHandler();
        return null;
    }

}
