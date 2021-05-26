package it.unisannio.ingegneriaDelSoftware.junit;
import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;



public class RemoveDipendenteTest {	
	static String token = null;
	Client client = ClientBuilder.newClient();

	
	@BeforeClass public static void populateDBDipendenti() throws ParseException, DipendenteNotFoundException {
		List<Dipendente> listaDipendenti = new ArrayList<Dipendente>();
	        
	 	Cdf cdf = Cdf.getCDF("122hfotndj13ht5f");
        Date datadinascita = Constants.sdf.parse("10-07-2000");
        LocalDate ld = DateUtil.convertDateToLocalDate(datadinascita);
        RuoloDipendente ruolo = RuoloDipendente.OperatoreCTT;
        String username = "username 002";
        String password = "002";
        Dipendente dip2 = new Dipendente(cdf, "pino", "sfatto", ld, ruolo, username, password);
        listaDipendenti.add(dip2);  
        
        cdf = Cdf.getCDF("123456789qwrrtyy");
        datadinascita = Constants.sdf.parse("12-01-1999");
        ld = DateUtil.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.AmministratoreCTT;
        username = "username 003";
        password = "003";
        Dipendente dip3 = new Dipendente(cdf, "Giovanni", "Rana", ld, ruolo, username, password);
        listaDipendenti.add(dip3); 
        
        cdf = Cdf.getCDF("123456789swertyy");
        datadinascita = Constants.sdf.parse("10-12-1996");
        ld = DateUtil.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.OperatoreCTT;
        username = "username 004";
        password = "004";
        Dipendente dip4 = new Dipendente(cdf, "pietro", "spini", ld, ruolo, username, password);
        listaDipendenti.add(dip4); 
        
        cdf =Cdf.getCDF("123456781qwertyy");
        datadinascita = Constants.sdf.parse("29-01-1998");
        ld = DateUtil.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.MagazziniereCTT;
        username = "username 005";
        password = "005";
        Dipendente dip5 = new Dipendente(cdf, "gionata", "boschetto", ld, ruolo, username, password);
        listaDipendenti.add(dip5); 
        
        cdf = Cdf.getCDF("123456789djkshnd");
        datadinascita = Constants.sdf.parse("10-04-1992");
        ld = DateUtil.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.MagazziniereCTT;
        username = "username 006";
        password = "006";
        Dipendente dip6 = new Dipendente(cdf, "marco", "aspini", ld, ruolo, username, password);
        listaDipendenti.add(dip6); 
        
        cdf = Cdf.getCDF("123456789qwedety");
        datadinascita = Constants.sdf.parse("20-10-1982");
        ld = DateUtil.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.OperatoreCTT;
        username = "username 007";
        password = "007";
        Dipendente dip7 = new Dipendente(cdf, "luca", "barra", ld, ruolo, username, password);
        listaDipendenti.add(dip7); 
        
        cdf = Cdf.getCDF("123456789dfgrhty");
        datadinascita = Constants.sdf.parse("12-12-2001");
        ld = DateUtil.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.AmministratoreCCS;
        username = "username 008";
        password = "008";
        Dipendente dip8 = new Dipendente(cdf, "andrea", "lezzi", ld, ruolo, username, password);
        listaDipendenti.add(dip8);
      
      
        for(Dipendente dip : listaDipendenti) {
        	MongoDataManager.createDipendente(dip);
        }  
        
        Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8080/rest/autentificazione");
		Form form1 = new Form();
		form1.param("username", "username 003");
		form1.param("password", "003");
		
		Response responselogin = login.request().post(Entity.form(form1));
		User user = responselogin.readEntity(User.class);
		token = user.getToken();
	}
	

	/**
	 * Test che dovrebbe restituire una lista di Dipendenti con 7 elementi 
	*/
	@Test	
	public void test1(){
		Client client = ClientBuilder.newClient();
		WebTarget rimozioneDipendente = client.target("http://127.0.0.1:8080/rest/amministratore/rimozioneDipendente/123456781qwertyy");
		
		Response responseaddDip = rimozioneDipendente.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).delete();
		assertEquals(Status.OK.getStatusCode(), responseaddDip.getStatus());	
	}
	
	
	/**
	 * Test che dovrebbe restituire una lista di Dipendenti con 7 elementi 
	*/
	@Test	
	public void test2(){
		Client client = ClientBuilder.newClient();
		WebTarget rimozioneDipendente = client.target("http://127.0.0.1:8080/rest/amministratore/rimozioneDipendente/abcdefg");
		
		Response responseaddDip = rimozioneDipendente.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).delete();
		assertEquals(Status.NOT_FOUND.getStatusCode(), responseaddDip.getStatus());	
	}
	
	
	/**
	 * Test che dovrebbe restituire una lista di Dipendenti con 7 elementi 
	*/
	@Test	
	public void test3(){
		Client client = ClientBuilder.newClient();
		WebTarget rimozioneDipendente = client.target("http://127.0.0.1:8080/rest/amministratore/rimozioneDipendente/123asdpoi9876gfb");
		
		Response responseaddDip = rimozioneDipendente.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).delete();
		assertEquals(Status.NOT_FOUND.getStatusCode(), responseaddDip.getStatus());	
	}
	
	
	
	

	
	
	@AfterClass public static void dropDBDipendenti() {
		MongoDataManager.dropDB();
	}
}