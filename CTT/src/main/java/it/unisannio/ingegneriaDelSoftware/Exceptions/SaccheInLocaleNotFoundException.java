package it.unisannio.ingegneriaDelSoftware.Exceptions;

/**Eccezione che viene lanciata nel momento in cui non è stato possibile completare un ordine in locale*/
public class SaccheInLocaleNotFoundException extends RuntimeException{
    
    public SaccheInLocaleNotFoundException() {}
    
    public SaccheInLocaleNotFoundException (String message) {
        super(message);
    }
}
