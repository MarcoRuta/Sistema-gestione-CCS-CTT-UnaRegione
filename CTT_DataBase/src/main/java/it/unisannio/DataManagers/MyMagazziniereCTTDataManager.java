package it.unisannio.DataManagers;

import java.text.ParseException;
import java.util.Date;

import it.unisannio.Classes.DatiSacca;
import it.unisannio.Classes.Sacca;
import it.unisannio.Interfaces.MagazziniereCTTDataManager;
import it.unisannio.TipiAggiuntivi.Seriale;

public class MyMagazziniereCTTDataManager implements MagazziniereCTTDataManager{
	
	public boolean login(String username, String password) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		if(mm.getDipendente(username, password)!= null) return true;
		else return false;
	}
	public String evasioneSacca(Sacca s, String enteRichiedente, Date dataAffidamento) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		mm.removeSacca(s.getSeriale());
		DatiSacca ds = mm.getDatiSacca(s.getSeriale());
		mm.setEnteRichiedenteDatiSacca(ds, enteRichiedente);
		mm.setDataAffidamentoDatiSacca(ds, dataAffidamento);
		return this.getSaccaMagazz(s.getSeriale());
	}

	public void removeSaccaScaduta(Sacca s) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		mm.removeSacca(s.getSeriale());
		DatiSacca ds = mm.getDatiSacca(s.getSeriale());
		mm.setDataAffidamentoDatiSacca(ds, s.getDataScadenza());
		mm.setEnteRichiedenteDatiSacca(ds, "Scaduta");
	}

	public void aggiuntaSaccaMagazz(Sacca s, DatiSacca ds) throws ParseException {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.createSacca(s);
		mm.createDatiSacca(ds);
	}
	
//metodo che dal seriale restituisce l'etichetta da stampare con Sacca e DatiSacca	
	private String getSaccaMagazz(Seriale ser) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		return mm.getSacca(ser).toStringPerEtichetta()+mm.getDatiSacca(ser).toStringPerEtichetta();	
	}
	
}
