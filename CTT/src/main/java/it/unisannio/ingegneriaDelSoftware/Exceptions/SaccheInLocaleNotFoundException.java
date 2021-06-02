package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class SaccheInLocaleNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public SaccheInLocaleNotFoundException() {}
    
    public SaccheInLocaleNotFoundException (String message) {
        super(message);
    }
}