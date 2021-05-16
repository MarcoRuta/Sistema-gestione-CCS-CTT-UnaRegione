package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.CTTNotFoundException;



public class RemoveCTTTest {
	/**
	 * Classe per la popolazione del database
	 * 					
	 * @throws ParseException
	 */
	@BeforeClass 
	public static void populateDBCTT() throws ParseException {
		
		MongoDataManager amm = new MongoDataManager();
		List<CTT> listaCTT = new ArrayList<CTT>();
	        
		Integer numero = 1;
        String denominazione = "San Giuliano";
        String provincia = "NA";
        String città = "Giugliano";
        String telefono = "0818955111";
        String indirizzo = "Via Montello 21";
        String email = "sangiuliano@aruba.com";
        double latitudine = 44.5;
        double longitudine = 20.6;
        CTT CTT001 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);  
        listaCTT.add(CTT001);
        
        numero = 2;
        denominazione = "Ospedale del Mare";
        provincia = "NA";
        città = "Napoli";
        telefono = "0812541111";
        indirizzo = "Via Aldo 71";
        email = "ospedaledelmare@aruba.com";
        latitudine = 67.5;
        longitudine = 80.6;
        CTT CTT002 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);  
        listaCTT.add(CTT002);
        
        numero = 3;
        denominazione = "Maria SS. Addolorata";
        provincia = "SA";
        città = "Eboli";
        telefono = "0828362111";
        indirizzo = "Via Asso 51";
        email = "addolorata@aruba.com";
        latitudine = 22.0;
        longitudine = 34.6;
        CTT CTT003 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);  
        listaCTT.add(CTT003);
        
        numero = 4;
        denominazione = "Ospedale SS. Annunziata";
        provincia = "NA";
        città = "Napoli";
        telefono = "0812542598";
        indirizzo = "Via Luigi Pirandello 121";
        email = "annunziata@aruba.com";
        latitudine = 65.5;
        longitudine = 65.6;
        CTT CTT004 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);    
        listaCTT.add(CTT004);
        
        numero = 5;
        denominazione = "San Pio";
        provincia = "BN";
        città = "Benevento";
        telefono = "082457111";
        indirizzo = "Via Torre Della Catena 118";
        email = "sanpio@aruba.com";
        latitudine = 40.5;
        longitudine = 60.1;
        CTT CTT005 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);  
        listaCTT.add(CTT005);
        
        numero = 6;
        denominazione = "San Giuseppe";
        provincia = "AV";
        città = "Avellino";
        telefono = "0825203111";
        indirizzo = "Via Arco 91";
        email = "sangiuseppe@aruba.com";
        latitudine = 64.5;
        longitudine = 13.6;
        CTT CTT006 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);   
        listaCTT.add(CTT006);
        
        numero = 7;
        denominazione = "PSAUT";
        provincia = "BN";
        città = "San Bartolomeo in Galdo";
        telefono = "0824789053";
        indirizzo = "Via Bocciato 17";
        email = "psaut@aruba.com";
        latitudine = 11.5;
        longitudine = 23.6;
        CTT CTT007 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);  
        listaCTT.add(CTT007);
      
        for(CTT ctt : listaCTT) {
        	amm.createCTT(ctt);
        }      
	}
	
	MongoDataManager mongo = new MongoDataManager();  	
		
	
	/**
	*
	*Test per il corretto popolamento del database
	*/
	@Test	
	public void test1(){  	
		assertEquals(7, mongo.getListaCTT().size());
	}
	
	/**
	*
	*Test per la corretta rimozione di un CTT
	 * @throws CTTNotFoundException 
	*/
	@Test	
	public void test2() throws CTTNotFoundException{  	
		mongo.removeCTT(1);
		assertEquals(6, mongo.getListaCTT().size());
	}
	
	/**
	*
	*Si prova a rimuovere un CTT non presente nel Database
	 * @throws CTTNotFoundException 
	*/
	@Test(expected = AssertionError.class)	
	public void test3() throws CTTNotFoundException{  	
		mongo.removeCTT(10);
		
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
