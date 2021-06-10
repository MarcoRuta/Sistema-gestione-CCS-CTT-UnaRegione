package it.unisannio.ingegneriaDelSoftware.Searcher;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**Classe che implementa il behavior di ricerca Sacca ma in locale*/
public class SearcherLocale implements Searcher {
	
    /**Ricerca la sacche filtrandole tramite i parametri passati come argomento
     * @param gs Gruppo sanguigno della Sacca
     * @param numeroSacche Il quantitativo di sacche che si vuole ricercare
     * @param dataArrivoMassima Specifica la data di arrivo massima entro cui la Sacca deve essere nel nuovo magazzino. Questo in modo da evitare che una Sacca arrivi scaduta a destinazione
     * @return List<Sacca> La lista delle sacche disponibili per quei parametri
     */
    @Override
    public List<Sacca> search(GruppoSanguigno gs, int x, LocalDate dataArrivoMassima) {
        assert(gs!=null) : "Il gruppo sanguigno non può essere nullo!";
        assert(dataArrivoMassima!=null) : "La data di arrivo non può essere nulla!";

        MongoDataManager md = MongoDataManager.getInstance();
        List<Sacca> saccheTrovate = new ArrayList<Sacca>();
        List<Sacca> listaSacche = md.getListaSacche();

        for (Sacca sacca : listaSacche)
            if(!sacca.isPrenotato() && sacca.getGruppoSanguigno().equals(gs)
                    && sacca.getDataScadenza().isAfter(dataArrivoMassima)
                    && sacca.getDataScadenza().isAfter(LocalDate.now()))
                saccheTrovate.add(sacca);

        return saccheTrovate;
    }
}