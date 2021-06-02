package it.unisannio.ingegneriaDelSoftware.Interfaces;

import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;

import java.time.LocalDate;
import java.util.List;

public interface Searcher {

	/**Ricerca la sacche filtrandole tramite i parametri passati come argomento
     * @param gs Gruppo sanguigno della sacca
     * @param numeroSacche Il quantitativo di sacche che si vuole ricercare
     * @param dataArrivoMassima Specifica la data di arrivo massima entro cui la Sacca deve essere nel nuovo magazzino. Questo in modo da evitare che una Sacca arrivi scaduta a destinazione
     * @return List<Sacca> La lista delle sacche disponibili per quei parametri
     */
    List<Sacca> search(GruppoSanguigno gs, int numeroSacche, LocalDate dataArrivoMassima);
}