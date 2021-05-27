# removeDipendente

  Metodo che rimuove un dipendente dal database del CTT

* **URL**

        /rest/amministratore/removeDipendente

* **Method:**
  
        DELETE
  
*  **URL Params**

        @PathParam cdf String formato [CCCCCCNNCNNCNNNC] C: char[A-Z] && N: int[1-9] 
   

* **Success Response:**
  
  il dipendente è stato trovato e rimosso correttamente al database

        Code: 200 OK
        Content: "Corretta rimozione del Dipendente: " + cdf
 
* **Error Response:**
  
  non è stato possibile trovare il dipendente all'interno del database

        Code: 404 NOT_FOUND
        Content: "Rimozione del Dipendente: " + cdf+" non riuscita\n"+Exception.getMessage()


* **Sample Call:**

        -X DELETE http://localhost:8080/rest/amministratore/removeDipendente?cdf=RSSMRO62C61B519Y