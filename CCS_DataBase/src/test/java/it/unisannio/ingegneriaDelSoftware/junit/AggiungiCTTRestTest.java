package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;


public class AggiungiCTTRestTest {
	
	static NewCookie cookie = null;
	static WebTarget rimozioneCTT = null;
	static WebTarget login = null;
	
	  @BeforeClass
	  public static void setUp(){
		
		MongoDataManager mm = new MongoDataManager();
		Cdf cdf = new Cdf("122hfotndj13ht5f");
	    LocalDate ld = LocalDate.of(1998,10,10);
	    RuoloDipendente ruolo = RuoloDipendente.AmministratoreCCS;
	    String username = "admin";
	    String password = "admin";
	    Dipendente dip = new Dipendente(cdf, "TestAdmin", "TestAdmin", ld, ruolo, username, password);
	    mm.addDipendente(dip);
	    
	    Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8080/rest/login");
		Form form = new Form();
		form.param("username", "admin");
		form.param("password", "admin");
		Response responselogin = login.request().post(Entity.form(form));
		cookie = responselogin.getCookies().get("access_token");
	  }
	  
  @Test	public void testAggiuntaCTTCorretto(){
	Client client = ClientBuilder.newClient();
	WebTarget aggiuntaCTT = client.target("http://127.0.0.1:8080/rest/CCS/aggiuntaCTT");
	Invocation.Builder invocationBuilder = aggiuntaCTT.request(MediaType.TEXT_PLAIN);
	invocationBuilder.cookie(cookie);
	Form form1 = new Form();
	form1.param("numero_ctt", "5");
	form1.param("nome_ctt", "CTT005");
	form1.param("provincia", "BN");
	form1.param("citta", "Campolattaro");
	form1.param("indirizzo", "Via del testing 12");
	form1.param("telefono", "0821432576");
	form1.param("email", "CTT005@gmail.com");
	form1.param("latitude", "65");
	form1.param("longitude", "41");
	Response responseaddCTT = invocationBuilder.post(Entity.form(form1));
	assertEquals(Status.OK.getStatusCode(), responseaddCTT.getStatus());
	}
  
  @Test	public void testWrong_numero_ctt(){
	Client client = ClientBuilder.newClient();
	WebTarget aggiuntaCTT = client.target("http://127.0.0.1:8080/rest/CCS/aggiuntaCTT");
	Invocation.Builder invocationBuilder = aggiuntaCTT.request(MediaType.TEXT_PLAIN);
	invocationBuilder.cookie(cookie);
	Form form1 = new Form();
	form1.param("numero_ctt", "a");						//è stato inserito un input non valido per il campo numero_ctt
	form1.param("nome_ctt", "CTT005");
	form1.param("provincia", "BN");
	form1.param("citta", "Campolattaro");
	form1.param("indirizzo", "Via del testing 12");
	form1.param("telefono", "0821432576");
	form1.param("email", "CTT005@gmail.com");
	form1.param("latitude", "65");
	form1.param("longitude", "41");
	Response responseaddCTT = invocationBuilder.post(Entity.form(form1));
	assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), responseaddCTT.getStatus());
	}
  
  @Test	public void testWrong_telefono_ctt(){
	Client client = ClientBuilder.newClient();
	WebTarget aggiuntaCTT = client.target("http://127.0.0.1:8080/rest/CCS/aggiuntaCTT");
	Invocation.Builder invocationBuilder = aggiuntaCTT.request(MediaType.TEXT_PLAIN);
	invocationBuilder.cookie(cookie);
	Form form1 = new Form();
	form1.param("numero_ctt", "a");						
	form1.param("nome_ctt", "CTT005");
	form1.param("provincia", "BN");
	form1.param("citta", "Campolattaro");
	form1.param("indirizzo", "Via del testing 12");
	form1.param("telefono", "0821432576");			//è stato inserito un input non valido per il campo telefono
	form1.param("email", "CTT005@gmail.com");
	form1.param("latitude", "65");
	form1.param("longitude", "41");
	Response responseaddCTT = invocationBuilder.post(Entity.form(form1));
	assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), responseaddCTT.getStatus());
	}

  @Test	public void testWrong_latitudine(){
	Client client = ClientBuilder.newClient();
	WebTarget aggiuntaCTT = client.target("http://127.0.0.1:8080/rest/CCS/aggiuntaCTT");
	Invocation.Builder invocationBuilder = aggiuntaCTT.request(MediaType.TEXT_PLAIN);
	invocationBuilder.cookie(cookie);
	Form form1 = new Form();
	form1.param("numero_ctt", "a");						
	form1.param("nome_ctt", "CTT005");
	form1.param("provincia", "BN");
	form1.param("citta", "Campolattaro");
	form1.param("indirizzo", "Via del testing 12");
	form1.param("telefono", "0821432576");			
	form1.param("email", "CTT005@gmail.com");
	form1.param("latitude", "190");			//è stato inserito un input non valido per la latitudine
	form1.param("longitude", "41");
	Response responseaddCTT = invocationBuilder.post(Entity.form(form1));
	assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), responseaddCTT.getStatus());
	}
	  	  
	@AfterClass public static void drop(){
	
	MongoDataManager mm = new MongoDataManager();
	mm.dropDB();
	}
	  
}
