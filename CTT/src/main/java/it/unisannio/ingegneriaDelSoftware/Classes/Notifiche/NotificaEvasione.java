package it.unisannio.ingegneriaDelSoftware.Classes.Notifiche;

import java.util.List;
import java.util.Objects;

import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;

public class NotificaEvasione implements Notifica {
	
	private List<Seriale> listaSeriali;
	private String enteRichiedente;
	private String indirizzoEnte;

	
	public NotificaEvasione(List<Seriale> seriali, String enteRichiedente, String indirizzoEnte){
		this.listaSeriali = seriali;
		this.enteRichiedente = enteRichiedente;
		this.indirizzoEnte = indirizzoEnte;
	}
	
	public List<Seriale> getListaSeriali() {
		return listaSeriali;
	}

	public void setListaSeriali(List<Seriale> listaSeriali) {
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

	@Override
	public String toString() {
		return "NotificaEvasione{" +
				"listaSeriali=" + listaSeriali +
				", enteRichiedente='" + enteRichiedente + '\'' +
				", indirizzoEnte='" + indirizzoEnte + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NotificaEvasione that = (NotificaEvasione) o;
		return listaSeriali.equals(that.listaSeriali);
	}

	@Override
	public int hashCode() {
		return Objects.hash(listaSeriali);
	}
}
