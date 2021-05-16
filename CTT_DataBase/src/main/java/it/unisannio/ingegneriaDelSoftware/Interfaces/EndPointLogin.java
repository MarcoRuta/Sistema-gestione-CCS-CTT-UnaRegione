package it.unisannio.ingegneriaDelSoftware.Interfaces;

import javax.ws.rs.core.Response;

public interface EndPointLogin {

	/**
	 * Login è l'operazione con la quale un generico dipendente del ctt accede al sistema
	 *
	 * @return il ruolo con il quale si è registrati
	 * esso setta il token di autentificazione nei cookie con cookieName = "access_token
	 * il cookie ha una max-Age pari ad un turno lavorativo di 8 ore"
	 */
	Response login(String username, String password);
	
}
