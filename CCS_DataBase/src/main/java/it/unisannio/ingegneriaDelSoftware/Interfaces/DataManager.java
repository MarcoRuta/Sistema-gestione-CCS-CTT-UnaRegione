package it.unisannio.ingegneriaDelSoftware.Interfaces;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Exceptions.CTTNotFoundException;

public interface DataManager {
	
	void createCTT(CTT c);

	void removeCTT(int numero);
	
	Dipendente getDipendente(String username, String password);
}