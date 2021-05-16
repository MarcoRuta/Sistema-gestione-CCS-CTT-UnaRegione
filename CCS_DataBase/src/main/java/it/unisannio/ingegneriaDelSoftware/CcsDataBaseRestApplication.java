package it.unisannio.ingegneriaDelSoftware;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestAmministratoreCCS;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestAutentificazione;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutentificazione;

import javax.ws.rs.ApplicationPath;

@SpringBootApplication
@ApplicationPath("/rest")
public class CcsDataBaseRestApplication extends ResourceConfig {

	public CcsDataBaseRestApplication() {
		//Configurazione
				
		//Filtro per Authentificazione
		register (FiltroDiAutentificazione.class);
		
		//Endpoint del Magazziniere
		register(EndPointRestAmministratoreCCS.class);

		//EndPoint per il login
		register(EndPointRestAutentificazione.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CcsDataBaseRestApplication.class, args);
	}
}