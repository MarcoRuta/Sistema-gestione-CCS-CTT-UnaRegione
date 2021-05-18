package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class SaccaGiaPresenteException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public SaccaGiaPresenteException() {}
    
    public SaccaGiaPresenteException (String message) {
        super(message);
    }
}
