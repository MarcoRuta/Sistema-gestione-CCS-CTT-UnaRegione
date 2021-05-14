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
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyAmministratoreCTTDataManager;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyMongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;

public class OrdinaGruppiSanguingiPerRichiestaTest {
MyAmministratoreCTTDataManager amm = new MyAmministratoreCTTDataManager();
	
	@BeforeClass public static void populateDBSacche() {
		
    	MyMongoDataManager mm = new MyMongoDataManager();
    	List<Sacca> listaSacche = new ArrayList<Sacca>();
    	List<DatiSacca> listaDatiSacche = new ArrayList<DatiSacca>();
    	
   	  	
    	GruppoSanguigno gs = GruppoSanguigno.Ap;
    	LocalDate localDataProduzione = LocalDate.of(2021,04,10);
    	LocalDate localDataScadenza = LocalDate.of(2021,12,12);
    	Boolean prenotato = false;
    	Sacca sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	LocalDate localDataArrivo = LocalDate.of(2018,04,12);
    	LocalDate localDataAffidamento = LocalDate.of(2018,05,30);
    	String enteDonatore = "ospedale1";
    	String enteRichiedente = "Ospedale2";
    	DatiSacca datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.Am;
    	localDataProduzione = LocalDate.of(2021,05,10);
    	localDataScadenza = LocalDate.of(2022,12,9);
    	prenotato = true;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2021,05,11);
    	localDataAffidamento = LocalDate.of(2021,12,07);
    	enteDonatore = "Ospedale3";
    	enteRichiedente = "Ospedale1";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.Bp;
    	localDataProduzione = LocalDate.of(2020,12,05);
    	localDataScadenza = LocalDate.of(2021,05,14);
    	prenotato = true;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2020,12,10);
    	localDataAffidamento = LocalDate.of(2020,12,15);
    	enteDonatore = "Ospedale4";
    	enteRichiedente = "Ospedale1";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.ZEROp;
    	localDataProduzione = LocalDate.of(2015,03,04);
    	localDataScadenza = LocalDate.of(2017,11,02);
    	prenotato = false;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2016,06,25);
    	localDataAffidamento = LocalDate.of(2016,12,29);
    	enteDonatore = "Ospedale3";
    	enteRichiedente = "Ospedale5";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 

    	
    	gs = GruppoSanguigno.ZEROm;
    	localDataProduzione = LocalDate.of(2021,05,12);
    	localDataScadenza = LocalDate.of(2021,05,13);
    	prenotato = true;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2021,06,23);
    	localDataAffidamento = LocalDate.of(2021,07,03);
    	enteDonatore = "Ospedale6";
    	enteRichiedente = "Ospedale5";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 

    	
    	gs = GruppoSanguigno.ABp;
    	localDataProduzione = LocalDate.of(2021,05,06);
    	localDataScadenza = LocalDate.of(2021,07, 9);
    	prenotato = false;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);

    	localDataArrivo = LocalDate.of(2021,05,12);
    	localDataAffidamento = LocalDate.of(2021,05,30);
    	enteDonatore = "Ospedale3";
    	enteRichiedente = "Ospedale1";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 

    	
    	gs = GruppoSanguigno.ABm;
    	localDataProduzione = LocalDate.of(2021, 8,06);
    	localDataScadenza = LocalDate.of(2022,07, 9);
    	prenotato = true;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2021,04,22);
    	localDataAffidamento = LocalDate.of(2021,04,24);
    	enteDonatore = "Ospedale1";
    	enteRichiedente = "Ospedale6";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.Ap;
    	localDataProduzione = LocalDate.of(2012,02,01);
    	localDataScadenza = LocalDate.of(2014,03,12);
    	prenotato = true;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);

    	localDataArrivo = LocalDate.of(2021,03,12);
    	localDataAffidamento = LocalDate.of(2021,04,23);
    	enteDonatore = "Ospedale5";
    	enteRichiedente = "Ospedale6";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.Am;
    	localDataProduzione = LocalDate.of(2019,11,10);
    	localDataScadenza = LocalDate.of(2021,04,23);
    	prenotato = false;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2019,12,15);
    	localDataAffidamento = LocalDate.of(2020,01,23);
    	enteDonatore = "Ospedale3";
    	enteRichiedente = "Ospedale4";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.Bm;
    	localDataProduzione = LocalDate.of(2020,04,23);
    	localDataScadenza = LocalDate.of(2020,05,12);
    	prenotato = false;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2020,05,26);
    	localDataAffidamento = LocalDate.of(2020,06,23);
    	enteDonatore = "Ospedale4";
    	enteRichiedente = "Ospedale2";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.Bp;
    	localDataProduzione = LocalDate.of(2020,03,26);
    	localDataScadenza = LocalDate.of(2022,10,27);
    	prenotato = false;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);

    	localDataArrivo = LocalDate.of(2020,04,07);
    	localDataAffidamento = LocalDate.of(2020,05,05);
    	enteDonatore = "Ospedale3";
    	enteRichiedente = "Ospedale2";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.ZEROp;
    	localDataProduzione = LocalDate.of(2019, 9,04);
    	localDataScadenza = LocalDate.of(2021,10,17);
    	prenotato = true;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2019,11,13);
    	localDataAffidamento = LocalDate.of(2019,12,23);
    	enteDonatore = "Ospedale1";
    	enteRichiedente = "Ospedale2";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.ZEROm;
    	localDataProduzione = LocalDate.of(2018,01,12);
    	localDataScadenza = LocalDate.of(2020, 8,18);
    	prenotato = false;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2020,03,15);
    	localDataAffidamento = LocalDate.of(2020,05,24);
    	enteDonatore = "Ospedale2";
    	enteRichiedente = "Ospedale5";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.ABp;
    	localDataProduzione = LocalDate.of(2020,11,12);
    	localDataScadenza = LocalDate.of(2022,04,18);
    	prenotato = true;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2020,12,16);
    	localDataAffidamento = LocalDate.of(2021,01,23);
    	enteDonatore = "Ospedale3";
    	enteRichiedente = "Ospedale6";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.ABm;
    	localDataProduzione = LocalDate.of(2020,11,07);
    	localDataScadenza = LocalDate.of(2021,05,26);
    	prenotato = true;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2020,12,15);
    	localDataAffidamento = LocalDate.of(2021,01,23);
    	enteDonatore = "Ospedale5";
    	enteRichiedente = "Ospedale2";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.Ap;
    	localDataProduzione = LocalDate.of(2016,03,15);
    	localDataScadenza = LocalDate.of(2018,05,11);
    	prenotato = false;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    
    	localDataArrivo = LocalDate.of(2016,06,23);
    	localDataAffidamento = LocalDate.of(2016,07,17);
    	enteDonatore = "Ospedale4";
    	enteRichiedente = "Ospedale1";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.Am;
    	localDataProduzione = LocalDate.of(2020,01,17);
    	localDataScadenza = LocalDate.of(2022,07,12);
    	prenotato = true;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2020,05,11);
    	localDataAffidamento = LocalDate.of(2020,10,22);
    	enteDonatore = "Ospedale6";
    	enteRichiedente = "Ospedale3";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.Bp;
    	localDataProduzione = LocalDate.of(2020,02,12);
    	localDataScadenza = LocalDate.of(2022,06,30);
    	prenotato = true;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2021,11,04);
    	localDataAffidamento = LocalDate.of(2020,12,07);
    	enteDonatore = "Ospedale4";
    	enteRichiedente = "Ospedale5";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 


    	gs = GruppoSanguigno.Bm;
    	localDataProduzione = LocalDate.of(2018,04,17);
    	localDataScadenza = LocalDate.of(2018,12,28);
    	prenotato = false;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2018,05,24);
    	localDataAffidamento = LocalDate.of(2018,06,12);
    	enteDonatore = "Ospedale5";
    	enteRichiedente = "Ospedale2";
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 
    	
    	gs = GruppoSanguigno.Bm;
    	localDataProduzione = LocalDate.of(2019,10,17);
    	localDataScadenza = LocalDate.of(2019,12,24);
    	prenotato = false;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2019,07,10);
    	localDataAffidamento = null;
    	enteDonatore = "Ospedale5";
    	enteRichiedente = null;
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca); 
    	
    	
    	gs = GruppoSanguigno.ABm;
    	localDataProduzione = LocalDate.of(2020,12,12);
    	localDataScadenza = LocalDate.of(2019,12,24);
    	prenotato = false;
    	sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	listaSacche.add(sacca);
    	    	
    	localDataArrivo = LocalDate.of(2019,07,10);
    	localDataAffidamento = null;
    	enteDonatore = "Ospedale5";
    	enteRichiedente = null;
    	datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, localDataAffidamento, enteDonatore, enteRichiedente);
    	listaDatiSacche.add(datisacca);
    	
    	
    	for(Sacca sac : listaSacche) {
        	mm.createSacca(sac);
        }
    	
    	for(DatiSacca datisac : listaDatiSacche) {
        	mm.createDatiSacca(datisac);
        }
	}
	
	@Test public void test1() throws ParseException {
		 List<GruppoSanguigno> lista = new ArrayList<GruppoSanguigno>();
	        lista.add(GruppoSanguigno.Am);
		assertEquals("Gruppo sanguigno Am= 1", amm.OrdinaGruppiSanguigniPerRichieste(lista,Constants.sdf.parse("01-01-2021"), Constants.sdf.parse("31-12-2021")));
	}
	
	@Test public void test2() throws ParseException {
		 List<GruppoSanguigno> lista = new ArrayList<GruppoSanguigno>();
	        lista.add(GruppoSanguigno.Bm);
		assertEquals("Gruppo sanguigno Bm= 0", amm.OrdinaGruppiSanguigniPerRichieste(lista,Constants.sdf.parse("01-01-2021"), Constants.sdf.parse("31-12-2021")));
	}


	@AfterClass public static void dropDBSacche() {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.dropDB();
	}
}
