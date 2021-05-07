package it.unisannio.Testers;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisannio.Classes.Sacca;
import it.unisannio.DataManagers.MyMongoDataManager;
import it.unisannio.TipiAggiuntivi.GruppoSanguigno;



public class TestMongoQuery {

	public static void main(String[] args) throws ParseException {
		
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		GruppoSanguigno gs = GruppoSanguigno.values()[2];
		List<Sacca> l = (new MyMongoDataManager()).listaSaccheGS(gs);
		for (Sacca s : l)
			System.out.println(s);
	}

}
