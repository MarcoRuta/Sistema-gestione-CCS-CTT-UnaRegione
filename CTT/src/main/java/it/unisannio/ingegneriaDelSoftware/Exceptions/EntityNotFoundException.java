package it.unisannio.ingegneriaDelSoftware.Exceptions;

/**Eccezione che viene lanciata nel momento in cui si prova a ricercare nel database un'entità non esistente*/
public class EntityNotFoundException extends Throwable{

	private static final long serialVersionUID = -1782228358663992105L;

	public EntityNotFoundException(String message) {
        super(message);
    }
}