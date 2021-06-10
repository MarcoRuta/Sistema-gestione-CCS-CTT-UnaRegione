package it.unisannio.ingegneriaDelSoftware.Exceptions;

/**Eccezione che viene lanciata nel momento in cui si prova ad aggiungere al database un'entità già esistente*/
public class EntityAlreadyExistsException extends Throwable {
 
	private static final long serialVersionUID = -3378136176506666475L;

	public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
