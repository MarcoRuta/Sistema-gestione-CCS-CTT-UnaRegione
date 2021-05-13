package it.unisannio.ingegneriaDelSoftware.Interfaces;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import java.time.LocalDate;
import java.util.List;

public interface DataManager {

	void createSacca(Sacca s);

	void createDatiSacca(DatiSacca ds);

	void removeSacca(Seriale ser);

	Sacca getSacca(Seriale ser);

	DatiSacca getDatiSacca(Seriale ser);
	
	List<Sacca> getListaSacche();
	
	List<DatiSacca> getListaDatiSacche();
	
	void setPrenotatoSacca(Seriale ser);
	
	void setDataArrivoDatiSacca(Seriale seriale, LocalDate dataArrivo);

	void setEnteRichiedenteDatiSacca(Seriale seriale, String enteRichiedente);

	void setDataAffidamentoDatiSacca(Seriale seriale, LocalDate dataAffidamento);
	
	void addDipendente(Dipendente d);

	void removeDipendente(Cdf cdf);
	
	Dipendente getDipendente(String username, String password);
	
	
}