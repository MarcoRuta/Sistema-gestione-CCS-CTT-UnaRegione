package it.unisannio.ingegneriaDelSoftware.Interfaces;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Exceptions.CTTNotFoundException;

public interface DataManager {
	
   /**
	* Metodo che permettere di aggiungere un CTT al database
	* 
	* @param c il CTT da aggiungere al database
	* 
	*/
	void createCTT(CTT c);
	
	/**
	 * Metodo che permette di rimuovere un CTT dal database
	 * 
	 * @param numero Numero del CTT da rimuovere dal database
	 * 
	 * 
	 */
	void removeCTT(int numero);
	
	/**
	 * Metodo che permette di cercare un CTT all'interno del database
	 * 
	 * @param numero Numero del CTT di cui si vogliono ottenere i dati
	 * 
	 * @return CTT Il CTT ricercato
	 * @throws CTTNotFoundException 
	 * 
	 */
	CTT getCTT(int numero) throws CTTNotFoundException;
	
	/**
	 * Metodo che restituisce la lista di tutti i CTT presenti nel database del CCS
	 * @return Lista di tutti i CTT
	 * 
	 */
	List<CTT> getListaCTT();
	
	/**
	 * Metodo che restituisce la lista di tutti i dipendenti presenti nel database del CCS
	 * @return Lista di tutti i dipendenti
	 * 
	 */
	List<Dipendente> getListaDip();
	
	/**
	 * Metodo che restituisce un dipendente presente nel database
	 * 
	 * @param username Username del dipendente da cercare
	 * @param password Password del dipendente da cercare
	 * 
	 * @return Dipendente cercato
	 */
	Dipendente getDipendente(String username, String password);
	
	/**
	 * Metodo che aggiunge il dipendente al database
	 * 
	 * @param dip Dipendente da aggiungere
	 * 
	 */
		public void addDipendente(Dipendente dip);
}