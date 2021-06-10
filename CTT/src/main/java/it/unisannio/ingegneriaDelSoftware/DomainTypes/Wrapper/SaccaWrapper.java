package it.unisannio.ingegneriaDelSoftware.DomainTypes.Wrapper;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;

import java.util.List;

/**Classe wrapper di una lista di Sacche*/
public class SaccaWrapper {
	
	/**Lista di sacche*/
	private List<Sacca> sacche;

	/**Metodo costruttore di SaccaWrapper
	 * @param sacche la lista di Sacche
	 */
    public SaccaWrapper(List<Sacca> sacche) {
        this.sacche = sacche;
    }

    /**Metodo costruttore senza parametri
     */
    public SaccaWrapper() {
    }

    /**Restituisce la lista delle Sacche
     * @return sacche la lista delle Sacche
     */
    public List<Sacca> getSacche() {
        return sacche;
    }

    /**Modifica la lista di Sacche
     * @param sacche la lista delle Sacche
     */
    public void setSacche(List<Sacca> sacche) {
        this.sacche = sacche;
    }
    
}