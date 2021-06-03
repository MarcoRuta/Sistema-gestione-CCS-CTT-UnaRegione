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
import org.junit.AfterClass;
import org.junit.BeforeClass;
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

	
	@BeforeClass public static void populateDBSacche() throws EntityAlreadyExistsException {
	
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
    	Sacca sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
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
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
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
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
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
		WebTarget login = client.target("http://127.0.0.1:8080/rest/autentificazione");
		Form form1 = new Form();
		form1.param("username", "admin");
		form1.param("password", "Adminadmin1");
		
		Response responselogin = login.request().post(Entity.form(form1));
		User user = responselogin.readEntity(User.class);
		token = user.getToken();  	
	}
	
	
	/**
	 * Test 
	*/
	@Test public void test1(){
				
		Form form1 = new Form();
		form1.param("listaSeriali", "CTT001-00000001,CTT001-00000002,CTT001-00000003,");
		form1.param("enteRichiedente", "Ospedale Rummo");
		form1.param("indirizzoEnte", "Benevento, via pacevecchia 12");
				
		Response evasioneSaccaMagazz = evasioneSacca.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).put(Entity.form(form1));
		assertEquals(Status.OK.getStatusCode(), evasioneSaccaMagazz.getStatus());
	}

	@Test public void test2(){
		
		Form form1 = new Form();					//sacca non presente	
		form1.param("listaSeriali", "CTT001-00000001,CTT001-00000123,CTT001-00000003,");
		form1.param("enteRichiedente", "Ospedale Rummo");
		form1.param("indirizzoEnte", "Benevento, via pacevecchia 12");
		
		
		Response evasioneSaccaMagazz = evasioneSacca.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).put(Entity.form(form1));
		assertEquals(Status.NOT_FOUND.getStatusCode(), evasioneSaccaMagazz.getStatus());
	}

	

	@AfterClass public static void dropDBSacche() {
		MongoDataManager md = MongoDataManager.getInstance();
		md.dropDB();
	}

}