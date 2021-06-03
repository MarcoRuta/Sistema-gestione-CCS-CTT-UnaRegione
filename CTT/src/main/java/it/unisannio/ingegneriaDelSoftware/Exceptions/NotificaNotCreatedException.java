package it.unisannio.ingegneriaDelSoftware.Exceptions;

/**Eccezione che viene lanciata nel momento in cui non è stato possibile creare una notifica*/
public class NotificaNotCreatedException extends Exception {
	
	public NotificaNotCreatedException(String s) {
		super(s);
	}


}
