package it.unisannio.ingegneriaDelSoftware.AbstractClasses;

import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;

public abstract class OperatoreSubject implements Subject {
    @Override
    public void notifyEvasioneObserver() {
        //do nothing
    }

    @Override
    public void notifySmaltimentoObserver() {
        //do nothing
    }

    @Override
    public abstract void notifyRicercaGlobaleObserver();

    @Override
    public abstract void notifySaccheInScadenzaObserver();
}
