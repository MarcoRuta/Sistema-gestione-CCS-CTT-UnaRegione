package it.unisannio.ingegneriaDelSoftware.Interfaces;
import java.util.List;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

public interface CTTFunction {

	/**Restituisce una lista di tutte le sacche che scadono tra 48 e 72h
	 * @return la lista di sacche non ancora scadute ma che scadono tra 48-72h da oggi
	 * @throws EntityNotFoundException 
	 */
	public void alertSaccheInScadenza() throws EntityNotFoundException;

	/**Rimuove tutte le Sacche scadute dal database delle Sacche e aggiorna i corrispettivi DatiSacca con enteRichiedente "Scaduta" e dataAffidamento con la data di scadenza
	 * @throws EntityNotFoundException 
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la sacca inserita non viene trovata
	 */
	public void removeSaccheScadute() throws EntityNotFoundException;
	
	/**Restituisce una lista di tutte le sacche che scadono entro le prossime 48-72 ore
	 * @return la lista di sacche non ancora scadute ma che scadono entro 48-72 ore da oggi
	 * @throws EntityNotFoundException 
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	public List<Sacca> getSaccheInScadenza() throws EntityNotFoundException;
}