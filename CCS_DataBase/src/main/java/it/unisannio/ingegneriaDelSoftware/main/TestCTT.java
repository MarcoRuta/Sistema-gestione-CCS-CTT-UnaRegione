package it.unisannio.ingegneriaDelSoftware.main;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyAmministratoreCCSDataManager;


public class TestCTT {
	
	public static void main(String[] args) throws ParseException {
	
	
	
	MyAmministratoreCCSDataManager amm = new MyAmministratoreCCSDataManager();
	
	List<CTT> listaCTT = new ArrayList<CTT>();
	
	CTT ctt = new CTT(1, "Magazzino Provinciale Benevento", "Benevento", "Benevento", "082412134", "C.da pacevecchia 12", "magazzinosacchebenevento@sanità.it", 41.1305, 14.787);
	listaCTT.add(ctt);
	
	ctt = new CTT(2, "Magazzino Provinciale Morcone", "Benevento", "Morcone", "08245512", "Via della montagna 3", "magazzinosacchemorcone@sanità.it", 41.3441, 14.6685);
	listaCTT.add(ctt);
	
	
	ctt = new CTT(3, "Magazzino Provinciale Ferrazano", "Campobasso", "Ferrazano", "08751112", "Via dello castello 11", "magazzinosaccheferrazzano@sanità.it", 41.533333, 14.666667);
	listaCTT.add(ctt);
	
	ctt = new CTT(4, "Magazzino Provinciale Campobasso", "Campobasso", "Campobasso", "0874311534", "Via Luigi Pirandello 7", "magazzinosacchecampobasso@sanità.it", 41.561, 14.6684);
	listaCTT.add(ctt);
	
	for(CTT c : listaCTT) {
    	amm.addCTT(c);
    }
	
	//amm.removeCTT(2);  //metodo che rimuove il magazzino di morcone, per testing (e per il meme)

	}
}
