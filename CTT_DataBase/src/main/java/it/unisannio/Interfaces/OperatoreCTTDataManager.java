package it.unisannio.Interfaces;

import java.util.Date;

import it.unisannio.Classes.Sacca;
import it.unisannio.TipiAggiuntivi.GruppoSanguigno;

public interface OperatoreCTTDataManager {
	boolean login(String username, String password);	

	Sacca ricercaSaccaLocale(GruppoSanguigno g, Date dataArrivoMassima, String enteRichiedente);	
}
