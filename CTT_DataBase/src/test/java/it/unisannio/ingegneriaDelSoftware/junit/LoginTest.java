package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyMagazziniereCTTDataManager;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyOperatoreCTTDataManager;

public class LoginTest {
	MyMagazziniereCTTDataManager magazz = new MyMagazziniereCTTDataManager();
	MyOperatoreCTTDataManager oper = new MyOperatoreCTTDataManager();
	
	@Test	
	public void test1(){  	
		assertEquals("BN10-000000000126", oper.ricercaSaccaLocale(GruppoSanguigno.Ap, LocalDate.of(2010,04,10), "Mr. Damme tutto").getSeriale().getSeriale());
	}
	
	@Test
	public void test2() {
		assertEquals("BN10-000000000128", oper.ricercaSaccaLocale(GruppoSanguigno.Ap, LocalDate.of(2010,04,10), "Mr. Damme tutto").getSeriale().getSeriale());
	}
}

