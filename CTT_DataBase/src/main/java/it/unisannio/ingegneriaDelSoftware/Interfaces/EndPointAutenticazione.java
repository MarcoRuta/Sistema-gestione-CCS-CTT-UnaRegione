package it.unisannio.ingegneriaDelSoftware.Interfaces;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

public interface EndPointAutenticazione {

	/**
	 * Login è l'operazione con la quale un generico dipendente del ctt accede al sistema
	 *
	 * @return il ruolo con il quale si è registrati
	 * esso setta il token di autentificazione nei cookie con cookieName = "access_token
	 * il cookie ha una max-Age pari ad un turno lavorativo di 8 ore"
	 */
	public Response login(String username,
						  String password);


	/**
	 * Metodo che effettua il logout, esso elimina il token dell'utente dal server cosi che esso non sia piu autenticato
	 *
	 * @param token da rimuovere per effetuare il logout
	 */
	public Response logOut(Cookie token);


	public Response cambioPassword(String cdf, String password);
}
