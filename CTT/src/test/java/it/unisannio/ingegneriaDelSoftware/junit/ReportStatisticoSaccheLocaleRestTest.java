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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Cdf;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class ReportStatisticoSaccheLocaleRestTest {

    static String token = null;
    Client client = ClientBuilder.newClient();
    WebTarget reportStatisticoSaccheLocale = client.target("http://127.0.0.1:8081/rest/amministratore/reportStatisticoSacche");

     /**Popola il database di Sacche, aggiunge un AmministratoreCTT per eseguire la query ed effettua il login
     * @throws EntityAlreadyExistsException
     */
    @Before
    public void setUp() throws EntityAlreadyExistsException {

    	List<Sacca> listaSacche = new ArrayList<Sacca>();
    	List<DatiSacca> listaDatiSacche = new ArrayList<DatiSacca>();
    	
    	//Caricamento sul sistema di cinque Sacche di tipo A+, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana (2022)
    	//Una sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca 
    	GruppoSanguigno gs = GruppoSanguigno.Ap;
    	LocalDate localDataProduzione = LocalDate.of(2020,04,10);
    	LocalDate localDataScadenza = LocalDate.now().plusDays(2);
    	Sacca sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	listaSacche.add(sacca);
    	        
    	LocalDate localDataArrivo = LocalDate.of(2021,07,15);
    	String enteDonatore = "AVIS - Benevento";
    	DatiSacca datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.Ap;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.Ap;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.Ap;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.Ap;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2023,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Caricamento sul sistema di cinque Sacche di tipo A-, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana
    	//Una sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca 
    	 gs = GruppoSanguigno.Am;
    	 localDataProduzione = LocalDate.of(2020,04,10);
    	 localDataScadenza = LocalDate.of(2022,04,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.Am;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.Am;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.Am;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.Am;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2023,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Caricamento sul sistema di cinque Sacche di tipo B+, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana
    	//Una sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca 
    	 gs = GruppoSanguigno.Bp;
    	 localDataProduzione = LocalDate.of(2020,04,10);
    	 localDataScadenza = LocalDate.of(2022,04,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.Bp;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.Bp;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.Bp;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.Bp;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2023,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//Caricamento sul sistema di cinque Sacche di tipo B-, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana
    	//Una sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca 
    	 gs = GruppoSanguigno.Bm;
    	 localDataProduzione = LocalDate.of(2020,04,10);
    	 localDataScadenza = LocalDate.now().plusDays(2);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.Bm;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.Bm;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.Bm;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.Bm;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Caricamento sul sistema di cinque Sacche di tipo AB+, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana
    	//Una Sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca 
    	 gs = GruppoSanguigno.ABp;
    	 localDataProduzione = LocalDate.of(2020,04,10);
    	 localDataScadenza = LocalDate.of(2022,04,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.ABp;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.ABp;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.now().plusDays(2);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.ABp;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.ABp;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2023,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Caricamento sul sistema di cinque Sacche di tipo AB-, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana
    	//Una Sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca 
    	 gs = GruppoSanguigno.ABm;
    	 localDataProduzione = LocalDate.of(2020,04,10);
    	 localDataScadenza = LocalDate.of(2022,04,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.ABm;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.ABm;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.ABm;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.ABm;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Caricamento sul sistema di cinque Sacche di tipo ZERO+, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana
    	//Una Sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca 
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataProduzione = LocalDate.of(2020,04,10);
    	 localDataScadenza = LocalDate.of(2022,04,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2023,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Caricamento sul sistema di cinque Sacche di tipo ZERO-, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana
    	//Una Sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca 
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataProduzione = LocalDate.of(2020,04,10);
    	 localDataScadenza = LocalDate.of(2022,04,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 
    	                                   
    	 MongoDataManager mm = MongoDataManager.getInstance();
    	    	
    	for(Sacca sac : listaSacche) {
    		mm.createSacca(sac);
        }
    	
    	for(DatiSacca datisac : listaDatiSacche) {
    		mm.createDatiSacca(datisac);
        }
    	
        Cdf cdf = Cdf.getCDF("XDDBHH45H57H684W");
        LocalDate ld = LocalDate.parse("2000-07-10");
        RuoloDipendente ruolo = RuoloDipendente.AmministratoreCTT;
        String username = "username 002";
        String password = "Password2";
        Dipendente dip = new Dipendente(cdf, "Pino", "Perugini", ld, ruolo, username, password);

        mm.createDipendente(dip);

        Client client = ClientBuilder.newClient();
        WebTarget login = client.target("http://127.0.0.1:8081/rest/autentificazione");
        Form form1 = new Form();
        form1.param("username", "username 002");
        form1.param("password", "Password2");

        Response responselogin = login.request().post(Entity.form(form1));
        User user = responselogin.readEntity(User.class);
        token = user.getToken();
    }

    /**Droppa il database*/
    @After
    public void dropDB() {
        MongoDataManager mm = MongoDataManager.getInstance();
        mm.dropDB();
    }

    /** Test per il metodo rest/amministratore/reportStatisticoSacche dell'amministratoreCTT, va a buon fine*/
    @Test
    public void testCorretto(){
        Response responseReport = reportStatisticoSaccheLocale.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).get();
        assertEquals(Status.OK.getStatusCode(), responseReport.getStatus());
    }
}