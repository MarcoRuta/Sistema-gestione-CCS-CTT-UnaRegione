package it.unisannio.ingegneriaDelSoftware.Searcher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;

public class SearcherCCS implements Searcher {
	 List<Searcher> searchers ;

	    public SearcherCCS(){
	        searchers =  new ArrayList<>();
	        searchers.add(new SearcherLocale());
	        searchers.add(new SearcherCompatibile());
	    }


	    /**Ricerca la sacche filtrandole tramite i parametri passati come argomento
	     * @param gs Gruppo sanguigno della sacca
	     * @param dataArrivoMassima Specifica la data di arrivo massima entro cui la Sacca deve essere nel nuovo magazzino. Questo in modo da evitare che una Sacca arrivi scaduta a destinazione
	     * @return List<Sacca> La lista delle sacche disponibili per quei parametri
	     */
	    @Override
	    public List<Sacca> search(GruppoSanguigno gs,int x, LocalDate dataArrivoMassima) {
	        List<Sacca> saccheTrovate = searchers.get(0).search(gs,x,dataArrivoMassima);
	        saccheTrovate.addAll(searchers.get(1).search(gs,x,dataArrivoMassima));
	        return saccheTrovate;
	    }
	

}