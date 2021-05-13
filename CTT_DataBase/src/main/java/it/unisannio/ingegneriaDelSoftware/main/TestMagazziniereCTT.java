package it.unisannio.ingegneriaDelSoftware.main;

import java.io.PrintStream;
import java.time.LocalDate;

import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyMagazziniereCTTDataManager;

public class TestMagazziniereCTT {

	public static void main(String[] args) {
    	PrintStream ps = new PrintStream(System.out);
    	MyMagazziniereCTTDataManager magazz = new MyMagazziniereCTTDataManager();

//TEST LOGIN    	
    	String username = "username 007";
    	String password = "007";
    	if(magazz.login(username, password)) ps.println("Login effettuato");
    	else ps.println("Login fallito");
    	
//CREAZIONE SACCA DA AGGIUNGERE  
    	GruppoSanguigno gs = GruppoSanguigno.Ap;
    	LocalDate localDataProduzione = LocalDate.of(2023,04,10);
    	LocalDate localDataScadenza = LocalDate.of(2021,12,12);
    	Boolean prenotato = false;
    	Sacca s = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	s.print(ps);
    	
//TEST AGGIUNTA SACCA AL MAGAZZINO  
    	String enteDonatore = "Donato Re";
    	magazz.aggiuntaSaccaMagazzino(s, enteDonatore);
   	  	
//TEST EVASIONE SACCA   	
    	String enteRichiedente = "nuovo richiedente";
    	LocalDate dataAffidamento = LocalDate.of(2022, 11, 30);   	
    	magazz.evasioneSacca(s, enteRichiedente, dataAffidamento);
	
	}
}
