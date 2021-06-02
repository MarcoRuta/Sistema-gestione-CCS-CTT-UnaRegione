package it.unisannio.ingegneriaDelSoftware.Interfaces;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;

import java.util.List;

public interface Observer {
    void update(List<NotificaEvasione> notifica);
}
