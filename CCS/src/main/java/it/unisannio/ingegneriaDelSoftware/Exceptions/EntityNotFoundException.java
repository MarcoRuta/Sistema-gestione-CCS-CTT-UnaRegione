package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class EntityNotFoundException extends Throwable{
	
	private static final long serialVersionUID = 1L;

	/** Eccezione che si presenta quando,durante la ricerca di un'entità nel database, essa non è presente
     * @param message Errore che viene visuallizato in console
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}