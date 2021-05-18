package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class DatiSaccaNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public DatiSaccaNotFoundException() {}
    
    public DatiSaccaNotFoundException (String message) {
        super(message);
    }
}
