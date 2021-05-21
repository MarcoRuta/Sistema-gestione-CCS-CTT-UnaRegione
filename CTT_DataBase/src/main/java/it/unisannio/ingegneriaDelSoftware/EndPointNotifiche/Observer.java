package it.unisannio.ingegneriaDelSoftware.EndPointNotifiche;

import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;

public interface Observer {
    void update(Notifica notifica);
}
