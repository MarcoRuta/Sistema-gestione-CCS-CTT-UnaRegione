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

public class LogOutRestTest {
	
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget LogOut = client.target("http://127.0.0.1:8080/rest/autentificazione/logout");
	
	/**Aggiunge al database dei Dipendenti un amministratoreCCS necessario per testare il metodo successivo ed esegue il Login
	 * @throws EntityAlreadyExistsException
	 */
	@Before
	public void setUp() throws EntityAlreadyExistsException {		
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

	/**Droppa i database*/
	@After
	public void dropDB(){
		MongoDataManager mm = MongoDataManager.getInstance();
		mm.dropDB();
	}

	
	/**Test per verificare che il Logout non vada a buon fine in presenza di un Token errato
	 * @throws EntityNotFoundException
	 */
	  @Test
	 public void testRimozioneTokenNonPresente() throws EntityNotFoundException {
		Response responseLogout = LogOut.request().header(HttpHeaders.AUTHORIZATION, "errato "+token).delete();
		assertEquals(Status.NOT_FOUND.getStatusCode(), responseLogout.getStatus());  
		}
	  
	  
	  /**Test per verificare la corretta rimozione del token in seguito ad una richiesta di logout da parte dell'amministratoreCCS
		 * @throws EntityNotFoundException
		 */
	  @Test
	  public void testRimozioneToken() throws EntityNotFoundException{
		  Response responseLogout = LogOut.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).delete();
		  assertEquals(Status.OK.getStatusCode(), responseLogout.getStatus());
	  }
}