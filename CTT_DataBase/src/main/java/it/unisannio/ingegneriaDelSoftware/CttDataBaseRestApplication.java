package it.unisannio.ingegneriaDelSoftware;

import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestLogin;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestMagazziniereCTT;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutorizzazione;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutentificazione;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.ws.rs.ApplicationPath;

@SpringBootApplication
@ApplicationPath("/rest")
public class CttDataBaseRestApplication extends ResourceConfig {

	public CttDataBaseRestApplication() {

		//Configurazione
		//Filtro per autorizzazzione
		register(FiltroDiAutorizzazione.class);
		//Filtro per Authentificazione
		register (FiltroDiAutentificazione.class);


		//Endpoint del Magazziniere
		register(EndPointRestMagazziniereCTT.class);
		//EndPoint per il login
		register(EndPointRestLogin.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CttDataBaseRestApplication.class, args);
		
	}
	
}
