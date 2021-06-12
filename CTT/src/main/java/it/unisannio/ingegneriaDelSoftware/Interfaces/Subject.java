package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.util.ArrayList;
import java.util.List;

/**This class represents an observable object, or "data" in the model-view paradigm. It can be subclassed to represent an object that the application wants to have observed. */
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