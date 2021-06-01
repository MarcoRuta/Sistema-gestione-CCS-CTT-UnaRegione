package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Beans.User;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;


public class EvasioneSaccaTest {
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget evasioneSacca = client.target("http://127.0.0.1:8080/rest/magazziniere/evasione");
	static MongoDataManager md = MongoDataManager.getInstance();
	
	/**
	 * Metodo statico per la popolazione del database
	 */
	public static void populateDB() throws EntityAlreadyExistsException {
    	List<Sacca> listaSacche = new ArrayList<Sacca>();
    	List<DatiSacca> listaDatiSacche = new ArrayList<DatiSacca>();
    	
    	//Prima sacca
    	GruppoSanguigno gs = GruppoSanguigno.Ap;
    	LocalDate localDataProduzione = LocalDate.of(2020,04,10);
    	LocalDate localDataScadenza = LocalDate.now().plusDays(2);
    	Sacca sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	sacca.setPrenotato();
    	listaSacche.add(sacca);
    	        
    	LocalDate localDataArrivo = LocalDate.of(2021,03,15);
    	String enteDonatore = "AVIS - Benevento";
    	DatiSacca datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	listaDatiSacche.add(datisacca); 

    	//Seconda sacca
    	 gs = GruppoSanguigno.Am;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 sacca.setPrenotato();
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca
    	 gs = GruppoSanguigno.Bp;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 sacca.setPrenotato();
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca);

    	
    	for(Sacca sac : listaSacche) {
    		md.createSacca(sac);
        }
    	
    	for(DatiSacca datisac : listaDatiSacche) {
    		md.createDatiSacca(datisac);
        }

    	Dipendente d = new Dipendente(Cdf.getCDF("PVFDTP90P61I426D"), "Mario", "Magazz", LocalDate.parse("1950-07-23", DateTimeFormatter.ofPattern(Constants.DATEFORMAT)), RuoloDipendente.MagazziniereCTT, "admin", "Adminadmin1");
        md.createDipendente(d);
    	
        Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8080/rest/autentificazione");
		Form form1 = new Form();
		form1.param("username", "admin");
		form1.param("password", "Adminadmin1");
		
		Response responselogin = login.request().post(Entity.form(form1));
		User user = responselogin.readEntity(User.class);
		token = user.getToken();  	
	}
	
	/**
	 * Metodo statico per la distruzione del database 
	 */
	public static void dropDB() {
		MongoDataManager md = MongoDataManager.getInstance();
		md.dropDB();
	}

	/**
	 * Test del metodo removeDipendente dell'EndPointMagazziniereCTT
	 * @throws EntityAlreadyExistsException 
	*/
	@Test public void test1() throws EntityAlreadyExistsException{
		EvasioneSaccaTest.populateDB();		
		Form form1 = new Form();	
		form1.param("listaSeriali", "CTT001-00000121,CTT001-00000122,CTT001-00000123,");
		//123,124,125 SONO STATI INSERITI PER FAR FUNZIONARE IL BRANCH COVERING, MA DI BASE ANDREBBE MESSA LA RIGA SOTTO 1,2,3
		//form1.param("listaSeriali", "CTT001-00000001,CTT001-00000002,CTT001-00000003,");
		form1.param("enteRichiedente", "Ospedale Rummo");
		form1.param("indirizzoEnte", "Benevento, via pacevecchia 12");
		Response evasioneSaccaMagazz = evasioneSacca.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
		assertEquals(Status.CREATED.getStatusCode(), evasioneSaccaMagazz.getStatus());
		EvasioneSaccaTest.dropDB();
	}
	
	/**
	 * Test del metodo removeDipendente dell'EndPointMagazziniereCTT con sacche non presenti
	 * @throws EntityAlreadyExistsException 
	*/
	@Test public void test2() throws EntityAlreadyExistsException{
		EvasioneSaccaTest.dropDB();
		EvasioneSaccaTest.populateDB();
		Form form1 = new Form();						
		form1.param("listaSeriali", "CTT001-00000001,CTT001-00000123,CTT001-00000003,");
		form1.param("enteRichiedente", "Ospedale Rummo");
		form1.param("indirizzoEnte", "Benevento, via pacevecchia 12");
		Response evasioneSaccaMagazz = evasioneSacca.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
		assertEquals(Status.NOT_FOUND.getStatusCode(), evasioneSaccaMagazz.getStatus());
		EvasioneSaccaTest.dropDB();
	}
}