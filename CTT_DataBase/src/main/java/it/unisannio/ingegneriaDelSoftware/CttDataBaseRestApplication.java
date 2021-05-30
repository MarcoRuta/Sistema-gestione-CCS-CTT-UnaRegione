package it.unisannio.ingegneriaDelSoftware;


import it.unisannio.ingegneriaDelSoftware.EndPointRest.*;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler.*;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutorizzazione;
import it.unisannio.ingegneriaDelSoftware.SaccheInScadenzaManager.GestioneScadenzeCTT;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutentificazione;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.ws.rs.ApplicationPath;


@SpringBootApplication (scanBasePackages = {"WebSocketConfig", "ingegneriaDelSoftware"})
@ApplicationPath("/rest")
@EnableScheduling
@Configuration


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
		register(EntityAlreadyExistsExceptionHandler.class);
		register(DateTimeParseExceptionHandler.class);
		register(EntityNotFoundExceptionHandler.class);
		register(IllegalArgumentExceptionHandler.class);



		//Endpoint del Magazziniere
		register(EndPointRestMagazziniereCTT.class);
		//EndPoint per il login
		register(EndPointRestAutentificazione.class);
		//Endpoint Amministratore
		register(EndPointRestAmministratoreCTT.class);
		//Endpoint Operatore
		register(EndPointRestOperatoreCTT.class);
		//endointNotifiche
		register(EndPointNotifiche.class);

	}
	
	@Scheduled(cron = "*/10 * * * * *")
	public void gestioneSaccheInScadenza() throws EntityNotFoundException {
		GestioneScadenzeCTT ctt = new GestioneScadenzeCTT();
		ctt.alertSaccheInScadenza();
		
	}

	public static void main(String[] args) {
		SpringApplication.run(CttDataBaseRestApplication.class, args);
		
	}

	
}
