package it.unisannio.Testers;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisannio.DataManagers.MyMongoDataManager;
import it.unisannio.Interfaces.DataManager;



public class TestDropMongoDB {

	public static void main(String[] args) {

		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);

		DataManager dm = new MyMongoDataManager();
		dm.dropDB();

	}

}
