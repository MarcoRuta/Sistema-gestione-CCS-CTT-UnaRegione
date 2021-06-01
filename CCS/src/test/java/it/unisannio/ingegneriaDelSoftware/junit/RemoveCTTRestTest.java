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

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Classes.Beans.User;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;


public class RemoveCTTRestTest {
	
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget rimozioneCTT = client.target("http://127.0.0.1:8080/rest/CCS/rimozioneCTT");
	
	
	/**
	 * Classe per la popolamento del database			
	 * @throws ParseException
	 */
	@BeforeClass 
	public static void setUp() throws EntityAlreadyExistsException {
		
		List<CTT> listaCTT = new ArrayList<CTT>();
	        
		Integer numero = 1;
        String denominazione = "San Giuliano";
        String provincia = "NA";
        String citta = "Giugliano";
        String telefono = "0818955111";
        String indirizzo = "Via Montello 21";
        String email = "sangiuliano@aruba.com";
        double latitudine = 44.5;
        double longitudine = 20.6;
        CTT CTT001 = new CTT(CTTName.getCttName("CTT00"+numero), provincia, citta, telefono, indirizzo, email, latitudine, longitudine);
        listaCTT.add(CTT001);
        
        numero = 2;
        denominazione = "Ospedale del Mare";
        provincia = "NA";
        citta = "Napoli";
        telefono = "0812541111";
        indirizzo = "Via Aldo 71";
        email = "ospedaledelmare@aruba.com";
        latitudine = 67.5;
        longitudine = 80.6;
        CTT CTT002 = new CTT(CTTName.getCttName("CTT00"+numero), provincia, citta, telefono, indirizzo, email, latitudine, longitudine);
        listaCTT.add(CTT002);
        
        numero = 3;
        denominazione = "Maria SS. Addolorata";
        provincia = "SA";
        citta = "Eboli";
        telefono = "0828362111";
        indirizzo = "Via Asso 51";
        email = "addolorata@aruba.com";
        latitudine = 22.0;
        longitudine = 34.6;
        CTT CTT003 = new CTT(CTTName.getCttName("CTT00"+numero), provincia, citta, telefono, indirizzo, email, latitudine, longitudine);
        listaCTT.add(CTT003);
        
        numero = 4;
        denominazione = "Ospedale SS. Annunziata";
        provincia = "NA";
        citta = "Napoli";
        telefono = "0812542598";
        indirizzo = "Via Luigi Pirandello 121";
        email = "annunziata@aruba.com";
        latitudine = 65.5;
        longitudine = 65.6;
        CTT CTT004 = new CTT(CTTName.getCttName("CTT00"+numero), provincia, citta, telefono, indirizzo, email, latitudine, longitudine);
        listaCTT.add(CTT004);
        
        numero = 5;
        denominazione = "San Pio";
        provincia = "BN";
        citta = "Benevento";
        telefono = "0824571115";
        indirizzo = "Via Torre Della Catena 118";
        email = "sanpio@aruba.com";
        latitudine = 40.5;
        longitudine = 60.1;
        CTT CTT005 = new CTT(CTTName.getCttName("CTT00"+numero), provincia, citta, telefono, indirizzo, email, latitudine, longitudine);
        listaCTT.add(CTT005);
        
        numero = 6;
        denominazione = "San Giuseppe";
        provincia = "AV";
        citta = "Avellino";
        telefono = "0825203111";
        indirizzo = "Via Arco 91";
        email = "sangiuseppe@aruba.com";
        latitudine = 64.5;
        longitudine = 13.6;
        CTT CTT006 = new CTT(CTTName.getCttName("CTT00"+numero), provincia, citta, telefono, indirizzo, email, latitudine, longitudine);
        listaCTT.add(CTT006);
        
        numero = 7;
        denominazione = "PSAUT";
        provincia = "BN";
        citta = "San Bartolomeo in Galdo";
        telefono = "0824789053";
        indirizzo = "Via Bocciato 17";
        email = "psaut@aruba.com";
        latitudine = 11.5;
        longitudine = 23.6;
        CTT CTT007 = new CTT(CTTName.getCttName("CTT00"+numero), provincia, citta, telefono, indirizzo, email, latitudine, longitudine);
        listaCTT.add(CTT007);
      
        MongoDataManager mm = MongoDataManager.getInstance();
        
        for(CTT ctt : listaCTT) {
        	mm.createCTT(ctt);
        }  
        
        Cdf cdf = Cdf.getCDF("KTMFSW67T64I460X");
	    LocalDate ld = LocalDate.parse("1998-10-10");
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
	}
	
	/**Test del metodo REST rest/CCS/rimozioneCTT
	 * Questo test deve andare a buon fine in quanto si tenta di eliminare un CTT inserito nel @BeforeClass
	 */
	@Test public void testRimozioneCTTCorretto(){	

		Response responseRemCTT = rimozioneCTT.path("CTT001").request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).delete();
		assertEquals(Status.OK.getStatusCode(), responseRemCTT.getStatus());
			} 	
	
	
	/**Test del metodo REST rest/CCS/rimozioneCTT
	 * Questo test non deve andare a buon fine in quanto si tenta di eliminare un CTT non presente nel database
	*/ 
	@Test public void testRimozioneCTTNonPresente(){
		

		Response responseRemCTT = rimozioneCTT.path("CTT008").request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).delete();
		assertEquals(Status.NOT_FOUND.getStatusCode(), responseRemCTT.getStatus());
	} 
	
	

	/**Classe per l'eliminazione del database
	 */
	@AfterClass public static void dropDBCTT() {
		MongoDataManager mm = MongoDataManager.getInstance();
		mm.dropDB();
	}
	
}
