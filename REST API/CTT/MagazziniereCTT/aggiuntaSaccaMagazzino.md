# aggiuntaSaccaMagazzino

 Metodo che aggiunge una sacca al database CTT, presente nell' MagazziniereRestEndpoint
 <br>

 

* **URL**

      /rest/magazziniere/aggiuntaSacca

* **Method:**
  

      POST 
  

 

* **Data Params**

       @FormParam gruppo_sanguigno String uno tra [Ap,Am,Bp,Bm,ABp,ABm,ZEROp,ZEROm]
       @FormParam data_scadenza String formato [yyyy-MM-dd]
       @FormParam data_produzione String formato [yyyy-MM-dd]
       @FormParam ente_donatore String

* **Success Response:**
  
  La sacca è stata creata e aggiunta correttamente al database

      Code: 201 CREATE
      Content: "Sacca con seriale " + *SERIALE GENERATO DAL SISTEMA* + " aggiunta correttamente"
 
* **Error Response:**

  Errore dovuto ad un inserimento errato dei dati

      Code: 400 BAD REQUEST 
      Content: AssertionError.getMessage() dell'eccezione che si è sollevata

  OR

      Code: 500 INTERNAL SERVER ERROR
      Content: "Non è stato possibile creare l'uri per la nuova risorsa"

* **Sample Call:**
  
       curl -d "gruppo_sanguigno="Ap"
                &data_scadenza="2025-11-8"
                &data_produzione="2020-11-8"
                &ente_donatore="Avis Benevento" 

       -X POST http://localhost:8080/rest/magazziniere/aggiuntaSacca

