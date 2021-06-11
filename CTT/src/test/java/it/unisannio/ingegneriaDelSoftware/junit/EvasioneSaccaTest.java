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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Cdf;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.User;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class EvasioneSaccaTest {
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget evasioneSacca = client.target("http://127.0.0.1:8081/rest/magazziniere/evasione");
	Seriale ser1 = new Seriale();
	Seriale ser2 = new Seriale();
	Seriale ser3 = new Seriale();
	
	@Before public  void populateDBSacche() throws EntityAlreadyExistsException {
	
    	List<Sacca> listaSacche = new ArrayList<Sacca>();
    	List<DatiSacca> listaDatiSacche = new ArrayList<DatiSacca>();
    	
    	//Caricamento sul sistema di cinque Sacche di tipo A+, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana (2022)
    	//Una sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca
		//simuliamo sia stata prenotata.
    	GruppoSanguigno gs = GruppoSanguigno.Ap;
    	LocalDate localDataProduzione = LocalDate.of(2020,04,10);
    	LocalDate localDataScadenza = LocalDate.now().plusDays(2);
    	Sacca sacca = new Sacca(ser1, gs, localDataProduzione, localDataScadenza, false);
    	sacca.setPrenotato();
    	listaSacche.add(sacca);
    	        
    	LocalDate localDataArrivo = LocalDate.of(2021,03,15);
    	String enteDonatore = "AVIS - Benevento";
    	DatiSacca datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	listaDatiSacche.add(datisacca); 

    	//Seconda sacca
		//simuliamo sia stata prenotata.
    	 gs = GruppoSanguigno.Ap;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 sacca = new Sacca(ser2, gs, localDataProduzione, localDataScadenza, false);
    	 sacca.setPrenotato();
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca
		//simuliamo sia stata prenotata
    	 gs = GruppoSanguigno.Ap;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(ser3, gs, localDataProduzione, localDataScadenza, false);
    	 sacca.setPrenotato();
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca);

    	MongoDataManager md = MongoDataManager.getInstance();
    	for(Sacca sac : listaSacche) {
    		md.createSacca(sac);
        }
    	
    	for(DatiSacca datisac : listaDatiSacche) {
    		md.createDatiSacca(datisac);
        }

    	Dipendente d = new Dipendente(Cdf.getCDF("PVFDTP90P61I426D"), "Mario", "Magazz", LocalDate.parse("1950-07-23", DateTimeFormatter.ofPattern(Constants.DATEFORMAT)), RuoloDipendente.MagazziniereCTT, "admin", "Adminadmin1");
        md.createDipendente(d);
    	
        Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8081/rest/autentificazione");
		Form form1 = new Form();
		form1.param("username", "admin");
		form1.param("password", "Adminadmin1");
		
		Response responselogin = login.request().post(Entity.form(form1));
		User user = responselogin.readEntity(User.class);
		token = user.getToken();  	
	}
	
	/** Test per il metodo rest/magazziniere/evasione del magazziniereCTT, che va a buon fine 
	 */
	@Test public void test1(){			
		List<Seriale> listaSeriali = new ArrayList<Seriale>();
		listaSeriali.add(ser1);
		listaSeriali.add(ser2);
		listaSeriali.add(ser3);
		String enteRichiedente= "Ospedale Rummo";
		String indirizzoEnte="Benevento, via pacevecchia 12";
		
		NotificaEvasione not= new NotificaEvasione(listaSeriali, enteRichiedente, indirizzoEnte, "messaggio");
		
		Response evasioneSaccaMagazz = evasioneSacca.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.json(not));
		assertEquals(Status.CREATED.getStatusCode(), evasioneSaccaMagazz.getStatus());
	}

	/** Test per il metodo rest/magazziniere/evasione del magazziniereCTT, 
	 * non funzionante siccome si cerca di evadere una Sacca non presente nel database delle Sacche
	 */
	@Test public void test2(){		
		List<Seriale> listaSeriali = new ArrayList<Seriale>();
		listaSeriali.add(ser1);
		Seriale nonPresente = new Seriale();
		listaSeriali.add(nonPresente);
		listaSeriali.add(ser3);
		String enteRichiedente= "Ospedale Rummo";
		String indirizzoEnte="Benevento, via pacevecchia 12";
		
		NotificaEvasione not= new NotificaEvasione(listaSeriali, enteRichiedente, indirizzoEnte, "messaggio");
		
		Response evasioneSaccaMagazz = evasioneSacca.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.json(not));
		assertEquals(Status.NOT_FOUND.getStatusCode(), evasioneSaccaMagazz.getStatus());
	}

	
	@After public void dropDBSacche() {
		MongoDataManager md = MongoDataManager.getInstance();
		md.dropDB();
	}

}