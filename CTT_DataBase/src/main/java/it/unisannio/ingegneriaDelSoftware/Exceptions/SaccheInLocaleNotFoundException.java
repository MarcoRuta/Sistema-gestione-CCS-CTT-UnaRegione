package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class SaccaLocaleNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public SaccaLocaleNotFoundException() {}
    
    public SaccaLocaleNotFoundException (String message) {
        super(message);
    }
}
