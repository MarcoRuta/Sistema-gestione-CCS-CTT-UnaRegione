package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class TokenNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public TokenNotFoundException() {}
    
    public TokenNotFoundException (String message) {
        super(message);
    }
}