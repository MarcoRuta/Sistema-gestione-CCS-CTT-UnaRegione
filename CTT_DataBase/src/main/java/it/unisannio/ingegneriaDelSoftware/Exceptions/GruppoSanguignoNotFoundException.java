package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class GruppoSanguignoNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public GruppoSanguignoNotFoundException() {}
    
    public GruppoSanguignoNotFoundException (String message) {
        super(message);
    }
}
