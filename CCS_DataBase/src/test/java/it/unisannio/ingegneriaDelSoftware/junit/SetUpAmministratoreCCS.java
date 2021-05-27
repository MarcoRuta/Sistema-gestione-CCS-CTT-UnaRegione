package it.unisannio.ingegneriaDelSoftware.junit;

import java.time.LocalDate;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;

public class SetUpAmministratoreCCS {

	public static void main(String[] args) {
		
		Cdf cdf = Cdf.getCDF("RRCTRC88S05V467H");
        LocalDate ld = LocalDate.parse("1970-07-10");
        RuoloDipendente ruolo = RuoloDipendente.AmministratoreCCS;
        String username = "admin";
        String password = "Adminadmin1";
        Dipendente dip = new Dipendente(cdf, "Arminio", "Stratore", ld, ruolo, username, password);
        
        MongoDataManager mm = MongoDataManager.getInstance();
        mm.createDipendente(dip);
	}

}