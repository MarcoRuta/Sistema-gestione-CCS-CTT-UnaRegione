package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import org.junit.Test;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class LoginRestTest {
	
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget aggiuntaCTT = client.target("http://127.0.0.1:8080/rest/autentificazione");
	
	public static void setUp() throws EntityAlreadyExistsException {		
		Cdf cdf = Cdf.getCDF("KTMFSW67T64I460X");
	    LocalDate ld = LocalDate.parse("1978-10-10");
	    RuoloDipendente ruolo = RuoloDipendente.AmministratoreCCS;
	    String username = "admin";
	    String password = "Adminadmin1";
	    Dipendente dip = new Dipendente(cdf, "TestAdmin", "TestAdmin", ld, ruolo, username, password);
	    MongoDataManager mm = MongoDataManager.getInstance();
	    mm.createDipendente(dip);
	}

	public static void dropDBDip(){
		MongoDataManager mm = MongoDataManager.getInstance();
		mm.dropDB();
	} 
	  
	  @Test
	  public void test1() throws EntityAlreadyExistsException {
		  LoginRestTest.setUp();
		  Form form = new Form();
		  form.param("username", "admiN");
		  form.param("password", "Admin");
		  Response responseaddCTT = aggiuntaCTT.request().post(Entity.form(form));
		  assertEquals(Status.NOT_FOUND.getStatusCode(), responseaddCTT.getStatus());
		  LoginRestTest.dropDBDip();	  
		  }
	  
	  @Test
	  public void test2() throws EntityAlreadyExistsException{
		  LoginRestTest.setUp();
		  Form form = new Form();
		  form.param("username", "admin");
		  form.param("password", "Adminadmin1");
		  Response responseaddCTT = aggiuntaCTT.request().post(Entity.form(form));
		  assertEquals(Status.CREATED.getStatusCode(), responseaddCTT.getStatus());
		  LoginRestTest.dropDBDip();
	  }
	  
}