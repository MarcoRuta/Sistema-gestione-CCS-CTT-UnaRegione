package it.unisannio.ingegneriaDelSoftware.Searcher;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**Classe che implementa il pattern composite*/
public class CompositionSearcher implements Searcher{
    List<Searcher> searchers ;

    /**Metodo costruttore di CompositionSearcher*/
    public CompositionSearcher(){
        searchers =  new ArrayList<>();
        searchers.add(new SearcherLocale());
        searchers.add(new SearcherCompatibile());
    }

    /**Ricerca le sacche filtrandole tramite i parametri passati come argomento
     * @param gs Gruppo sanguigno della Sacca
     * @param numeroSacche Il quantitativo di sacche che si vuole ricercare
     * @param dataArrivoMassima Specifica la data di arrivo massima entro cui la Sacca deve essere nel nuovo magazzino. Questo in modo da evitare che una Sacca arrivi scaduta a destinazione
     * @return List<Sacca> la lista delle sacche del tipo ricercato e dei tipi compatibili per la trasfusione
     */
    public List<Sacca> search(GruppoSanguigno gs, int numeroSacche, LocalDate dataArrivoMassima) {
        List<Sacca> saccheTrovate = searchers.get(0).search(gs,numeroSacche,dataArrivoMassima);

        if(saccheTrovate.size() == numeroSacche)
            return saccheTrovate;

        saccheTrovate.addAll(searchers.get(1).search(gs,numeroSacche - saccheTrovate.size(),dataArrivoMassima));

        return saccheTrovate;
    }
}