package it.unisannio.ingegneriaDelSoftware.junit;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unisannio.ingegneriaDelSoftware.DataManagers.MyAmministratoreCCSDataManager;

public class DistanceCTTTest {
	
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
	
}
