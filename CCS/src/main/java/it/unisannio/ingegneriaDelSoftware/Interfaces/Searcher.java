package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTT;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface Searcher {
    public Map<CTTName, List<Sacca>> search(Map<CTTName,
            String> cttOnline, CTT cttRichiedente, String dataArrivoMassima, String gruppoSanguigno, int numeroSacche) throws EntityNotFoundException;
}
