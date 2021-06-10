package it.unisannio.ingegneriaDelSoftware.DomainTypes.Wrapper;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Sacca;

import java.util.List;

public class SaccaWrapper {
private List<Sacca> sacche;

    public SaccaWrapper(List<Sacca> sacche) {
        this.sacche = sacche;
    }

    public SaccaWrapper() {
    }

    public List<Sacca> getSacche() {
        return sacche;
    }

    public void setSacche(List<Sacca> sacche) {
        this.sacche = sacche;
    }
}
