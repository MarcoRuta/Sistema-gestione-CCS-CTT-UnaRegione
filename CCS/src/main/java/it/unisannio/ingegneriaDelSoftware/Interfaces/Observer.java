package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.util.List;

public interface Observer {
    void update(List<Notifica> notifiche);
}