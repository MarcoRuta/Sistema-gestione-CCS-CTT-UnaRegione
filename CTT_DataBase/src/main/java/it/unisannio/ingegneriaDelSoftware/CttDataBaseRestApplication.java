package it.unisannio.ingegneriaDelSoftware;

import it.unisannio.ingegneriaDelSoftware.DataManagers.MyMagazziniereCTTDataManager;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.ws.rs.ApplicationPath;

@SpringBootApplication
@ApplicationPath("/rest")
public class CttDataBaseRestApplication extends ResourceConfig {

	public CttDataBaseRestApplication() {
		register(MyMagazziniereCTTDataManager.class); //stiamo passando la classe da istanziare per il servizio
	}

	public static void main(String[] args) {
		SpringApplication.run(CttDataBaseRestApplication.class, args);
		
	}
	
}
