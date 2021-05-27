# reportStatisticoSacche

Metodo che ritorna la lista di tutte le sacche di un determinato gruppo sanguigno presenti nel database

* **URL**

        /rest/amministratore/reportOperatoriCtt

* **Method:**
  
        GET
  
*  **URL Params**

        @QueryParam gs String uno tra [Ap,Am,Bp,Bm,ABp,ABm,ZEROp,ZEROm]

* **Success Response:**
  
    La lista delle sacche trovate

        Code: 200 OK
        Content: La lista delle sacche trovate

* **Sample Call:**

        -X GET http://localhost:8080/rest/magazziniere/aggiuntaSacca?gs=Ap
       