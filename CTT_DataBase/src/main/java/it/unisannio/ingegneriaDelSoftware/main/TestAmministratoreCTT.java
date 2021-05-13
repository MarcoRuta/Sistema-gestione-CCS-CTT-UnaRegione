package it.unisannio.ingegneriaDelSoftware.main;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Util.*;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MyAmministratoreCTTDataManager;

public class TestAmministratoreCTT {

	public static void main(String[] args) throws ParseException {
		MyAmministratoreCTTDataManager amm = new MyAmministratoreCTTDataManager();
		PrintStream ps = new PrintStream(System.out);
				
//TEST LOGIN                
    	if(amm.login("username 008", "008")) ps.println("Login effettuato");
        else ps.println("Login fallito");
        
        if(amm.login("username 007", "007")) ps.println("Login effettuato");
        else ps.println("Login fallito");
        
        if(amm.login("username 008", "005")) ps.println("Login effettuato");
        else ps.println("Login fallito");
   
//TEST REPORTOPERATORI PER RUOLO (OperatoriCTT)			
		RuoloDipendente role = RuoloDipendente.OperatoreCTT;
		List<Dipendente> listone = amm.reportOperatoriCTT(role);
        for(Dipendente dip : listone) {
        	dip.print(ps);
        }
        
//TEST RIMOZIONE DIPENDENTE       
        Cdf cdf = new Cdf("122hfotndj13ht5f");
        amm.removeDipendente(cdf);
        
//STAMPA        
        role = RuoloDipendente.OperatoreCTT;
		listone = amm.reportOperatoriCTT(role);
        for(Dipendente dip : listone) {
        	dip.print(ps);
        }
//TEST QUERY ReportLocaleSaccheInviateERicevuteCTT      
        Date dataInizio = Constants.sdf.parse("01-01-2021");
        Date dataFine = Constants.sdf.parse("31-12-2021");
        
        for(DatiSacca ds : amm.ReportLocaleSaccheInviateERicevuteCTT(dataInizio, dataFine)) {
        	ds.print(ps);
        }
               
//TEST QUERY OrdinaGruppiSanguigniPerRichieste        
        Date dataIniz = Constants.sdf.parse("01-01-2021");
        Date dataFin = Constants.sdf.parse("31-12-2021");
        
        List<GruppoSanguigno> lista = new ArrayList<GruppoSanguigno>();
        lista.add(GruppoSanguigno.Am);
        lista.add(GruppoSanguigno.Bm);
        
        ps.print(amm.OrdinaGruppiSanguigniPerRichieste(lista, dataIniz, dataFin));
	}
}
