package it.unisannio.ingegneriaDelSoftware.Searchers;

import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe utilizzata per la ricerca prioritaria e non fra i vari CTT.
 * E' un oggetto Component del CompositePattern
 */
public class SearcherFactory  {

    public static Map<String,Searcher> searcherMap = new  HashMap<>();
    static {
        searcherMap.put("true",new PrioritySearcher());
        searcherMap.put("false",new NonPrioritySearcher());
    }

}
