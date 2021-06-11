package it.unisannio.ingegneriaDelSoftware.Searchers;

import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTT;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.ClientRest.CCSRestClient;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Functional.Comparator.CTTDistanceComparator;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;
import java.util.*;

/**
 * Classe utilizzata per la ricerca priorità fra i vari CTT.
 * E' un oggetto composite del Composite Pattern derivante dal Component SearcherFactory
 */
public class PrioritySearcher implements Searcher {

    @Override
    public Map<CTTName, List<Sacca>> search(Map<CTTName, String> cttOnline, CTT cttRichiedente, String dataArrivoMassima, String gruppoSanguigno, int numeroSacche) throws EntityNotFoundException {

        //CTT ordinati in base alla vicinanza da quello richiedente
        Set<CTTName> cttOrdinati = new TreeSet<>(new CTTDistanceComparator(cttRichiedente));
        cttOrdinati.addAll(cttOnline.keySet());
        Map<CTTName, List<Sacca>> risultato = new TreeMap<CTTName,List<Sacca>>(new CTTDistanceComparator(cttRichiedente));
        int sacchetrovate =0;
        for (CTTName ctt : cttOrdinati) {
            List<Sacca> saccaBeans = CCSRestClient.RicercaGlobaleSaccheCompatibili(
                    gruppoSanguigno, dataArrivoMassima, Settings.ip.get(MongoDataManager.getInstance().getCTT(ctt)), Settings.PORTA);
            sacchetrovate = sacchetrovate + saccaBeans.size();
            //controllo se ho sforato, in tal caso prendo solo quelle necessarie ed esco
            if (sacchetrovate >= numeroSacche) {
                saccaBeans =saccaBeans.subList(0, saccaBeans.size() - (sacchetrovate - numeroSacche));
                risultato.put(ctt, saccaBeans);
                return risultato;
            }

            if (!saccaBeans.isEmpty())
                risultato.put(ctt, saccaBeans);
        }

        return risultato;
    }
}