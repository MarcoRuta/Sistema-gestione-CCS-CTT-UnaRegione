package it.unisannio.ingegneriaDelSoftware.Searcher;

import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompositionSearcher implements Searcher {
    List<Searcher> searchers ;

    public CompositionSearcher(){
        searchers =  new ArrayList<>();
        searchers.add(new SearcherLocale());
        searchers.add(new SearcherCompatibile());
    }


    @Override
    public List<Sacca> search(GruppoSanguigno gs, int numeroSacche, LocalDate dataArrivoMassima) {
        List<Sacca> saccheTrovate = searchers.get(0).search(gs,numeroSacche,dataArrivoMassima);
        if(!(saccheTrovate.size() < numeroSacche))
            return saccheTrovate;
        saccheTrovate.addAll(this.searchers.get(1).search(gs,numeroSacche,dataArrivoMassima));
        if(saccheTrovate.size()>numeroSacche)
            saccheTrovate = saccheTrovate.subList(0,numeroSacche);
        return saccheTrovate;
    }
}
