package it.unisannio.DataManagers;

import java.util.List;

import it.unisannio.Classes.Sacca;
import it.unisannio.Interfaces.CTTDataManager;

public class MyCTTDataManager implements CTTDataManager{
		
	public boolean login(String username, String password) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		if(mm.getDipendente(username, password)!= null) return true;
		else return false;
	}

	public List<Sacca> alertControlScadenza() {
		MyMongoDataManager mm = new MyMongoDataManager();
		return mm.getSaccaEntroScadenza();
	}
}
