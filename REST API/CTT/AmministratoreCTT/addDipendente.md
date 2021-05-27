# aggiuntaDipendente

 Metodo che aggiunge un dipendente al database CTT
 <br>
 
 

* **URL**

        /rest/amministratore/aggiuntaDipendente

* **Method:**
  

        POST 
  

 

* **Data Params**

        @FormParam cdf String formato [CCCCCCNNCNNCNNNC] C: char[A-Z] && N: int[1-9] 
        @FormParam nome String
        @FormParam cognome String 
        @FormParam dataDiNascita String formato [yyyy-MM-dd]
        @FormParam ruolo uno tra [AmministratoreCTT, OperatoreCTT, MagazziniereCTT]
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
  
        curl -H "Authorization: Basic Wi61vJbx9AL_IsAZ0Vf7XYYA5bwQ3lHX" -d "cdf=MRORSS89Y63M830P" -d "nome=Mario" -d "cognome=Rossi" -d "dataDiNascita=1998-11-11" -d "ruolo=AmministratoreCTT" -d "username=Amministratore12" -d "password=115mag2021" -X POST http://127.0.0.1:8080/rest/amministratore/aggiuntaDipendente

