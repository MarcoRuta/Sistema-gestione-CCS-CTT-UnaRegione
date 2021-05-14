package it.unisannio.ingegneriaDelSoftware.Interfaces;
import java.util.List;
import it.unisannio.ingegneriaDelSoftware.Classes.CTT;

public interface AmministratoreCCSDataManager {
	
	boolean login(String username, String password);
	
	void addCTT(CTT c);
		
	void removeCTT(int numero);	

	List<CTT> listaDeiCTT();

}
