package it.unisannio.ingegneriaDelSoftware.Interfaces;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface DataManager {

	void createSacca(Sacca s) throws SaccaNotFoundException;

	void createDatiSacca(DatiSacca ds);

	void removeSacca(Seriale ser) throws SaccaNotFoundException;

	Sacca getSacca(Seriale ser) throws SaccaNotFoundException;

	DatiSacca getDatiSacca(Seriale ser);
	
	List<Sacca> getListaSacche() throws SaccaNotFoundException;
	
	List<DatiSacca> getListaDatiSacche();

	boolean containsSacca(Seriale seriale) throws SaccaNotFoundException;
	
	void setPrenotatoSacca(Seriale ser) throws SaccaNotFoundException;

	void setEnteRichiedenteDatiSacca(Seriale seriale, String enteRichiedente);

	void setDataAffidamentoDatiSacca(Seriale seriale, LocalDate dataAffidamento);

	void setIndirizzoEnteDatiSacca(Seriale seriale, String indirizzo);
	
	void addDipendente(Dipendente d);

	void removeDipendente(Cdf cdf);
	
	Dipendente getDipendente(String username, String password);


}