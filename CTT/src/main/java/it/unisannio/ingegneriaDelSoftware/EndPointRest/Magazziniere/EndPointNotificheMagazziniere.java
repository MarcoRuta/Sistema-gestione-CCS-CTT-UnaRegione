package it.unisannio.ingegneriaDelSoftware.EndPointRest.Magazziniere;

import com.itextpdf.text.DocumentException;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
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

    private static TerminaleMagazziniereObserver observer = new TerminaleMagazziniereObserver();
    private MongoDataManager mm = MongoDataManager.getInstance();
    private static List<NotificaEvasione> notificheEvasione = new ArrayList<>();
    public static NotificaSmaltimentoSacche serialiDaSmaltire;


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


    @GET
    @Path("/smaltimento/pdf")
    @Produces("application/pdf")
    @Consumes(MediaType.TEXT_PLAIN)
    public StreamingOutput getPDF() {
        return new StreamingOutput(){
            public void write(OutputStream output){
                try {
                    PDFGenerator.makeDocumentSmaltimento(output,serialiDaSmaltire.getSerialeList());
                    serialiDaSmaltire= null;
                } catch (DocumentException | IOException e) {
                    throw new WebApplicationException(Response
                            .status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("Impossibile creare il PDF per lo smaltimento ")
                            .build());
                }
            }
        };
    }


    public void removeNotificaEvasione(NotificaEvasione notificaEvasione){
        CttDataBaseRestApplication.logger.info("Sto per rimuovere La notificaEvasione della sacca evasa");
        notificheEvasione.remove(notificaEvasione);
        this.notifyMagazziniereObserver(notificheEvasione);
    }

    public static List<NotificaEvasione> getNotificheEvasione(){
        return notificheEvasione;
    }


    public void notifyMagazziniereObserver(List<NotificaEvasione> notificaEvasione) {
        CttDataBaseRestApplication.logger.info("Sto per inoltrare la notifica al Terminale Magazziniere CTT");
            observer.update(notificaEvasione);
    }

    public void notifyMagazziniereObserver(NotificaSmaltimentoSacche notificaSmaltimentoSacche) {
        CttDataBaseRestApplication.logger.info("Sto per inoltrare la notifica di smaltimento sacca al Terminale Magazziniere CTT");
        serialiDaSmaltire = notificaSmaltimentoSacche;
        observer.update(notificaSmaltimentoSacche);
    }
}
