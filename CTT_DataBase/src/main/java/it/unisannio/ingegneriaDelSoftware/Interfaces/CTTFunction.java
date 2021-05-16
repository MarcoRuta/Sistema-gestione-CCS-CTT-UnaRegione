package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;;


public interface CTTFunction {

	List<Sacca> alertControlScadenza() throws SaccaNotFoundException;
	
	void removeSaccheScadute() throws SaccaNotFoundException;
}