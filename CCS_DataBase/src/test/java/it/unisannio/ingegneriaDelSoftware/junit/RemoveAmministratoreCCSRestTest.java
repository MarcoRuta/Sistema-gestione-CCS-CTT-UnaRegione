package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.time.LocalDate;
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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;


public class RemoveAmministratoreCCSRestTest {
	
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget rimozioneAmm = client.target("http://127.0.0.1:8080/rest/CCS/rimozioneAmministratore");
	
	
	@BeforeClass public static void populateDBDipendenti() throws EntityAlreadyExistsException {
		List<Dipendente> listaDipendenti = new ArrayList<Dipendente>();
	        
	 	Cdf cdf = Cdf.getCDF("XDDBHH45H57H684W");
        LocalDate ld = LocalDate.parse("2000-07-10");
        RuoloDipendente ruolo = RuoloDipendente.AmministratoreCCS;
        String username = "username 002";
        String password = "Password2";
        Dipendente dip2 = new Dipendente(cdf, "Pino", "Sfatto", ld, ruolo, username, password);
        listaDipendenti.add(dip2);  
        
        cdf = Cdf.getCDF("CZGMJS46A28I333C");
        ld = LocalDate.parse("1999-01-12");
        ruolo = RuoloDipendente.AmministratoreCCS;
        username = "username 003";
        password = "Password3";
        Dipendente dip3 = new Dipendente(cdf, "Giovanni", "Rana", ld, ruolo, username, password);
        listaDipendenti.add(dip3); 
        
        cdf = Cdf.getCDF("FZDTSS79C20F641W");
        ld = LocalDate.parse("1996-12-10");
        ruolo = RuoloDipendente.AmministratoreCCS;
        username = "username 004";
        password = "Password4";
        Dipendente dip4 = new Dipendente(cdf, "Pietro", "Spini", ld, ruolo, username, password);
        listaDipendenti.add(dip4); 
        
        cdf = Cdf.getCDF("BVNZDG48A06D684R");
        ld = LocalDate.parse("1998-01-29");
        ruolo = RuoloDipendente.AmministratoreCCS;
        username = "username 005";
        password = "Password5";
        Dipendente dip5 = new Dipendente(cdf, "Gionata", "Boschetto", ld, ruolo, username, password);
        listaDipendenti.add(dip5); 
        
        cdf = Cdf.getCDF("KCHXSP82M66M295O");
        ld = LocalDate.parse("1992-04-10");
        ruolo = RuoloDipendente.AmministratoreCCS;
        username = "username 006";
        password = "Password6";
        Dipendente dip6 = new Dipendente(cdf, "Marco", "Aspini", ld, ruolo, username, password);
        listaDipendenti.add(dip6); 
        
        cdf = Cdf.getCDF("VYHBLK93H24B888J");
        ld = LocalDate.parse("1982-10-20");
        ruolo = RuoloDipendente.AmministratoreCCS;
        username = "username 007";
        password = "Password7";
        Dipendente dip7 = new Dipendente(cdf, "Luca", "Barra", ld, ruolo, username, password);
        listaDipendenti.add(dip7); 
        
        cdf = Cdf.getCDF("XLFBJN93A04F885H");
        ld = LocalDate.parse("2001-12-12");
        ruolo = RuoloDipendente.AmministratoreCCS;
        username = "username 008";
        password = "Password8";
        Dipendente dip8 = new Dipendente(cdf, "Andrea", "Lezzi", ld, ruolo, username, password);
        listaDipendenti.add(dip8);
      
      
        MongoDataManager mm = MongoDataManager.getInstance();
        
        for(Dipendente dip : listaDipendenti) {
        	mm.createDipendente(dip);
        }  
        
        Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8080/rest/autentificazione");
		Form form1 = new Form();
		form1.param("username", "username 003");
		form1.param("password", "Password3");
		
		Response responselogin = login.request().post(Entity.form(form1));
		User user = responselogin.readEntity(User.class);
		token = user.getToken();
	}
	
	/**Test del metodo REST rest/CCS/rimozioneAmministratore
	 * Questo test deve andare a buon fine in quanto si tenta di eliminare un Dipendente inserito nel @BeforeClass
	 */
	@Test public void testRimozioneAmministratoreCCSCorretto(){	

		Response responseRemAmm = rimozioneAmm.path("BVNZDG48A06D684R").request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).delete();
		assertEquals(Status.OK.getStatusCode(), responseRemAmm.getStatus());
	} 	
	
	
	/**Test del metodo REST rest/CCS/rimozioneAmministratore
	 * Questo test non deve andare a buon fine in quanto si tenta di eliminare un Dipendente non presente nel database
	*/ 
	@Test public void testRimozioneAmministratoreCCSNonPresente(){
		
		Response responseRemAmm = rimozioneAmm.path("BVNZDG48A06D684R").request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).delete();
		assertEquals(Status.NOT_FOUND.getStatusCode(), responseRemAmm.getStatus());
	} 
	
	

	/**Classe per l'eliminazione del database
	 */
	@AfterClass public static void dropDBDip() {
		MongoDataManager mm = MongoDataManager.getInstance();
		mm.dropDB();
	}
	
}