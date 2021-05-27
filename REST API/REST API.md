

# REST API 

## Autentificazione 

Metodi necessari per autentificarsi con il sistema

[login](#login)

[logout](#logout)

[cambioPassword](#cambioPassword)


## CTT

Metodi presenti sull'interfaccia rest dell'operatoreCTT

[ricercaSaccaLocale](#ricercaSaccaLocale)

Metodi presenti sull'interfaccia rest del MagazziniereCTT

[aggiuntaSaccaMagazzino](#aggiuntaSaccaMagazzino)

[evasioneSacca](#evasioneSacca)

Metodi presenti sull'interfaccia rest dell'amministratore CTT

[addDipendente](#addDipendente)

[removeDipendente](#removeDipendente)

[getDipendenti](#getDipendenti)

[ordinaGruppiSanguigniPerRichiesta](#ordinaGruppiSanguigniPerRichiesta)

[reportLocaleSaccheInviateERicevute](#reportLocaleSaccheInviateERicevute)

[reportOperatoriCTT](#reportOperatoriCTT)

[reportStatisticoSacche](#reportStatisticoSacche)

## CCS

Metodi presenti sull'interfaccia rest dell'AmministratoreCCS

[addCTT](#addCTT)

[removeCTT](#removeCTT)

[addDipendente](#addDipendente)

[removeDipendente](#removeDipendente)




# login

  Metodo che permette ad un user generico di accedere alla piattaforma CTT/CCS

* **URL**

        /rest/autentificazione

* **Method:**
  
        POST
  
*  **URL Params**

        @FormParam username String username
        @FormParam password String password formato almeno 8 caratteri di cui almeni 1 numerico
   

* **Success Response:**
  

        Code: 200 OK
        Content: L'utente loggato correttamente
 
* **Error Response:**

        Code: 403 FORBIDDEN
        Content: username o password errati!


* **Sample Call:**
        
        curl -d "username="Admin1"
                &password="APqu8792"
                
        -X POST http://localhost:8080/rest/autentificazione

# logOut

  Metodo che permette ad un user generico di uscire dalla piattaforma CTT/CCS

* **URL**

        /rest/autentificazione/logout

* **Method:**
  
        DELETE
  
*  **URL Params**

        @HeaderParam(HttpHeaders.AUTHORIZATION) String header
   

* **Success Response:**
  

        Code: 200 OK
        Content: Token rimosso correttamente
 
* **Error Response:**

        Code: 400 BAD_REQUEST
        Content: Impossibile rimuovere il Token,  + Eccezione


* **Sample Call:**

        -X DELETE http://localhost:8080/rest/autentificazione/logout

# cambioPassword

  Metodo che permette ad un user generico di cambiare la password di accesso alla piattaforma CTT/CCS

* **URL**

        /rest/autentificazione/cambiopassword/DCFSDF34F45E564R

* **Method:**
  
        PUT
  
*  **URL Params**

        @PathParam("cdf")String cdf formato [CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9] 

*  **Required:**
 
         String password formato almeno 8 caratteri di cui almeno 1 numerico 
   

* **Success Response:**
  
        Code: 200 OK
        Content: Password cambiata correttamente
 
* **Error Response:**

        Code: 400 BAD_REQUEST
        Content: Dipendente non trovato


* **Sample Call:**
        
        -X DELETE http://localhost:8080/rest/autentificazione/cambiopassword/DCFSDF34F45E564R

# ricercaSaccaLocale

Metodo che ritorna una lista di sacche ricercate dall'operatore del CTT 

* **URL**

        /rest/operatore/ricerca

* **Method:**
  
       GET
  
*  **URL Params**

        @QueryParam gruppoSanguigno String gruppoSanguigno uno tra [Ap,Am,Bp,Bm,ZEROp,ZEROm,ABp,ABm]
        @QueryParam numeroSacche String numeroSacche
		@QueryParam dataArrivoMassima String dataArrivoMassima formato [yyyy-MM-dd]
		@QueryParam enteRichiedente String enteRichiedente
		@QueryParam indirizzoEnte String indirizzoEnte
		@QueryParam priorità String priorita uno tra [TRUE,FALSE]

* **Success Response:**
  
    La richiesta è stata completamente soddisfatta in locale l

        Code: 200 OK
        Content: La lista delle sacche trovate
 
* **Error Response:**

    Non è stato possibile soddisfare completamente la ricerca

        Code: 404 NOT_FOUND
        Content: Non è stato possibile soddisfare completamente la richiesta in locale, verrà contattato il CCS

        OR

        Code: 400 BAD_REQUEST
        Content: Argomenti passati in modo errato

* **Sample Call:**

        curl -d gruppoSanguigno="Ap"
                &numeroSacche="5"
                &dataArrivoMassima="2021-07-12"
                &enteRichiedente="Ospedale Rummo"
                &indirizzoEnte="Benevento, via Pacevecchia 118"
                &priorità="TRUE"
 




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


# addDipendente

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
  
       curl -d "nome="Mario"
                &cognome="Rossi"
                &dataDiNascita="1998-11-8"
                &ruolo="AmministratoreCTT"
                &username="Amministratore12"
                &password="115mag2021" 

       -X POST http://localhost:8080/rest/amministratore/aggiuntaDipendente


# removeDipendente


  Metodo che rimuove un dipendente dal database del CTT

* **URL**

        /rest/amministratore/removeDipendente

* **Method:**
  
        DELETE
  
*  **URL Params**

        @PathParam cdf String formato [CCCCCCNNCNNCNNNC] C: char[A-Z] && N: int[1-9] 
   

* **Success Response:**
  
  il dipendente è stato trovato e rimosso correttamente al database

        Code: 200 OK
        Content: "Corretta rimozione del Dipendente: " + cdf
 
* **Error Response:**
  
  non è stato possibile trovare il dipendente all'interno del database

        Code: 404 NOT_FOUND
        Content: "Rimozione del Dipendente: " + cdf+" non riuscita\n"+Exception.getMessage()


* **Sample Call:**

        -X DELETE http://localhost:8080/rest/amministratore/removeDipendente?cdf=RSSMRO62C61B519Y


# getDipendenti

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

# ordinaGruppiSanguigniPerRichieste

Metodo utilizzato dall'amministratoreCTT per ricevere un feedback sul numero di richieste di sacche ricevute per tipo ogni tipo selezionato

* **URL**

        /rest/amministratore/ordinaGruppiSanguigniPerRichieste

* **Method:**
  
        GET
  
*  **URL Params**

        @QueryParam listaGS String listaGS a scelta multipla tra[Ap,Am,Bp,Bm,ZEROp,ZEROm,ABp,ABm]
	@QueryParam dataInizio String dataInizio formato [yyyy-MM-dd]
	@QueryParam dataFine String dataFine formato [yyyy-MM-dd]

* **Success Response:**
  
    La richiesta è andata a buon fine e viene restituita la lista dei gruppi sanguigni ordinati per il numero di richieste ricevute nell'arco temporale

        Code: 200 OK
        Content: La lista dei gruppi sanguigni ordinati per richiesta

* **Error Response:**

        Code: 400 BAD_REQUEST
        Content: Errore nel formato dei dati inseriti

* **Sample Call:**

        -X POST http://localhost:8080/rest/amministratore/ordinaGruppiSanguigniPerRichieste?listaGS=Ap,Bp,ABp,&dataInizio=2020-11-05&dataFine=2021-11-05
 

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

**removeDipendente**
----
  Metodo che rimuove un AmministratoreCCS dal database del CCS

* **URL**

        /rest/amministratore/removeAmministratore

* **Method:**
  
        DELETE
  
*  **URL Params**

        @PathParam cdf String formato [CCCCCCNNCNNCNNNC] C: char[A-Z] && N: int[1-9] 
   

* **Success Response:**
  
  il dipendente è stato trovato e rimosso correttamente al database

        Code: 200 OK
        Content: "Corretta rimozione del Dipendente: " + cdf
 
* **Error Response:**
  
  non è stato possibile trovare il dipendente all'interno del database

        Code: 404 NOT_FOUND
        Content: "Rimozione del Dipendente: " + cdf+" non riuscita\n"+Exception.getMessage()


* **Sample Call:**

        -X DELETE http://localhost:8080/rest/amministratore/removeDipendente?cdf=RSSMRO62C61B519Y