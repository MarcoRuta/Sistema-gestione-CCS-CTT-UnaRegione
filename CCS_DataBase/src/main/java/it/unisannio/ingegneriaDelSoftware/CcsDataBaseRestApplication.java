package it.unisannio.ingegneriaDelSoftware;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestAmministratoreCCS;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestAutentificazione;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestSaccheInScadenza;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutentificazione;

import javax.ws.rs.ApplicationPath;

@SpringBootApplication (scanBasePackages = {"WebSocketConfig", "ingegneriaDelSoftware"})
@ApplicationPath("/rest")
public class CcsDataBaseRestApplication extends ResourceConfig {

	public CcsDataBaseRestApplication() {
		/**####Configurazione#####*/
				
		//Filtro per Autentificazione
		register (FiltroDiAutentificazione.class);
		
		//EndPoint per il login
		register(EndPointRestAutentificazione.class);
		
		//Endpoint del Magazziniere
		register(EndPointRestAmministratoreCCS.class);
		
		//Endpoint gestione sacche in scadenza
		register(EndPointRestSaccheInScadenza.class);
		
		//exceptionHandler
		register(AssertionError.class);
		register(DateTimeParseExceptionHandler.class);
		register(EntityAlreadyExisistsExceptionHandler.class);
		register(EntityNotFoundExceptionHandler.class);
		register(ExceptionHandler.class);
		register(IllegalArgumentExceptionHandler.class);
		register(NumberFormaExceptionHandler.class);

	
	}

	public static void main(String[] args) {
		SpringApplication.run(CcsDataBaseRestApplication.class, args);
	}
}