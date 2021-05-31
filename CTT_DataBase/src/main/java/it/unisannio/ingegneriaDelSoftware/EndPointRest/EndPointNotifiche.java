package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.NotificheObservers.TerminaleMagazziniereObserver;
import it.unisannio.ingegneriaDelSoftware.NotificheObservers.TerminaleOperatoreObserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/notifica")
//@Secured
public class EndPointNotifiche implements Subject {

    private static  List<Observer> observers = new ArrayList<>();
    private MongoDataManager mm = MongoDataManager.getInstance();

    static {
        observers.add(new TerminaleOperatoreObserver());
        observers.add(new TerminaleMagazziniereObserver());
    }


    @POST
    @Path("/notificaEvasione")
    @Consumes(MediaType.APPLICATION_JSON)
    public void notificaEvasione(NotificaEvasione notificaEvasione) throws EntityNotFoundException {
        CttDataBaseRestApplication.logger.info("L'operatore ha richiesto l'evasione per le sacche: "+notificaEvasione.getListaSeriali());
        List<String> seriali = new ArrayList<>();
        for (String seriale : notificaEvasione.getListaSeriali()) {
            mm.setPrenotatoSacca(Seriale.getSeriale(seriale));
            seriali.add(seriale+",");
        }
        notificaEvasione.setListaSeriali(seriali);
        this.notifyMagazziniereObserver(notificaEvasione);
    }


    @Override
    public void notifyMagazziniereObserver(Notifica notificaEvasione) {
        CttDataBaseRestApplication.logger.info("Sto per inoltrare la notifica al Terminale Magazziniere CTT");
            observers.get(1).update(notificaEvasione);
    }
}
