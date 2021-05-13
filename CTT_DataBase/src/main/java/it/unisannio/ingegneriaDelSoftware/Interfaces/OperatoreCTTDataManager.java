package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;

public interface OperatoreCTTDataManager {	

	Sacca ricercaSaccaLocale(GruppoSanguigno gs, LocalDate dataArrivoMassima, String enteRichiedente);	
	
	Sacca getSaccaPerRicerca(GruppoSanguigno gs, LocalDate dataAffidamento) throws ParseException; 	
	
	Sacca getSaccaCompatibilePerRicerca(GruppoSanguigno gs, LocalDate dataAffidamento);
	
	List<Sacca> getSaccheEntroScadenza(); 
	

	
}
