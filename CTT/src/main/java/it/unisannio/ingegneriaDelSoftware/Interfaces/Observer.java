package it.unisannio.ingegneriaDelSoftware.Interfaces;

public interface Observer {
	
	/**Aggiorna le notifiche di ogni sessione, ogni volta che cambia lo stato
	 * @param notifica
	 */
    void update(Notifica notifica);
}