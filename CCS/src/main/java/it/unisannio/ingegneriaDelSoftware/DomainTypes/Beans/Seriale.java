package it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans;

import java.util.*;

/**FlyWeight*/
public class Seriale {
	
	private String seriale;
	
	@Override
	public String toString() {
		return "Seriale{" +
				"seriale='" + seriale + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Seriale seriale1 = (Seriale) o;
		return seriale.equals(seriale1.seriale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(seriale);
	}

	/**Restituisce il seriale della Sacca
	 * @return il valore del seriale come stringa
	 */
	public String getSeriale(){
		return this.seriale;
	}

	/**Modifica il seriale della Sacca
	 * @param seriale Il valore del seriale come stringa
	 */
	public void setSeriale(String seriale){
		this.seriale = seriale;
	}
}