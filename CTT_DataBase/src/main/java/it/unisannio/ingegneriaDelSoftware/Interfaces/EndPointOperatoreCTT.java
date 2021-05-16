package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.time.LocalDate;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;

public interface EndPointOperatoreCTT {

	Sacca ricercaSaccaLocale(GruppoSanguigno gs, LocalDate dataArrivoMassima, String enteRichiedente, String indirizzoEnte) throws SaccaNotFoundException;	
}
