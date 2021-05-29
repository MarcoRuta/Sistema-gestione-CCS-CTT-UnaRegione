package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

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
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class AggiungiCTTRestTest {
	
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget aggiuntaCTT = client.target("http://127.0.0.1:8080/rest/CCS/aggiuntaCTT");
	
	  @BeforeClass
	  public static void setUp(){
		
		Cdf cdf = Cdf.getCDF("KTMFSW67T64I460X");
	    LocalDate ld = LocalDate.parse("1978-10-10");
	    RuoloDipendente ruolo = RuoloDipendente.AmministratoreCCS;
	    String username = "admin";
	    String password = "Adminadmin1";
	    Dipendente dip = new Dipendente(cdf, "TestAdmin", "TestAdmin", ld, ruolo, username, password);
	    MongoDataManager mm = MongoDataManager.getInstance();
	    mm.createDipendente(dip);
	    
	    Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8080/rest/autentificazione");
		Form form1 = new Form();
		form1.param("username", "admin");
		form1.param("password", "Adminadmin1");
		
		Response responselogin = login.request().post(Entity.form(form1));
		User user = responselogin.readEntity(User.class);
		token = user.getToken();
	  }
	  
  @Test	public void testAggiuntaCTTCorretto(){
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
	Response responseaddCTT = aggiuntaCTT.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
	assertEquals(Status.OK.getStatusCode(), responseaddCTT.getStatus());
	}
  
  @Test	public void testWrong_numero_ctt(){
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
	Response responseaddCTT = aggiuntaCTT.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
	assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), responseaddCTT.getStatus());
	}
  
  @Test	public void testWrong_telefono_ctt(){
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
	Response responseaddCTT = aggiuntaCTT.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
	assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), responseaddCTT.getStatus());
	}

  @Test	public void testWrong_latitudine(){
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
	Response responseaddCTT = aggiuntaCTT.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
	assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), responseaddCTT.getStatus());
	}
	  	  
  
	@AfterClass public static void drop(){
	MongoDataManager mm = MongoDataManager.getInstance();
	mm.dropDB();
	}
	  
}