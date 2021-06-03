package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;

import java.util.List;

public interface Subject {
    void notifyMagazziniereObserver(List<NotificaEvasione> notifica);
}
