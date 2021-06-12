package it.unisannio.ingegneriaDelSoftware;

import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestRicercaGlobale;
import it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestAmministratoreCCS;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestAutentificazione;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestSaccheInScadenza;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutentificazione;
import javax.ws.rs.ApplicationPath;

@SpringBootApplication (scanBasePackages = {"WebSocket", "ingegneriaDelSoftware"})
@ApplicationPath("/rest")
public class CcsRestApplication extends ResourceConfig {

	public static Logger logger = LoggerFactory.getLogger(CcsRestApplication.class);

	public CcsRestApplication() {
		/**####Configurazione#####*/
				
		//Filtro per Autentificazione
		register (FiltroDiAutentificazione.class);
		
		//EndPoint per il login
		register(EndPointRestAutentificazione.class);
		
		//Endpoint del Magazziniere
		register(EndPointRestAmministratoreCCS.class);
		
		//Endpoint gestione sacche in scadenza
		register(EndPointRestSaccheInScadenza.class);

		//EndPoint ricerca globale
		register(EndPointRestRicercaGlobale.class);

		//exceptionHandler
		register(AssertionError.class);
		register(DateTimeParseExceptionHandler.class);
		register(EntityAlreadyExistsExceptionHandler.class);
		register(EntityNotFoundExceptionHandler.class);
		register(ExceptionHandler.class);
		register(IllegalArgumentExceptionHandler.class);
		register(NumberFormaExceptionHandler.class);
	}

	public static void main(String[] args) {
		MongoDataManager.getInstance().dropSaccheInScadenza();
		SpringApplication.run(CcsRestApplication.class, args);
	}
}