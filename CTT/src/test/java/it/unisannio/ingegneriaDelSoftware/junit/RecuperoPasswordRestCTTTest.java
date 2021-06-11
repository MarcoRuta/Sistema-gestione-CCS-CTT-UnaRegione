package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Cdf;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class RecuperoPasswordRestCTTTest {
	
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget RecuperoPassword = client.target("http://127.0.0.1:8081/rest/autentificazione/recuperoPassword");
	MongoDataManager mm = MongoDataManager.getInstance();
	
	/**Aggiunge al database dei Dipendenti un amministratoreCTT necessario per testare il metodo successivo ed esegue il Login
	 * @throws EntityAlreadyExistsException
	 */
	@Before
	public void setUp() throws EntityAlreadyExistsException {		
		Cdf cdf = Cdf.getCDF("KTMFSW67T64I460X");
	    LocalDate ld = LocalDate.parse("1978-10-10");
	    RuoloDipendente ruolo = RuoloDipendente.AmministratoreCTT;
	    String username = "admin";
	    String password = "Adminadmin1";
	    Dipendente dip = new Dipendente(cdf, "TestAdmin", "TestAdmin", ld, ruolo, username, password);
	    mm.createDipendente(dip);
	    
	    Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8081/rest/autentificazione");
		Form form1 = new Form();
		form1.param("username", "admin");
		form1.param("password", "Adminadmin1");
		
		Response responselogin = login.request().post(Entity.form(form1));
		User user = responselogin.readEntity(User.class);
		token = user.getToken();
		
		cdf = Cdf.getCDF("MFDFSW67T89I460X");
	    ld = LocalDate.parse("1987-10-10");
	    ruolo = RuoloDipendente.AmministratoreCTT;
	    username = "admin2";
	    password = "Adminadmin2";
	    dip = new Dipendente(cdf, "Marco", "Liverani", ld, ruolo, username, password);
	    mm.createDipendente(dip);
	}

	/**Droppa i database*/
	@After
	public void dropDB(){
		MongoDataManager mm = MongoDataManager.getInstance();
		mm.dropDB();
	}
	  
	  
	  /**Test per verificare il corretto recupero della password ottenendone una nuova
		 * @ throws EntityNotFoundException,WebApplicationException
		 */
	  @Test
	  public void testRecuperoPassword() throws EntityNotFoundException,WebApplicationException{
		  String username = "admin";
		  Response responseRecuperoPassword = RecuperoPassword.path("KTMFSW67T64I460X").request().put(Entity.text(username));
		  assertEquals(Status.OK.getStatusCode(), responseRecuperoPassword.getStatus());
	  }
	
	
	/**Test per verificare che il recupero password non vada a buon fine se tentato su un utente non presente
	 * @throws EntityNotFoundException,WebApplicationException
	 */
	  @Test
	 public void testRecuperoPasswordUtenteNonPresente()  throws EntityNotFoundException,WebApplicationException{
		String username = "admin";
		Response responseRecuperoPassword = RecuperoPassword.path("FALSOW67T64I460X").request().put(Entity.text(username));
		assertEquals(Status.NOT_FOUND.getStatusCode(), responseRecuperoPassword.getStatus());  
		}
	
	  
	/**Test per verificare che il recupero password non vada a buon fine se tentato su un altro utente
	 * @throws EntityNotFoundException,WebApplicationException
	 */
	  @Test
	 public void testRecuperoPasswordAltroUtente()  throws EntityNotFoundException,WebApplicationException{
		String username = "admin3";
		Response responseRecuperoPassword = RecuperoPassword.path("MFDFSW67T89I460X").request().put(Entity.text(username));
		assertEquals(Status.FORBIDDEN.getStatusCode(), responseRecuperoPassword.getStatus());  
		}

}