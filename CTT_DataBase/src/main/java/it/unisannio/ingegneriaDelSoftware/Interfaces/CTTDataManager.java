package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;;


public interface CTTDataManager {		

	List<Sacca> alertControlScadenza();
	
	void removeSaccaScaduta(Sacca s);
}