package it.unisannio.Testers;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;

public class tester {

	public static void main(String[] args) {
		 System.err.println("111");		 
		 MongoClientURI uri = new MongoClientURI(
				    "mongodb+srv://laroccam99:0Mongodb0@cluster0.l6uc6.mongodb.net/jolly?retryWrites=true&w=majority");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase("DB1");
		 
		 System.err.println("222");
		 MongoCollection<Document> collection = database.getCollection("lucio");
		 
		 Document doc = new Document("Seriale", "000000000000")
	                .append("type", "B")
	                .append("Data_Produzione", 04-11-1998)
	                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
	                .append("info", new Document("x", 203).append("y", 102));
		 
		 collection.insertOne(doc);
		 
		 Document doc2 = new Document("prova", "cane")
	                .append("type", "prova")
	                .append("count", 1)
	                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
	                .append("info", new Document("x", 203).append("y", 102));
		 
		 collection.insertOne(doc2);
		 		 
		 mongoClient.close();
	}

}
