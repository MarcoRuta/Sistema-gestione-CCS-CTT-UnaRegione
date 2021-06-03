package it.unisannio.ingegneriaDelSoftware.Exceptions;

/**Eccezione che viene lanciata nel momento in cui si prova a ricercare nel database un'entità non esistente*/
public class EntityNotFoundException extends Throwable{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
