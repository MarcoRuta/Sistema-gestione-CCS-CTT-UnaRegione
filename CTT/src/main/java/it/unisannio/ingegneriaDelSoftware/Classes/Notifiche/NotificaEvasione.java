package it.unisannio.ingegneriaDelSoftware.Classes.Notifiche;

import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;

public class NotificaEvasione implements Notifica {
	

	/**Lista dei seriali da evadere */
	private List<Seriale> listaSeriali;
	/**Ente che ha richiesto tali sacche */
	private String enteRichiedente;
	/**Indirizzo dell'ente che ha richiesto tali sacche */
	private String indirizzoEnte;

	public NotificaEvasione() {}
	
	/**Metodo costruttore di NotificaEvasione
	 * @param seriali lista di seriali
	 * @param enteRichiedente ente richiedente dei DatiSacca delle sacche che vengono evase
	 * @param indirizzoEnte ente richiedente dei DatiSacca delle sacche che vengono evase
	 */
	public NotificaEvasione(List<Seriale> seriali, String enteRichiedente, String indirizzoEnte){
		this.listaSeriali = seriali;
		this.enteRichiedente = enteRichiedente;
		this.indirizzoEnte = indirizzoEnte;
	}
	
	
	/**Restituisce la lista dei seriali
	 * @return listaSeriali 
	 */
	public List<Seriale> getListaSeriali() {
		return listaSeriali;
	}
	
	
	/**Modifica la lista dei seriali
	 * @param listaSeriali
	 */
	public void setListaSeriali(List<Seriale> listaSeriali) {
		this.listaSeriali = listaSeriali;
	}
	
	
	/**Restituisce l'ente richiedente
	 * @return enteRichiedente
	 */
	public String getEnteRichiedente() {
		return enteRichiedente;
	}
	
	/**Modifica l'ente richiedente
	 * @param enteRichiedente
	 */
	public void setEnteRichiedente(String enteRichiedente) {
		this.enteRichiedente = enteRichiedente;
	}
	
	
	/**Restituisce l'indirizzo ente
	 * @return indirizzoEnte
	 */
	public String getIndirizzoEnte() {
		return indirizzoEnte;
	}
	
	/**Modifica l'indirizzo ente
	 * @param indirizzoEnte
	 */
	public void setIndirizzoEnte(String indirizzoEnte) {
		this.indirizzoEnte = indirizzoEnte;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enteRichiedente == null) ? 0 : enteRichiedente.hashCode());
		result = prime * result + ((indirizzoEnte == null) ? 0 : indirizzoEnte.hashCode());
		result = prime * result + ((listaSeriali == null) ? 0 : listaSeriali.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		return "NotificaEvasione [listaSeriali=" + listaSeriali + ", enteRichiedente=" + enteRichiedente
				+ ", indirizzoEnte=" + indirizzoEnte + "]";
	}
	
	
}