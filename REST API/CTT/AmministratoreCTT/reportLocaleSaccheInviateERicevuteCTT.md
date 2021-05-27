# reportLocaleSaccheInviateERicevute

Metodo che ritorna la lista di tutte le sacche che sono arrivate o hanno lasciato il magazzino in un determinato arco temporale

* **URL**

        /rest/amministratore/reportOperatoriCtt

* **Method:**
  
        GET
  
*  **URL Params**

        @QueryParam dataInizio String formato [yyyy-MM-dd]
        @QueryParam dataFine String formato [yyyy-MM-dd]

* **Success Response:**
  
    La lista delle sacche trovate

        Code: 200 OK
        Content: La lista delle sacche trovate

* **Sample Call:**

        -X GET http://localhost:8080/rest/magazziniere/reportLocaleSaccheInviateERicevute?dataInizio=2020-01-01&dataFine=2021-01-01
       