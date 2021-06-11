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
import it.unisannio.ingegneriaDelSoftware.Util.Constants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Cdf;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class AggiungiDipendenteTest {
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget aggiuntaAmministratore = client.target("http://127.0.0.1:8081/rest/amministratore/aggiuntaDipendente");
		
	
	@Before public void setUp() throws EntityAlreadyExistsException {
			List<Dipendente> listaDipendenti = new ArrayList<Dipendente>();
		        
		 	Cdf cdf = Cdf.getCDF("XDDBHH45H57H684W");
	        LocalDate ld = LocalDate.parse("2000-07-10", DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
	        RuoloDipendente ruolo = RuoloDipendente.AmministratoreCTT;
	        String username = "username 002";
	        String password = "Password2";
	        Dipendente dip2 = new Dipendente(cdf, "Pino", "Sfatto", ld, ruolo, username, password);
	        listaDipendenti.add(dip2);  
	        
	        cdf = Cdf.getCDF("CZGMJS46A28I333C");
	        ld = LocalDate.parse("1999-01-12", DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
	        ruolo = RuoloDipendente.AmministratoreCTT;
	        username = "username 003";
	        password = "Password3";
	        Dipendente dip3 = new Dipendente(cdf, "Giovanni", "Rana", ld, ruolo, username, password);
	        listaDipendenti.add(dip3); 
	        
	        cdf = Cdf.getCDF("FZDTSS79C20F641W");
	        ld = LocalDate.parse("1996-12-10", DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
	        ruolo = RuoloDipendente.OperatoreCTT;
	        username = "username 004";
	        password = "Password4";
	        Dipendente dip4 = new Dipendente(cdf, "Pietro", "Spini", ld, ruolo, username, password);
	        listaDipendenti.add(dip4); 
	        
	        cdf = Cdf.getCDF("BVNZDG48A06D684R");
	        ld = LocalDate.parse("1998-01-29", DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
	        ruolo = RuoloDipendente.OperatoreCTT;
	        username = "username 005";
	        password = "Password5";
	        Dipendente dip5 = new Dipendente(cdf, "Gionata", "Boschetto", ld, ruolo, username, password);
	        listaDipendenti.add(dip5); 
	        
	        cdf = Cdf.getCDF("KCHXSP82M66M295O");
	        ld = LocalDate.parse("1992-04-10", DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
	        ruolo = RuoloDipendente.MagazziniereCTT;
	        username = "username 006";
	        password = "Password6";
	        Dipendente dip6 = new Dipendente(cdf, "Marco", "Aspini", ld, ruolo, username, password);
	        listaDipendenti.add(dip6); 
	        
	        cdf = Cdf.getCDF("VYHBLK93H24B888J");
	        ld = LocalDate.parse("1982-10-20", DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
	        ruolo = RuoloDipendente.MagazziniereCTT;
	        username = "username 007";
	        password = "Password7";
	        Dipendente dip7 = new Dipendente(cdf, "Luca", "Barra", ld, ruolo, username, password);
	        listaDipendenti.add(dip7); 
	        
	        cdf = Cdf.getCDF("XLFBJN93A04F885H");
	        ld = LocalDate.parse("2001-12-12", DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
	        ruolo = RuoloDipendente.AmministratoreCTT;
	        username = "username 008";
	        password = "Password8";
	        Dipendente dip8 = new Dipendente(cdf, "Andrea", "Lezzi", ld, ruolo, username, password);
	        listaDipendenti.add(dip8);
	      
	      
	        MongoDataManager mm = MongoDataManager.getInstance();
	        
	        for(Dipendente dip : listaDipendenti) {
	        	mm.createDipendente(dip);
	        }  
	        
	        Client client = ClientBuilder.newClient();
			WebTarget login = client.target("http://127.0.0.1:8081/rest/autentificazione");
			Form form1 = new Form();
			form1.param("username", "username 003");
			form1.param("password", "Password3");
			
			Response responselogin = login.request().post(Entity.form(form1));
			User user = responselogin.readEntity(User.class);
			token = user.getToken();
		}

		
		/** Test per il metodo rest/amministratore/aggiuntaDipendente dell'amministratoreCTT, che va a buon fine
		 */
		@Test	
		public void testAggiuntaMagazziniere(){
			Form form1 = new Form();
			form1.param("cdf", "SRNGJZ50B54C143L");
			form1.param("nome", "Ario");
			form1.param("cognome", "lucarelli");
			form1.param("dataDiNascita", "1988-07-04");
			form1.param("ruolo", RuoloDipendente.MagazziniereCTT.toString());
			form1.param("username", "username 123");
			form1.param("password", "Password123");
			Response responseaddAmm = aggiuntaAmministratore.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
			assertEquals(Status.CREATED.getStatusCode(), responseaddAmm.getStatus());	
		}
		
		
		/** Test per il metodo rest/amministratore/aggiuntaDipendente dell'amministratoreCTT, che va a buon fine
		 */
		@Test	
		public void testAggiuntaOperatore(){
			Form form1 = new Form();
			form1.param("cdf", "LZZBHR41C46C446V");
			form1.param("nome", "Lucio");
			form1.param("cognome", "Spora");
			form1.param("dataDiNascita", "1977-04-01");
			form1.param("ruolo", RuoloDipendente.OperatoreCTT.toString());
			form1.param("username", "username 234");
			form1.param("password", "Password234");
			Response responseaddAmm = aggiuntaAmministratore.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
			assertEquals(Status.CREATED.getStatusCode(), responseaddAmm.getStatus());	
		}
		
		
		/** Test per il metodo rest/amministratore/aggiuntaDipendente dell'amministratoreCTT,
		 *  non funzionante a causa del formato errato della data di nascita
		 */
		@Test	
		public void testFormatoDatiErrato(){
			Form form1 = new Form();
			form1.param("cdf", "LZZBHR41C46C446V");
			form1.param("nome", "Lucio");
			form1.param("cognome", "Spora");
			form1.param("dataDiNascita", "01-04-1977"); //formato errato
			form1.param("ruolo", RuoloDipendente.AmministratoreCTT.toString());
			form1.param("username", "username 234");
			form1.param("password", "Password234");
			Response responseaddAmm = aggiuntaAmministratore.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
			assertEquals(Status.BAD_REQUEST.getStatusCode(), responseaddAmm.getStatus());
		}
		
		
		@After public void dropDBi() {
			MongoDataManager mm = MongoDataManager.getInstance();
			mm.dropDB();
		}
	}