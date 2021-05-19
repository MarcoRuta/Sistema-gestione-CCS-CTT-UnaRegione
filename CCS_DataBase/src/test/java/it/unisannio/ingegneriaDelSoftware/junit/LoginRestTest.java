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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;


public class LoginRestTest {
	
	static Client client = ClientBuilder.newClient();
	static WebTarget rimozioneCTT = null;
	static WebTarget login = null;
	
	  @BeforeClass
	  public static void setUp(){
		login = client.target("http://127.0.0.1:8080/rest/login");
		rimozioneCTT = client.target("http://127.0.0.1:8080/rest/CCS/rimozioneCTT/3");
		
		MongoDataManager mm = new MongoDataManager();
		Cdf cdf = new Cdf("122hfotndj13ht5f");
	    LocalDate ld = LocalDate.of(1998,10,10);
	    RuoloDipendente ruolo = RuoloDipendente.AmministratoreCCS;
	    String username = "admin";
	    String password = "admin";
	    Dipendente dip = new Dipendente(cdf, "TestAdmin", "TestAdmin", ld, ruolo, username, password);
	    mm.addDipendente(dip);

	  }

	  
	  @Test
	  public void test1() {
		  Form form = new Form();
		  form.param("username", "admiN");
		  form.param("password", "Admin");
		  Response response = login.request().post(Entity.form(form));
		  assertEquals(Status.FORBIDDEN.getStatusCode(),response.getStatus());	 
	  }
	  
	  @Test
	  public void test2() {
		  Form form = new Form();
		  form.param("username", "admin");
		  form.param("password", "admin");
		  Response response = login.request().post(Entity.form(form));
		  assertEquals(Status.OK.getStatusCode(),response.getStatus());	 
	  }
	  
	@AfterClass public static void drop(){
	
	MongoDataManager mm = new MongoDataManager();
	mm.dropDB();
	}
	  
}
