package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import com.itextpdf.text.DocumentException;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaSmaltimentoSacche;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.AbstractClasses.MagazziniereSubject;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.Magazziniere.EvasioneSacche.EvasioneObserver;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Magazziniere.SmaltimentoSacche.SmaltimentoObserver;
import it.unisannio.ingegneriaDelSoftware.Functional.PDFGenerator;
import it.unisannio.ingegneriaDelSoftware.Magazziniere.SmaltimentoSacche.SaccheScaduteRemover;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Path("/notifica")
@Secured
@RolesAllowed({"CCS","OperatoreCTT", "MagazziniereCTT"})
@Component
@Singleton
public class EndPointRestNotificheMagazziniere extends MagazziniereSubject {

    /**Riferimento all'unica istanza del MongoDataManager*/
    private MongoDataManager mm = MongoDataManager.getInstance();
    /**Lista delle notifiche di evasione sacche, mantenuta staticamente*/
    private static List<NotificaEvasione> notificheEvasione = new ArrayList<>();
    /**Notifica di smaltimento sacche, mantenuta staticamente*/
    public static NotificaSmaltimentoSacche serialiDaSmaltire;


    /**
     * Restituisce Notifica Smaltimento Sacca
     *
     * @return NotificaSmaltimentoSacche
     */
    public static NotificaSmaltimentoSacche getSerialiDaSmaltire() {
        return serialiDaSmaltire;
    }


    /**Restituisce la lista delle notifiche di evasione
     * @return notificheEvasione
     */
    public static List<NotificaEvasione> getNotificheEvasione() {
        return notificheEvasione;
    }


    /**Aggiunge una notifica alla lista notifiche evasione vista dal magazziniere
     * @param notificaEvasione la notifica che si vuole aggiungere alla lista di notifiche evasione
     * @throws EntityNotFoundException nel caso in cui si sta tentando di prenotare una sacca non presente
     */
    @POST
    @Path("/notificaEvasione")
    @Consumes(MediaType.APPLICATION_JSON)
    public void inoltroNotificaEvasione(NotificaEvasione notificaEvasione) throws EntityNotFoundException {
        CttRestApplication.logger.info("L'operatore ha richiesto l'evasione per le sacche: " + notificaEvasione.getListaSeriali());
        for (Seriale seriale : notificaEvasione.getListaSeriali())
            mm.setPrenotatoSacca(seriale);
        notificheEvasione.add(notificaEvasione);
        this.notifyEvasioneObserver();
    }

    
    /**Stampa su un pdf un ordine di evasione
     * @return StreamingOutput il file su cui viene stampata la lettera di evasione
     */
    @GET
    @Path("/smaltimento/pdf")
    @Produces("application/pdf")
    @Consumes(MediaType.TEXT_PLAIN)
    public StreamingOutput getPDF() {
        return new StreamingOutput() {
            public void write(OutputStream output) {
                try {
                    PDFGenerator.makeDocumentSmaltimento(output, serialiDaSmaltire.getSerialeList());
                    serialiDaSmaltire = null;
                } catch (DocumentException | IOException e) {
                    throw new WebApplicationException(Response
                            .status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("Impossibile creare il PDF per lo smaltimento ")
                            .build());
                }
            }
        };
    }

    
    /**Rimuove dalla lista notifiche una notifica relativa ad un ordine evaso
     * Questo metodo viene attivato dal magazziniere nel momento in cui evade un ordine
     * @param notificaEvasione
     */
    public void removeNotificaEvasione(NotificaEvasione notificaEvasione) {
        CttRestApplication.logger.info("Sto per rimuovere La notificaEvasione della sacca evasa");
        notificheEvasione.remove(notificaEvasione);
        this.notifyEvasioneObserver();
    }


    @Override
    /**Metodo che notifica l'observer passandogli la lista aggiornata delle notifiche di evasione*/
    public void notifyEvasioneObserver() {
        CttRestApplication.logger.info("Sto per inoltrare le notifiche evasioni al Terminale Magazziniere CTT");
        for (Observer obs : Subject.observerList)
            if (obs instanceof EvasioneObserver)
                obs.update();
    }

    @Override
    /**Metodo che notifica l'observer passandogli notifica di smaltimento sacche*/
    public void notifySmaltimentoObserver() {
        CttRestApplication.logger.info("Sto per inoltrare la notifica di smaltimento sacca al Terminale Magazziniere CTT");
        for (Observer obs : Subject.observerList)
            if (obs instanceof SmaltimentoObserver)
                obs.update();
    }

    @Scheduled(initialDelay = 1000*1, fixedRate = 1000*60*3)
    /** */
    private void updateSaccheScadute() throws EntityNotFoundException {
        serialiDaSmaltire =SaccheScaduteRemover.getSmaltimentoSacche();
        if(serialiDaSmaltire != null)
            this.notifySmaltimentoObserver();
    }

}