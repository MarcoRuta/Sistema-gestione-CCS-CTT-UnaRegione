package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;

import javax.ws.rs.WebApplicationException;
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

public class CambioPasswordRestCCSTest {
	
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget CambioPassword = client.target("http://127.0.0.1:8080/rest/autentificazione/cambiopassword");
	MongoDataManager mm = MongoDataManager.getInstance();
	
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
	    mm.createDipendente(dip);
	    
	    Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8080/rest/autentificazione");
		Form form1 = new Form();
		form1.param("username", "admin");
		form1.param("password", "Adminadmin1");
		
		Response responselogin = login.request().post(Entity.form(form1));
		User user = responselogin.readEntity(User.class);
		token = user.getToken();
		
		cdf = Cdf.getCDF("MFDFSW67T89I460X");
	    ld = LocalDate.parse("1987-10-10");
	    ruolo = RuoloDipendente.AmministratoreCCS;
	    username = "admin";
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
	  
	  
	  /**Test per verificare la corretta modifica della password inserendone una nuova
		 * @throws AssertionError, EntityNotFoundException,WebApplicationException
		 */
	  @Test
	  public void testCambioPassword() throws AssertionError, EntityNotFoundException,WebApplicationException{
		  String password = "Password1";
		  Response responseCambioPassword = CambioPassword.path("KTMFSW67T64I460X").request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).put(Entity.text(password));
		  assertEquals(Status.OK.getStatusCode(), responseCambioPassword.getStatus());
	  }
	
	
	/**Test per verificare che il Cambio password non vada a buon fine se tentato con un token errato
	 * @throws AssertionError, EntityNotFoundException,WebApplicationException
	 */
	  @Test
	 public void testCambioPasswordTokenErrato()  throws AssertionError, EntityNotFoundException,WebApplicationException{
		String password = "passworderrata";
		Response responseCambioPassword = CambioPassword.path("KTMFSW67T64I460X").request().header(HttpHeaders.AUTHORIZATION, "errato "+token).put(Entity.text(password));
		assertEquals(Status.NOT_FOUND.getStatusCode(), responseCambioPassword.getStatus());  
		}
	
	
	/**Test per verificare che il Cambio password non vada a buon fine se non rispetto il formato della password
	 * @throws AssertionError, EntityNotFoundException,WebApplicationException
	 */
	  @Test
	 public void testCambioPasswordNonRiuscito()  throws AssertionError, EntityNotFoundException,WebApplicationException {
		String password = "passworderrata";
		Response responseCambioPassword = CambioPassword.path("KTMFSW67T64I460X").request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).put(Entity.text(password));
		assertEquals(Status.BAD_REQUEST.getStatusCode(), responseCambioPassword.getStatus());  
		}
	
	  
	/**Test per verificare che il Cambio password non vada a buon fine se tentato su un altro utente
	 * @throws AssertionError, EntityNotFoundException,WebApplicationException
	 */
	  @Test
	 public void testCambioPasswordAltroUtente()  throws AssertionError, EntityNotFoundException,WebApplicationException{
		String password = "Password1";
		Response responseCambioPassword = CambioPassword.path("MFDFSW67T89I460X").request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).put(Entity.text(password));
		assertEquals(Status.FORBIDDEN.getStatusCode(), responseCambioPassword.getStatus());  
		}

}