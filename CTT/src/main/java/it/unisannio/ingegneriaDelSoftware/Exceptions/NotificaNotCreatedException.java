package it.unisannio.ingegneriaDelSoftware.Exceptions;

/**Eccezione che viene lanciata nel momento in cui non è stato possibile creare una notifica*/
public class NotificaNotCreatedException extends Exception {
	
	private static final long serialVersionUID = 7137681988475902858L;

	public NotificaNotCreatedException(String s) {
		super(s);
	}

}