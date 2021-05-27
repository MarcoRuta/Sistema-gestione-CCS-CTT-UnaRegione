# addCTT

 Metodo che aggiunge un CTT al database CCS
 <br>
 
 

* **URL**

        /rest/amministratore/aggiuntaCTT

* **Method:**
  

        POST 
  

 

* **Data Params**

        @FormParam numero_ctt String intero compreso tra [0-999]
        @FormParam nome_ctt String nel formato [CTTXXX] dove XXX è il numero_ctt inserito
        @FormParam provincia String 
        @FormParam citta String 
        @FormParam indirizzo String 
        @FormParam telefono String 
        @FormParam email String 
        @FormParam latitude String [formato double da -180 a 180]
        @FormParam longitude String [formato double da -90 a 90]

* **Success Response:**
  
     il CTT è stato creato e aggiunto correttamente al database

      Code: 200 OK
      Content: "CTT numero " + ctt.getNumero() + " aggiunto correttamente"
 
* **Error Response:**

  Errore dovuto ad un inserimento errato dei dati

      Code: 400 BAD REQUEST 
      Content: AssertionError.getMessage() dell'eccezione che si è sollevata

      OR

      Code: 500 INTERNAL SERVER ERROR
      Content: "Non è stato possibile creare l'uri per la nuova risorsa"


* **Sample Call:**
  
       curl -d "numero_ctt="113"
                &nome_ctt="CTT113"
                &provincia="Benevento"
                &citta="Benevento"
                &indirizzo="Via delle puglie 15"
                &telefono="0824311643" 
                &email="ctt113@reteGestioneCCS.it" 
                &latitude="41,1305" 
                &longitude="14,717" 


       -X POST http://localhost:8080/rest/amministratore/aggiuntaCTT

