package it.unisannio.ingegneriaDelSoftware.Interfaces;
import it.unisannio.ingegneriaDelSoftware.Classes.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DataManager {

	void createSacca(Sacca s);

	void createDatiSacca(DatiSacca ds);

	void removeSacca(Seriale ser);

	Sacca getSacca(Seriale ser);

	
	DatiSacca getDatiSacca(Seriale ser);
	
	Sacca getSaccaPerRicerca(GruppoSanguigno gs, LocalDate dataAffidamento) throws ParseException; 	
	
	Sacca getSaccaCompatibilePerRicerca(GruppoSanguigno gs, LocalDate dataAffidamento);
	
	List<Sacca> getSaccheEntroScadenza(); 													

	List<Sacca> listaSaccheGS(GruppoSanguigno gs); 
	
	List<DatiSacca> listaDatiSaccheInIntervallo(Date dataInizio, Date dataFine);
	
	void setDataArrivoDatiSacca(Seriale seriale, LocalDate dataArrivo);

	void setEnteRichiedenteDatiSacca(Seriale seriale, String enteRichiedente);

	void setDataAffidamentoDatiSacca(Seriale seriale, LocalDate dataAffidamento);
	
	void addDipendente(Dipendente d);

	void removeDipendente(Cdf cdf);
	
	Dipendente getDipendente(String username, String password);
	
	List<Dipendente> getlistaDipendentiByRuolo(RuoloDipendente ruolo);
	
}