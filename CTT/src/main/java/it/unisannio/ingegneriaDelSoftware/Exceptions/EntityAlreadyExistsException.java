package it.unisannio.ingegneriaDelSoftware.Exceptions;

/**Eccezione che viene lanciata nel momento in cui si prova ad aggiungere al database un'entità già esistente*/
public class EntityAlreadyExistsException extends Throwable {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
