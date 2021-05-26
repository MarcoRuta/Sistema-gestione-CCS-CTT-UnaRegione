package it.unisannio.ingegneriaDelSoftware;


import it.unisannio.ingegneriaDelSoftware.EndPointRest.*;
import it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler.AssertionErrorHandler;
import it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler.ExceptionHandler;
import it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler.MarlFormedURLExceptionHandler;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutorizzazione;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutentificazione;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.ws.rs.ApplicationPath;
import java.net.MalformedURLException;

@SpringBootApplication (scanBasePackages = {"WebSocketConfig", "ingegneriaDelSoftware"})
@ApplicationPath("/rest")
public class CttDataBaseRestApplication extends ResourceConfig {

	public CttDataBaseRestApplication() throws Exception {

		//Filtro per autorizzazzione
		register(FiltroDiAutorizzazione.class);
		//Filtro per Authentificazione
		register (FiltroDiAutentificazione.class);
		//gestione AssertionError
		register(AssertionErrorHandler.class);
		//gestione Exception
		register(ExceptionHandler.class);
		//gestione MalformedURLException
		register(MarlFormedURLExceptionHandler.class);


		//Endpoint del Magazziniere
		register(EndPointRestMagazziniereCTT.class);
		//EndPoint per il login
		register(EndPointRestAutentificazione.class);
		//Endpoint Amministratore
		register(EndPointRestAmministratoreCTT.class);
		//Endpoint Operatore
		register(EndPointRestOperatoreCTT.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CttDataBaseRestApplication.class, args);
		
	}
	
}
