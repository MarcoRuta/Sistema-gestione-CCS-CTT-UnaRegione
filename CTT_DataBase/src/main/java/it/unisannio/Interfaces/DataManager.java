package it.unisannio.Interfaces;
import java.util.Date;
import java.util.List;

import it.unisannio.Classes.DatiSacca;
import it.unisannio.Classes.Dipendente;
import it.unisannio.Classes.Sacca;
import it.unisannio.TipiAggiuntivi.Cdf;
import it.unisannio.TipiAggiuntivi.GruppoSanguigno;
import it.unisannio.TipiAggiuntivi.RuoloDipendente;
import it.unisannio.TipiAggiuntivi.Seriale;

public interface DataManager {
//	void createDB();
	void dropDB();

//INTERFACCIA DEL DB(OPERAZIONI BASE CRUD)
	
/**
 * @param Sacca - Sacca da aggiungere al db (non null)
 * @return void - 
 */	
	void createSacca(Sacca s); //crea e aggiunge Sacca al db (completo di tutti i dati): utilizzato quando arriva un sacca al db che è stata inviata da un altro CTT
	void createDatiSacca(DatiSacca ds); //crea e aggiunge DatiSacca al db (completo di tutti i dati)
		
	void removeSacca(Seriale ser);
				
	Sacca getSacca(Seriale ser);														//utilizzato per getSaccaMagazz : stampa dell'etichetta
	Sacca getSaccaPerRicerca(GruppoSanguigno gs, Date dataAffidamento); 				//utilizzato per RicercaSaccaLocale	
	Sacca getSaccaCompatibilePerRicerca(GruppoSanguigno gs, Date dataAffidamento);	 	//utilizzato per RicercaSaccaLocale	
	DatiSacca getDatiSacca(Seriale ser);
	
	List<Sacca> getSaccaEntroScadenza(); 												//utilizzato per Alert
	List<Sacca> listaSaccheGS(GruppoSanguigno gs); 
			
	void setDataArrivoDatiSacca(DatiSacca ds, Date dataArrivo);
	void setEnteRichiedenteDatiSacca(DatiSacca ds, String enteRichiedente);	
	void setDataAffidamentoDatiSacca(DatiSacca ds, Date dataAffidamento);	
	
	
	void addDipendente(Dipendente d);
	void removeDipendente(Cdf cdf);
	
	Dipendente getDipendente(String username, String password); //Restituisce il dipendente che ha quella coppia specifica username e password : utilizzato per il login
	List<Dipendente> getlistaDipendentiByRuolo(RuoloDipendente ruolo); //utilizzato per report statistico sui dipendenti
}