package it.unisannio.ingegneriaDelSoftware.Searcher;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**Classe che implementa il pattern composite
 */
public class CompositionSearcher implements Searcher{
    List<Searcher> searchers ;

    public CompositionSearcher(){
        searchers =  new ArrayList<>();
        searchers.add(new SearcherLocale());
        searchers.add(new SearcherCompatibile());
    }


    /**Ricerca la sacche filtrandole tramite i parametri passati come argomento
     * @param gs Gruppo sanguigno della sacca
     * @param numeroSacche Il quantitativo di sacche che si vuole ricercare
     * @param dataArrivoMassima Specifica la data di arrivo massima entro cui la Sacca deve essere nel nuovo magazzino. Questo in modo da evitare che una Sacca arrivi scaduta a destinazione
     * @return List<Sacca> La lista delle sacche disponibili per quei parametri
     */
    public List<Sacca> search(GruppoSanguigno gs, int numeroSacche, LocalDate dataArrivoMassima) {
        List<Sacca> saccheTrovate = searchers.get(0).search(gs,numeroSacche,dataArrivoMassima);
        saccheTrovate.sort(Comparator.comparing((Sacca s)-> s.getDataScadenza()));

        if(saccheTrovate.size() >= numeroSacche)
            return saccheTrovate.subList(0,numeroSacche);

        List<Sacca>saccheCompatbili =this.searchers.get(1).search(gs,numeroSacche,dataArrivoMassima);
        saccheCompatbili.sort(Comparator.comparing((Sacca s)-> s.getDataScadenza()));
        saccheTrovate.addAll(saccheCompatbili);

        if(saccheTrovate.size()>=numeroSacche)
            return saccheTrovate.subList(0,numeroSacche);

        return saccheTrovate;
    }
}
