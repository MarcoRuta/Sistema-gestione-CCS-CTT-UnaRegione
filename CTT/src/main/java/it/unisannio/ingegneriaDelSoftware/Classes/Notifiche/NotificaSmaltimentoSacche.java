package it.unisannio.ingegneriaDelSoftware.Classes.Notifiche;

import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificaSmaltimentoSacche implements Notifica {
    List<Seriale> serialeList = new ArrayList<>();

    public NotificaSmaltimentoSacche(List<Seriale> serialeList) {
        this.serialeList = serialeList;
    }

    public NotificaSmaltimentoSacche() {
    }

    public List<Seriale> getSerialeList() {
        return serialeList;
    }

    public void setSerialeList(List<Seriale> serialeList) {
        this.serialeList = serialeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificaSmaltimentoSacche that = (NotificaSmaltimentoSacche) o;
        return serialeList.equals(that.serialeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialeList);
    }

    @Override
    public String toString() {
        return "NotificaSmaltimentoSacche{" +
                "serialeList=" + serialeList +
                '}';
    }
}
