package it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;

import java.util.List;
import java.util.Objects;

public class NotificaRisultatiRicerca {
	
	/**Lista dei seriali risultato della ricerca*/
    private List<Seriale> serialeList;
    /**Messaggio informativo presente sulla notifica */
    private String message;

    /**Metodo costruttore di NotificaRisultatiRicerca
     * @param serialeList lista di seriali
     * @param message messaggio contenuto nella notifica
     */
    public NotificaRisultatiRicerca(List<Seriale> serialeList, String message) {
        this.serialeList = serialeList;
        this.message = message;
    }

    /**Metodo costruttore senza parametri*/
    public NotificaRisultatiRicerca() {
    }

    
    /**Restituisce la lista dei seriali
	 * @return serialeList 
	 */
    public List<Seriale> getSerialeList() {
        return serialeList;
    }

    /**Modifica la lista dei seriali
	 * @param serialeList
	 */
    public void setSerialeList(List<Seriale> serialeList) {
        this.serialeList = serialeList;
    }

    /**Restituisce il messaggio contenuto nella notifica
	 * @return message 
	 */
    public String getMessage() {
        return message;
    }

    /**Modifica il messaggio presente sulla notifica
	 * @param message
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