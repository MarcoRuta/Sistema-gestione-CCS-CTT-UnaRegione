package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class SerialeNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public SerialeNotFoundException() {}
    
    public SerialeNotFoundException (String message) {
        super(message);
    }
}