package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.util.Date;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;


public interface AmministratoreCTTDataManager {
	
	void addDipendente(Dipendente d);	

	void removeDipendente(Cdf cdf);	

	List<Sacca> listaSaccheGS(GruppoSanguigno gs); 
	
	List<DatiSacca> listaDatiSaccheInIntervallo(Date dataInizio, Date dataFine);
	
	List<Dipendente> reportOperatoriCTT(RuoloDipendente ruolo);

	List<Sacca> reportStatisticoSacche(GruppoSanguigno g);
	
	String OrdinaGruppiSanguigniPerRichieste(List<GruppoSanguigno> lista, Date dataInizio, Date dataFine);
	
	List<DatiSacca> ReportLocaleSaccheInviateERicevuteCTT(Date dataInizio, Date dataFine);
	
	List<Dipendente> getlistaDipendentiByRuolo(RuoloDipendente ruolo);
}
