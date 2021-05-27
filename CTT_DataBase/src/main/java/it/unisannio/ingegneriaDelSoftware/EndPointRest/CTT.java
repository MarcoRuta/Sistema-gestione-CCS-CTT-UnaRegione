package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.*;
import org.apache.tomcat.jni.Local;
;

public class CTT implements CTTFunction {

	private  MongoDataManager md = MongoDataManager.getInstance();

	/**Restituisce una lista di tutte le sacche che scadono entro 72 ore
	 * @return la lista di sacche non ancora scadute ma che scadono entro 72 ore da oggi
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	public List<Sacca> alertControlScadenza() throws SaccaNotFoundException {
		
		List<Sacca> listaSacche = md.getListaSacche();
		List<Sacca> saccheInScadenza = new ArrayList<Sacca>();
		LocalDate oggi = LocalDate.now();
		LocalDate scadenzaTra72ore = oggi.plusDays(3);

		for (Sacca sacca : listaSacche){
			if((sacca.getDataScadenza().isEqual(scadenzaTra72ore) ||sacca.getDataScadenza().minusDays(3).isBefore(oggi))
				&& !(sacca.getDataScadenza().isBefore(oggi))){
				saccheInScadenza.add(sacca);
			}
		}
		return saccheInScadenza;
	}


	/**Rimuove tutte le Sacche scadute dal database delle Sacche e aggiorna i corrispettivi DatiSacca con enteRichiedente "Scaduta" e dataAffidamento con la data di scadenza
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	public void removeSaccheScadute() throws SaccaNotFoundException {
		List<Sacca> listaSacche = md.getListaSacche();

		for(Sacca sacca : listaSacche) {
			if(sacca.getDataScadenza().isBefore(LocalDate.now())) {
				removeSaccaScaduta(sacca);
			}
		}
	}


	/**Rimuove una Sacca dal Database e setta la dataAffidamento di DatiSacca alla data di scadenza e setta l'enteRichiedente con "Scaduta"
	 * @param s Sacca da rimuovere dal database delle Sacche
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	private void removeSaccaScaduta(Sacca s) throws SaccaNotFoundException {

		md.removeSacca(s.getSeriale());
		md.setDataAffidamentoDatiSacca(s.getSeriale(), s.getDataScadenza());
		md.setEnteRichiedenteDatiSacca(s.getSeriale(), "Scaduta");
		md.setIndirizzoEnteDatiSacca(s.getSeriale(), "Scaduta");
	}
}