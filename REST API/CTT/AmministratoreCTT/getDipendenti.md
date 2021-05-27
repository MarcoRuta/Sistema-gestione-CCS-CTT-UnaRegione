# getDipendenti
----
Metodo che ritorna la lista di tutti i dependenti che lavorano all'interno del singolo CTT 

* **URL**

        /rest/amministratore/dipendenti

* **Method:**
  
        GET
  

* **Success Response:**
  
    La lista dei dipendenti

        Code: 200 OK
        Content: La lista dei dipendenti presenti nel CTT

* **Sample Call:**

        -X GET http://localhost:8080/rest/amministratore/dipendenti