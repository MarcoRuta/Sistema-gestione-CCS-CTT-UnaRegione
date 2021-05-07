package it.unisannio.Testers;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;



import it.unisannio.Classes.Sacca;
import it.unisannio.Constants.Constants;
import it.unisannio.DataManagers.MyMongoDataManager;
import it.unisannio.Interfaces.DataManager;
import it.unisannio.TipiAggiuntivi.GruppoSanguigno;
import it.unisannio.TipiAggiuntivi.Seriale;


public class TestCreateAndPopulateMongoDB {

	public static void main(String[] args) throws ParseException {
		PrintStream ps = new PrintStream(System.out);
		
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);
		DataManager mm = new MyMongoDataManager();
		Sacca s = null;	
		
		/*	
		final int NUMSACCHE=5;
	    GruppoSanguigno gruppi[] = GruppoSanguigno.values();
		Random generatore = new Random();
		
	
		
		for (int i=0; i<NUMSACCHE; i++) {
			s = new Sacca(gruppi[generatore.nextInt(gruppi.length)], "04-11-1998", "05-11-2000", false);
			dm.createSacca(s);
			System.out.println("Added sacca: "+s);			
		}		
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		        Seriale.updateSettings();
		    }
		}));
*/
		Seriale ser = new Seriale();	
		Date data1 = Constants.sdf.parse("04-11-1998");
		Date data2 = Constants.sdf.parse("05-11-2000");
		s=new Sacca(ser, GruppoSanguigno.Am, data1, data2, false);
		mm.createSacca(s);		
		s.print(ps);
		
		Sacca g = mm.getSacca(ser);
		g.print(ps);
	}

}
