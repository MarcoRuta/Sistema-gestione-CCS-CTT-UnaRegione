package it.unisannio.ingegneriaDelSoftware.Classes;

import java.util.Objects;

public class Cdf {
	private String codiceFiscale;

	/**
	 * Metodo costruttore del codice fiscale
	 * 
	 * @param cdf  Stringa di 16 caratteri
	 * 
	 */
	public Cdf (String cdf) {
		assert cdf != null: "Il codice fiscale non può essere null";
		assert(cdf.length()==16): "Formato del codice fiscale non valido";
		this.codiceFiscale = cdf;
		}
	/**
	 * Metodo che restituisce il codice fiscale come stringa
	 * 
	 * @return  codice fiscale
	 *
	 */
	public String getCodiceFiscale() {

		return codiceFiscale;
	}

	/**
	 * Metodo che verifica l'uguaglianza tra due codici fiscali
	 * 
	 * @return Un boolean true o false a seconda dell'esito del confronto
	 *
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cdf cdf = (Cdf) o;
		return codiceFiscale.equals(cdf.codiceFiscale);
	}

	/**
	 * Metodo che calcola l'hashcode di un codice fiscale
	 * 
	 * @return Un intero pari all'hashcode generato
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale);
	}
}
