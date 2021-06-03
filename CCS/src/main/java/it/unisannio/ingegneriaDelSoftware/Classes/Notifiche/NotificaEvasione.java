package it.unisannio.ingegneriaDelSoftware.Classes.Notifiche;

import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.Beans.SerialeBean;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;

public class NotificaEvasione implements Notifica {
	
	private List<SerialeBean> listaSeriali;
	private String enteRichiedente;
	private String indirizzoEnte;

	public NotificaEvasione() {}
	
	/**Metodo costruttore di NotificaEvasione
	 * @param seriali lista di seriali
	 * @param enteRichiedente ente richiedente dei DatiSacca delle sacche che vengono evase
	 * @param indirizzoEnte ente richiedente dei DatiSacca delle sacche che vengono evase
	 */
	public NotificaEvasione(List<SerialeBean> seriali, String enteRichiedente, String indirizzoEnte){
		this.listaSeriali = seriali;
		this.enteRichiedente = enteRichiedente;
		this.indirizzoEnte = indirizzoEnte;
	}
	
	
	/**Restituisce la lista dei seriali
	 * @return listaSeriali 
	 */
	public List<SerialeBean> getListaSeriali() {
		return listaSeriali;
	}
	
	
	/**Modifica la lista dei seriali
	 * @param listaSeriali
	 */
	public void setListaSeriali(List<SerialeBean> listaSeriali) {
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
}