package it.unisannio.ingegneriaDelSoftware.DataManagers;

import java.util.Date;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Interfaces.AmministratoreCTTDataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DipendenteCTT;


public class MyAmministratoreCTTDataManager implements AmministratoreCTTDataManager, DipendenteCTT{

	/**Login è l'operazione con la quale AmministratoreCTT accede al sistema
	 * @param username Username che usa AmministratoreCTT per entrare nel sistema
	 * @param password Password che usa AmministratoreCTT per entrare nel sistema
	 * @return true se username e password corrispondono; false altrimenti
	 */
	public boolean login(String username, String password) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		if(mm.getDipendente(username, password)!= null) return true;
		else return false;
	}

	
	/**Aggiunge un Dipendente al DataBase
	 * @param d Dipendente da aggiungere al DataBase
	 */
	public void addDipendente(Dipendente d) {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.addDipendente(d);
	}

	
	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del dipendente da rimuovere dal DataBase
	 */
	public void removeDipendente(Cdf cdf) {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.removeDipendente(cdf);
	}
	
	
	/**Restituisce una lista di Dipendenti del Ruolo desiderato 
	 * @param ruolo Ruolo desiderato
	 * @return la lista di dipendenti
	 */
	public List<Dipendente> reportOperatoriCTT(RuoloDipendente ruolo) {
		MyMongoDataManager mm = new MyMongoDataManager();
		return mm.getlistaDipendentiByRuolo(ruolo);
	}	
	
	
	/**Restituisce una lista di Sacche del GruppoSanguigno desiderato
	 * @param gs GruppoSanguigno desiderato
	 * @return la lista di sacche
	 */
	public List<Sacca> reportStatisticoSacche(GruppoSanguigno gs){
		MyMongoDataManager mm = new MyMongoDataManager();	
		List<Sacca> sacche = mm.listaSaccheGS(gs);
		return sacche;
	}

	
	/**Restituisce il numero di Sacche ricevute e inviate in un arco temporale, per ogni Gruppo sanguigno scelto dall'AmministratoreCTT
	 * @param lista Lista dei Gruppi sanguigni scelti dall'AmministratoreCTT
	 * @param dataInizio Data di inizio dell' arco temporale
	 * @param dataFine Data di fine dell' arco temporale
	 * @return la stringa contenente il numero di Sacche ricevute e inviate in un arco temporale, per ogni Gruppo sanguigno
	 */
	public String OrdinaGruppiSanguigniPerRichieste(List<GruppoSanguigno> lista, Date dataInizio, Date dataFine) {
		MyMongoDataManager mm = new MyMongoDataManager();
		String risultatoQuery = null;
		
		List<DatiSacca> listaDatiSaccheInIntervallo = mm.listaDatiSaccheInIntervallo(dataInizio, dataFine);
				
		for(GruppoSanguigno gs: lista) {
			if(risultatoQuery==null) risultatoQuery = getNumeroRichiesteByGS(gs, listaDatiSaccheInIntervallo);
			else risultatoQuery = risultatoQuery+"\n"+getNumeroRichiesteByGS(gs, listaDatiSaccheInIntervallo);
		}		
		return risultatoQuery;
	}

	
	/**Restituisce il numero di Sacche ricevute e inviate in un arco temporale
	 * @param dataInizio Data di inizio dell' arco temporale
	 * @param dataFine Data di inizio dell' arco temporale
	 * @return la lista di Sacche
	 */
	public List<DatiSacca> ReportLocaleSaccheInviateERicevuteCTT(Date dataInizio, Date dataFine) {
		MyMongoDataManager mm = new MyMongoDataManager();
		List<DatiSacca> listaDatiSacche = mm.listaDatiSaccheInIntervallo(dataInizio, dataFine);
		return listaDatiSacche;
	}

	
	/**Restituisce il numero di richieste per un determinato Gruppo sanguigno
	 * @param gs Gruppo sanguigno sul quale viene fatta la ricerca
	 * @param lista Lista di Sacche su cui viene effettuata la query
	 * @return la stringa contenente il Gruppo sanguigno più in numero di richieste
	 */
	private String getNumeroRichiesteByGS(GruppoSanguigno gs, List<DatiSacca> lista) {
	    int numRichieste = 0;
		@SuppressWarnings("unused")
		String risultato = null;
		
		for (DatiSacca ds : lista) {
			if(ds.getGruppoSanguigno().equals(gs)) numRichieste++;		
		}	
		return risultato = "Gruppo sanguigno "+gs.toString()+ "= "+numRichieste;
	}
}	