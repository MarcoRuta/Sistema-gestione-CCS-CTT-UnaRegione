package it.unisannio.ingegneriaDelSoftware.Searchers;

import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;

import java.util.HashMap;
import java.util.Map;

public class SearcherFactory  {

    public static Map<String,Searcher> searcherMap = new  HashMap<>();
    static {
        searcherMap.put("true",new PrioritySearcher());
        searcherMap.put("false",new NonPrioritySearcher());
    }

}
