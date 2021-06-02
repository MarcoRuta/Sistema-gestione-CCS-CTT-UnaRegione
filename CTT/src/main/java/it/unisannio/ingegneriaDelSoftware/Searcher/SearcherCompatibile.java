package it.unisannio.ingegneriaDelSoftware.Searcher;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;
import it.unisannio.ingegneriaDelSoftware.Functional.ScadenzeComparator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**Classe che implementa il behavior di ricerca Sacca ma per gruppi sanguigni compatibili */
public class SearcherCompatibile implements Searcher {
	
	 /**Ricerca la sacche filtrandole tramite i parametri passati come argomento
     * @param gs Gruppo sanguigno della Sacca
     * @param numeroSacche Il quantitativo di sacche che si vuole ricercare
     * @param dataArrivoMassima Specifica la data di arrivo massima entro cui la Sacca deve essere nel nuovo magazzino. Questo in modo da evitare che una Sacca arrivi scaduta a destinazione
     * @return List<Sacca> La lista delle sacche disponibili per quei parametri
     */
    @Override
    public List<Sacca> search(GruppoSanguigno gs, int numeroSacche, LocalDate dataArrivoMassima) {
        MongoDataManager md = MongoDataManager.getInstance();
        List<Sacca> saccheTrovate = new ArrayList<Sacca>();
        List<Sacca> listaSacche = md.getListaSacche();

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