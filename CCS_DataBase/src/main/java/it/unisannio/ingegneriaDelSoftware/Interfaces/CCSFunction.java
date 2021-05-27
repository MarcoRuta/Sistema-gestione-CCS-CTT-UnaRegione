package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Exceptions.CTTNotFoundException;

public interface CCSFunction {
		public CTT CttPiuVicino(int num) throws CTTNotFoundException;
	}