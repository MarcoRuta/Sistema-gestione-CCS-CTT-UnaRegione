package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;


public class Caricamento_Dati_Amministratore{
	
	@BeforeClass public static void populateDataBaseCTT() {
		MongoDataManager mm = new MongoDataManager();
		Cdf cdf = new Cdf("122hfotndj13ht5f");
	    LocalDate ld = LocalDate.of(2000,10,10);
	    RuoloDipendente ruolo = RuoloDipendente.AmministratoreCCS;
	    String username = "admin";
	    String password = "admin";
	    Dipendente dip = new Dipendente(cdf, "pino", "sfatto", ld, ruolo, username, password);
	    mm.addDipendente(dip);
	}
	
	
	 @Test
	  public void test1() {
		  Form form = new Form();
		  form.param("username", "admin");
		  form.param("password", "admin");
		  Response response = ClientBuilder.newClient().target("http://127.0.0.1:8080/rest/login").request().post(Entity.form(form));
		  assertEquals(Status.OK.getStatusCode(),response.getStatus());	 
	  }
	
	
	
}
	
