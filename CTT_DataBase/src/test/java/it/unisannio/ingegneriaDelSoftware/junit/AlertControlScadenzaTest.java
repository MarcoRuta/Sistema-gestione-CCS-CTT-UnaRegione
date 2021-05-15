package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.CTT;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointMagazziniereCTT;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class AlertControlScadenzaTest {
	EndPointMagazziniereCTT magazz = new EndPointMagazziniereCTT();
	CTT ctt = new CTT();
	
	@BeforeClass public static void populateDBSacche() {
		
	    	MongoDataManager mm = new MongoDataManager();
	    	List<Sacca> listaSacche = new ArrayList<Sacca>();
	    	List<DatiSacca> listaDatiSacche = new ArrayList<DatiSacca>();
	    	
	    	//Caricamento sul sistema di cinque Sacche di tipo A+, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana (2022)
	    	//Una sacca è arrrivata nel 2018 ed è già scaduta
	    	//Tutte le sacche sono non prenotate e quindi affidabili ad un ente esterno 

	    	//Prima sacca 
	    	GruppoSanguigno gs = GruppoSanguigno.Ap;
	    	LocalDate localDataProduzione = LocalDate.of(2020,04,10);
	    	LocalDate localDataScadenza = LocalDate.now().plusDays(2);
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
	    	//Una sacca è arrrivata nel 2018 ed è già scaduta
	    	//Tutte le sacche sono non prenotate e quindi affidabili ad un ente esterno 

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
	    	//Una sacca è arrrivata nel 2018 ed è già scaduta
	    	//Tutte le sacche sono non prenotate e quindi affidabili ad un ente esterno 

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
	    	//Una sacca è arrrivata nel 2018 ed è già scaduta
	    	//Tutte le sacche sono non prenotate e quindi affidabili ad un ente esterno 

	    	//Prima sacca 
	    	 gs = GruppoSanguigno.Bm;
	    	 localDataProduzione = LocalDate.of(2020,04,10);
	    	 localDataScadenza = LocalDate.now().plusDays(2);
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
	    	//Una sacca è arrrivata nel 2018 ed è già scaduta
	    	//Tutte le sacche sono non prenotate e quindi affidabili ad un ente esterno 

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
	    	 localDataScadenza = LocalDate.now().plusDays(2);
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
	    	//Una sacca è arrrivata nel 2018 ed è già scaduta
	    	//Tutte le sacche sono non prenotate e quindi affidabili ad un ente esterno 

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
	    	//Una sacca è arrrivata nel 2018 ed è già scaduta
	    	//Tutte le sacche sono non prenotate e quindi affidabili ad un ente esterno 

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
	    	//Una sacca è arrrivata nel 2018 ed è già scaduta
	    	//Tutte le sacche sono non prenotate e quindi affidabili ad un ente esterno 

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
	    	 prenotato = false;
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
		}
	
	/**
	*Test che dovrebbe restituire una lista con 3 Sacche che stanno per scadere (nel beforeclass ci sono 3 sacche con dataScadenza LocalDate.now().plusDays(2)!)
	*/
	@Test	
	public void test1(){  	
		assertEquals(3, ctt.alertControlScadenza().size());
	}
	
	@AfterClass public static void dropDBSacche() {
		MongoDataManager mm = new MongoDataManager();
		mm.dropDB();
	}
}
