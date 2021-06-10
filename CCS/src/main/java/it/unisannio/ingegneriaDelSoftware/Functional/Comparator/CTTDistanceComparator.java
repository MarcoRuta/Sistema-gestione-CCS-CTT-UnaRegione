package it.unisannio.ingegneriaDelSoftware.Functional.Comparator;

import java.util.Comparator;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTT;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import javax.ws.rs.WebApplicationException;


public class CTTDistanceComparator implements Comparator<CTTName> {
    public CTT cttRichiedente;

    public CTTDistanceComparator(CTT cttRichiedente) {
        this.cttRichiedente = cttRichiedente;
    }

    @Override
    public int compare(CTTName o1, CTTName o2) {
        try {
            if (MongoDataManager.getInstance().getCTT(o1).distanzaDalCtt(cttRichiedente) >
                    MongoDataManager.getInstance().getCTT(o2).distanzaDalCtt(cttRichiedente))
                return 1;

            if (MongoDataManager.getInstance().getCTT(o1).distanzaDalCtt(cttRichiedente)==
                    MongoDataManager.getInstance().getCTT(o2).distanzaDalCtt(cttRichiedente))
                return 0;

            return -1;
        } catch (EntityNotFoundException e) {
            throw new WebApplicationException("Impossibile ordinare i CTT, un CTT non è presente nel DB");
        }

    }
}
