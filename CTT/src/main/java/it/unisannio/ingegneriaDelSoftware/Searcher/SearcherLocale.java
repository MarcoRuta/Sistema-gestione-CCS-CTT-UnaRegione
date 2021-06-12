package it.unisannio.ingegneriaDelSoftware.Searcher;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**Classe che implementa il behavior di ricerca Sacca in locale di un determinato gruppo sanguigno*/
public class SearcherLocale implements Searcher {
	
    /**Ricerca la sacche filtrandole tramite i parametri passati come argomento
     * @param gs Gruppo sanguigno della Sacca
     * @param x Il quantitativo di sacche che si vuole ricercare
     * @param dataArrivoMassima Specifica la data di arrivo massima entro cui la Sacca deve essere nel nuovo magazzino. Questo in modo da evitare che una Sacca arrivi scaduta a destinazione
     * @return List<Sacca> La lista delle sacche dello stesso gruppo sanguigno ricercato
     */
    @Override
    public List<Sacca> search(GruppoSanguigno gs, int x, LocalDate dataArrivoMassima) {
        assert(gs!=null) : "Il gruppo sanguigno non può essere nullo!";
        assert(dataArrivoMassima!=null) : "La data di arrivo non può essere nulla!";
        MongoDataManager md = MongoDataManager.getInstance();

        return md.getListaSacche()
                .stream()
                .filter(sacca -> !sacca.isPrenotato())
                .filter(sacca -> sacca.getGruppoSanguigno().equals(gs))
                .filter(sacca -> sacca.getDataScadenza().isAfter(dataArrivoMassima))
                .filter(sacca -> sacca.getDataScadenza().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing((Sacca s)-> s.getDataScadenza()))
                .limit(x)
                .collect(Collectors.toList());

        }
    }