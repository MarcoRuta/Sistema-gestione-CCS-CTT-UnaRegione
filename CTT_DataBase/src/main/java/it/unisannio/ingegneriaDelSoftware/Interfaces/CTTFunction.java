package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;;


public interface CTTFunction {

	/**Restituisce una lista di tutte le sacche che scadono entro 72 ore
	 * @return la lista di sacche non ancora scadute ma che scadono entro 72 ore da oggi
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la sacca inserita non viene trovata
	 */
	List<Sacca> alertControlScadenza() throws SaccaNotFoundException;

	/**Rimuove tutte le Sacche scadute dal database delle Sacche e aggiorna i corrispettivi DatiSacca con enteRichiedente "Scaduta" e dataAffidamento con la data di scadenza
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la sacca inserita non viene trovata
	 */
	void removeSaccheScadute() throws SaccaNotFoundException;
}