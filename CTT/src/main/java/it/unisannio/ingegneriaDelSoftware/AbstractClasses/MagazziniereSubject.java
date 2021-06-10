package it.unisannio.ingegneriaDelSoftware.AbstractClasses;

import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;


public abstract class MagazziniereSubject implements Subject {

    @Override
    public abstract void notifyEvasioneObserver() ;

    @Override
    public abstract void notifySmaltimentoObserver() ;

    @Override
    public void notifyRicercaGlobaleObserver() {
        //do nothing
    }

    @Override
    public void notifySaccheInScadenzaObserver() {
        //do nothing
    }

}
