package it.unisannio.Interfaces;

import java.util.List;

import it.unisannio.Classes.Dipendente;
import it.unisannio.Classes.Sacca;
import it.unisannio.TipiAggiuntivi.GruppoSanguigno;
import it.unisannio.TipiAggiuntivi.RuoloDipendente;

public interface AmministratoreCTTDataManager {

	boolean login(String username, String password);
		
	List<Sacca> reportStatisticoSacche(GruppoSanguigno g);
	
//mancano sicuramente dei report	
	void addDipendente(Dipendente d);
	void removeDipendente(Dipendente d);
	List<Dipendente> reportOperatoriCTT(RuoloDipendente ruolo);

}
