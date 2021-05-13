package it.unisannio.ingegneriaDelSoftware.Classes;

import java.util.Objects;

public class Cdf {
	private String codiceFiscale;


	/**
	 * @param cdf  deve essere una stringa di 16 caratteri*/
    public Cdf(String s){

        assert(s.length()==16) : "Il codice Fiscale deve essere di 16 caratteri";                     
        this.codiceFiscale = s;
    }
	
	/**
	 * @return  restituisce il codice fiscale come stringa*/
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cdf cdf = (Cdf) o;
		return codiceFiscale.equals(cdf.codiceFiscale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale);
	}
}
