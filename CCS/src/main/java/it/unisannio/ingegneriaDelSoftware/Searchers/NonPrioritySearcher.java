package it.unisannio.ingegneriaDelSoftware.Searchers;

import it.unisannio.ingegneriaDelSoftware.Classes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.CcsDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;
import it.unisannio.ingegneriaDelSoftware.ClientRest.CCSRestClient;
import it.unisannio.ingegneriaDelSoftware.Functional.Comparator.CTTConPiuSacche;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

import java.util.*;

public class NonPrioritySearcher implements it.unisannio.ingegneriaDelSoftware.Interfaces.Searcher {


    @Override
    public Map<CTTName, List<Sacca>> search(Map<CTTName, String> cttOnline, CTT cttRichiedente, String dataArrivoMassima, String gruppoSanguigno, int numeroSacche) {

    	CcsDataBaseRestApplication.logger.info("è iniziata un operazione di ricerca sacche non prioritaria sulla rete CTT");

        Map<CTTName, List<Sacca>> saccheTrovate = new HashMap<>();

        for (CTTName ctt : cttOnline.keySet()) {
        	CcsDataBaseRestApplication.logger.info("Sto ricercando sacche compatibili presso il ctt "+ctt.getCttname()+" con indirizzo IP: "+Settings.ip.get(ctt));
            saccheTrovate.put(ctt,CCSRestClient.RicercaGlobaleSaccheCompatibili(gruppoSanguigno, dataArrivoMassima, Settings.ip.get(ctt), Settings.PORTA));
        }



        int numSaccheTrovate = 0;
        //CTT ordinati in base al numero di sacche che hanno
        Map<CTTName, List<Sacca>> mappaOrdinata = new TreeMap<CTTName, List<Sacca>>(new CTTConPiuSacche(saccheTrovate));
        mappaOrdinata.putAll(saccheTrovate);

        Map<CTTName, List<Sacca>> risultato = new TreeMap<>(new CTTConPiuSacche(saccheTrovate));


        for (CTTName cttName : mappaOrdinata.keySet()){

            List<Sacca> saccheBeans = mappaOrdinata.get(cttName);
            numSaccheTrovate = numSaccheTrovate+saccheBeans.size();


            if(numSaccheTrovate >= numeroSacche){
                saccheBeans = saccheBeans.subList(0,(saccheBeans.size()-(numSaccheTrovate-numeroSacche)));
                risultato.put(cttName,saccheBeans);
                return risultato;
            }

            if(!saccheBeans.isEmpty())
               risultato.put(cttName,saccheBeans);

        }

        CcsDataBaseRestApplication.logger.info("Ho finitio la ricerca non prioritaria: ",mappaOrdinata);
        return risultato;
    }

}