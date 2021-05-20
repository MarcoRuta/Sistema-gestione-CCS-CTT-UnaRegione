package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.util.ArrayList;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManagerBean;
import it.unisannio.ingegneriaDelSoftware.Exceptions.CTTNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.*;

public class CCS implements CCSFunction{
	
	/*
	 * *Metodo che restituisce il CTT più vicino a quello dato
	 * 
	 * @param num Numero del CTT dato
	 * @return CTT più vicino a quello dato
	 */
	public CTT CttPiùVicino(int num) throws CTTNotFoundException{
		MongoDataManagerBean mmb = new MongoDataManagerBean();
		
		CTT c = mmb.getCTT(num);
		
		List<CTT> listaCTT = mmb.getListaCTT();
		
		List<CTT> listaCTTOrdinata = new ArrayList<CTT>();
		
	
		for (CTT ctt : listaCTT) if (!ctt.equals(c)) listaCTTOrdinata.add(ctt);     
		
		CTT min = listaCTTOrdinata.get(0);
		
		for (CTT ctt : listaCTTOrdinata) if(min.distanzaDalCtt(c) > ctt.distanzaDalCtt(c)) min = ctt;
		
		return min;
	}
	
	
}
