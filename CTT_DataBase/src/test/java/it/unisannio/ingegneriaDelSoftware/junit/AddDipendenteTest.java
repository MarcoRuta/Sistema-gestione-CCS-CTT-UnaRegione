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
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import it.unisannio.ingegneriaDelSoftware.Classes.User;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManagerBean;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;

public class AddDipendenteTest {	
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget aggiuntaDipendente = client.target("http://127.0.0.1:8080/rest/amministratore/aggiuntaDipendente");

	@BeforeClass public static void populateDBDipendenti() throws ParseException, DipendenteNotFoundException {

		List<Dipendente> listaDipendenti = new ArrayList<Dipendente>();

	 	Cdf cdf = Cdf.getCDF("122hfotndj13ht5f");
        Date datadinascita = Constants.sdf.parse("10-07-2000");
        LocalDate ld = DateUtil.convertDateToLocalDate(datadinascita);
        RuoloDipendente ruolo = RuoloDipendente.OperatoreCTT;
        String username = "username 004";
        String password = "004";
        Dipendente dip2 = new Dipendente(cdf, "pino", "sfatto", ld, ruolo, username, password);
        listaDipendenti.add(dip2);
        
        cdf = Cdf.getCDF("123456789qwrrtyy");
        datadinascita = Constants.sdf.parse("12-01-1999");
        ld = DateUtil.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.MagazziniereCTT;
        username = "username 005";
        password = "005";
        Dipendente dip3 = new Dipendente(cdf, "Francesco", "Rana", ld, ruolo, username, password);
        listaDipendenti.add(dip3); 
        
        cdf = Cdf.getCDF("187656789qasdtyy");
        datadinascita = Constants.sdf.parse("12-01-1999");
        ld = DateUtil.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.AmministratoreCTT;
        username = "admin";
        password = "admin";
        Dipendente dip4 = new Dipendente(cdf, "Giovanni", "Rana", ld, ruolo, username, password);
        listaDipendenti.add(dip4); 

      
        for(Dipendente dip : listaDipendenti) {
        	MongoDataManagerBean.createDipendente(dip);
        }  
        
		Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8080/rest/autentificazione");
		Form form1 = new Form();
		form1.param("username", "admin");
		form1.param("password", "admin");
		
		Response responselogin = login.request().post(Entity.form(form1));
		User user = responselogin.readEntity(User.class);
		token = user.getToken();
	}

	
	/**
	 * Test che dovrebbe restituire una lista di Dipendenti con 7 elementi 
	*/
	@Test	
	public void test1(){
		Form form1 = new Form();
		form1.param("cdf", "12123gasndj13ht5");
		form1.param("nome", "mario");
		form1.param("cognome", "lucarelli");
		form1.param("dataDiNascita", "1988-07-04");
		form1.param("ruolo", RuoloDipendente.AmministratoreCTT.toString());
		form1.param("username", "username 123");
		form1.param("password", "123");
		Response responseaddDip = aggiuntaDipendente.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
		assertEquals(Status.OK.getStatusCode(), responseaddDip.getStatus());
	}
	
	@Test
	public void test2(){
		Form form1 = new Form();
		form1.param("cdf", "12123gasndj13ht512");    //Codice fiscale errato
		form1.param("nome", "mario");
		form1.param("cognome", "lucarelli");
		form1.param("dataDiNascita", "1988-07-04");
		form1.param("ruolo", RuoloDipendente.AmministratoreCTT.toString());
		form1.param("username", "username 123");
		form1.param("password", "123");
		Response responseaddDip = aggiuntaDipendente.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
		assertEquals(Status.BAD_REQUEST.getStatusCode(), responseaddDip.getStatus());
	}
	
	@Test
	public void test3(){
		Form form1 = new Form();
		form1.param("cdf", "123456poitnsuel1");   
		form1.param("nome", "mario");
		form1.param("cognome", "lucarelli");
		form1.param("dataDiNascita", "2009-07-04");		//Data non valida
		form1.param("ruolo", RuoloDipendente.AmministratoreCTT.toString());
		form1.param("username", "username 123");
		form1.param("password", "123");
		Response responseaddDip = aggiuntaDipendente.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
		assertEquals(Status.BAD_REQUEST.getStatusCode(), responseaddDip.getStatus());
	}
	

	

	@AfterClass
	public static void dropDBDipendenti() {
		MongoDataManagerBean.dropDB();
	}


}