package it.unisannio.DataManagers;

import java.util.Date;

import it.unisannio.Classes.Sacca;
import it.unisannio.TipiAggiuntivi.GruppoSanguigno;

public class MyOperatoreCTTDataManager {
	
	public boolean login(String username, String password) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		if(mm.getDipendente(username, password)!= null) return true;
		else return false;
	}

	public Sacca ricercaSaccaLocale(GruppoSanguigno gs, Date dataArrivoMassima, String enteRichiedente) {
		MyMongoDataManager mm = new MyMongoDataManager();
		Sacca s = mm.getSaccaPerRicerca(gs, dataArrivoMassima);
//Se non ci sono sacche del GruppoSanguigno desiderato, si effettua la stessa ricerca con le sacche compatibili
		if(s==null) s = mm.getSaccaCompatibilePerRicerca(gs, dataArrivoMassima);
//DatiSacca vanno aggiornati con enteRichiedente e dataAffidamento(con la data di oggi)				
		mm.setEnteRichiedenteDatiSacca(mm.getDatiSacca(s.getSeriale()), enteRichiedente);
		Date date = new Date();
		mm.setDataAffidamentoDatiSacca(mm.getDatiSacca(s.getSeriale()), date);
		return s;
	}
}
