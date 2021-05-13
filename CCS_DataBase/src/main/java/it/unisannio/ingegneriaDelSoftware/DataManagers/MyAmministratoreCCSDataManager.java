package it.unisannio.ingegneriaDelSoftware.DataManagers;



import java.util.ArrayList;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Interfaces.AmministratoreCCSDataManager;



public class MyAmministratoreCCSDataManager implements AmministratoreCCSDataManager{

	/**Login è l'operazione con la quale AmministratoreCCS accede al sistema
	 * 
	 * @param username Username che usa AmministratoreCCS per entrare nel sistema
	 * @param password Password che usa AmministratoreCCS per entrare nel sistema
	 * @return true se username e password corrispondono; false altrimenti
	 * 
	 */
	public boolean login(String username, String password) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		if(mm.getDipendente(username, password)!= null) return true;
		else return false;
	}

	/**Metodo che carica un CTT, completo dei suoi dati, all'interno del database
	 * @param c CTT da inserire all'interno del database
	 * 
	 */
	public void addCTT(CTT c) {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.createCTT(c);
	}
	
	/**Metodo che rimuove un CTT e tutti i suoi dati dal database
	 * 
	 * @param numero il numero del CTT che si vuole rimuovere dal database
	 * 
	 */
	public void removeCTT(int numero) {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.removeCTT(numero);
	}

	/**Metodo che ricerca tutti i CTT all' interno del DataBase
	 * 
	 * @return Lista di tutti i CTT presenti all'interno del DataBase
	 * 
	 */
	public List<CTT> listaDeiCTT(){
		MyMongoDataManager mm = new MyMongoDataManager();
		return mm.getListaCTT();
	}
	
	/**Metodo che restituisce il CTT più vicino a quello dato
	 * 
	 * @param num Numero del CTT dato
	 * @return CTT più vicino a quello dato
	 */
	public CTT CttPiùVicino(int num){
		MyMongoDataManager mm = new MyMongoDataManager();
		
		CTT c = mm.getCTT(num);
		
		List<CTT> listaCTT = mm.getListaCTT();
		
		List<CTT> listaCTTOrdinata = new ArrayList<CTT>();
		
	
		for (CTT ctt : listaCTT) if (!ctt.equals(c)) listaCTTOrdinata.add(ctt);       	//ho la lista di tutti i CTT tranne quello che voglio confrontare
		
		CTT min = listaCTTOrdinata.get(0);  // imposto arbitrariamente il primo della lista come più vicino;
		
		for (CTT ctt : listaCTTOrdinata) if(min.distanzaDalCtt(c) > ctt.distanzaDalCtt(c)) min = ctt;  //per ogni CTT controllo se è più vicino del minimo attuale e aggiorno 
		
		return min;
	}


}
