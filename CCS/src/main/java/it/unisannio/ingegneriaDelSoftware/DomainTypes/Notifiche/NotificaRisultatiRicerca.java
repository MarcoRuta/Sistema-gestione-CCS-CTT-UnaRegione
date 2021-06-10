package it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Seriale;
import java.util.List;
import java.util.Objects;

public class NotificaRisultatiRicerca {

    private List<Seriale> serialeList ;
    private String message;

    /**Metodo costruttore della NotificaRisultatiRicerca
     * @param serialeList Lista dei seriali presenti nella notifica
     * @param message Messaggio presente nella notifica
     */
    public NotificaRisultatiRicerca(List<Seriale> serialeList, String message) {
        this.serialeList = serialeList;
        this.message = message;
    }

    /**Metodo costruttore senza argomenti*/
    public NotificaRisultatiRicerca() {
    }

    /**Ritorna la lista dei seriali presenti nella notifica
     * @return serialeList Lista dei seriali presenti nella notifica
     */
    public List<Seriale> getSerialeList() {
        return serialeList;
    }

    /**Modifica la lista dei seriali presenti nella notifica
     * @param serialeList Lista dei seriali
     */
    public void setSerialeList(List<Seriale> serialeList) {
        this.serialeList = serialeList;
    }

    /**Ritorna il messaggio presente nella notifica
     * @return messagge Il messaggio presente nella notifica
     */
    public String getMessage() {
        return message;
    }

    /**Modifica il messaggio presente nella notifica
     * @param message Il messaggio presente nella notifica
     */
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