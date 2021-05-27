# addDipendente

 Metodo che aggiunge un amministratoreCCS al database CCS
 <br>
 
 

* **URL**

        /rest/amministratore/aggiuntaAmministratore

* **Method:**
  

        POST 
  

 

* **Data Params**

        @FormParam cdf String formato [CCCCCCNNCNNCNNNC] C: char[A-Z] && N: int[1-9] 
        @FormParam nome String
        @FormParam cognome String 
        @FormParam dataDiNascita String formato [yyyy-MM-dd]
        @FormParam ruolo String [AmministratoreCCS]
        @FormParam username String 
        @FormParam password String [minimo di 8 caratteri di cui un numero]

* **Success Response:**
  
     il dipendente è stato creato e aggiunto correttamente al database

      Code: 201 CREATE
      Content: "Corretta aggiunta del Dipendente: "+cdf
 
* **Error Response:**

  Errore dovuto ad un inserimento errato dei dati

      Code: 400 BAD REQUEST 
      Content: AssertionError.getMessage() dell'eccezione che si è sollevata

* **Sample Call:**
  
       curl -d "nome="Mario"
                &cognome="Rossi"
                &dataDiNascita="1998-11-8"
                &ruolo="AmministratoreCCS"
                &username="Amministratore115"
                &password="115mag2021" 

       -X POST http://localhost:8080/rest/amministratore/aggiuntaAmministratore

