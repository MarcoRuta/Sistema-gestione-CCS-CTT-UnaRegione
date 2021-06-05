package it.unisannio.ingegneriaDelSoftware.Functional;

import it.unisannio.ingegneriaDelSoftware.Classes.Beans.SaccaBean;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaSaccaInScadenza;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;

import java.util.ArrayList;
import java.util.List;

public class NotificaSaccaInScadenzaMaker {

    public static List<Notifica> creaNotificheSaccheInScadenza() {
        List<SaccaBean> listaSacche = MongoDataManager.getInstance().getListaSacche();
        List<Notifica> listaNotifiche = new ArrayList<Notifica>();
        for(SaccaBean s : listaSacche) listaNotifiche.add(new NotificaSaccaInScadenza(s.getSeriale().getSeriale(),s.getDataScadenza(),s.getGruppoSanguigno().toString()));
        return listaNotifiche;
    }
}