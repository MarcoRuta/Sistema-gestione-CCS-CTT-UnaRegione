package it.unisannio.ingegneriaDelSoftware;

import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointLogin;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointMagazziniereCTT;
import it.unisannio.ingegneriaDelSoftware.Filtri.AuthorizationFilter;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutentificazione;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.ws.rs.ApplicationPath;

@SpringBootApplication
@ApplicationPath("/rest")
public class CttDataBaseRestApplication extends ResourceConfig {

	public CttDataBaseRestApplication() {
		register(EndPointMagazziniereCTT.class); //stiamo passando la classe da istanziare per il servizio
		register(AuthorizationFilter.class);
		register (FiltroDiAutentificazione.class);
		register(EndPointLogin.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CttDataBaseRestApplication.class, args);
		
	}
	
}
