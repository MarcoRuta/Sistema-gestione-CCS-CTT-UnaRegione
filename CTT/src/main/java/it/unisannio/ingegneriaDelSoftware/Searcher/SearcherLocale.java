package it.unisannio.ingegneriaDelSoftware.Searcher;

import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;
import it.unisannio.ingegneriaDelSoftware.Functional.ScadenzeComparator;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearcherLocale implements Searcher {
    @Override
    public List<Sacca> search(GruppoSanguigno gs, int numeroSacche, LocalDate dataArrivoMassima) {
        assert(gs!=null) : "Il gruppo sanguigno non può essere nullo!";
        assert(dataArrivoMassima!=null) : "la data di arrivo non può essere nulla!";

        MongoDataManager md = MongoDataManager.getInstance();
        List<Sacca> saccheTrovate = new ArrayList<Sacca>();
        List<Sacca> listaSacche = md.getListaSacche();

        for (Sacca sacca : listaSacche)
            if(!sacca.isPrenotato() && sacca.getGruppoSanguigno().equals(gs)
                    && sacca.getDataScadenza().isAfter(dataArrivoMassima)
                    && sacca.getDataScadenza().isAfter(LocalDate.now()))
                saccheTrovate.add(sacca);

        saccheTrovate.sort(new ScadenzeComparator());
        return saccheTrovate;
    }
}
