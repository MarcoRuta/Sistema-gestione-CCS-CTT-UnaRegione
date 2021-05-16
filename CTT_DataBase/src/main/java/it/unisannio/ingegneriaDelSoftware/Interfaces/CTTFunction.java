package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;;


public interface CTTFunction {

	List<Sacca> alertControlScadenza();
	
	void removeSaccheScadute();
}