package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestAmministratoreCTT;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;

public class ReportLocaleSaccheInviateERicevuteTest {
	EndPointRestAmministratoreCTT amm = new EndPointRestAmministratoreCTT();
	
	@BeforeClass public static void populateDBSacche() {
		
		MongoDataManager mm = new MongoDataManager();
    	List<Sacca> listaSacche = new ArrayList<Sacca>();
    	List<DatiSacca> listaDatiSacche = new ArrayList<DatiSacca>();
    	
    	//Caricamento sul sistema di cinque Sacche di tipo A+, 4 sacche sono arrivate nel magazzino tra il 15-07-2020 e il 02-05-2021 e hanno data di scadenza lontana (2022)
    	//Una sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le sacche sono non prenotate e quindi affidabili ad un ente esterno 

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
    	//Una sacca è arrivata nel 2018 ed è già scaduta
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
    	//Una sacca è arrivata nel 2018 ed è già scaduta
    	//Tutte le sacche sono non prenotate e quindi affidabili ad un ente esterno 

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
    	//Una sacca è arrivata nel 2018 ed è già scaduta
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
    	//Una sacca è arrivata nel 2018 ed è già scaduta
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

//Caricamento sul sistema di datiSacca relativi a sacche che hanno attraversato il magazzino nel passato (e quindi non sono presenti attualmente)
    	 
    	 Seriale ser = new Seriale("CTT001-00000100");
    	 gs = GruppoSanguigno.Am;
    	 localDataArrivo = LocalDate.of(2019,12,15); //YY-MM-DD
    	 LocalDate localDataAffidamento = LocalDate.of(2020,01,23);
    	 enteDonatore = "Ospedale3";
    	 String enteRichiedente = "Ospedale4";
    	 String indirizzoEnte = "Aversa, via kennedy 6";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000101");
    	 gs = GruppoSanguigno.Am;
    	 localDataArrivo = LocalDate.of(2019,01,01); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2019,12,12);
    	 enteDonatore = "Ospedale1";
    	 enteRichiedente = "Ospedale8";
    	 indirizzoEnte = "Napoli, via Churchill 1";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000102");
    	 gs = GruppoSanguigno.Am;
    	 localDataArrivo = LocalDate.of(2018,02,18); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2018,03,30);
    	 enteDonatore = "Ospedale2";
    	 enteRichiedente = "Ospedale6";
    	 indirizzoEnte = "Benevento, via Traiano 44";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000103");
    	 gs = GruppoSanguigno.Am;
    	 localDataArrivo = LocalDate.of(2016,03,04); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2017,02,06);
    	 enteDonatore = "Ospedale2";
    	 enteRichiedente = "Ospedale7";
    	 indirizzoEnte = "Morcone, via Roma 43";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000104");
    	 gs = GruppoSanguigno.Am;
    	 localDataArrivo = LocalDate.of(2000,8,18); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2000,9,14);
    	 enteDonatore = "Clinica Santa Rita";
    	 enteRichiedente = "FateBeneFratelli";
    	 indirizzoEnte = "Benevento, viale Principe di Savoia 14";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000105");
    	 gs = GruppoSanguigno.Ap;
    	 localDataArrivo = LocalDate.of(2002,03,17); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2002,03,20);
    	 enteDonatore = "SanPio";
    	 enteRichiedente = "FateBeneFratelli";
    	 indirizzoEnte = "Benevento, via Pacevecchia 140";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000106");
    	 gs = GruppoSanguigno.Ap;
    	 localDataArrivo = LocalDate.of(2007,11,19); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2008,9,14);
    	 enteDonatore = "Ospedale5";
    	 enteRichiedente = "FateBeneFratelli";
    	 indirizzoEnte = "SanBartolomeo, viale Mellusi 14";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000107");
    	 gs = GruppoSanguigno.Ap;
    	 localDataArrivo = LocalDate.of(2001,05,11); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2003,9,24);
    	 enteDonatore = "Clinica La Madonnina";
    	 enteRichiedente = "Milanello";
    	 indirizzoEnte = "Morcone, via della Montagna 3";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000108");
    	 gs = GruppoSanguigno.Ap;
    	 localDataArrivo = LocalDate.of(2008,06,30); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2009,9,14);
    	 enteDonatore = "Napoli1";
    	 enteRichiedente = "Via del Mare";
    	 indirizzoEnte = "Napoli, viale della misericordia 118";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000109");
    	 gs = GruppoSanguigno.Ap;
    	 localDataArrivo = LocalDate.of(2000,8,18); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2000,9,14);
    	 enteDonatore = "Clinica Santa Rita";
    	 enteRichiedente = "FateBeneFratelli";
    	 indirizzoEnte = "Benevento, viale Principe di Savoia 14";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);
    	                                                 
    	 ser = new Seriale("CTT001-00000110");
    	 gs = GruppoSanguigno.Bm;
    	 localDataArrivo = LocalDate.of(2004,10,10); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2004,10,30);
    	 enteDonatore = "Ospedale1";
    	 enteRichiedente = "Clinica Cardarelli";
    	 indirizzoEnte = "Montesarchio, viale Vittoria 30";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000111");
    	 gs = GruppoSanguigno.Bm;
    	 localDataArrivo = LocalDate.of(2001,11,05); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2001,12,31);
    	 enteDonatore = "Sant'Agostino";
    	 enteRichiedente = "BambinGesù";
    	 indirizzoEnte = "Avellino, via Università 31";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000112");
    	 gs = GruppoSanguigno.Bm;
    	 localDataArrivo = LocalDate.of(2002,07,07); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2002,8,30);
    	 enteDonatore = "Rummo";
    	 enteRichiedente = "FateBeneFratelli";
    	 indirizzoEnte = "Benevento, viale Principe di Savoia 14";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000113");
    	 gs = GruppoSanguigno.Bm;
    	 localDataArrivo = LocalDate.of(2012,8,18); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2014,9,14);
    	 enteDonatore = "Clinica Santa Rita";
    	 enteRichiedente = "FateBeneFratelli";
    	 indirizzoEnte = "Benevento, viale Principe di Savoia 14";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000114");
    	 gs = GruppoSanguigno.Bm;
    	 localDataArrivo = LocalDate.of(2019,12,30); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2020,01,01);
    	 enteDonatore = "Ospedale7";
    	 enteRichiedente = "OspedaleCentrale";
    	 indirizzoEnte = "Ariano Irpino, viale Principe di Savoia 14";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000115");
    	 gs = GruppoSanguigno.Bp;
    	 localDataArrivo = LocalDate.of(2017,01,12); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2019,10,14);
    	 enteDonatore = "Ospedale9";
    	 enteRichiedente = "Ospedale6";
    	 indirizzoEnte = "Salerno, via Roma 123";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000116");
    	 gs = GruppoSanguigno.Bp;
    	 localDataArrivo = LocalDate.of(2015,04,30); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2015,05,01);
    	 enteDonatore = "Ospedale4";
    	 enteRichiedente = "Ospedale3";
    	 indirizzoEnte = "Caserta, via Vittorio Veneto 17";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000117");
    	 gs = GruppoSanguigno.Bp;
    	 localDataArrivo = LocalDate.of(2018,07,21); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2020,01,01);
    	 enteDonatore = "ViaDelMare";
    	 enteRichiedente = "OspedaleCentrale";
    	 indirizzoEnte = "Paduli, via Madre di Gesù 12";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000118");
    	 gs = GruppoSanguigno.Bp;
    	 localDataArrivo = LocalDate.of(2016,12,01); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2017,01,01);
    	 enteDonatore = "Ospedale1";
    	 enteRichiedente = "OspedaleCentrale";
    	 indirizzoEnte = "Baselice, piazza Centrale 8";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000119");
    	 gs = GruppoSanguigno.Bp;
    	 localDataArrivo = LocalDate.of(2017,03,12); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2017,03,29);
    	 enteDonatore = "Ospedale1";
    	 enteRichiedente = "Santa Rosalia";
    	 indirizzoEnte = "Guardia Sanframondi, via Giro d'Italia 15";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);
    	                                
    	 ser = new Seriale("CTT001-00000120");
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataArrivo = LocalDate.of(2003,10,10); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2003,12,12);
    	 enteDonatore = "Ospedale1";
    	 enteRichiedente = "Ospedale4";
    	 indirizzoEnte = "Cerreto Sannita, piazza Italia 157";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000121");
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataArrivo = LocalDate.of(2015,8,05); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2016,04,29);
    	 enteDonatore = "Ospedale9";
    	 enteRichiedente = "Ospedale8";
    	 indirizzoEnte = "SanLupo, via città libera 34";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000122");
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataArrivo = LocalDate.of(2014,06,15); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2014,07,30);
    	 enteDonatore = "Ospedale1";
    	 enteRichiedente = "Santa Rosalia";
    	 indirizzoEnte = "Guardia Sanframondi, via Giro d'Italia 15";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000123");
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataArrivo = LocalDate.of(2014,02,14); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2014,02,28);
    	 enteDonatore = "Ospedale12";
    	 enteRichiedente = "Giustiniano";
    	 indirizzoEnte = "Castelpagano, corso italico 14";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000124");
    	 gs = GruppoSanguigno.ZEROp;
    	 localDataArrivo = LocalDate.of(2001,01,10); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2002,01,18);
    	 enteDonatore = "Rummo";
    	 enteRichiedente = "Civile";
    	 indirizzoEnte = "Campolattaro, via Roma 156";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);
    	                                       
    	 ser = new Seriale("CTT001-00000125");
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataArrivo = LocalDate.of(2001,01,10); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2001,01,18);
    	 enteDonatore = "Ospedale3";
    	 enteRichiedente = "Civile";
    	 indirizzoEnte = "Pontelandolfo, via Roma 156";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000126");
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataArrivo = LocalDate.of(2010,10,10); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2011,01,02);
    	 enteDonatore = "Civile";
    	 enteRichiedente = "Rummo";
    	 indirizzoEnte = "Baselice, via della liberazione 15";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000127");
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataArrivo = LocalDate.of(2020,10,20); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2021,05,14);
    	 enteDonatore = "FateBeneFratelli";
    	 enteRichiedente = "Clinica la Madonnina";
    	 indirizzoEnte = "Morcone, via Mastino 20";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000128");
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataArrivo = LocalDate.of(2020,9,30); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2021,05,01);
    	 enteDonatore = "Ospedale5";
    	 enteRichiedente = "Clinica Santa Rita";
    	 indirizzoEnte = "Ariano Irpino, via Roma 189";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000129");
    	 gs = GruppoSanguigno.ZEROm;
    	 localDataArrivo = LocalDate.of(2020,9,14); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2021,01,01);
    	 enteDonatore = "Ospedale1";
    	 enteRichiedente = "Ospedale4";
    	 indirizzoEnte = "Cerreto Sannita, via Roma 1";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);
    	                                    
    	 ser = new Seriale("CTT001-00000130");
    	 gs = GruppoSanguigno.ABp;
    	 localDataArrivo = LocalDate.of(2000,10,14); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2002,12,01);
    	 enteDonatore = "Ospedale10";
    	 enteRichiedente = "Ospedale12";
    	 indirizzoEnte = "Sassinoro, via Roma 111";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000131");
    	 gs = GruppoSanguigno.ABp;
    	 localDataArrivo = LocalDate.of(2020,01,17); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2020,05,10);
    	 enteDonatore = "Ospeale1";
    	 enteRichiedente = "Casa di Riposo 12";
    	 indirizzoEnte = "Benevento, viale mellusi 12";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000132");
    	 gs = GruppoSanguigno.ABp;
    	 localDataArrivo = LocalDate.of(2018,9,14); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2018,10,01);
    	 enteDonatore = "Ospedale1";
    	 enteRichiedente = "Ospedale4";
    	 indirizzoEnte = "Cerreto Sannita, via Roma 1";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000133");
    	 gs = GruppoSanguigno.ABp;
    	 localDataArrivo = LocalDate.of(2017,10,12); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2017,11,02);
    	 enteDonatore = "Ospedale2";
    	 enteRichiedente = "Ospedale3";
    	 indirizzoEnte = "Casalduni, via della liberazione 12";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000134");
    	 gs = GruppoSanguigno.ABp;
    	 localDataArrivo = LocalDate.of(2020,10,12); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2021,02,12);
    	 enteDonatore = "Ospedale2";
    	 enteRichiedente = "Ospedale3";
    	 indirizzoEnte = "Napoli, Lungomare 12";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);
    	                                 
    	 ser = new Seriale("CTT001-00000135");
    	 gs = GruppoSanguigno.ABm;
    	 localDataArrivo = LocalDate.of(2002,01,12); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2004,01,01);
    	 enteDonatore = "Rummo";
    	 enteRichiedente = "Napoli1";
    	 indirizzoEnte = "Casoria, via Luigi Pirandello 19";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);         

    	 ser = new Seriale("CTT001-00000136");
    	 gs = GruppoSanguigno.ABm;
    	 localDataArrivo = LocalDate.of(2020,10,12); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2021,02,12);
    	 enteDonatore = "Ospedale2";
    	 enteRichiedente = "Ospedale3";
    	 indirizzoEnte = "Napoli, Lungomare 12";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000137");
    	 gs = GruppoSanguigno.ABm;
    	 localDataArrivo = LocalDate.of(2020,10,12); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2021,02,12);
    	 enteDonatore = "Ospedale2";
    	 enteRichiedente = "Ospedale3";
    	 indirizzoEnte = "Napoli, Lungomare 12";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000138");
    	 gs = GruppoSanguigno.ABm;
    	 localDataArrivo = LocalDate.of(2020,10,12); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2021,02,12);
    	 enteDonatore = "Ospedale2";
    	 enteRichiedente = "Ospedale3";
    	 indirizzoEnte = "Napoli, Lungomare 12";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);

    	 ser = new Seriale("CTT001-00000139");
    	 gs = GruppoSanguigno.ABm;
    	 localDataArrivo = LocalDate.of(2020,10,12); //YY-MM-DD
    	 localDataAffidamento = LocalDate.of(2021,02,12);
    	 enteDonatore = "Ospedale2";
    	 enteRichiedente = "Ospedale3";
    	 indirizzoEnte = "Napoli, Lungomare 12";
    	 datisacca = new DatiSacca(ser, gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente, indirizzoEnte);
    	 listaDatiSacche.add(datisacca);
    	                                   
    	    	
    	for(Sacca sac : listaSacche) {
        	mm.createSacca(sac);
        }
    	
    	for(DatiSacca datisac : listaDatiSacche) {
        	mm.createDatiSacca(datisac);
        }
	}
	
	/**
	 * Test che dovrebbe restituire una lista di DatiSacca con 1 elemento
	 * @throws ParseException
	 */
	@Test public void test1() throws ParseException {
		assertEquals(1, amm.ReportLocaleSaccheInviateERicevuteCTT(Constants.sdf.parse("01-01-2021"), Constants.sdf.parse("01-02-2021")).size());
	}
	
	/**
	 * Test che dovrebbe restituire una lista di DatiSacca con 14 elementi
	 * @throws ParseException
	 */
	@Test public void test2() throws ParseException {
		assertEquals(14, amm.ReportLocaleSaccheInviateERicevuteCTT(Constants.sdf.parse("01-01-2021"), Constants.sdf.parse("01-04-2021")).size());
	}
	
	/**
	 * Test che dovrebbe restituire una lista di DatiSacca con 40 elementi
	 * @throws ParseException
	 */
	@Test public void test3() throws ParseException {
		assertEquals(40, amm.ReportLocaleSaccheInviateERicevuteCTT(Constants.sdf.parse("01-01-2021"), Constants.sdf.parse("01-06-2021")).size());
	}

	@AfterClass public static void dropDBSacche() {
		MongoDataManager mm = new MongoDataManager();
		mm.dropDB();
	}
}