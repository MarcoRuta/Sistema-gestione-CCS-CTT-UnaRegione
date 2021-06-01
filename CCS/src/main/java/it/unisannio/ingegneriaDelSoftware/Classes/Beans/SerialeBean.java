package it.unisannio.ingegneriaDelSoftware.Classes.Beans;
import java.util.*;


/**FlyWeight*/
public class SerialeBean {

	
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
		SerialeBean seriale1 = (SerialeBean) o;
		return seriale.equals(seriale1.seriale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(seriale);
	}

	/**@return  il valore del seriale come stringa*/
	public String getSeriale(){
		return this.seriale;
	}
	
	/**@param  il valore del seriale come stringa*/
	public void setSeriale(String seriale){
		this.seriale = seriale;
	}
	
	
	
	

	

}