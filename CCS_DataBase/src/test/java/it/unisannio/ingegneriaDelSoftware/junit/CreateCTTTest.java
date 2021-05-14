package it.unisannio.ingegneriaDelSoftware.junit;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyAmministratoreCCSDataManager;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyMongoDataManager;

public class CreateCTTTest {
	@BeforeClass 
	public static void populateDBCTT() throws ParseException {
		
			MyAmministratoreCCSDataManager amm = new MyAmministratoreCCSDataManager();
			List<CTT> listaCTT = new ArrayList<CTT>();
		        
			Integer numero = 1;
	        String denominazione = "Pippo";
	        String provincia = "BN";
	        String città = "Morcone";
	        String telefono = "0824956789";
	        String indirizzo = "Via Montello 21";
	        String email = "pippo@aruba.com";
	        double latitudine = 44.5;
	        double longitudine = 20.6;
	        CTT CTT001 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);  
	        listaCTT.add(CTT001);
	        
	        numero = 2;
	        denominazione = "Pluto";
	        provincia = "NA";
	        città = "Baselice";
	        telefono = "0824678902";
	        indirizzo = "Via Aldo 71";
	        email = "pluto@aruba.com";
	        latitudine = 67.5;
	        longitudine = 80.6;
	        CTT CTT002 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);  
	        listaCTT.add(CTT002);
	        
	        numero = 3;
	        denominazione = "Pino";
	        provincia = "SA";
	        città = "Salerno";
	        telefono = "0824940982";
	        indirizzo = "Via Asso 51";
	        email = "pino@aruba.com";
	        latitudine = 22.0;
	        longitudine = 34.6;
	        CTT CTT003 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);  
	        listaCTT.add(CTT003);
	        
	        numero = 4;
	        denominazione = "Lavatrice";
	        provincia = "BN";
	        città = "Pontelandolfo";
	        telefono = "0824867953";
	        indirizzo = "Via Luigi Pirandello 121";
	        email = "lavatrice@aruba.com";
	        latitudine = 65.5;
	        longitudine = 65.6;
	        CTT CTT004 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);    
	        listaCTT.add(CTT004);
	        
	        numero = 5;
	        denominazione = "Marchizza";
	        provincia = "BN";
	        città = "Benevento";
	        telefono = "0824546783";
	        indirizzo = "Via Torre Della Catena 118";
	        email = "marchizza@aruba.com";
	        latitudine = 40.5;
	        longitudine = 678.6;
	        CTT CTT005 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);  
	        listaCTT.add(CTT005);
	        
	        numero = 6;
	        denominazione = "Caso";
	        provincia = "BN";
	        città = "Paduli";
	        telefono = "0824124532";
	        indirizzo = "Via Arco 91";
	        email = "caso@aruba.com";
	        latitudine = 64.5;
	        longitudine = 123.6;
	        CTT CTT006 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);   
	        listaCTT.add(CTT006);
	        
	        numero = 7;
	        denominazione = "Albano";
	        provincia = "CE";
	        città = "Caserta";
	        telefono = "0824789053";
	        indirizzo = "Via Bocciato 17";
	        email = "albano@aruba.com";
	        latitudine = 11.5;
	        longitudine = 123.6;
	        CTT CTT007 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);  
	        listaCTT.add(CTT007);
	      
	        for(CTT ctt : listaCTT) {
	        	amm.addCTT(ctt);
	        }             
	}
	
	MyMongoDataManager mongo = new MyMongoDataManager();  	
		
	
	/**
	*
	*
	*/
	@Test	
	public void test1(){  	
		assertEquals(7, mongo.getListaCTT().size());
	}
	
	/**
	*
	*
	*/
	@Test	
	public void test2(){  	
		Integer numero = 8;
        String denominazione = "Santo Padre CTT";
        String provincia = "NA";
        String città = "Napoli";
        String telefono = "08212316789";
        String indirizzo = "Via pizza 21";
        String email = "pippo123123@aruba.com";
        double latitudine = 46.5;
        double longitudine = 27.6;
        CTT CTT008 = new CTT(numero, denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine);   
		mongo.createCTT(CTT008);
		assertEquals(8, mongo.getListaCTT().size());
	}

	@AfterClass public static void dropDBCTT() {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.dropDB();
	}
	
}
