package it.unisannio.ingegneriaDelSoftware;

import java.time.LocalDate;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManagerBean;

public class CaricaAmministratore {

	public static void main(String[] args) {
		
		Dipendente d = new Dipendente(Cdf.getCDF("RTUMRC98E08B519C"),"Marco","Ruta",LocalDate.now().minusYears(20),RuoloDipendente.AmministratoreCTT,"admin","admin");	
		MongoDataManagerBean.createDipendente(d);

	}

}
