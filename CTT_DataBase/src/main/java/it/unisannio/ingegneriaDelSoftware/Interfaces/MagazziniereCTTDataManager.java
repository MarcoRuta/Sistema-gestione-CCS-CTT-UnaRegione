package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.time.LocalDate;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;

public interface MagazziniereCTTDataManager {

	void aggiuntaSaccaMagazzino(Sacca unaSacca, String enteDonatore);	
	
	String evasioneSacca(Sacca s, String enteRichiedente, LocalDate dataAffidamento);
	
}
