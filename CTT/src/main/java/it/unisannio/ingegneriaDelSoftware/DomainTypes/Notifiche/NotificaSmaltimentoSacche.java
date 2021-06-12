package it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificaSmaltimentoSacche {
    
	/**Lista dei seriali delle sacche scadute */
	List<Seriale> serialeList = new ArrayList<>();

	/**Metodo costruttore di NotificaSmaltimentoSacche
	 * @param serialeList lista dei seriali delle sacche in scadenza
	 */
    public NotificaSmaltimentoSacche(List<Seriale> serialeList) {
        this.serialeList = serialeList;
    }

    
    /**Metodo costruttore senza parametri*/
    public NotificaSmaltimentoSacche() {
    }

    
    /**Restituisce la lista dei seriali delle sacche scadute
	 * @return serialeList
	 */
    public List<Seriale> getSerialeList() {
        return serialeList;
    }

    
    /**Modifica la lista dei seriali delle sacche scadute
	 * @param serialeList
	 */
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