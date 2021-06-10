package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTT;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

public interface CCSFunction {
	/**Metodo utilizzato per trovare il CTT più vicino ad uno dato(spesso il CTT richiedente)
	 * @param cttName Nome del CTT richiedente
	 * @return CTT Il CTT più vicino
	 * @throws EntityNotFoundException
	 */
		public CTT CttPiuVicino(CTTName cttName) throws EntityNotFoundException;
}