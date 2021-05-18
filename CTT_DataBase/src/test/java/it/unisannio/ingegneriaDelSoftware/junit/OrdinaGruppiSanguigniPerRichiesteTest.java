package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
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

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestAmministratoreCTT;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;


public class OrdinaGruppiSanguigniPerRichiesteTest {
	static NewCookie cookie = null;
	
	@BeforeClass public static void populateDBSacche() throws SaccaNotFoundException, AssertionError, ParseException {
		
    	MongoDataManager mm = new MongoDataManager();
    	List<Sacca> listaSacche = new ArrayList<Sacca>();
    	List<DatiSacca> listaDatiSacche = new ArrayList<DatiSacca>();
    	
   	  	
    	//Caricamento sul sistema di cinque Sacche di tipo A+, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana (2022)
    	//Una sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca
    	GruppoSanguigno gs = GruppoSanguigno.Ap;
    	LocalDate localDataProduzione = LocalDate.of(2020,04,10);
    	LocalDate localDataScadenza = LocalDate.of(2022,04,10);
    	Boolean prenotato = false;
    	Sacca sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	        
    	LocalDate localDataArrivo = LocalDate.of(2020,07,15);
    	String enteDonatore = "AVIS - Benevento";
    	DatiSacca datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.Ap;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.Ap;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.Ap;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.Ap;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2020,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
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
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.Am;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.Am;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.Am;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.Am;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2020,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
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
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.Bp;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.Bp;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.Bp;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.Bp;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2020,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
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
    	 localDataScadenza = LocalDate.of(2022,04,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.Bm;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.Bm;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.Bm;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.Bm;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2020,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Caricamento sul sistema di cinque Sacche di tipo AB+, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana
    	//Una sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca 
    	 gs = GruppoSanguigno.ABp;
    	 localDataProduzione = LocalDate.of(2020,04,10);
    	 localDataScadenza = LocalDate.of(2022,04,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.ABp;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.ABp;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.ABp;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.ABp;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2020,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Caricamento sul sistema di cinque Sacche di tipo AB-, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana
    	//Una sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca 
    	 gs = GruppoSanguigno.ABm;
    	 localDataProduzione = LocalDate.of(2020,04,10);
    	 localDataScadenza = LocalDate.of(2022,04,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.ABm;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.ABm;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.ABm;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.ABm;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2020,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
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
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2020,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Caricamento sul sistema di cinque Sacche di tipo ZERO-, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana
    	//Una sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le Sacche sono non prenotate e quindi affidabili ad un ente esterno 

    	//Prima sacca 
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataProduzione = LocalDate.of(2020,04,10);
    	 localDataScadenza = LocalDate.of(2022,04,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2020,07,15);
    	 enteDonatore = "AVIS - Benevento";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Seconda sacca 
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataProduzione = LocalDate.of(2020,05,10);
    	 localDataScadenza = LocalDate.of(2022,05,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,03,9);
    	 enteDonatore = "AVIS - Avellino";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Terza sacca 
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataProduzione = LocalDate.of(2020,06,10);
    	 localDataScadenza = LocalDate.of(2022,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Centrale";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	//Quarta sacca
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataProduzione = LocalDate.of(2020,07,12);
    	 localDataScadenza = LocalDate.of(2022,07,12);
    	 prenotato = true;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Nord";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 


    	//La sacca scaduta
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataProduzione = LocalDate.of(2018,06,10);
    	 localDataScadenza = LocalDate.of(2020,06,10);
    	 prenotato = false;
    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	 listaSacche.add(sacca);
    	        
    	 localDataArrivo = LocalDate.of(2021,05,02);
    	 enteDonatore = "AVIS - Napoli_Sud";
    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
    	 listaDatiSacche.add(datisacca); 

    	
    	for(Sacca sac : listaSacche) {
        	mm.createSacca(sac);
        }
    	
    	for(DatiSacca datisac : listaDatiSacche) {
        	mm.createDatiSacca(datisac);
        }

        Dipendente d = new Dipendente(Cdf.getCDF("999hpoindj13ht9f"), "Mario", "Magazz", DateUtil.convertDateToLocalDate(Constants.sdf.parse("10-07-1950")), RuoloDipendente.AmministratoreCTT, "username 999", "999");
        mm.addDipendente(d);
    	
    	Client client = ClientBuilder.newClient();
		WebTarget login = client.target("http://127.0.0.1:8080/rest/autentificazione");
		Form form1 = new Form();
		form1.param("username", "username 999");
		form1.param("password", "999");
		
		Response responselogin = login.request().post(Entity.form(form1));
		cookie = responselogin.getCookies().get("access_token");  	
	}
	
	
	/**
	 * Test che dovrebbe restituire il testo "Gruppo sanguigno Am= 1", siccome viene effettuato il metodo solo sul gruppo sanguigno Am
	 * @throws ParseException
	 */
	@Test 
	public void test1(){
		//#################################################percorso diverso siccome manca il form adesso
		Client client = ClientBuilder.newClient();
		WebTarget OrdinaGruppiSanguigniPerRichieste = client.target("http://127.0.0.1:8080/rest/amministratore/ordinagruppisanguigniperrichieste/##############################################");
		Invocation.Builder invocationBuilder = OrdinaGruppiSanguigniPerRichieste.request(MediaType.TEXT_PLAIN);
		invocationBuilder.cookie(cookie);
		Form form1 = new Form();
		form1.param("listaGS", "Ap");
		form1.param("dataInizio", "2021-01-01");
		form1.param("dataFine", "2021-12-31");

		
		Response responseOrdinaGruppiSanguigniPerRichieste = invocationBuilder.post(Entity.form(form1));
		assertEquals(Status.OK.getStatusCode(), responseOrdinaGruppiSanguigniPerRichieste.getStatus());
	}
	

	/**
	 * Test che dovrebbe restituire il testo "Gruppo sanguigno Am= 1", siccome viene effettuato il metodo solo sul gruppo sanguigno Am
	 * @throws ParseException
	 */
	@Test 
	public void test2(){
//################################################# percorso diverso siccome manca il form adesso
		
		Client client = ClientBuilder.newClient();
		WebTarget OrdinaGruppiSanguigniPerRichieste = client.target("http://127.0.0.1:8080/rest/amministratore/ordinagruppisanguigniperrichieste/##############################################");
		Invocation.Builder invocationBuilder = OrdinaGruppiSanguigniPerRichieste.request(MediaType.TEXT_PLAIN);
		invocationBuilder.cookie(cookie);
		Form form1 = new Form();
//#################################### andrebbe fatto anche con una lista con più gruppi sanguigni
		form1.param("listaGS", "Ap");
		form1.param("dataInizio", "2021-01-01");
		form1.param("dataFine", "2021-12-31");

		
		Response responseOrdinaGruppiSanguigniPerRichieste = invocationBuilder.post(Entity.form(form1));
		assertEquals(Status.OK.getStatusCode(), responseOrdinaGruppiSanguigniPerRichieste.getStatus());
	}
	
	
	@AfterClass public static void dropDBSacche() {
		MongoDataManager mm = new MongoDataManager();
		mm.dropDB();
	}
}