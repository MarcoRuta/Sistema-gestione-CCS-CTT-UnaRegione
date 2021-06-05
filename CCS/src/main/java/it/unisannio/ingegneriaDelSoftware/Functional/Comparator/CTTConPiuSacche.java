package it.unisannio.ingegneriaDelSoftware.Functional.Comparator;

import it.unisannio.ingegneriaDelSoftware.Classes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
