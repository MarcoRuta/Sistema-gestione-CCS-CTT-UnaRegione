package it.unisannio.ingegneriaDelSoftware.Interfaces;


import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface EndPointAmministratoreCCS {
	
	Response addCTT(@FormParam("numero") String numero,
		   		    @FormParam("denominazione") String denominazione,
		   	        @FormParam("provincia") String provincia,
		            @FormParam("citta") String citta, 
			        @FormParam("indirizzo") String indirizzo,
			        @FormParam("telefono") String telefono, 
				    @FormParam("email") String email,
				    @FormParam("latitude") String latitudine,
				    @FormParam("longitude") String longitudine);
		
	Response removeCTT(@PathParam("numero") String numero);	

	

}
