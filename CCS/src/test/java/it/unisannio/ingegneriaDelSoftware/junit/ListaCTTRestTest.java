package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Cdf;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class ListaCTTRestTest {

    MongoDataManager mm = MongoDataManager.getInstance();
    static String token = null;
    Client client = ClientBuilder.newClient();
    WebTarget aggiuntaCTT = client.target("http://127.0.0.1:8080/rest/CCS/aggiuntaCTT");
    WebTarget listaCTT = client.target("http://127.0.0.1:8080/rest/CCS/centers");

    /**Aggiunge al database dei Dipendenti un Amministratore CCS con i permessi necessari per testare il metodo successivo
     * @throws EntityAlreadyExistsException
     */
    @Before
    public  void setUp() throws EntityAlreadyExistsException {

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
    }

    /**Droppa il database*/
    @After
    public void drop(){
        MongoDataManager mm = MongoDataManager.getInstance();
        mm.dropDB();
    }

    /** Test per il metodo rest/CCS/centers dell'amministratoreCCS, va a buon fine
     * @throws EntityAlreadyExistsException
     */
    @Test public void testCorrettoNumeroCTT() throws EntityAlreadyExistsException{

        Form form1 = new Form();
        form1.param("numero_ctt", "5");
        form1.param("nome_ctt", "CTT005");
        form1.param("provincia", "BN");
        form1.param("citta", "Campolattaro");
        form1.param("indirizzo", "Via del testing 12");
        form1.param("telefono", "0821432576");
        form1.param("email", "CTT005@gmail.com");
        form1.param("latitude", "65");
        form1.param("longitude", "41");
        aggiuntaCTT.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
        listaCTT.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).get();
        assertEquals(1,mm.getListaCTT().size());
    }

    /** Test per il metodo rest/CCS/centers dell'amministratoreCCS, non va a buon fine siccome il parametro telefono è in un formato errato
     * @throws EntityAlreadyExistsException
    */
    @Test public void testWrongNumberCTT() throws EntityAlreadyExistsException{
        Form form1 = new Form();
        form1.param("provincia", "BN");
        form1.param("citta", "Campolattaro");
        form1.param("indirizzo", "Via del testing 12");
        form1.param("telefono", "0821432576a");			//è stato inserito un input non valido per il campo telefono
        form1.param("email", "CTT005@gmail.com");
        form1.param("latitude", "65");
        form1.param("longitude", "41");
        aggiuntaCTT.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).post(Entity.form(form1));
        listaCTT.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).get();
        assertNotEquals(2,mm.getListaCTT().size());
    }

}