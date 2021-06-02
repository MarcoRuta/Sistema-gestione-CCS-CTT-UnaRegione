package it.unisannio.ingegneriaDelSoftware.Functional;

import java.util.Comparator;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;

public class ScadenzeComparator implements Comparator<Sacca> {

	/**Metodo che compara due date 
	 * @return 1 se la prima Sacca scade dopo la seconda,  -1 se la prima Sacca scade prima della seconda,  0 se le due date sono uguali
	 */
	@Override
	public int compare(Sacca o1, Sacca o2) {
		
		if(o1.equals(o2)) return 0;
		
		return o1.getDataScadenza().isAfter(o2.getDataScadenza()) ? 1 : -1;
		
	}
}