package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.User;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class AggiuntaSaccaMagazzinoTest {
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget aggiuntaSaccaMagazz = client.target("http://127.0.0.1:8080/rest/magazziniere/aggiuntaSacca");
	static MongoDataManager md = MongoDataManager.getInstance();

	/**
	 * Metodo statico per la popolazione del database
	 */
	public static void populateDB() throws EntityAlreadyExistsException {
        Dipendente d = new Dipendente(Cdf.getCDF("RLISNR72C54F356H"), "Mario", "Magazz", LocalDate.parse("1950-07-10", DateTimeFormatter.ofPattern(Constants.DATEFORMAT)), RuoloDipendente.MagazziniereCTT, "admin", "Adminadmin1");
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
		md.dropDB();
	}
	
	/**
	 * Test del metodo aggiuntaSaccaMagazzino dell'EndPointMagazziniere
	 * @throws EntityAlreadyExistsException
	 */
	@Test public void test1() throws EntityAlreadyExistsException {
		AggiuntaSaccaMagazzinoTest.dropDB();
		AggiuntaSaccaMagazzinoTest.populateDB();
		Form form1 = new Form();
		form1.param("gruppo_sanguigno", GruppoSanguigno.Ap.toString());
		form1.param("data_scadenza", "2023-11-10");
		form1.param("data_produzione", "2020-11-12");
		form1.param("ente_donatore", "DonatoreProva1");

		Response responseaddSaccaMagazz = aggiuntaSaccaMagazz.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
		assertEquals(Status.CREATED.getStatusCode(), responseaddSaccaMagazz.getStatus());
		AggiuntaSaccaMagazzinoTest.dropDB();
	}
	
	/**
	 * Test del metodo aggiuntaSaccaMagazzino dell'EndPointMagazziniere applicato su una sacca con dati errati
	 * @throws EntityAlreadyExistsException
	 */
	@Test public void test2() throws EntityAlreadyExistsException {
		AggiuntaSaccaMagazzinoTest.populateDB();
		Form form1 = new Form();
		form1.param("gruppo_sanguigno", GruppoSanguigno.Ap.toString());
		form1.param("data_scadenza", "2016-11-10");
		form1.param("data_produzione", "2016-11-12");
		form1.param("ente_donatore", "DonatoreProva1");

		Response responseaddSaccaMagazz = aggiuntaSaccaMagazz.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
		assertEquals(Status.BAD_REQUEST.getStatusCode(), responseaddSaccaMagazz.getStatus());
		AggiuntaSaccaMagazzinoTest.dropDB();
	}
}