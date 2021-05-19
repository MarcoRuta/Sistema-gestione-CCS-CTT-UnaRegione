package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;




public class RemoveCTTRestTest {
	
	static NewCookie cookie = null;
	static WebTarget rimozioneCTT = null;
	static WebTarget login = null;
	
	/**
	 * Classe per la popolazione del database
	 * 					
	 * @throws ParseException
	 */
	@BeforeClass 
	public static void setUp() throws ParseException {
		
		MongoDataManager amm = new MongoDataManager();
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
        CTT CTT001 = new CTT(numero, denominazione, provincia, citta, telefono, indirizzo, email, latitudine, longitudine);  
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
        CTT CTT002 = new CTT(numero, denominazione, provincia, citta, telefono, indirizzo, email, latitudine, longitudine);  
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
        CTT CTT003 = new CTT(numero, denominazione, provincia, citta, telefono, indirizzo, email, latitudine, longitudine);  
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
        CTT CTT004 = new CTT(numero, denominazione, provincia, citta, telefono, indirizzo, email, latitudine, longitudine);    
        listaCTT.add(CTT004);
        
        numero = 5;
        denominazione = "San Pio";
        provincia = "BN";
        citta = "Benevento";
        telefono = "082457111";
        indirizzo = "Via Torre Della Catena 118";
        email = "sanpio@aruba.com";
        latitudine = 40.5;
        longitudine = 60.1;
        CTT CTT005 = new CTT(numero, denominazione, provincia, citta, telefono, indirizzo, email, latitudine, longitudine);  
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
        CTT CTT006 = new CTT(numero, denominazione, provincia, citta, telefono, indirizzo, email, latitudine, longitudine);   
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
        CTT CTT007 = new CTT(numero, denominazione, provincia, citta, telefono, indirizzo, email, latitudine, longitudine);  
        listaCTT.add(CTT007);
      
        for(CTT ctt : listaCTT) {
        	amm.createCTT(ctt);
        }  
        
        Cdf cdf = new Cdf("122hfotndj13ht5f");
	    LocalDate ld = LocalDate.of(1998,10,10);
	    RuoloDipendente ruolo = RuoloDipendente.AmministratoreCCS;
	    String username = "admin";
	    String password = "admin";
	    Dipendente dip = new Dipendente(cdf, "TestAdmin", "TestAdmin", ld, ruolo, username, password);
	    amm.addDipendente(dip);
        
        Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8080/rest/login");
		Form form = new Form();
		form.param("username", "admin");
		form.param("password", "admin");
		Response responselogin = login.request().post(Entity.form(form));
		cookie = responselogin.getCookies().get("access_token");
	}
	
	/**Test del metodo REST rest/CCS/rimozioneCTT
	 * Questo test deve andare a buon fine in quanto si tenta di eliminare un CTT inserito nel @BeforeClass
	 */
	@Test public void testRimozioneCTTCorretto(){
		Client client = ClientBuilder.newClient();
		WebTarget rimozioneCTT = client.target("http://127.0.0.1:8080/rest/CCS/rimozioneCTT/1");
		Invocation.Builder invocationBuilder = rimozioneCTT.request(MediaType.TEXT_PLAIN);
		invocationBuilder.cookie(cookie);
		Response responseRemoveCTT = invocationBuilder.delete();
		assertEquals(Status.OK.getStatusCode(), responseRemoveCTT.getStatus());
	} 	
	
	
	/**Test del metodo REST rest/CCS/rimozioneCTT
	 * Questo test non deve andare a buon fine in quanto si tenta di eliminare un CTT non presente nel database
	 */
	@Test public void testRimozioneCTTNonPresente(){
		Client client = ClientBuilder.newClient();
		WebTarget rimozioneCTT = client.target("http://127.0.0.1:8080/rest/CCS/rimozioneCTT/8");
		Invocation.Builder invocationBuilder = rimozioneCTT.request(MediaType.TEXT_PLAIN);
		invocationBuilder.cookie(cookie);
		Response responseRemoveCTT = invocationBuilder.delete();
		assertEquals(Status.BAD_REQUEST.getStatusCode(), responseRemoveCTT.getStatus());
	} 
	
	

	/**
	 * Classe per l'eliminazione del database
	 * 
	 */
	@AfterClass public static void dropDBCTT() {
		MongoDataManager mm = new MongoDataManager();
		mm.dropDB();
	}
	
}
