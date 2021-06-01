package it.unisannio.ingegneriaDelSoftware.Classes;
import java.util.*;

//Questo è un FlyWeight
public class Cdf {
	private String codiceFiscale;
	private static Map<String, Cdf> cdfs = new HashMap<String,Cdf>();
	
	/**
	 * Metodo tramite il quale costruiamo una istanza di CDF se non è stata gia istanziata in precedenza
	 * @param cdf codice fiscale che vogliamo creare
	 */
	public static Cdf getCDF(String cdf) throws AssertionError, IllegalArgumentException{
		assert cdf != null: "Il cdf non puo essere null";
		if (Cdf.cdfs.containsKey(cdf)) return Cdf.cdfs.get(cdf);
		Cdf aCdf = new Cdf(cdf);
		Cdf.cdfs.put(cdf,aCdf);
		return aCdf;
	}

	/**
	 * Metodo per il controllo della creazione di un cdf
	 * @param cdf  Il cdf deve essere una stringa di 16 caratteri organizzata secondo lo standard unico europeo [A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$
	 * @throws IllegalArgumentException se il codice fiscale non rispetta lo standard del codice fiscale
	 */
    private Cdf(String cdf) throws IllegalArgumentException{
		if(!(cdf.matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$"))) throw  new IllegalArgumentException("Il codice fiscale non è nel formato corretto");
        this.codiceFiscale = cdf;
    }
	/**
	 * Metodo che ritorna il codice fiscale
	 * @return codice fiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * Metodo che verifica l'uguaglianza tra due oggetti
	 * @param o oggetto del testing
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cdf cdf = (Cdf) o;
		return codiceFiscale.equals(cdf.codiceFiscale);
	}

	/**
	 * Metodo per la creazione dell'hashcode
	 * @return hashcode dell'oggetto
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale);
	}
}
