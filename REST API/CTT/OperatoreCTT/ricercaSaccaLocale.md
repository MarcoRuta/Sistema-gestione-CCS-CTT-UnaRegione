# ricercaSaccaLocale

Metodo che ritorna una lista di sacche ricercate dall'operatore del CTT 

* **URL**

        /rest/operatore/ricerca

* **Method:**
  
       GET
  
*  **URL Params**

        @QueryParam gruppoSanguigno String gruppoSanguigno uno tra [Ap,Am,Bp,Bm,ZEROp,ZEROm,ABp,ABm]
        @QueryParam numeroSacche String numeroSacche
		@QueryParam dataArrivoMassima String dataArrivoMassima formato [yyyy-MM-dd]
		@QueryParam enteRichiedente String enteRichiedente
		@QueryParam indirizzoEnte String indirizzoEnte
		@QueryParam priorità String priorita uno tra [TRUE,FALSE]

* **Success Response:**
  
    La richiesta è stata completamente soddisfatta in locale l

        Code: 200 OK
        Content: La lista delle sacche trovate
 
* **Error Response:**

    Non è stato possibile soddisfare completamente la ricerca

        Code: 404 NOT_FOUND
        Content: Non è stato possibile soddisfare completamente la richiesta in locale, verrà contattato il CCS

        OR

        Code: 400 BAD_REQUEST
        Content: Argomenti passati in modo errato

* **Sample Call:**

        curl -d gruppoSanguigno="Ap"
                &numeroSacche="5"
                &dataArrivoMassima="2021-07-12"
                &enteRichiedente="Ospedale Rummo"
                &indirizzoEnte="Benevento, via Pacevecchia 118"
                &priorità="TRUE"
 