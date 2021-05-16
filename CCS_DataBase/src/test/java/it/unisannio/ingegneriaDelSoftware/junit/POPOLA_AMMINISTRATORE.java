package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.CTTNotFoundException;

public class POPOLA_AMMINISTRATORE {
	
	@BeforeClass public static void populateDataBaseCTT() {
		MongoDataManager mm = new MongoDataManager();
		Cdf cdf = new Cdf("122hfotndj13ht5f");
	    LocalDate ld = LocalDate.of(2000,10,10);
	    RuoloDipendente ruolo = RuoloDipendente.AmministratoreCCS;
	    String username = "admin";
	    String password = "admin";
	    Dipendente dip = new Dipendente(cdf, "pino", "sfatto", ld, ruolo, username, password);
	    mm.addDipendente(dip);
	}
	
	
	@Test
	public void test2() throws CTTNotFoundException {
		assertEquals(2,2);
	}
	
	
	
}
	
