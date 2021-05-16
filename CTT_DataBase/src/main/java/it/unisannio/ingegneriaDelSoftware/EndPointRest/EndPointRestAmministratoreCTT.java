package it.unisannio.ingegneriaDelSoftware.EndPointRest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointAmministratoreCTT;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;


public class EndPointRestAmministratoreCTT implements EndPointAmministratoreCTT{

	
	/**Aggiunge un Dipendente al DataBase
	 * @param d Dipendente da aggiungere al DataBase
	 */
	public void addDipendente(Dipendente d) {
		MongoDataManager mm = new MongoDataManager();
		mm.addDipendente(d);
	}

	
	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del dipendente da rimuovere dal DataBase
	 */
	public void removeDipendente(Cdf cdf) {
		MongoDataManager mm = new MongoDataManager();
		mm.removeDipendente(cdf);
	}
	
	
	/**Restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return la lista dei Dipendenti del Ruolo scelto
	 */
	public List<Dipendente> reportOperatoriCTT(RuoloDipendente ruolo) {
		MongoDataManager mm = new MongoDataManager();
		
		List<Dipendente> dipendenti = mm.getListaDipendenti();
        List<Dipendente> dipendentiRuolo = new ArrayList<Dipendente>();

        for (Dipendente dipendente : dipendenti)
        	if(dipendente.getRuolo().equals(ruolo))
        		dipendentiRuolo.add(dipendente);
        
        return dipendentiRuolo;
	}	
	
	
	/**Restituisce la lista delle Sacche di un determinato Gruppo sanguigno, presenti del database delle Sacche
	 * @param g Gruppo sanguigno delle Sacche che si vogliono ricercare
	 * @return la lista di Sacche di un determinato Gruppo sanguigno
	 * @throws SaccaNotFoundException 
	 */
	public List<Sacca> reportStatisticoSacche(GruppoSanguigno gs) throws SaccaNotFoundException{	
		MongoDataManager mm = new MongoDataManager();
		List<Sacca> saccheGS = new ArrayList<Sacca>();
		
		List<Sacca> listaSacche = mm.getListaSacche();	    
		for (Sacca sacca : listaSacche) { 
			if(sacca.getGruppoSanguigno().equals(gs)) saccheGS.add(sacca);
		}			
		return saccheGS;
	}

	
	/**Restituisce la lista dei datiSacche che sono transitate per un CTT in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return la lista di sacche che sono transitate per un CTT in un determinato arco temporale
	 */
	public List<DatiSacca> ReportLocaleSaccheInviateERicevuteCTT(Date dataInizio, Date dataFine) {
		MongoDataManager mm = new MongoDataManager();
		
		List<DatiSacca> listaDati = mm.getListaDatiSacche();
		List<DatiSacca> datiSaccaTransitati = new ArrayList<DatiSacca>();
	    
	    Date dataArrivo = null;
    	Date dataAffidamento = null;
	    
	    for (DatiSacca datiSacca : listaDati) {
	    	 dataArrivo = DateUtil.convertLocalDateToDate(datiSacca.getDataArrivo());
	    	 dataAffidamento = DateUtil.convertLocalDateToDate(datiSacca.getDataAffidamento());
	    	     	 
	    	if((dataArrivo.after(dataInizio) && dataArrivo.before(dataFine))
	    			|| (dataAffidamento.after(dataInizio) && dataAffidamento.before(dataFine))) {

			datiSaccaTransitati.add(datiSacca);
	    	}
		}	    
		return datiSaccaTransitati;
	}


	/**Restituisce il numero di Sacche ricevute e inviate in un arco temporale, per ogni Gruppo sanguigno scelto dall'AmministratoreCTT
	 * @param lista Lista dei Gruppi sanguigni scelti dall'AmministratoreCTT
	 * @param dataInizio Data di inizio dell' arco temporale
	 * @param dataFine Data di fine dell' arco temporale
	 * @return la stringa contenente il numero di Sacche ricevute e inviate in un arco temporale, per ogni Gruppo sanguigno
	 */
	public String OrdinaGruppiSanguigniPerRichieste(List<GruppoSanguigno> lista, Date dataInizio, Date dataFine) {
		String risultatoQuery = null;		
		List<DatiSacca> listaDatiSaccheInIntervallo = ReportLocaleSaccheInviateERicevuteCTT(dataInizio, dataFine);
				
		for(GruppoSanguigno gs: lista) {
			if(risultatoQuery==null) risultatoQuery = getNumeroRichiesteByGS(gs, listaDatiSaccheInIntervallo);
			else risultatoQuery = risultatoQuery+"\n"+getNumeroRichiesteByGS(gs, listaDatiSaccheInIntervallo);
		}		
		return risultatoQuery;
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