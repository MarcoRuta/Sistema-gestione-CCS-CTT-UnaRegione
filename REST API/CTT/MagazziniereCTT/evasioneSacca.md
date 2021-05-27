# evasioneSacca

 Metodo che elimina N sacche dal database ed aggiorna i loro dati nello storico
 <br>

 

* **URL**

      /rest/magazziniere/evasione

* **Method:**
  

        PUT 
  

 

* **Data Params**

       @FormParam listaSeriali String nel formato [SERIALE,SERIALE,SERIALE,...SERIALE,] dove seriale è una stringa nel formato [CTTNNN-NNNNNNNN] con N
       @FormParam enteRichiedente String 
       @FormParam indirizzoEnte String 

* **Success Response:**
  
  L'ordine è stato evaso correttamente, si prepara il PDF da stampare per la consegna

      Code: 201 CREATE
      Content: etichetta + "\nEnte richiedente: " + ente_richiedente + "\nIndirizzo: " + indirizzo 

      dove etichetta è una stringa contenente tutti i dati relativi alle sacche evase
 
* **Error Response:**

  Errore dovuto ad un inserimento errato dei dati (ad esempio un seriale non registrato)

      Code: 400 BAD REQUEST 
      Content: AssertionError.getMessage() dell'eccezione che si è sollevata


* **Sample Call:**
  
       curl -d "listaSeriali="CTT001-00000001,CTT001-00000002,CTT001-00000003,"
                &enteRichiedente="Ospedale Rummo"
                &indirizzoEnte="Benevento, Via Pacevecchia 16"

       -X PUT http://localhost:8080/rest/magazziniere/aggiuntaSacca

