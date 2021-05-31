package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.NotificheObservers.TerminaleMagazziniereObserver;
import it.unisannio.ingegneriaDelSoftware.NotificheObservers.TerminaleOperatoreObserver;
import it.unisannio.ingegneriaDelSoftware.SaccheInScadenzaManager.NotificaSaccaInScadenza;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/notifica")
//@Secured
public class EndPointNotifiche implements Subject {

    private static  List<Observer> observers = new ArrayList<>();

    static {
        observers.add(new TerminaleOperatoreObserver());
        observers.add(new TerminaleMagazziniereObserver());
    }


    /**Metodo attivato dall CCS nel momento in cui termina la sua ricerca globale esssa crea una notifica
     * con le sacche trovate e la inoltra al terminale del magazziniere*/
    @POST
    @Path("/saccaGlobaleTrovate")
    public void notificaSaccaGlobale(Notifica notificaSacca){
        this.notifyOperatoreObserver(notificaSacca);
    }

    /**Metodo attivato dal CCS nel momento in cui c'è un alert di sacca in scadenza che deve inoltrare ai vari CTT
     * esso crea una notifica che mostra sul terminale dell'operatore*/
    @POST
    @Path("/alertScadenza")
    public void alertSaccaScadenza(NotificaSaccaInScadenza alertScadenza){
        System.err.println("sacca arrivata "+alertScadenza);
        this.notifyOperatoreObserver(alertScadenza);
    }

    @POST
    @Path("/notificaEvasione")
    public void notificaEvasione(NotificaEvasione notificaEvasione){
        this.notifyMagazziniereObserver(notificaEvasione);

    }


    /**Notifica l'operatore dell'arrivo di una notifica*/
    @Override
    public void notifyOperatoreObserver(Notifica alertScadenza) {
        observers.get(0).update(alertScadenza);
    }

    @Override
    public void notifyMagazziniereObserver(Notifica notificaEvasione) {
        if(notificaEvasione instanceof  NotificaEvasione) {
            NotificaEvasione unaNotifica = (NotificaEvasione) notificaEvasione;
            observers.get(1).update(unaNotifica);
        }


    }
}
