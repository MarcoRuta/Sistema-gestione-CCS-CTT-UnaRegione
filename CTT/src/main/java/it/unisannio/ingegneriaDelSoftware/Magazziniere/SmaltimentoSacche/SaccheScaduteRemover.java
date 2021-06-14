package it.unisannio.ingegneriaDelSoftware.Magazziniere.SmaltimentoSacche;


import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaSmaltimentoSacche;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SaccheScaduteRemover {
    private static MongoDataManager mm = MongoDataManager.getInstance();

    public static  NotificaSmaltimentoSacche getSmaltimentoSacche() throws EntityNotFoundException {
        return removeSaccheScadute();
    }

    /**Rimuove tutte le sacche scadute dal database delle sacche e aggiorna i corrispettivi DatiSacca
     * con enteRichiedente "Scaduta" e dataAffidamento con la data di scadenza.
     * Notifica il MagazziniereObserver con le sacche da smaltire
     * @throws EntityNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
     */
    public static NotificaSmaltimentoSacche removeSaccheScadute() throws EntityNotFoundException  {
        CttRestApplication.logger.info("Inizio la procedura di ricerca sacche scadute");
        List<Seriale> serialiDaSmaltireList = new ArrayList<>();
        for(Sacca sacca : mm.getListaSacche())
            if(isScaduta(sacca)) {
                serialiDaSmaltireList.add(sacca.getSeriale());
                removeSaccaScaduta(sacca);
            }
        if(!(serialiDaSmaltireList.isEmpty()))
            return new NotificaSmaltimentoSacche(serialiDaSmaltireList);
        return null;

    }

    /**Restituisce true se la sacca è scaduta*/
    public static boolean isScaduta(Sacca s) {
        return  (LocalDate.now().isEqual(s.getDataScadenza()) || LocalDate.now().isAfter(s.getDataScadenza()));
    }


    /**Rimuove tutte le sacche scadute dal database delle sacche e aggiorna i corrispettivi DatiSacca con enteRichiedente "Scaduta" e dataAffidamento con la data di scadenza
     * @param s Sacca da rimuovere dal database delle Sacche
     * @throws EntityNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
     */
    private static void removeSaccaScaduta(Sacca s) throws EntityNotFoundException {

        mm.removeSacca(s.getSeriale());
        mm.setDataAffidamentoDatiSacca(s.getSeriale(), s.getDataScadenza());
        mm.setEnteRichiedenteDatiSacca(s.getSeriale(), "Scaduta");
        mm.setIndirizzoEnteDatiSacca(s.getSeriale(), "Scaduta");
        CttRestApplication.logger.info("Ho rimosso la sacca: "+s.getSeriale().getSeriale()+" perchè era scaduta!");
    }



}
