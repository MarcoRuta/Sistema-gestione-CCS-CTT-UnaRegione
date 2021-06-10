package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    public static List<Observer> observerList = new ArrayList<>();

    void notifyEvasioneObserver();
    void notifySmaltimentoObserver();
    void notifyRicercaGlobaleObserver();
    void notifySaccheInScadenzaObserver();

    public static void addObserver(Observer observer){
        observerList.add(observer);
    }
    public static void removeObserver(Observer observer){
        observerList.remove(observer);
    }
}
