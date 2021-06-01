package it.unisannio.ingegneriaDelSoftware.Functional;

import java.util.ArrayList;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Classes.CTTName;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.*;

public class CCS implements CCSFunction{
	
	/*Restituisce il CTT più vicino al CTT identificato tramite numero
	 * @param num Numero del CTT dato
	 * @return CTT più vicino a quello selezionato
	 */
	public CTT CttPiuVicino(CTTName cttName) throws EntityNotFoundException {
		MongoDataManager mm = MongoDataManager.getInstance();
		
		CTT c = mm.getCTT(cttName);
		List<CTT> listaCTT = mm.getListaCTT();		
		List<CTT> listaCTTOrdinata = new ArrayList<CTT>();

		for (CTT ctt : listaCTT) if (!ctt.equals(c)) listaCTTOrdinata.add(ctt);     
		
		CTT min = listaCTTOrdinata.get(0);
		
		for (CTT ctt : listaCTTOrdinata) if(min.distanzaDalCtt(c) > ctt.distanzaDalCtt(c)) min = ctt;
		
		return min;
	}
}