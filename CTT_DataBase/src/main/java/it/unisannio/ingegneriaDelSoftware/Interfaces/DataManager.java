package it.unisannio.ingegneriaDelSoftware.Interfaces;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface DataManager {

	void createSacca(Sacca s);

	void createDatiSacca(DatiSacca ds);

	void removeSacca(Seriale ser);

	Sacca getSacca(Seriale ser) throws EntityNotFoundException;

	DatiSacca getDatiSacca(Seriale ser) throws EntityNotFoundException;
	
	List<Sacca> getListaSacche();
	
	List<DatiSacca> getListaDatiSacche();

	boolean containsSacca(Seriale seriale);
	
	void setPrenotatoSacca(Seriale ser);

	void setEnteRichiedenteDatiSacca(Seriale seriale, String enteRichiedente);

	void setDataAffidamentoDatiSacca(Seriale seriale, LocalDate dataAffidamento);

	void setIndirizzoEnteDatiSacca(Seriale seriale, String indirizzo);
	
	void addDipendente(Dipendente d);

	void removeDipendente(Cdf cdf);
	
	Dipendente getDipendente(String username, String password) throws EntityNotFoundException;

	Dipendente getDipendente(Cdf cdf) throws EntityNotFoundException;

	/**Modifica la password di un Dipendente all'interno del DB
	 * @param password la nuova passworda da aggiungere
	 * @param cdf  il codice fiscale del Dipendente di cui si vuole aggiornare la password
	 * */
	public void setPassword(Cdf cdf, String password);

	/**Restituisce la lista dei Dipendenti del CTT presenti nel database
	 * @return la lista dei Dipendenti del CTT
	 */
	public List<Dipendente> getListaDipendenti();


}