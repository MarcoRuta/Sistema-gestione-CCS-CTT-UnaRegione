package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.util.Date;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;


public interface EndPointAmministratoreCTT {
	
	void addDipendente(Dipendente d);	

	void removeDipendente(Cdf cdf);	
	
	List<Dipendente> reportOperatoriCTT(RuoloDipendente ruolo);

	List<Sacca> reportStatisticoSacche(GruppoSanguigno g) throws SaccaNotFoundException;
	
	List<DatiSacca> ReportLocaleSaccheInviateERicevuteCTT(Date dataInizio, Date dataFine);

	String OrdinaGruppiSanguigniPerRichieste(List<GruppoSanguigno> lista, Date dataInizio, Date dataFine);
}