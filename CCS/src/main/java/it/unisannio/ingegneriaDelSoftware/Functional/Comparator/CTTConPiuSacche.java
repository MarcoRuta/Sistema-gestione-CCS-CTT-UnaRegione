package it.unisannio.ingegneriaDelSoftware.Functional.Comparator;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Classe utilizzata per confrontare CTT sulla base del numero di sacche.
 * Una volta confrontati avremo una mappa dei CTT ordinati per numero di sacche.
 * Il CTT in cima alla mappa sarà quello da cui prenderemo le sacche in ricerca globale non prioritaria
 */

public class CTTConPiuSacche implements Comparator<CTTName> {

    private Map<CTTName, List<Sacca>> saccheTrovate;

    public CTTConPiuSacche(Map<CTTName, List<Sacca>> saccheTrovate) {
        this.saccheTrovate = saccheTrovate;
    }

    @Override
    public int compare(CTTName o1, CTTName o2) {
        if (saccheTrovate.get(o1).size() - saccheTrovate.get(o2).size() == 0) {
            if (o1.equals(o2)) {
                return 0;
            } else {
                return 1;
            }
        }

        return  saccheTrovate.get(o2).size() - saccheTrovate.get(o1).size();
    }
}
