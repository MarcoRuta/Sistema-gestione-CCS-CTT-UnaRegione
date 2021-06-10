package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class EntityAlreadyExistsException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	/** Eccezione che si presenta durante l'inserimento di un'entità nel Database, se essa è gia presente 
     * @param message Errore che viene visuallizato in console
     */
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}