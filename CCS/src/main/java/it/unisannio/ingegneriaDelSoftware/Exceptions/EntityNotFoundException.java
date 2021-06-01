package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class EntityNotFoundException extends Throwable{
	
	private static final long serialVersionUID = 1L;

	/** Eccezione che si presenta durante la ricerca di un'entità nel database, essa non è presente
     * @param message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}