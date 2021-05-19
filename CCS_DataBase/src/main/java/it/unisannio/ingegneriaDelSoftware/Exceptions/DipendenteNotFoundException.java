package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class DipendenteNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public DipendenteNotFoundException() {}
    
    public DipendenteNotFoundException (String message) {
        super(message);
    }
}
