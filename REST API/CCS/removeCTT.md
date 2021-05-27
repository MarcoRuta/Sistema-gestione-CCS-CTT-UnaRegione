# removeCTT

  Metodo che rimuove un CTT dal database del CCS

* **URL**

        /rest/amministratore/rimozioneCTT

* **Method:**
  
        DELETE
  
*  **URL Params**

        @PathParam numero String intero compreso tra [0-999]
   

* **Success Response:**
  
  il CTT è stato trovato e rimosso correttamente dal database

        Code: 200 OK
        Content: "CTT numero " + numero + " rimosso correttamente"
 
* **Error Response:**
  
  non è stato possibile trovare il dipendente all'interno del database

        Code: 400 BAD REQUEST
        Content: "Rimozione del Dipendente: " + cdf+" non riuscita\n"+Exception.getMessage()


* **Sample Call:**

        -X DELETE http://localhost:8080/rest/amministratore/removeCTT?numero=12