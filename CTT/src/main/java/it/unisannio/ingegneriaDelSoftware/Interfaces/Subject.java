package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;

public interface Subject {
    void notifyMagazziniereObserver(Notifica notifica);
    void notifyOperatoreObserver(Notifica notifica);
}
