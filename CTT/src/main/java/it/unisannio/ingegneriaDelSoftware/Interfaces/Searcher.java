package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;

import java.time.LocalDate;
import java.util.List;

public interface Searcher {

    List<Sacca> search(GruppoSanguigno gs, int numeroSacche, LocalDate dataArrivoMassima);
}
