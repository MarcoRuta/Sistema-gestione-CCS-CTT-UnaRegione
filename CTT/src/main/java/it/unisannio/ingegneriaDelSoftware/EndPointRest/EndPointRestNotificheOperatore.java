package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.AbstractClasses.OperatoreSubject;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaRisultatiRicerca;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.Operatore.RicercaGlobale.RisultatiRicercaGlobaleObserver;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Secured
@RolesAllowed("CCS")
@Path("/notificheOperatore")
@Singleton
public class EndPointRestNotificheOperatore extends OperatoreSubject {

    private static NotificaRisultatiRicerca notificaRisultatiRicerca;

    public static NotificaRisultatiRicerca getNotificaRisultatiRicerca() {
        return notificaRisultatiRicerca;
    }

    public static void removeNotificaRisultatiRicercaGlobale() {
        notificaRisultatiRicerca = null;
    }


    @POST
    @Path("/risultatiRicercaGlobale")
    @Consumes(MediaType.APPLICATION_JSON)
    public void notifyOperatore(NotificaRisultatiRicerca risultatiRicerca){
        CttRestApplication.logger.info("Mi è arrivata un risultato di una ricerca globale: "+risultatiRicerca);
        notificaRisultatiRicerca= risultatiRicerca;
        this.notifyRicercaGlobaleObserver();
    }

    
    @Override
    public void notifyRicercaGlobaleObserver() {
        for(Observer obs : Subject.observerList)
            if(obs instanceof RisultatiRicercaGlobaleObserver)
                obs.update();
    }

    
    @Override
    public void notifySaccheInScadenzaObserver() {
        //do nothing
    }

}