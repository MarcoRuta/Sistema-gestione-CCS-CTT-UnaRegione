package it.unisannio.ingegneriaDelSoftware.ResponseHandler;

import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Interfaces.ResponseHandler;
import it.unisannio.ingegneriaDelSoftware.ResponseHandler.SacceTrovateInLocaleHandler;
import it.unisannio.ingegneriaDelSoftware.ResponseHandler.SaccheNonTrovateInLocaleHandelr;
import it.unisannio.ingegneriaDelSoftware.ResponseHandler.SaccheTrovateParzialmenteInLocaleHandler;

import java.util.List;

public class ResponseBuilderFactory {


    public static ResponseHandler GetResponseHandler(List<Seriale> serialiDaEvadere, int numSacche) {
        int saccheMancanti = numSacche - serialiDaEvadere.size();
        if (saccheMancanti == 0)
            return new SacceTrovateInLocaleHandler();
        if (serialiDaEvadere.size() == 0)
            return new SaccheNonTrovateInLocaleHandelr();
        if (saccheMancanti > 0)
            return new SaccheTrovateParzialmenteInLocaleHandler();
        return null;
    }

}
