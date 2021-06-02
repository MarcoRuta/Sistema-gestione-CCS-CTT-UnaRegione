package it.unisannio.ingegneriaDelSoftware.Classes.Notifiche;

import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.Beans.SerialeBean;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;

public class NotificaEvasione implements Notifica {
	
	private List<SerialeBean> listaSeriali;
	private String enteRichiedente;
	private String indirizzoEnte;

	public NotificaEvasione() {}
	
	public NotificaEvasione(List<SerialeBean> seriali, String enteRichiedente, String indirizzoEnte){
		this.listaSeriali = seriali;
		this.enteRichiedente = enteRichiedente;
		this.indirizzoEnte = indirizzoEnte;
	}
	
	public List<SerialeBean> getListaSeriali() {
		return listaSeriali;
	}
	public void setListaSeriali(List<SerialeBean> listaSeriali) {
		this.listaSeriali = listaSeriali;
	}
	
	public String getEnteRichiedente() {
		return enteRichiedente;
	}
	public void setEnteRichiedente(String enteRichiedente) {
		this.enteRichiedente = enteRichiedente;
	}
	public String getIndirizzoEnte() {
		return indirizzoEnte;
	}
	public void setIndirizzoEnte(String indirizzoEnte) {
		this.indirizzoEnte = indirizzoEnte;
	}

}
