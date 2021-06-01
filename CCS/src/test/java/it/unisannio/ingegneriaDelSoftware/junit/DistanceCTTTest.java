package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;
import it.unisannio.ingegneriaDelSoftware.Functional.CCS;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;



public class DistanceCTTTest {
	
	/**Classe per il popolamento del database
	 */
	@Before
	public void setUp() throws EntityAlreadyExistsException {
		
		List<CTT> listaCTT = new ArrayList<CTT>();
		
		CTT ctt = new CTT(CTTName.getCttName("CTT001"), "BN", "Benevento", "0824121347", "C.da pacevecchia 12", "magazzinosacchebenevento@sanità.it", 41.1305, 14.787);
		listaCTT.add(ctt);
		
		ctt = new CTT(CTTName.getCttName("CTT002"), "BN", "Morcone", "0824551247", "Via della montagna 3", "magazzinosacchemorcone@sanità.it", 41.3441, 14.6685);
		listaCTT.add(ctt);
		
		
		ctt = new CTT(CTTName.getCttName("CTT003"), "CB", "Ferrazano", "0875111276", "Via dello castello 11", "magazzinosaccheferrazzano@sanità.it", 41.533333, 14.666667);
		listaCTT.add(ctt);
		
		ctt = new CTT(CTTName.getCttName("CTT004"), "CB", "Campobasso", "0874311537", "Via Luigi Pirandello 7", "magazzinosacchecampobasso@sanità.it", 41.561, 14.6684);
		listaCTT.add(ctt);

		MongoDataManager mm = MongoDataManager.getInstance();
		for(CTT c : listaCTT)
			mm.createCTT(c);
	}
	
	CCS ccs = new CCS();

	
	@After
	public void dropDB(){
		MongoDataManager mm = MongoDataManager.getInstance();
		mm.dropDB();
	}
	
	
	/**Test 1
	 * Il CTT più vicino al 4 (Campobasso) dovrebbe essere il 3 (Ferrazano)
	 * @throws it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException
	 * @throws EntityAlreadyExistsException 
	 */
	@Test
	public void test1() throws EntityNotFoundException, EntityAlreadyExistsException {
		assertEquals("CTT003",ccs.CttPiuVicino(CTTName.getCttName("CTT004")).getDenominazione().getCttname());
	}
	
	
	/**Test 2
	 * Il CTT più vicino al 1 (Benevento) dovrebbe essere il 2 (Morcone)
	 * @throws EntityNotFoundException
	 * @throws EntityAlreadyExistsException 
	 */
	@Test
	public void test2() throws EntityNotFoundException, EntityAlreadyExistsException {
		assertEquals("CTT002",ccs.CttPiuVicino(CTTName.getCttName("CTT001")).getDenominazione().getCttname());
	}
	
	
	/**Test 3
	 * Il CTT più vicino al 2 (Morcone) dovrebbe essere il 4 (Ferrazzano)
	 * @throws EntityNotFoundException
	 * @throws EntityAlreadyExistsException 
	 */
	@Test
	public void test3() throws EntityNotFoundException, EntityAlreadyExistsException {
		assertEquals("CTT003",ccs.CttPiuVicino(CTTName.getCttName("CTT002")).getDenominazione().getCttname());
	}
	
	
	/**Test 4
	 * Il CTT più vicino al 2 (Ferrazzano) dovrebbe essere il 4 (Campobasso)
	 * @throws EntityNotFoundException
	 * @throws EntityAlreadyExistsException 
	 */
	@Test
	public void test4() throws EntityNotFoundException, EntityAlreadyExistsException {
		assertEquals("CTT004",ccs.CttPiuVicino(CTTName.getCttName("CTT003")).getDenominazione().getCttname());
	}
}