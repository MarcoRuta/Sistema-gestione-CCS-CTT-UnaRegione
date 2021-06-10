package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Cdf;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class LoginRestTest {
	
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget aggiuntaCTT = client.target("http://127.0.0.1:8080/rest/autentificazione");
	
	/**Aggiunge al database dei Dipendenti un amministratoreCCS necessario per testare il metodo successivo
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
	}

	/**Droppa i database*/
	@After
	public void dropDB(){
		MongoDataManager mm = MongoDataManager.getInstance();
		mm.dropDB();
	}

	/**Test per verificare la non presenza di un amministratoreCCS all'interno del database tramite username e password passate nel form
	 * @throws EntityAlreadyExistsException
	 */
	  @Test
	 public void testAmministratoreNonPresente() throws EntityAlreadyExistsException {
		Form form = new Form();
		form.param("username", "admiN");
		form.param("password", "Admin");
		Response responseaddCTT = aggiuntaCTT.request().post(Entity.form(form));
		assertEquals(Status.NOT_FOUND.getStatusCode(), responseaddCTT.getStatus());  
		}
	  
	  /**Test per verificare la presenza dell'amministratoreCCS creato nel setUp all'interno del database  
		 * @throws EntityAlreadyExistsException
		 */
	  @Test
	  public void testAmministratorePresente() throws EntityAlreadyExistsException{
		  Form form = new Form();
		  form.param("username", "admin");
		  form.param("password", "Adminadmin1");
		  Response responseaddCTT = aggiuntaCTT.request().post(Entity.form(form));
		  assertEquals(Status.CREATED.getStatusCode(), responseaddCTT.getStatus());
	  }
}