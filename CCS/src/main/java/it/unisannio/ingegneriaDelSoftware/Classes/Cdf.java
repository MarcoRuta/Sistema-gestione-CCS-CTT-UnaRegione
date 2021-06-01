package it.unisannio.ingegneriaDelSoftware.Classes;

import java.util.*;

/**Questo è un FlyWeight*/
public class Cdf {
	private String codiceFiscale;
	private static Map<String, Cdf> cdfs = new HashMap<String,Cdf>();


	/**Costruisce un' istanza di Cdf solo se non è stata già istanziata in precedenza
	 * @param cdf Codice fiscale in formato Stringa*/
	public static Cdf getCDF(String cdf) throws AssertionError, IllegalArgumentException{
		assert cdf != null: "Il cdf non puo essere null";
		if (Cdf.cdfs.containsKey(cdf))
			return Cdf.cdfs.get(cdf);
		Cdf aCdf = new Cdf(cdf);
		Cdf.cdfs.put(cdf,aCdf);
		return aCdf;
	}

	/**Restituisce il Codice fiscale in formato Cdf partendo da una stringa
	 * @param cdf Codice fiscale, che deve essere una stringa di 16 caratteri
	 * @throws IllegalArgumentException se la String non rispetta lo standard del codice fiscale*/
	private Cdf(String cdf) throws IllegalArgumentException{
		if(!(cdf.matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$"))) throw  new IllegalArgumentException("Il codice fiscale non è nel formato corretto");
        this.codiceFiscale = cdf;
    }
	
	/**Restituisce il codice fiscale come stringa
	 * @return codice fiscale*/
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