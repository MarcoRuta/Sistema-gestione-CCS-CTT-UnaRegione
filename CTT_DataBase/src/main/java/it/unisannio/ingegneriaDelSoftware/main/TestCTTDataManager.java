package it.unisannio.ingegneriaDelSoftware.main;

import java.io.PrintStream;
import java.time.LocalDate;

import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyCTTDataManager;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyMagazziniereCTTDataManager;

public class TestCTTDataManager {

	public static void main(String[] args) {
		MyMagazziniereCTTDataManager magazz = new MyMagazziniereCTTDataManager();
		PrintStream ps = new PrintStream(System.out);
		MyCTTDataManager ctt = new MyCTTDataManager();
		
		GruppoSanguigno gs = GruppoSanguigno.Ap;
    	LocalDate localDataProduzione = LocalDate.of(2020,05,10);
    	LocalDate localDataScadenza = LocalDate.of(2020,10,10);
    	Boolean prenotato = false;
    	Sacca sacca = new Sacca(gs, localDataProduzione, localDataScadenza, prenotato);
    	String enteDonatore = "Donatore della sacca";
    	magazz.aggiuntaSaccaMagazzino(sacca, enteDonatore);
//TEST RIMOZIONE SACCA SCADUTA  	
    	ctt.removeSaccaScaduta(sacca);   	
		
//TEST ALERT		
		for(Sacca s : ctt.alertControlScadenza()) {
			s.print(ps);
		}
		
	}
}
