# reportOperatoriCTT

Metodo che ritorna una lista di dipendenti filtrati per ruolo 

* **URL**

        /rest/amministratore/reportOperatoriCtt

* **Method:**
  
        GET
  
*  **URL Params**

        @QueryParam ruolo String ruolo uno tra [MagazziniereCTT,OperatoreCTT,AmministratoreCTT]

* **Success Response:**
  
    La lista di dipendenti trovati

        Code: 200 OK
        Content: La lista di dipendenti trovati

* **Sample Call:**

        -X GET http://localhost:8080/rest/amministratore/reportOperatoriCtt?ruolo=OperatoreCTT
 