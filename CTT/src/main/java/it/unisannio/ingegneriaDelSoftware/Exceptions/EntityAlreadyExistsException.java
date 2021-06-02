package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class EntityAlreadyExistsException extends Throwable {

	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistsException(String message) {
        super(message);
    }
}