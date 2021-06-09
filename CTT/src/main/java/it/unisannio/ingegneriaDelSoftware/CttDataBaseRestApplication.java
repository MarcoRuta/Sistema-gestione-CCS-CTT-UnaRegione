package it.unisannio.ingegneriaDelSoftware;


import it.unisannio.ingegneriaDelSoftware.EndPointRest.*;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.Amministratore.EndPointRestAmministratoreCTT;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.Magazziniere.EndPointNotificheMagazziniere;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.Magazziniere.EndPointRestMagazziniereCTT;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.Operatore.EndPointRestOperatoreCTT;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.Operatore.RicercaGlobale.EndPointNotificheOperatore;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler.*;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutorizzazione;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.Operatore.SaccheInScadenza.GestioneScadenzeCTT;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.Operatore.SaccheInScadenza.SaccheInScadenzaClientEndPoint;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutentificazione;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.ws.rs.ApplicationPath;


@SpringBootApplication (scanBasePackages = {"WebSocket", "ingegneriaDelSoftware"})
@ApplicationPath("/rest")
@EnableScheduling
@Configuration
public class CttDataBaseRestApplication extends ResourceConfig {

	public static Logger logger = LoggerFactory.getLogger(CttDataBaseRestApplication.class);

	public CttDataBaseRestApplication() throws Exception {

		//Filtro per autorizzazione
		register(FiltroDiAutorizzazione.class);
		//Filtro per Autentificazione
		register (FiltroDiAutentificazione.class);
		//gestione AssertionError
		register(AssertionErrorHandler.class);
		//gestione Exception
		//register(ExceptionHandler.class);
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
		//Endpoint NotificheMagazzinere
		register(EndPointNotificheMagazziniere.class);
		//EndPoint NotificheOperatore
		register(EndPointNotificheOperatore.class);

	}

	@Scheduled(fixedDelay = 1000*60*2)
	public void gestioneSaccheInScadenza() throws EntityNotFoundException {
		CttDataBaseRestApplication.logger.info("Inizio il controllo per inoltrare le sacche in scadenza al CCS");
		new GestioneScadenzeCTT().alertSaccheInScadenza();
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(CttDataBaseRestApplication.class, args);
		SaccheInScadenzaClientEndPoint saccheInScadenzaClient = new SaccheInScadenzaClientEndPoint();
	}

	
}
