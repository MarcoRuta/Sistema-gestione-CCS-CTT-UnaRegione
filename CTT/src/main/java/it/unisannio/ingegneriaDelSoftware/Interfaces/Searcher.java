package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;

import java.time.LocalDate;
import java.util.List;

public interface Searcher {
	
    List<Sacca> search(GruppoSanguigno gs, int x, LocalDate dataArrivoMassima);

}