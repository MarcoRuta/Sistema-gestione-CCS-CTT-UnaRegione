package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class SaccaNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public SaccaNotFoundException() {}
    
    public SaccaNotFoundException (String message) {
        super(message);
    }
}