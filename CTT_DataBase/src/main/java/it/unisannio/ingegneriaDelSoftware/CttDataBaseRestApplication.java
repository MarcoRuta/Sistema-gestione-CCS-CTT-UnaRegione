package it.unisannio.ingegneriaDelSoftware;


import it.unisannio.ingegneriaDelSoftware.EndPointRest.*;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutorizzazione;
import it.unisannio.ingegneriaDelSoftware.Filtri.FiltroDiAutentificazione;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import MosquitoNotificationTry.NotificaEvasioneSubscriber;

import javax.ws.rs.ApplicationPath;

@SpringBootApplication (scanBasePackages = {"WebSocketConfig"})
@ApplicationPath("/rest")
public class CttDataBaseRestApplication extends ResourceConfig {

	public CttDataBaseRestApplication() throws Exception {

		NotificaEvasioneSubscriber sub = new NotificaEvasioneSubscriber("tcp://127.0.0.1:1883");
		sub.subscribe();
		
		//Configurazione
		//Filtro per autorizzazzione
		register(FiltroDiAutorizzazione.class);
		//Filtro per Authentificazione
		register (FiltroDiAutentificazione.class);

		//Endpoint del Magazziniere
		register(EndPointRestMagazziniereCTT.class);
		//EndPoint per il login
		register(EndPointRestAutentificazione.class);
		register(EndPointLogout.class);
		//Endpoint Amministratore
		register(EndPointRestAmministratoreCTT.class);
		//Endpoint Operatore
		register(EndPointRestOperatoreCTT.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CttDataBaseRestApplication.class, args);
		
	}
	
}
