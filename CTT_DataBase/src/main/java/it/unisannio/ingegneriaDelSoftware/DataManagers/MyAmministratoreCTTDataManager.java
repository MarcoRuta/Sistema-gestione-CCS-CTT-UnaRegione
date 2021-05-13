package it.unisannio.ingegneriaDelSoftware.DataManagers;
import java.util.ArrayList;
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
import it.unisannio.ingegneriaDelSoftware.Util.DateConverter;


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
		MyAmministratoreCTTDataManager mm = new MyAmministratoreCTTDataManager();
		return mm.getlistaDipendentiByRuolo(ruolo);
	}	
	
	
	/**Restituisce una lista di Sacche del GruppoSanguigno desiderato
	 * @param gs GruppoSanguigno desiderato
	 * @return la lista di sacche
	 */
	public List<Sacca> reportStatisticoSacche(GruppoSanguigno gs){
		MyAmministratoreCTTDataManager mm = new MyAmministratoreCTTDataManager();	
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
		MyAmministratoreCTTDataManager mm = new MyAmministratoreCTTDataManager();
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
		MyAmministratoreCTTDataManager mm = new MyAmministratoreCTTDataManager();
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

	/**Restituisce la lista dei datiSacche che sono transitate per un CTT in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return la lista di sacche che sono transitate per un CTT in un determinato arco temporale
	 */
	public List<DatiSacca> listaDatiSaccheInIntervallo(Date dataInizio, Date dataFine) {
		MyMongoDataManager mm = new MyMongoDataManager();
		
		List<DatiSacca> listaDati = mm.getListaDatiSacche();
		List<DatiSacca> datiSaccaTransitati = new ArrayList<DatiSacca>();
	    
	    Date dataArrivo = null;
    	Date dataAffidamento = null;
	    
	    for (DatiSacca datiSacca : listaDati) {
	    	 dataArrivo = DateConverter.convertLocalDateToDate(datiSacca.getDataArrivo());
	    	 dataAffidamento = DateConverter.convertLocalDateToDate(datiSacca.getDataAffidamento());
	    	     	 
	    	if((dataArrivo.after(dataInizio) && dataArrivo.before(dataFine))
	    			|| (dataAffidamento.after(dataInizio) && dataAffidamento.before(dataFine))) {

			datiSaccaTransitati.add(datiSacca);
	    	}
		}	    
		return datiSaccaTransitati;
	}
    
    /**Restituisce la lista delle Sacche di un determinato Gruppo sanguigno, presenti del database delle Sacche
	 * @param g Gruppo sanguigno delle Sacche che si vogliono ricercare
	 * @return la lista di Sacche di un determinato Gruppo sanguigno
	 */
	public List<Sacca> listaSaccheGS(GruppoSanguigno g){		
		MyMongoDataManager mm = new MyMongoDataManager();
		
		List<Sacca> saccheGS = new ArrayList<Sacca>();
		List<Sacca> listaSacche = mm.getListaSacche();

	    
		for (Sacca sacca : listaSacche) 
			if(sacca.getGruppoSanguigno().equals(g))
				saccheGS.add(sacca);
		
			
		return saccheGS;
	}
	
	/**Restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return la lista dei Dipendenti del Ruolo scelto
	 */
	public List<Dipendente> getlistaDipendentiByRuolo(RuoloDipendente ruolo) {
		MyMongoDataManager mm = new MyMongoDataManager();
		
		List<Dipendente> dipendenti = mm.getListaDipendenti();
        List<Dipendente> dipendentiRuolo = new ArrayList<Dipendente>();

        for (Dipendente dipendente : dipendenti)
        	if(dipendente.getRuolo().equals(ruolo))
        		dipendentiRuolo.add(dipendente);
        
        return dipendentiRuolo;
    }
}	
