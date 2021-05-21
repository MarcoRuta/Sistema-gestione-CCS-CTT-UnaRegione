package it.unisannio.ingegneriaDelSoftware.Searcher;

import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManagerBean;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;
import it.unisannio.ingegneriaDelSoftware.Util.ScadenzeComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearcherCompatibile implements Searcher {
    @Override
    public List<Sacca> search(GruppoSanguigno gs, int numeroSacche, LocalDate dataArrivoMassima) {
        List<Sacca> saccheTrovate = new ArrayList<Sacca>();
        List<Sacca> listaSacche = MongoDataManagerBean.getListaSacche();

        Iterator<GruppoSanguigno> i = GruppoSanguigno.puoRicevereDa(gs);
        while(i.hasNext()) {
            GruppoSanguigno grs = i.next();
            for (Sacca sacca : listaSacche)
                if(!sacca.isPrenotato()
                        && sacca.getGruppoSanguigno().equals(grs)
                        && sacca.getDataScadenza().isAfter(dataArrivoMassima)
                        && sacca.getDataScadenza().isAfter(LocalDate.now())
                        && !grs.equals(gs))
                    saccheTrovate.add(sacca);
        }

        saccheTrovate.sort(new ScadenzeComparator());
        return saccheTrovate;
    }
}
