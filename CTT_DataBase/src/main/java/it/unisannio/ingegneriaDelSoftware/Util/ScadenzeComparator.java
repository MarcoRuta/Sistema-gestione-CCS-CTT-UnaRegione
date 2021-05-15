package it.unisannio.ingegneriaDelSoftware.Util;


import java.util.Comparator;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;


/**Metodo che compara due date 
 * @return 1 se la prima sacca scade prima della seconda
 * @return -1 se la prima sacca scade dopo la seconda
 * @return 0 se le due date sono uguali
 * 
 */
public class ScadenzeComparator implements Comparator<Sacca> {

	@Override
	public int compare(Sacca o1, Sacca o2) {
		
		if(o1.equals(o2)) return 0;
		
		return o1.getDataScadenza().isAfter(o2.getDataScadenza()) ? -1 : 1;
		
	}

}
