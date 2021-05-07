package it.unisannio.Interfaces;

import java.text.ParseException;
import java.util.Date;

import it.unisannio.Classes.DatiSacca;
import it.unisannio.Classes.Sacca;

public interface MagazziniereCTTDataManager {
	boolean login(String username, String password);
				
	String evasioneSacca(Sacca s, String enteRichiedente, Date dataAffidamento); //rimuove Sacca dal db
			
	void removeSaccaScaduta(Sacca s);
			
	void aggiuntaSaccaMagazz(Sacca s, DatiSacca ds) throws ParseException;
		
}
