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
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.User;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class RicercaSaccaLocaleTest {

	static MongoDataManager md = MongoDataManager.getInstance();
	static String token = null;
	Client client = ClientBuilder.newClient();
	WebTarget evasioneSacca = client.target("http://127.0.0.1:8080/rest/operatore/ricerca");
	
	/**
     * Metodo statico per il popolamento del database
     */
	public static void populateDB() throws EntityAlreadyExistsException {
		
	    	List<Sacca> listaSacche = new ArrayList<Sacca>();
	    	List<DatiSacca> listaDatiSacche = new ArrayList<DatiSacca>();
	
	    	// Carico cinque sacche per tipo 
	    	
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

	    	 //Quinta sacca
	    	 gs = GruppoSanguigno.Ap;
	    	 localDataProduzione = LocalDate.of(2018,06,10);
	    	 localDataScadenza = LocalDate.of(2023,06,10);
	    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
	    	 listaSacche.add(sacca);
	    	        
	    	 localDataArrivo = LocalDate.of(2021,05,02);
	    	 enteDonatore = "AVIS - Napoli_Sud";
	    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
	    	 listaDatiSacche.add(datisacca); 

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

	    	//Quinta sacca
	    	 gs = GruppoSanguigno.Am;
	    	 localDataProduzione = LocalDate.of(2018,06,10);
	    	 localDataScadenza = LocalDate.of(2023,06,10);
	    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
	    	 listaSacche.add(sacca);
	    	        
	    	 localDataArrivo = LocalDate.of(2021,05,02);
	    	 enteDonatore = "AVIS - Napoli_Sud";
	    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
	    	 listaDatiSacche.add(datisacca); 

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


	    	//Quinta sacca
	    	 gs = GruppoSanguigno.Bp;
	    	 localDataProduzione = LocalDate.of(2018,06,10);
	    	 localDataScadenza = LocalDate.of(2023,06,10);
	    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
	    	 listaSacche.add(sacca);
	    	        
	    	 localDataArrivo = LocalDate.of(2021,05,02);
	    	 enteDonatore = "AVIS - Napoli_Sud";
	    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
	    	 listaDatiSacche.add(datisacca); 
	    	 
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


	    	//Quinta sacca
	    	 gs = GruppoSanguigno.Bm;
	    	 localDataProduzione = LocalDate.of(2018,06,10);
	    	 localDataScadenza = LocalDate.of(2022,06,10);
	    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
	    	 listaSacche.add(sacca);
	    	        
	    	 localDataArrivo = LocalDate.of(2021,05,02);
	    	 enteDonatore = "AVIS - Napoli_Sud";
	    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
	    	 listaDatiSacche.add(datisacca); 

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

	    	//Quinta sacca
	    	 gs = GruppoSanguigno.ABp;
	    	 localDataProduzione = LocalDate.of(2018,06,10);
	    	 localDataScadenza = LocalDate.of(2023,06,10);
	    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
	    	 listaSacche.add(sacca);
	    	        
	    	 localDataArrivo = LocalDate.of(2021,05,02);
	    	 enteDonatore = "AVIS - Napoli_Sud";
	    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
	    	 listaDatiSacche.add(datisacca); 

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


	    	//Quinta sacca
	    	 gs = GruppoSanguigno.ABm;
	    	 localDataProduzione = LocalDate.of(2018,06,10);
	    	 localDataScadenza = LocalDate.of(2022,06,10);
	    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
	    	 listaSacche.add(sacca);
	    	        
	    	 localDataArrivo = LocalDate.of(2021,05,02);
	    	 enteDonatore = "AVIS - Napoli_Sud";
	    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
	    	 listaDatiSacche.add(datisacca); 

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


	    	//Quinta sacca
	    	 gs = GruppoSanguigno.ZEROp;
	    	 localDataProduzione = LocalDate.of(2018,06,10);
	    	 localDataScadenza = LocalDate.of(2023,06,10);
	    	 sacca = new Sacca(gs, localDataProduzione, localDataScadenza);
	    	 listaSacche.add(sacca);
	    	        
	    	 localDataArrivo = LocalDate.of(2021,05,02);
	    	 enteDonatore = "AVIS - Napoli_Sud";
	    	 datisacca = new DatiSacca(sacca.getSeriale(), gs, localDataArrivo, null, enteDonatore, null,null);
	    	 listaDatiSacche.add(datisacca); 

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


	    	//Quinta sacca
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
	
	/**
     * Metodo statico per la distruzione del database
     */
	public static void dropDB() {
		md.dropDB();
	}
	
	/**
	 * Test per la ricerca sacca in locale sull'EndPointRestOperatoreCTT
	 * @throws EntityAlreadyExistsException
	 */
	@Test
	public void test1() throws EntityAlreadyExistsException{
		RicercaSaccaLocaleTest.dropDB();
		RicercaSaccaLocaleTest.populateDB();
		WebTarget ev1 = evasioneSacca.queryParam("gruppoSanguigno", "Bp")
									 .queryParam("numeroSacche", "5")
									 .queryParam("dataArrivoMassima", "2021-05-22")
									 .queryParam("enteRichiedente", "Ospedale Rummo")
									 .queryParam("indirizzoEnte", "Benevento, via pacevecchia 2")
									 .queryParam("priorità", "TRUE");
									 
		Response responseRicerca = ev1.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).get();
		assertEquals(Status.OK.getStatusCode(), responseRicerca.getStatus());
		RicercaSaccaLocaleTest.dropDB();
	}
	
	/**
	 * Test per la ricerca sacca in locale non disponibile sull'EndPointRestOperatoreCTT
	 * @throws EntityAlreadyExistsException
	 */
	@Test
	public void test2() throws EntityAlreadyExistsException{
		RicercaSaccaLocaleTest.populateDB();
		WebTarget ev1 = evasioneSacca.queryParam("gruppoSanguigno", "Ap")
									 .queryParam("numeroSacche", "150")
									 .queryParam("dataArrivoMassima", "2021-05-22")
									 .queryParam("enteRichiedente", "Ospedale Rummo")
									 .queryParam("indirizzoEnte", "Benevento, via pacevecchia 2")
									 .queryParam("priorità", "TRUE");
									 
		Response responseRicerca = ev1.request().header(HttpHeaders.AUTHORIZATION, "Basic "+token).get();
		assertEquals(Status.NOT_FOUND.getStatusCode(), responseRicerca.getStatus());
		RicercaSaccaLocaleTest.dropDB();
	}
}