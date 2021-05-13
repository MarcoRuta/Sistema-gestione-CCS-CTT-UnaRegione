package it.unisannio.ingegneriaDelSoftware.main;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyMongoDataManager;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyOperatoreCTTDataManager;
import java.io.PrintStream;
import java.text.ParseException;
import java.time.LocalDate;


public class TestOperatoreCTT {
    public static void main(String[] args) throws ParseException {
    	PrintStream ps = new PrintStream(System.out);
    	MyOperatoreCTTDataManager oper = new MyOperatoreCTTDataManager();
  	 
    	GruppoSanguigno gs = GruppoSanguigno.Ap;    	
    	LocalDate localDataArrivoMassima = LocalDate.of(2010,10,18);   		
    	String enteRichiedente = "Mr. Damme tutto";
    	Sacca s = oper.ricercaSaccaLocale(gs, localDataArrivoMassima, enteRichiedente);	
    	
    	s.print(ps);
    	
    	MyMongoDataManager mm = new MyMongoDataManager();
    	DatiSacca ds = mm.getDatiSacca(s.getSeriale());
    	ds.print(ps);
    	
    }
}
