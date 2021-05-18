package it.unisannio.ingegneriaDelSoftware.Interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;
import java.util.List;


public interface EndPointAmministratoreCTT {

	/**Aggiunge un Dipendente al DataBase
	 * @param cdf Dipendente da aggiungere al DataBase
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param ruolo
	 * @param username
	 * @param password
	 * @return
	 */
	public Response addDipendente(String cdf,
								  String nome, String cognome,
								  String dataDiNascita,
								  String ruolo,
								  String username,
								  String password);


	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return
	 */
	public Response removeDipendente(String cdf) ;


	/**Restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return la lista dei Dipendenti del Ruolo scelto
	 */
	public List<Dipendente> reportOperatoriCTT(String ruolo);

	
	/**Restituisce la lista delle Sacche di un determinato Gruppo sanguigno, presenti del database delle Sacche
	 * @param gs Gruppo sanguigno delle Sacche che si vogliono ricercare
	 * @return la lista di Sacche di un determinato Gruppo sanguigno
	 * @throws SaccaNotFoundException
	 */
	public List<Sacca> reportStatisticoSacche(String gs);



	/**Restituisce la lista dei datiSacche che sono transitate per un CTT in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return la lista di sacche che sono transitate per un CTT in un determinato arco temporale
	 */
	public Response ReportLocaleSaccheInviateERicevuteCTT(String dataInizio,
														  String dataFine);


	/**Restituisce il numero di Sacche ricevute e inviate in un arco temporale, per ogni Gruppo sanguigno scelto dall'AmministratoreCTT
	 * @param listaGS Lista dei Gruppi sanguigni scelti dall'AmministratoreCTT, una strigna con gruppo1:gruppo2:gruppo3
	 * @param dataInizio Data di inizio dell' arco temporale
	 * @param dataFine Data di fine dell' arco temporale
	 * @return la stringa contenente il numero di Sacche ricevute e inviate in un arco temporale, per ogni Gruppo sanguigno
	 */
	public Response OrdinaGruppiSanguigniPerRichieste(String listaGS,
													  String dataInizio,
													  String dataFine);

	/**Metodo tramite il quale è possibile accedere alla lista di dipendenti che lavorano al CTT
	 * @return  la lista di dipendenti che lavorano al CTT*/
	public List<Cdf> getDipendenti();
}