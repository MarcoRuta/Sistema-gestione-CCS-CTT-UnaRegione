package it.unisannio.ingegneriaDelSoftware.Util;

import it.unisannio.ingegneriaDelSoftware.Beans.DipendenteBean;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;

public class DipendenteConverter {

	/**Metodo che converte un DipendenteBean in un oggetto Dipendente
	 * @return  Dipendente convertito*/
    public static Dipendente convertDipendenteBeanToDipendente(DipendenteBean db) {
        return new Dipendente(Cdf.getCDF(db.getCdf().toString()),
        		db.getNome(),
        		db.getCognome(),
        		db.getDataDiNascita(),
        		RuoloDipendente.valueOf(db.getRuolo()),
        		db.getUsername(),
        		db.getPassword());
    }


	/**Metodo che converte un Dipendente in un oggetto DipendenteBean
	 * @return  Dipendente convertito*/
    public static DipendenteBean convertDipendenteToDipendenteBean(Dipendente unDipendente) {
        return new DipendenteBean(unDipendente.getCdf().getCodiceFiscale(),
        		unDipendente.getNome(),
        		unDipendente.getCognome(),
        		unDipendente.getDataDiNascita(),
        		unDipendente.getRuolo().toString(),
        		unDipendente.getUsername(),
        		unDipendente.getPassword());
    }
}