package it.unisannio.ingegneriaDelSoftware.Classes;

import java.util.*;

/**Questo è un FlyWeight*/
public class Cdf {
	private String codiceFiscale;
	private static Map<String, Cdf> cdfs = new HashMap<String,Cdf>();


	public static Cdf getCDF(String cdf) throws AssertionError{
		assert cdf != null: "Il cdf non puo essere null";
		if (Cdf.cdfs.containsKey(cdf))
			return Cdf.cdfs.get(cdf);
		Cdf aCdf = new Cdf(cdf);
		Cdf.cdfs.put(cdf,aCdf);
		return aCdf;
	}

	/**
	 * @param cdf  deve essere una stringa di 16 caratteri*/
    private Cdf(String cdf){
		if(!(cdf.length()==16)) throw new AssertionError("Il codice Fiscale deve essere di 16 caratteri");
        this.codiceFiscale = cdf;
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
