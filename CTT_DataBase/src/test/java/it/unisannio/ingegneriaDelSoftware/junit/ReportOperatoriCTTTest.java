package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyAmministratoreCTTDataManager;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyMongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.DateConverter;

public class ReportOperatoriCTTTest {
			
	@BeforeClass public static void populateDBDipendenti() throws ParseException {
		MyAmministratoreCTTDataManager amm = new MyAmministratoreCTTDataManager();
		List<Dipendente> listaDipendenti = new ArrayList<Dipendente>();
	        
	 	Cdf cdf = new Cdf("122hfotndj13ht5f");
        Date datadinascita = Constants.sdf.parse("10-07-2000");
        LocalDate ld = DateConverter.convertDateToLocalDate(datadinascita);
        RuoloDipendente ruolo = RuoloDipendente.OperatoreCTT;
        String username = "username 002";
        String password = "002";
        Dipendente dip2 = new Dipendente(cdf, "pino", "sfatto", ld, ruolo, username, password);
        listaDipendenti.add(dip2);  
        
        cdf = new Cdf("123456789qwrrtyy");
        datadinascita = Constants.sdf.parse("12-01-1999");
        ld = DateConverter.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.AmministratoreCTT;
        username = "username 003";
        password = "003";
        Dipendente dip3 = new Dipendente(cdf, "Giovanni", "Rana", ld, ruolo, username, password);
        listaDipendenti.add(dip3); 
        
        cdf = new Cdf("123456789swertyy");
        datadinascita = Constants.sdf.parse("10-12-1996");
        ld = DateConverter.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.OperatoreCTT;
        username = "username 004";
        password = "004";
        Dipendente dip4 = new Dipendente(cdf, "pietro", "spini", ld, ruolo, username, password);
        listaDipendenti.add(dip4); 
        
        cdf = new Cdf("123456781qwertyy");
        datadinascita = Constants.sdf.parse("29-01-1998");
        ld = DateConverter.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.MagazziniereCTT;
        username = "username 005";
        password = "005";
        Dipendente dip5 = new Dipendente(cdf, "gionata", "boschetto", ld, ruolo, username, password);
        listaDipendenti.add(dip5); 
        
        cdf = new Cdf("123456789djkshnd");
        datadinascita = Constants.sdf.parse("10-04-1992");
        ld = DateConverter.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.MagazziniereCTT;
        username = "username 006";
        password = "006";
        Dipendente dip6 = new Dipendente(cdf, "marco", "aspini", ld, ruolo, username, password);
        listaDipendenti.add(dip6); 
        
        cdf = new Cdf("123456789qwedety");
        datadinascita = Constants.sdf.parse("20-10-1982");
        ld = DateConverter.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.OperatoreCTT;
        username = "username 007";
        password = "007";
        Dipendente dip7 = new Dipendente(cdf, "luca", "barra", ld, ruolo, username, password);
        listaDipendenti.add(dip7); 
        
        cdf = new Cdf("123456789dfgrhty");
        datadinascita = Constants.sdf.parse("12-12-2001");
        ld = DateConverter.convertDateToLocalDate(datadinascita);
        ruolo = RuoloDipendente.MagazziniereCTT;
        username = "username 008";
        password = "008";
        Dipendente dip8 = new Dipendente(cdf, "andrea", "lezzi", ld, ruolo, username, password);
        listaDipendenti.add(dip8);
      
      
        for(Dipendente dip : listaDipendenti) {
        	amm.addDipendente(dip);
        }       
	}
	
	MyAmministratoreCTTDataManager amm = new MyAmministratoreCTTDataManager();  	
		
	
	/**
	 * Test che dovrebbe restituire una lista di Dipendenti con 3 elementi, siccome gli OperatoriCTT presenti nel database dei Dipendenti sono 3
	*/
	@Test	
	public void test1(){  	
		assertEquals(3, amm.reportOperatoriCTT(RuoloDipendente.OperatoreCTT).size());
		
	}
	
	/**
	 * Test che dovrebbe restituire una lista di Dipendenti con 3 elementi, siccome gli MagazziniereCTT presenti nel database dei Dipendenti sono 3
	*/
	@Test	
	public void test3(){  	
		assertEquals(3, amm.reportOperatoriCTT(RuoloDipendente.MagazziniereCTT).size());
	}

	
	/**
	 * Test che dovrebbe restituire una lista di Dipendenti con 1 elemento, siccome gli AmministratoriCTT presenti nel database dei Dipendenti sono 1
	*/
	@Test	
	public void test4(){  	
		assertEquals(1, amm.reportOperatoriCTT(RuoloDipendente.AmministratoreCTT).size());
	}
	
	/**
	 * Test che dovrebbe restituire una lista di Dipendenti con 0 elementi, siccome gli AmministratoriCCS presenti nel database dei Dipendenti sono 0
	 */
	@Test	
	public void test5(){  	
		assertEquals(0, amm.reportOperatoriCTT(RuoloDipendente.AmministratoreCCS).size());
	}
	
	
	@AfterClass public static void dropDBDipendenti() {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.dropDB();
	}
}