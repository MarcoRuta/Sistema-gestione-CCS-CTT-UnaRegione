package it.unisannio.ingegneriaDelSoftware.EndPointNotifiche;

import java.util.ArrayList;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;


public class EndPointNotificheMagazziniere {
	
	private ArrayList<Notifica> notificheDiEvasione;
	


	public static int aggiungiNotificaEvasione(List<String> listaSeriali, String enteRichiedente, String indirizzoEnte) {
		
		NotificaEvasione notifica = new NotificaEvasione(listaSeriali,enteRichiedente,indirizzoEnte);
		
		
		return 0;
		
		
		
	}



	public ArrayList<Notifica> getNotificheDiEvasione() {
		return notificheDiEvasione;
	}



	public void setNotificheDiEvasione(ArrayList<Notifica> notificheDiEvasione) {
		this.notificheDiEvasione = notificheDiEvasione;
	}

}
