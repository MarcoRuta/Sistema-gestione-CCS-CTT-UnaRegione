package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.User;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManagerBean;

public class AggiuntaSaccaMagazzinoTest {
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget aggiuntaSaccaMagazz = client.target("http://127.0.0.1:8080/rest/magazziniere/aggiuntaSacca");


	
	@BeforeClass public static void populateDBSacche() throws SaccaNotFoundException, ParseException {
		
    	
   


        Dipendente d = new Dipendente(Cdf.getCDF("999hpoindj13ht9f"), "Mario", "Magazz", DateUtil.convertDateToLocalDate(Constants.sdf.parse("10-07-1950")), RuoloDipendente.MagazziniereCTT, "admin", "admin");
        MongoDataManagerBean.createDipendente(d);
    	
        Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8080/rest/autentificazione");
		Form form1 = new Form();
		form1.param("username", "admin");
		form1.param("password", "admin");
		
		Response responselogin = login.request().post(Entity.form(form1));
		User user = responselogin.readEntity(User.class);
		token = user.getToken();
	}
	

	
	@Test public void test1() {
		Form form1 = new Form();
		form1.param("gruppo_sanguigno", GruppoSanguigno.Ap.toString());
		form1.param("data_scadenza", "2023-11-10");
		form1.param("data_produzione", "2020-11-12");
		form1.param("ente_donatore", "DonatoreProva1");

		Response responseaddSaccaMagazz = aggiuntaSaccaMagazz.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
		assertEquals(Status.CREATED.getStatusCode(), responseaddSaccaMagazz.getStatus());
	}
	
	@Test public void test2() {
		Form form1 = new Form();
		form1.param("gruppo_sanguigno", GruppoSanguigno.Ap.toString());
		form1.param("data_scadenza", "2016-11-10");
		form1.param("data_produzione", "2016-11-12");
		form1.param("ente_donatore", "DonatoreProva1");

		Response responseaddSaccaMagazz = aggiuntaSaccaMagazz.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
		assertEquals(Status.BAD_REQUEST.getStatusCode(), responseaddSaccaMagazz.getStatus());
	}
	
	

	
	
	@AfterClass public static void dropDBSacche() {
		MongoDataManagerBean.dropDB();
	}
}