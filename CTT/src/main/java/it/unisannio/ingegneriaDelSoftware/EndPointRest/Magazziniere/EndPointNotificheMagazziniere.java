package it.unisannio.ingegneriaDelSoftware.EndPointRest.Magazziniere;

import com.itextpdf.text.DocumentException;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaSmaltimentoSacche;
import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.PDF.PDFGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Path("/notifica")
//@Secured
public class EndPointNotificheMagazziniere implements Subject {

	/**Observer che viene aggiornato ogni volta che arriva una nuova notifica per il magazziniere */
    private static TerminaleMagazziniereObserver observer = new TerminaleMagazziniereObserver();
    /**Riferimento all'unica istanza del MongoDataManager*/
    private MongoDataManager mm = MongoDataManager.getInstance();
    /**Lista delle notifiche di evasione sacche, mantenuta staticamente*/
    private static List<NotificaEvasione> notificheEvasione = new ArrayList<>();
    /**Notifica di smaltimento sacche, mantenuta staticamente */
    public static NotificaSmaltimentoSacche serialiDaSmaltire ;


    /**Aggiunge una notifica alla lista notifiche evasione vista dal magazziniere
     * @param notificaEvasione la notifica che si vuole aggiungere alla lista di notifiche evasione
     * @throws EntityNotFoundException nel caso in cui si sta tentando di prenotare una sacca non presente
     */
    @POST
    @Path("/notificaEvasione")
    @Consumes(MediaType.APPLICATION_JSON)
    public void inoltroNotificaEvasione(NotificaEvasione notificaEvasione) throws EntityNotFoundException {
        CttDataBaseRestApplication.logger.info("L'operatore ha richiesto l'evasione per le sacche: "+notificaEvasione.getListaSeriali());
        for (Seriale seriale : notificaEvasione.getListaSeriali())
            mm.setPrenotatoSacca(seriale);
        notificheEvasione.add(notificaEvasione);
        this.notifyMagazziniereObserver(notificheEvasione);
    }

    /**Stampa su un pdf un ordine di evasione
     * @return StreamingOutput il file su cui viene stampata la lettera di evasione 
     */
    @GET
    @Path("/smaltimento/pdf")
    @Produces("application/pdf")
    @Consumes(MediaType.TEXT_PLAIN)
    public StreamingOutput getPDF() {
        return new StreamingOutput(){
            public void write(OutputStream output){
                try {
                    PDFGenerator.makeDocumentSmaltimento(output,serialiDaSmaltire.getSerialeList());
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
    public void removeNotificaEvasione(NotificaEvasione notificaEvasione){
        CttDataBaseRestApplication.logger.info("Sto per rimuovere La notificaEvasione della sacca evasa");
        notificheEvasione.remove(notificaEvasione);
        this.notifyMagazziniereObserver(notificheEvasione);
    }

    /**Restituisce la lista delle notifiche di evasione
     * @return notificheEvasione
     */
    public static List<NotificaEvasione> getNotificheEvasione(){
        return notificheEvasione;
    }

    /**Metodo che notifica l'observer passandogli la lista aggiornata delle notifiche di evasione*/
    public void notifyMagazziniereObserver(List<NotificaEvasione> notificaEvasione) {
        CttDataBaseRestApplication.logger.info("Sto per inoltrare la notifica al Terminale Magazziniere CTT");
            observer.update(notificaEvasione);
    }
    /**Metodo che notifica l'observer passandogli notifica di smaltimento sacche*/
    public void notifyMagazziniereObserver(NotificaSmaltimentoSacche notificaSmaltimentoSacche) {
        CttDataBaseRestApplication.logger.info("Sto per inoltrare la notifica di smaltimento sacca al Terminale Magazziniere CTT");
        serialiDaSmaltire = notificaSmaltimentoSacche;
        observer.update(notificaSmaltimentoSacche);
    }
}
