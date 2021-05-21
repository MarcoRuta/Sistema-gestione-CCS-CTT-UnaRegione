package it.unisannio.ingegneriaDelSoftware.EndPointNotifiche;

import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;

public interface Subject {
    void notifyObserver(Notifica notifica);
}
