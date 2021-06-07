package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.Classes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;
import java.util.List;
import java.util.Map;

public interface Searcher {
    public Map<CTTName, List<Sacca>> search(Map<CTTName,
            String> cttOnline, CTT cttRichiedente, String dataArrivoMassima, String gruppoSanguigno, int numeroSacche);
}