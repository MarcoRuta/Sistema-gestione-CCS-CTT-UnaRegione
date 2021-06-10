package it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche;

import java.time.LocalDate;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;

public class NotificaSaccaInScadenza implements Notifica {

	private String seriale;
	private LocalDate dataScadenza;
	private String gruppoSanguigno;
	
	/**Metodo costruttore di NotificaSaccaInScadenza
	 * @param seriale seriale della Sacca in scadenza
	 * @param dataScadenza data di scadenza della Sacca in scadenza
	 * @param gruppoSanguigno gruppoSanguigno della Sacca in scadenza
	 */
	public NotificaSaccaInScadenza(String seriale, LocalDate dataScadenza, String gruppoSanguigno) {
		this.setSeriale(seriale);
		this.setDataScadenza(dataScadenza);
		this.setGruppoSanguigno(gruppoSanguigno);
	}

	/**Restituisce il seriale della Sacca
	 * @return seriale Il seriale della Sacca
	 */
	public String getSeriale() {
		return seriale;
	}

	/**Modifica il seriale della Sacca
	 * @param seriale Il seriale della Sacca
	 */
	public void setSeriale(String seriale) {
		this.seriale = seriale;
	}

	/**Restituisce la data di scadenza
	 * @return dataScadenza La data di scadenza
	 */
	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	/**Modifica la data di scadenza della Sacca
	 * @param dataScadenza La data di scadenza
	 */
	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	/**Restituisce il gruppo sanguigno della Sacca
	 * @return gruppoSanguigno Il gruppo sanguigno della Sacca
	 */
	public String getGruppoSanguigno() {
		return gruppoSanguigno;
	}

	/**Modifica il gruppo sanguigno della Sacca
	 * @param gruppoSanguigno Il gruppo sanguigno della Sacca
	 */
	public void setGruppoSanguigno(String gruppoSanguigno) {
		this.gruppoSanguigno = gruppoSanguigno;
	}

	@Override
	public String toString() {
		return "NotificaSaccaInScadenza [seriale=" + seriale + ","
				+ " dataScadenza=" + dataScadenza 
				+ ", gruppoSanguigno="+ gruppoSanguigno +
				"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataScadenza == null) ? 0 : dataScadenza.hashCode());
		result = prime * result + ((gruppoSanguigno == null) ? 0 : gruppoSanguigno.hashCode());
		result = prime * result + ((seriale == null) ? 0 : seriale.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificaSaccaInScadenza other = (NotificaSaccaInScadenza) obj;
		if (dataScadenza == null) {
			if (other.dataScadenza != null)
				return false;
		} else if (!dataScadenza.equals(other.dataScadenza))
			return false;
		if (gruppoSanguigno == null) {
			if (other.gruppoSanguigno != null)
				return false;
		} else if (!gruppoSanguigno.equals(other.gruppoSanguigno))
			return false;
		if (seriale == null) {
			if (other.seriale != null)
				return false;
		} else if (!seriale.equals(other.seriale))
			return false;
		return true;
	}
}