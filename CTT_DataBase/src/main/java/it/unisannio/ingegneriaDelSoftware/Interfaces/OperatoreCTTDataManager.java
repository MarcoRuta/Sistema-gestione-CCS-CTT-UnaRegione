package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.time.LocalDate;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;

public interface OperatoreCTTDataManager {	

	Sacca ricercaSaccaLocale(GruppoSanguigno gs, LocalDate dataArrivoMassima, String enteRichiedente, String indirizzoEnte);	
}
