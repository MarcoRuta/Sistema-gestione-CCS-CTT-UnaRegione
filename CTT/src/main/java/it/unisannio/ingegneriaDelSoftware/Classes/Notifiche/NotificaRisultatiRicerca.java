package it.unisannio.ingegneriaDelSoftware.Classes.Notifiche;

import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;

import java.util.List;
import java.util.Objects;

public class NotificaRisultatiRicerca {
    private List<Seriale> serialeList;
    private String message;

    public NotificaRisultatiRicerca(List<Seriale> serialeList, String message) {
        this.serialeList = serialeList;
        this.message = message;
    }

    public NotificaRisultatiRicerca() {
    }

    public List<Seriale> getSerialeList() {
        return serialeList;
    }

    public void setSerialeList(List<Seriale> serialeList) {
        this.serialeList = serialeList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificaRisultatiRicerca that = (NotificaRisultatiRicerca) o;
        return serialeList.equals(that.serialeList) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialeList, message);
    }

    @Override
    public String toString() {
        return "NotificaRisultatiRicerca{" +
                "serialeList=" + serialeList +
                ", message='" + message + '\'' +
                '}';
    }
}
