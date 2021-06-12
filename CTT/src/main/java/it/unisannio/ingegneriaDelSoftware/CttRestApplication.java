package it.unisannio.ingegneriaDelSoftware;

import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.*;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestAmministratoreCTT;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestNotificheMagazziniere;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestMagazziniereCTT;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestOperatoreCTT;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.Magazziniere.EvasioneSacche.EvasioneObserver;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.EndPointRestNotificheOperatore;
import it.unisannio.ingegneriaDelSoftware.Exceptions.ExceptionHandler.*;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutorizzazione;
import it.unisannio.ingegneriaDelSoftware.Magazziniere.SmaltimentoSacche.SmaltimentoObserver;
import it.unisannio.ingegneriaDelSoftware.Operatore.RicercaGlobale.RisultatiRicercaGlobaleObserver;
import it.unisannio.ingegneriaDelSoftware.Operatore.SaccheInScadenza.GestioneScadenzeCTT;
import it.unisannio.ingegneriaDelSoftware.Operatore.SaccheInScadenza.SaccheInScadenzaClientEndPoint;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutentificazione;
import it.unisannio.ingegneriaDelSoftware.Operatore.SaccheInScadenza.SaccheInScadenzaObserver;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


import javax.ws.rs.ApplicationPath;


@SpringBootApplication (scanBasePackages = {"WebSocket", "ingegneriaDelSoftware"})
@ApplicationPath("/rest")
@EnableScheduling
public class CttRestApplication extends ResourceConfig {

	public static Logger logger = LoggerFactory.getLogger(CttRestApplication.class);

	public CttRestApplication() throws Exception {


		//Filtro per autorizzazione
		register(FiltroDiAutorizzazione.class);
		//Filtro per Autentificazione
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
		//Endpoint NotificheMagazzinere
		register(EndPointRestNotificheMagazziniere.class);
		//EndPoint NotificheOperatore
		register(EndPointRestNotificheOperatore.class);

	}

	@Bean
	public GestioneScadenzeCTT gestioneSaccheInScadenza() {
		return new GestioneScadenzeCTT();
	}

	@Bean
	public EndPointRestNotificheMagazziniere registerScheduledTask(){
		return new EndPointRestNotificheMagazziniere();
	}


	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(CttRestApplication.class, args);
		Subject.addObserver(new EvasioneObserver(()-> EndPointRestNotificheMagazziniere.getNotificheEvasione()));
		Subject.addObserver(new SmaltimentoObserver(()-> EndPointRestNotificheMagazziniere.getSerialiDaSmaltire()));
		Subject.addObserver(new RisultatiRicercaGlobaleObserver( () -> EndPointRestNotificheOperatore.getNotificaRisultatiRicerca() ));
		//andrebbe un get
		Subject.addObserver(new SaccheInScadenzaObserver(()->SaccheInScadenzaClientEndPoint.notificheSaccheInScadenza));
		SaccheInScadenzaClientEndPoint saccheInScadenzaClient = new SaccheInScadenzaClientEndPoint();
	}


}
