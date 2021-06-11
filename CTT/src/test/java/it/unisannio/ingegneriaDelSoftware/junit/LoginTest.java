package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class LoginTest {
	
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget Login = client.target("http://127.0.0.1:8081/rest/autentificazione");
	
	/**Aggiunge al database dei Dipendenti un amministratoreCCS necessario per testare il metodo successivo
	 * @throws EntityAlreadyExistsException
	 */
	@Before
	public void setUp() throws EntityAlreadyExistsException {
		List<Dipendente> listaDipendenti = new ArrayList<Dipendente>();
		
		Cdf cdf = Cdf.getCDF("KTMFSW67T64I460X");
	    LocalDate ld = LocalDate.parse("1978-10-10");
	    RuoloDipendente ruolo = RuoloDipendente.AmministratoreCTT;
	    String username = "admin";
	    String password = "Adminadmin1";
	    Dipendente dip1 = new Dipendente(cdf, "TestAdmin", "TestAdmin", ld, ruolo, username, password);
        listaDipendenti.add(dip1);

        cdf = Cdf.getCDF("FZDTSS79C20F641W");
        ld = LocalDate.parse("1996-12-10");
        ruolo = RuoloDipendente.OperatoreCTT;
        username = "username 004";
        password = "Password4";
        Dipendente dip2 = new Dipendente(cdf, "Pietro", "Spini", ld, ruolo, username, password);
        listaDipendenti.add(dip2);
        
	    cdf = Cdf.getCDF("CZGMJS46A28I333C");
        ld = LocalDate.parse("1999-01-12");
        ruolo = RuoloDipendente.MagazziniereCTT;
        username = "username 003";
        password = "Password3";
        Dipendente dip3 = new Dipendente(cdf, "Giovanni", "Rana", ld, ruolo, username, password);
        listaDipendenti.add(dip3);
	    
        MongoDataManager mm = MongoDataManager.getInstance();

        for(Dipendente dip : listaDipendenti) {
            mm.createDipendente(dip);
        }
	}

	/**Droppa i database*/
	@After
	public void dropDB(){
		MongoDataManager mm = MongoDataManager.getInstance();
		mm.dropDB();
	}

	/**Test per verificare l'assenza di un DipendenteCTT all'interno del database tramite username e password passate nel form
	 * @throws EntityNotFoundException
	 */
	  @Test
	 public void testDipendenteNonPresente() throws EntityNotFoundException {
		Form form = new Form();
		form.param("username", "admiN");
		form.param("password", "Admin");
		Response responseLogin = Login.request().post(Entity.form(form));
		assertEquals(Status.NOT_FOUND.getStatusCode(), responseLogin.getStatus());  
		}
	  
	  /**Test per verificare la presenza di un DipendenteCTT creato nel setUp e aggiunto nel database dei Dipendenti
		 * @throws EntityNotFoundException
		 */
	  @Test
	  public void testDipendentePresente() throws EntityNotFoundException{
		  Form form = new Form();
		  form.param("username", "admin");
		  form.param("password", "Adminadmin1");
		  Response responseLogin = Login.request().post(Entity.form(form));
		  assertEquals(Status.CREATED.getStatusCode(), responseLogin.getStatus());
	  }
}