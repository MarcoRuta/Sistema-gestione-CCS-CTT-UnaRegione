package it.unisannio.ingegneriaDelSoftware.Exceptions;

public class CTTNotFoundException extends Exception {

	/**Eccezione che viene lanciata nel momento in cui si cerca nel database un CTT non presente
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CTTNotFoundException(String message) {
		super(message);
	}
	
}
