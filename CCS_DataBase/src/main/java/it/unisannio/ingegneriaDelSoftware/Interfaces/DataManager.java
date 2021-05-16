package it.unisannio.ingegneriaDelSoftware.Interfaces;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Exceptions.CTTNotFoundException;

public interface DataManager {
	
	void createCTT(CTT c);

	void removeCTT(int numero);
	
	CTT getCTT(int numero) throws CTTNotFoundException;
	
	List<CTT> getListaCTT();
	
	Dipendente getDipendente(String username, String password);
}