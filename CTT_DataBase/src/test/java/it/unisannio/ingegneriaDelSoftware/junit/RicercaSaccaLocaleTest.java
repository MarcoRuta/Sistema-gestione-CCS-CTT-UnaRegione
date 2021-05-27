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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;

public class RicercaSaccaLocaleTest {

	static MongoDataManager md = MongoDataManager.getInstance();
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget evasioneSacca = client.target("http://127.0.0.1:8080/rest/operatore/ricerca");
	
	@BeforeClass public static void populateDBSacche() throws SaccaNotFoundException, AssertionError, ParseException {
		

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
	    	                                   
	    	    	
	    	for(Sacca sac : listaSacche) {
	    		md.createSacca(sac);
	        }
	    	
	    	for(DatiSacca datisac : listaDatiSacche) {
	    		md.createDatiSacca(datisac);
	        }
	    	
	    	Dipendente d = new Dipendente(Cdf.getCDF("PVFDTP90P61I426D"), "Mario", "Magazz", LocalDate.parse("1955-07-10"), RuoloDipendente.OperatoreCTT, "admin", "Adminadmin1");
	        md.createDipendente(d);
	    	
	        Client client = ClientBuilder.newClient();
			WebTarget login = client.target("http://127.0.0.1:8080/rest/autentificazione");
			Form form1 = new Form();
			form1.param("username", "admin");
			form1.param("password", "Adminadmin1");
			
			Response responselogin = login.request().post(Entity.form(form1));
			User user = responselogin.readEntity(User.class);
			token = user.getToken(); 
	 		
	 		
		}
	
	@Test
	public void test1(){
		WebTarget ev1 = evasioneSacca.queryParam("gruppoSanguigno", "Bp")
									 .queryParam("numeroSacche", "5")
									 .queryParam("dataArrivoMassima", "2021-05-22")
									 .queryParam("enteRichiedente", "Ospedale Rummo")
									 .queryParam("indirizzoEnte", "Benevento, via pacevecchia 2")
									 .queryParam("priorità", "TRUE");
									 
		Response responseRicerca = ev1.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).get();
		assertEquals(Status.OK.getStatusCode(), responseRicerca.getStatus());
	}
	
	
	@Test
	public void test2(){
		WebTarget ev1 = evasioneSacca.queryParam("gruppoSanguigno", "Ap")
									 .queryParam("numeroSacche", "150")
									 .queryParam("dataArrivoMassima", "2021-05-22")
									 .queryParam("enteRichiedente", "Ospedale Rummo")
									 .queryParam("indirizzoEnte", "Benevento, via pacevecchia 2")
									 .queryParam("priorità", "TRUE");
									 
		Response responseRicerca = ev1.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), responseRicerca.getStatus());
	}

	@AfterClass public static void dropDBSacche() {
		md.dropDB();
	}
	
	
}