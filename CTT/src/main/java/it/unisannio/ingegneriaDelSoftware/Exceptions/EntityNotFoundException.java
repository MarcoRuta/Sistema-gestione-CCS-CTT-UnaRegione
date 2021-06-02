package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class EntityNotFoundException extends Throwable{

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
        super(message);
    }
}