package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

public interface CCSFunction {
		public CTT CttPiuVicino(CTTName cttName) throws EntityNotFoundException;
}