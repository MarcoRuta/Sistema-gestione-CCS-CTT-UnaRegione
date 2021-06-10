package it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Seriale;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import java.util.List;

public class NotificaEvasione implements Notifica {

	private List<Seriale> listaSeriali;
	private String enteRichiedente;
	private String indirizzoEnte;
	private String message;

	/**Costruttore senza argomenti*/
	public NotificaEvasione() {
	}

	/**Metodo costruttore di NotificaEvasione
	 * @param seriali Lista dei seriali da evadere
	 * @param enteRichiedente Ente richiedente dei DatiSacca delle sacche che vengono evase
	 * @param indirizzoEnte Ente richiedente dei DatiSacca delle sacche che vengono evase
	 * @param message Il messaggio correlato ad ogni notifica evasione che si genera
	 */
	public NotificaEvasione(List<Seriale> seriali, String enteRichiedente, String indirizzoEnte, String message) {
		this.listaSeriali = seriali;
		this.enteRichiedente = enteRichiedente;
		this.indirizzoEnte = indirizzoEnte;
		this.setMessage(message);
	}

	/**Restituisce la lista dei seriali
	 * @return listaSeriali La lista dei seriali da evadere
	 */
	public List<Seriale> getListaSeriali() {
		return listaSeriali;
	}

	/**Modifica la lista dei seriali
	 * @param listaSeriali La lista dei seriali da evadere
	 */
	public void setListaSeriali(List<Seriale> listaSeriali) {
		this.listaSeriali = listaSeriali;
	}

	/**Restituisce l'ente richiedente
	 * @return enteRichiedente Ente richiedente
	 */
	public String getEnteRichiedente() {
		return enteRichiedente;
	}

	/**Modifica l'ente richiedente
	 * @param enteRichiedente Ente richiedente
	 */
	public void setEnteRichiedente(String enteRichiedente) {
		this.enteRichiedente = enteRichiedente;
	}

	/**Restituisce l'indirizzo ente
	 * @return indirizzoEnte Indirizzo ente
	 */
	public String getIndirizzoEnte() {
		return indirizzoEnte;
	}

	/**Modifica l'indirizzo ente
	 * @param indirizzoEnte Indirizzo ente
	 */
	public void setIndirizzoEnte(String indirizzoEnte) {
		this.indirizzoEnte = indirizzoEnte;
	}

	/**Restituisce il messaggio contenuto nella notifica
	 * @return message Il messaggio contenuto nella notifica
	 */
	public String getMessage() {
		return message;
	}

	/**Modifica il messaggio presente sulla notifica
	 * @param message Il messaggio presente sulla notifica
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "NotificaEvasione [listaSeriali=" + listaSeriali + ", enteRichiedente=" + enteRichiedente
				+ ", indirizzoEnte=" + indirizzoEnte + ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enteRichiedente == null) ? 0 : enteRichiedente.hashCode());
		result = prime * result + ((indirizzoEnte == null) ? 0 : indirizzoEnte.hashCode());
		result = prime * result + ((listaSeriali == null) ? 0 : listaSeriali.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificaEvasione other = (NotificaEvasione) obj;
		if (enteRichiedente == null) {
			if (other.enteRichiedente != null)
				return false;
		} else if (!enteRichiedente.equals(other.enteRichiedente))
			return false;
		if (indirizzoEnte == null) {
			if (other.indirizzoEnte != null)
				return false;
		} else if (!indirizzoEnte.equals(other.indirizzoEnte))
			return false;
		if (listaSeriali == null) {
			if (other.listaSeriali != null)
				return false;
		} else if (!listaSeriali.equals(other.listaSeriali))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
}