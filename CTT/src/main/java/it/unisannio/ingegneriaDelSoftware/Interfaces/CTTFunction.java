package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.util.List;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;


public interface CTTFunction {

	/**Restituisce una lista di tutte le sacche che scadono tra 48 e 72h
	 * @return la lista di sacche non ancora scadute ma che scadono tra 48-72h da oggi
	 * @throws EntityNotFoundException 
	 */
	public void alertSaccheInScadenza() throws EntityNotFoundException;

	
	/**Restituisce una lista di tutte le sacche che scadono entro le prossime 48-72 ore
	 * @return la lista di sacche non ancora scadute ma che scadono entro 48-72 ore da oggi
	 * @throws EntityNotFoundException
	 */
	public List<Sacca> getSaccheInScadenza() throws EntityNotFoundException;
}