package it.unisannio.ingegneriaDelSoftware.junit;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyAmministratoreCCSDataManager;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyMongoDataManager;

public class DistanceCTTTest {
	
	@BeforeClass public static void populateDataBaseCTT() {
		
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
	}
	
	MyAmministratoreCCSDataManager amm = new MyAmministratoreCCSDataManager();
	
	/**Test 1
	 * 
	 * Il ctt più vicino al 4 (Campobasso) dovrebbe essere il 3 (Ferrazano)
	 */
	@Test
	public void test1() {
		assertEquals(3,amm.CttPiùVicino(4).getNumero());
	}
	
	/**Test 2
	 * 
	 * Il ctt più vicino al 1 (Benevento) dovrebbe essere il 2 (Morcone)
	 */
	@Test
	public void test2() {
		assertEquals(2,amm.CttPiùVicino(1).getNumero());
	}
	
	/**Test 3
	 * 
	 * Il ctt più vicino al 2 (Morcone) dovrebbe essere il 4 (Ferrazzano)
	 */
	@Test
	public void test3() {
		assertEquals(3,amm.CttPiùVicino(2).getNumero());
	}
	
	/**Test 4
	 * 
	 * Il ctt più vicino al 2 (Ferrazzano) dovrebbe essere il 4 (Campobasso)
	 */
	@Test
	public void test4() {
		assertEquals(4,amm.CttPiùVicino(3).getNumero());
	}
	
	@AfterClass public static void dropDataBaseCTT() {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.dropDB();
	}
	
}
