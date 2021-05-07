package it.unisannio.DataManagers;

import java.util.List;

import it.unisannio.Classes.Dipendente;
import it.unisannio.Classes.Sacca;
import it.unisannio.Interfaces.AmministratoreCTTDataManager;
import it.unisannio.TipiAggiuntivi.GruppoSanguigno;
import it.unisannio.TipiAggiuntivi.RuoloDipendente;

public class MyAmministratoreCTTDataManager implements AmministratoreCTTDataManager{

	public boolean login(String username, String password) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		if(mm.getDipendente(username, password)!= null) return true;
		else return false;
	}
	
	public void addDipendente(Dipendente d) {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.addDipendente(d);
	}

	public void removeDipendente(Dipendente d) {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.removeDipendente(d.getCdf());
	}

	public List<Dipendente> reportOperatoriCTT(RuoloDipendente ruolo) {
		MyMongoDataManager mm = new MyMongoDataManager();
		return mm.getlistaDipendentiByRuolo(ruolo);
	}	

	public List<Sacca> reportStatisticoSacche(GruppoSanguigno gs){
		MyMongoDataManager mm = new MyMongoDataManager();	
		List<Sacca> sacche = mm.listaSaccheGS(gs);
		return sacche;
	}	
}
