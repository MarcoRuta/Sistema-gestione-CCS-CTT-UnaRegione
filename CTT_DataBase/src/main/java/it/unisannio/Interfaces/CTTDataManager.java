package it.unisannio.Interfaces;

import java.util.List;

import it.unisannio.Classes.Sacca;


public interface CTTDataManager {

	boolean login(String username, String password);
		
	//tra tutte le sacche restituisce quali hanno meno di 72 ore di vita
	List<Sacca> alertControlScadenza();

}