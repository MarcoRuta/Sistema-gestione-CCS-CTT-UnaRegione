

# REST API 

## Autentificazione 

Metodi necessari per autentificarsi con il sistemaCTT/CCS

[login](#login)

[logOut](#logOut)

[cambioPassword](#cambioPassword)

[recuperoPassword](#recuperoPassword)


## CTT

Metodi presenti sull'interfaccia rest dell'OperatoreCTT

[ricercaSaccaLocale](#ricercaSaccaLocale)

[ricercaSaccheCCS](#ricercaSaccheCCS)

[prenotaSaccaAlert](#prenotaSaccaAlert)

[notifyOperatore](#notifyOperatore)

Metodi presenti sull'interfaccia rest del MagazziniereCTT

[inoltroNotificaEvasione](#inoltroNotificaEvasione)

[getPDF()](#getPDF())

[aggiuntaSaccaMagazzino](#aggiuntaSaccaMagazzino)

[evasioneSacca](#evasioneSacca)

[getPDF(@PathParam)](#getPDF(@PathParam))

Metodi presenti sull'interfaccia rest dell'AmministratoreCTT

[getPDF](#getPDF)

[addDipendente](#addDipendente)

[removeDipendente](#removeDipendente)

[reportDipedentiCTT](#reportDipendentiCTT)

[reportStatisticoSacche](#reportStatisticoSacche)

[reportLocaleSaccheInviate](#reportLocaleSaccheInviate)

[reportLocaleSaccheRicevute](#reportLocaleSaccheRicevute)

[giacenzaMedia](#giacenzaMedia)

[getDipendenti](#getDipendenti)

## CCS

Metodi presenti sull'interfaccia rest dell'AmministratoreCCS

[ricercaSaccaGlobale](#ricercaSaccaGlobale)

[aggiungiSaccaInScadenza](#aggiungiSaccaInScadenza)

[prenotaSacca](#prenotaSacca)

[ritiroAlertCTTSacca](#ritiroAlertCTTSacca)

[addCTT](#addCTT)

[removeCTT](#removeCTT)

[listaCTT](#listaCTT)

[addAmministratore](#addAmministratore)

[getPDF](#getPDF)

[removeAmministratore](#removeAmministratore)

[getAmministratori](#getAmministratori)

[reportDipendentiCCS](#reportDipendentiCCS)

[reportStatisticoSaccheRegionale](#reportStatisticoSaccheRegionale)

[reportSaccheInviate](#reportSaccheInviate)

[reportSaccheRicevute](#reportSaccheRicevute)

[giacenzaMediaCCS](#giacenzaMediaCCS)

# login

  Metodo che permette ad un generico user di accedere alla piattaforma CTT/CCS

* **URL**

        /rest/autentificazione

* **Method:**
  
        POST
  
*  **URL Params**

        @FormParam username String username
        @FormParam password String password (almeno 8 caratteri di cui almeno 1 numerico)
   
**Required:**
 
         String password formata da almeno 8 caratteri di cui almeno 1 numerico.

* **Success Response:**
        
        Ritorna l'utente loggato correttamente
        
        Code: 201 CREATED 
 
* **Error Response:**

        Code: 404 NOT_FOUND
        Content: Username o password sbagliati

# logOut

  Metodo che permette ad un generico user  di uscire dalla piattaforma CTT/CCS

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

        Code: 404 NOT_FOUND
        Content: Impossibile rimuovere il Token

# cambioPassword

  Metodo che permette ad un generico user  di cambiare la password di accesso alla piattaforma CTT/CCS

* **URL**

        /rest/autentificazione/cambiopassword/DCFSDF34F45E564R

* **Method:**
  
        PUT
  
*  **URL Params**

       @HeaderParam(HttpHeaders.AUTHORIZATION) String header,@PathParam("cdf")String cdf, String password

*  **Required:**
 
         String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]
         String password formata da almeno 8 caratteri di cui almeno 1 numerico.
   
* **Success Response:**
  
        Code: 200 OK
        Content: Password cambiata correttamente
 
* **Error Response:**

        Code: 400 BAD_REQUEST
        Content: Formato password errato

        OR

        Code: 404 NOT_FOUND
        Content: Formato password errato

# recuperoPassword

  Metodo che permette ad un generico user  di recuperare la propria password di accesso alla piattaforma CTT/CCS

* **URL**

        /rest/autentificazione/recuperoPassword/DCFSDF34F45E564R

* **Method:**
  
        PUT
  
*  **URL Params**

       @PathParam("cdf")String cdf, String username

*  **Required:**
 
         String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9].
   
* **Success Response:**
  
        Code: 200 OK
        Content: La tua nuova password è: +nuovaPassword
 
* **Error Response:**

        Code: 400 BAD_REQUEST
        Content: Formato password errato

        OR

        Code: 404 NOT_FOUND
        Content: Formato password errato

# ricercaSaccaLocale

Metodo che ritorna una lista di sacche ricercate dall'OperatoreCTT 

* **URL**

        /rest/operatore/ricerca

* **Method:**
  
       GET
  
*  **URL Params**

        @QueryParam gruppoSanguigno String gruppoSanguigno 
        @QueryParam numeroSacche String numeroSacche
		@QueryParam dataArrivoMassima String dataArrivoMassima 
		@QueryParam enteRichiedente String enteRichiedente
		@QueryParam indirizzoEnte String indirizzoEnte
		@QueryParam priorità String priorita

 * **Required:**
 
         String gruppoSanguigno uno tra [Ap,Am,Bp,Bm,ZEROp,ZEROm,ABp,ABm]
         String dataArrivoMassima nel formato [yyyy-MM-dd]
         String priorita uno tra [TRUE,FALSE]
* **Success Response:**
  
        Code: 200 OK
        Content: La richiesta è stata soddisfatta completamente in locale. Una volta confermata l'evasione il magazziniere sarà notificato.
 
* **Error Response:**

        Code: 404 NOT_FOUND
        Content: Localmente non è stata trovata nessuna sacca.Inoltriamo la richiesta al CCS.

        OR

        Code: 404 NOT_FOUND
        Content: Localmente non è stata trovata nessuna sacca.Non posso contattare il CCS perchè è offline.

        OR

        Code: 206 PARTIAL_CONTENT
        Content: La richiesta non è stata soddisfatta completamente in locale.Verrà contattato il CCS.

        OR

        Code: 206 PARTIAL_CONTENT
        Content: La richiesta non è stata soddisfatta completamente in locale.CCS è offline quindi non è possibile effettuare una ricerca GLOBALE.Se conferma l'evasione verrà notificato il magazziniere di evadere comunque le sacche trovate in locale.

        OR

        Code: 500 INTERNAL_SERVER_ERROR
        Content: Non è stato possibile ripetere la richiesta per allinearsi con lo stato del CCS.Contattare il CCS per risolvere il problema.
 
# ricercaSaccheCCS

Metodo che ritorna una lista di sacche ricercate dall'OperatoreCTT dopo una richiesta proveniente dal CCS

* **URL**

        /rest/operatore/listaSaccheCompatibili/ZEROm/2021-06-22

* **Method:**
  
       GET
  
*  **URL Params**

        @PathParam("gs") String gruppoSanguigno,
        @PathParam("data") String dataArrivoMassima

 * **Required:**
 
         String gruppoSanguigno uno tra [Ap,Am,Bp,Bm,ZEROp,ZEROm,ABp,ABm]
         String dataArrivoMassima nel formato [yyyy-MM-dd]

* **Success Response:**
  
        Code: 200 OK
        Content: Lista delle sacche trovate

# prenotaSaccaAlert

Metodo che permette di prenotare delle sacche in scadenza provenienti da altri CTT sul terminale dell'Operatore, in seguito ad un alert inviato dal CCS

* **URL**

        /rest/operatore/prenotaSaccaInScadenza/CTT001-00000001, CTT002-00000022

* **Method:**
  
       POST
  
*  **URL Params**

        @PathParam("seriale") String seriale,
        @FormParam("indirizzoEnte") String indirizzoEnte,
        @FormParam("enteRichiedente")String enteRichiedente

 * **Required:**
 
         String seriale nel seguente formato: 15 caratteri [CTT+numeroCTT(max 3 interi)+'-'+numeroSacca(8 interi)].

* **Success Response:**
  
        Rimuove la notifica della sacca in scadenza sui terminali degli operatoriCTT

# notifyOperatore

Metodo che notifica sul terminale dell'OperatoreCTT il risultato della ricerca globale

* **URL**

        /rest/notificheOperatore/risultatiRicercaGlobale

* **Method:**
  
       POST

* **Data Param:**

        NotificaRisultatiRicerca risultatiRicerca

* **Success Response:**
  
        Notifica risultati ricerca inoltrata correttamente al terminale operatore con sessione.
 
* **Error Response:**

        Impossibile inoltrare al'operatore con sessione i risultati della ricerca

        OR

        Impossibile  serializzare la lista delle sacche trovate online con sessione.

# inoltroNotificaEvasione

 Metodo che aggiunge una notifica alla lista delle notifiche delle sacche da evadere da parte del MagazziniereCTT dopo una richiesta da parte dell'OperatoreCTT
 
* **URL**

      /rest/notifica/notificaEvasione

* **Method:**
  
      POST 
  
* **Data Param**

      NotificaEvasione notificaEvasione

* **Success Response:**
  
  Compare una notifica sul terminale del MagazziniereCTT
 
* **Error Response:**

      Code: 404 NOT_FOUND La notifica della sacca da evadere non è stata trovata

# getPDF()

 Metodo che genera il PDF con la lista delle sacche scadute da smaltire da parte del MagazziniereCTT
 
* **URL**

      /rest/notifica/smaltimento/pdf

* **Method:**
  
      GET
  
* **Data Param**

      OutputStream output

* **Success Response:**
  
  Viene ritornato il pdf generato
 
* **Error Response:**

      Code: 500 INTERNAL_SERVER_ERROR Impossibile recuperare il PDF per lo smaltimento

# aggiuntaSaccaMagazzino

 Metodo che aggiunge una sacca al database CTT, presente nel MagazziniereRestEndpoint

* **URL**

      /rest/magazziniere/aggiuntaSacca

* **Method:**
  
      POST 
  
* **URL Params**

       @FormParam("gruppo_sanguigno") String gruppo_sanguigno,
       @FormParam("data_scadenza") String data_scadenza,   @FormParam("data_produzione") String data_produzione,
       @FormParam("ente_donatore") String ente_donatore
       @Context UriInfo uriInfo

* **Required:**
 
         String gruppo_sanguigno uno tra [Ap,Am,Bp,Bm,ZEROp,ZEROm,ABp,ABm]
         String data_scadenza nel formato [yyyy-MM-dd]
         String data_produzione nel formato [yyyy-MM-dd]
* **Success Response:**
  
  La sacca è stata creata e aggiunta correttamente al database

      Code: 201 CREATED
      Content: "Sacca con seriale " + *SERIALE GENERATO DAL SISTEMA* + " aggiunta correttamente"
 
* **Error Response:**

      Code: 400 BAD REQUEST 
      Content: Impossibile aggiungere una sacca scaduta

      OR

      Code: 400 BAD_REQUEST
      Content: Sacca già esistente

# evasioneSacca

 Metodo che riceve una notifica evasione sacca e rimuove la sacca dal database delle sacche, aggiornando i dati sacca. Inoltre crea il PDF con i dati dell'evasione della sacca

* **URL**

      /rest/magazziniere/evasione

* **Method:**
  
        POST 
  
* **Data Params**

        NotificaEvasione notificaEvasione,
        @Context UriInfo uriInfo

* **Success Response:**
  
  L'ordine è stato evaso correttamente, si prepara il PDF da stampare per la consegna

      Code: 201 CREATED
      Content: Sacche evase correttamente
* **Error Response:**

  Errore dovuto ad un inserimento errato dei dati (ad esempio un seriale non registrato)

      Code: 404 NOT_FOUND 
      Content: Sacca da evadere non trovata

# getPDF(@PathParam)

 Metodo che permette di ottenere i dati di una evasione sottoforma di PDF.

* **URL**

      /rest/magazziniere/evasione/pdf/{id_evasione}

* **Method:**
  
        GET 
  
* **Data Params**

        @PathParam("id_evasione")String id_evasione

* **Success Response:**
        
        Il PDF viene creato correttamente e visualizzato su una nuova finestra.
         
* **Error Response:**

  Errore dovuto ad un inserimento errato dei dati (ad esempio un seriale non registrato)

      Code: 404 NOT_FOUND 
      Content: Evasione non trovata

      OR

      Code : 500 INTERNAL_SERVER_ERROR
      Content: Impossibile creare il PDF per l'evasione con id +id_evasione

# getPDF()

 Metodo che genera il PDF con la lista delle sacche scadute da smaltire da parte del MagazziniereCTT
 
* **URL**

      /rest/amministratore/aggiuntaDipendente/pdf/DCFSDF34F45E564R

* **Method:**
  
      GET
  
* **URL Param**

      @PathParam("cdf")String cdf

*  **Required:**
 
         String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]

* **Success Response:**
  
   Viene ritornato il pdf generato
 
* **Error Response:**

      Code: 500 INTERNAL_SERVER_ERROR 
      Content: Impossibile creare il dipendente

      OR

      Code: 404 NOT_FOUND
      Content: Impossibile creare il dipendente

# addDipendente

 Metodo che aggiunge un dipendente al database CTT

* **URL**

        /rest/amministratore/aggiuntaDipendente

* **Method:**
  
        POST 

* **Data Params**

        @FormParam("cdf")String cdf,
        @FormParam("nome")String nome,
        @FormParam("cognome")String cognome,
        @FormParam("dataDiNascita")String dataDiNascita
        @FormParam("ruolo")String ruolo,
        @FormParam("username")String username,
        @Context UriInfo uriInfo

*  **Required:**
 
         String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]
         String dataDiNascita nel formato [yyyy-MM-dd]
         String ruolo uno tra [AmministratoreCTT,OperatoreCTT,MagazziniereCTT]

* **Success Response:**
  
      Code: 201 CREATED
      Content: Dipendente aggiunto correttamente
 
* **Error Response:**

      Code: 400 BAD REQUEST 
      Content: Formato data errato

      OR

      Code: 400 BAD REQUEST 
      Content: Formato dati errati

      OR

      Code: 400 BAD REQUEST 
      Content: Condizioni sui dati non rispettate 

      OR 

      Code: 400 BAD REQUEST 
      Content: Dipendente già presente nel database

# removeDipendente

  Metodo che rimuove un dipendente dal database del CTT

* **URL**

        /rest/amministratore/rimozioneDipendente/DCFSDF34F45E564R

* **Method:**
  
        DELETE
  
*  **URL Params**

       @HeaderParam(HttpHeaders.AUTHORIZATION)String header
       @PathParam("cdf") String cdf
   
*  **Required:**
 
         String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]

* **Success Response:**
  
        Code: 200 OK
        Content: "Corretta rimozione del Dipendente: + cdf
 
* **Error Response:**
  
        Code: 404 NOT_FOUND
        Content: Dipendente da rimuovere non trovato

        OR

        Code: 403 FORBIDDEN
        Content: Non puoi cancellare te stesso

# reportOperatoriCTT

  Metodo che restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto

* **URL**

        /rest/amministratore/reportOperatoriCtt

* **Method:**
  
        GET
  
*  **URL Params**

       @QueryParam("ruolo")String ruolo
   
*  **Required:**
 
        String ruolo uno tra [AmministratoreCTT,OperatoreCTT,MagazziniereCTT]

* **Success Response:**
  
        Code: 200 OK
        Content: Query elaborata con successo

# reportStatisticoSacche

  Metodo che restituisce il numero di sacche presenti di ogni tipo nel database

* **URL**

        /rest/amministratore/reportStatisticoSacche

* **Method:**
  
        GET
  
*  **URL Params**

       @HeaderParam(HttpHeaders.AUTHORIZATION) String headers

* **Success Response:**
  
        Code: 200 OK
        Content: Query elaborata con successo

# reportLocaleSaccheInviate

  Metodo che restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale

* **URL**

        /rest/amministratore/reportLocaleSaccheInviate

* **Method:**
  
        GET
  
*  **URL Params**

       @QueryParam("dataInizio")String dataInizio,
       @QueryParam("dataFine")String dataFine

 * **Required:**
 
         String dataInizio nel formato [yyyy-MM-dd]
         String dataFine nel formato [yyyy-MM-dd]

* **Success Response:**
  
        Code: 200 OK
        Content: Query elaborata con successo
 
 * **Error Response:**

      Code: 400 BAD_REQUEST
      Content: Formato data errato

# reportLocaleSaccheRicevute

  Metodo che restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale

* **URL**

        /rest/amministratore/reportLocaleSaccheRicevute

* **Method:**
  
        GET
  
*  **URL Params**

       @QueryParam("dataInizio")String dataInizio,
       @QueryParam("dataFine")String dataFine

 * **Required:**
 
         String dataInizio nel formato [yyyy-MM-dd]
         String dataFine nel formato [yyyy-MM-dd]

* **Success Response:**
  
        Code: 200 OK
        Content: Query elaborata con successo
 
 * **Error Response:**

      Code: 400 BAD_REQUEST
      Content: Formato data errato

# giacenzaMedia

  Metodo che calcola quanto è il tempo medio di giacenza delle sacche di sangue all'interno del magazzino

* **URL**

        /rest/amministratore/permanenzaMediaSacche

* **Method:**
  
        GET

* **Success Response:**
  
        Code: 200 OK
        Content: Query elaborata con successo

# getDipendenti

Metodo che ritorna la lista di tutti i dependenti che lavorano all'interno del CTT 

* **URL**

        /rest/amministratore/dipendenti

* **Method:**
  
        GET

* **URL Param**

        @HeaderParam(HttpHeaders.AUTHORIZATION) String header

* **Success Response:**
  
    La lista dei dipendenti

* **Error Response**

        Code: 404 NOT_FOUND
        Content: Dipendenti non trovati

# ricercaSaccaGlobale

Metodo che le sacche di sacche del gruppo sanguigno richiesto cercando nei database dei vari CTT

* **URL**

        /rest/CCS/ricercaGlobale

* **Method:**
  
       GET
  
*  **URL Params**

        @QueryParam("nome") String nome,
        @QueryParam("gruppo") String gruppoSanguigno,
        @QueryParam("numero") String numeroSacche,
        @QueryParam("dataArrivoMassima") String dataArrivoMassima,
        @QueryParam("enteRichiedente") String enteRichiedente,
        @QueryParam("indirizzoEnte") String indirizzoEnte,
        @QueryParam("priorità") String priorita

 * **Required:**
 
         String gruppoSanguigno uno tra [Ap,Am,Bp,Bm,ZEROp,ZEROm,ABp,ABm]
         String dataArrivoMassima nel formato [yyyy-MM-dd]
         String priorita uno tra [TRUE,FALSE]
* **Success Response:**
  
        Code: 204 NO_CONTENT
        Content:Esito ricerca globale: completata totalmente.

        OR

        Code: 204 NO_CONTENT
        Content: Esito ricerca globale: completata parzialmente. Mancano: + numeroSaccheMancanti.

* **Error Response:**

        Code: 404 NOT_FOUND
        Content: Nessuna sacca trovata presso i CTT della rete CCS

        OR

        Code: 404 NOT_FOUND
        Content: Entity not found

        Code: 500 INTERNAL_SERVER_ERROR
        Content: Non è stato possibile ripetere la richiesta per allinearsi con lo stato del CCS.Contattare il CCS per risolvere il problema.

# aggiungiSaccaInScadenza

Metodo che aggiunge al database delle sacche in scadenza una lista di sacche in scadenza a partire da quelle presenti nei database dei vari CTT

* **URL**

        /rest/CCS/saccheInScadenza

* **Method:**
  
       POST
  
*  **Data Param**

        Sacca[] listaSaccheInScadenza

* **Success Response:**
  
        Sacca aggiunta correttamente al DB

* **Error Response**

        Code: 400 BAD_REQUEST
        Content: Sacca già presente nel database

# prenotaSacca

Metodo che accetta una delle sacche in scadenza presenti nella rete. L'iterazione del metodo termina con una richiesta di evasione presso il CTT che possiede tale sacca e una notifica di conferma per il CTT che l'ha richiesta.
	 

* **URL**

        /rest/CCS/prenotaSaccaInScadenza/CTT001-00000021

* **Method:**
  
       GET
  
*  **URL Params**

        @PathParam("seriale") String seriale,
        @QueryParam("enteRichiedente") String ente_richiedente,
        @QueryParam("indirizzoEnte") String indirizzo

 * **Required:**
 
         String seriale nel seguente formato: 15 caratteri [CTT+numeroCTT(max 3 interi)+'-'+numeroSacca(8 interi)].

* **Success Response:**
  
        Code: 200 OK
        Content:Sacca In Scadenza Prenotata Correttamente.

* **Error Response:**

        Code: 404 NOT_FOUND
        Content: Entity not found

# ritiroAlertCTTSacca

Metodo che accetta il seriale di una sacca, precedentemente inviata al CCS perchè in scadenza, successivamente prenotata e quindi da rimuovere dalla lista.
	
* **URL**

        /rest/CCS/ritiroAlertCTT/CTT002-00000009

* **Method:**
  
       GET
  
*  **URL Params**

        @PathParam("seriale") String seriale

 * **Required:**
 
         String seriale nel seguente formato: 15 caratteri [CTT+numeroCTT(max 3 interi)+'-'+numeroSacca(8 interi)].

* **Success Response:**
  
        Code: 200 Ok
        Content:Alert Ritirato.

* **Error Response:**

        Code: 404 NOT_FOUND
        Content: Entity not found

 # addCTT

 Metodo che aggiunge un CTT al database CCS

* **URL**

        /rest/CCS/aggiuntaCTT

* **Method:**

        POST 
  
* **URL Params**

        @FormParam("provincia") String provincia,
        @FormParam("citta") String citta,
        @FormParam("indirizzo") String indirizzo,
        @FormParam("telefono") String telefono,
        @FormParam("email") String email,
        @FormParam("latitude") String latitudine,
        @FormParam("longitude") String longitudine,
        @Context UriInfo uriInfo

* **Success Response:**

      Code: 200 OK
      Content: CTT +nomeCTT+ aggiunto correttamente"
 
* **Error Response:**

      Code: 500 INTERNAL SERVER ERROR
      Content: CTT già presente nel database

# removeCTT

  Metodo che rimuove un CTT dal database del CCS

* **URL**

        /rest/CCS/rimozioneCTT/CTT001

* **Method:**
  
        DELETE
  
*  **URL Params**

        @PathParam("cttName") String cttName
   
* **Success Response:**
  
        Code: 200 OK
        Content: CTT  +nomeCTT+ rimosso correttamente
 
* **Error Response:**
 
        Code: 404 NOT_FOUND
        Content: CTT non presente nel database

        OR
        
        Code: 400 BAD_REQUEST
        Content: Dati inseriti non corretti       

# listaCTT

  Metodo che restituisce la lista di tutti i CTT presenti nel database del CCS
	 
* **URL**

        /rest/CCS/centers

* **Method:**
  
        GET
   
* **Success Response:**
  
        La lista dei CTT presenti nel database

# addAmministratore

 Metodo che aggiunge un amministratoreCCS al database CCS

* **URL**

        /rest/CCS/aggiuntaAmministratore

* **Method:**

        POST 
  
* **URL Params**

        @FormParam("cdf")String cdf,
        @FormParam("nome")String nome,
        @FormParam("cognome")String cognome,
        @FormParam("dataDiNascita")String dataDiNascita,
        @FormParam("username")String username,
        @Context UriInfo uriInfo

* **Required**

        String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]
        String dataDiNascita nel formato [yyyy-MM-dd]

* **Success Response:**
  
      Code: 201 CREATED
      Content: Dipendente aggiunto correttamente
 
* **Error Response:**

      Code: 400 BAD_REQUEST 
      Content: Formato data sbagliato

      OR

      Code: 400 BAD_REQUEST 
      Content: dati non corretti

      OR

      Code: 400 BAD_REQUEST 
      Content: Formato cdf non corretto

      OR

      Code: 500 INTERNAL_SERVER_ERROR 
      Content: Dipendente già presente nel database.

# getPDF

 Metodo che recupera un pdf con cdf, username e password di un dipendente

* **URL**

        /rest/CCS/aggiuntaAmministratore/pdf/DCFSDF34F45E564R

* **Method:**

        GET 
  
* **URL Param**

        @PathParam("cdf")String cdf

* **Required**

        String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]

* **Success Response:**
  
       Viene ritornato il pdf generato
 
* **Error Response:**

      Code: 404 NOT_FOUND 
      Content: Impossibile creare il dipendente

      OR

      Code: 500 INTERNAL_SERVER_ERROR 
      Content: Impossibile creare il dipendente

**removeAmministratore**

  Metodo che rimuove un AmministratoreCCS dal database dei dipendenti

* **URL**

        /rest/CCS/rimozioneAmministratore/DCFSDF34F45E564R

* **Method:**
  
        DELETE
  
*  **URL Params**

        HttpHeaders.AUTHORIZATION)String header,
        @PathParam("cdf") String cdf 

* **Required**

        String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]
   
* **Success Response:**
  
        Code: 200 OK
        Content: Corretta rimozione del Dipendente + cdf.
 
* **Error Response:**
  
        Code: 403 FORBIDDEN
        Content: Non puoi cancellare te stesso

        OR

        Code: 404 NOT_FOUND
        Content: Entity not found

# getAmministratori

Metodo che ritorna la lista di tutti i dipendenti che lavorano all'interno del CTT 

* **URL**

        /rest/CCS/amministratori

* **Method:**
  
        GET

* **URL Param**

        @HeaderParam(HttpHeaders.AUTHORIZATION) String header

* **Success Response:**
  
    La lista dei dipendenti

* **Error Response**

        Code: 404 NOT_FOUND
        Content: Dipendenti non trovati

reportDipendentiCCS](#reportDipendentiCCS)

[reportStatisticoSaccheRegionale](#reportStatisticoSaccheRegionale)

[reportSaccheInviate](#reportSaccheInviate)

[reportSaccheRicevute](#reportSaccheRicevute)

[giacenzaMediaCCS](#giacenzaMediaCCS)

# reportDipendentiCCS

  Metodo che restituisce la lista di tutti i dipendenti dei CTT che occupano il ruolo scelto

* **URL**

        /rest/CCS/reportDipendentiCCS

* **Method:**
  
        GET
  
*  **URL Param**

       @QueryParam("ruolo")String ruolo
   
*  **Required:**
 
        String ruolo uno tra [AmministratoreCTT,OperatoreCTT,MagazziniereCTT]

* **Success Response:**
  
        Code: 200 OK
        Content: Query elaborata con successo

# reportStatisticoSacche

  Metodo che restituisce il numero di sacche presenti di ogni tipo nella regione

* **URL**

        /rest/CCS/reportStatisticoSaccheCCS

* **Method:**
  
        GET
  
*  **URL Params**

       @HeaderParam(HttpHeaders.AUTHORIZATION) String headers

* **Success Response:**
  
        Code: 200 OK
        Content: Query elaborata con successo

# reportSaccheInviate

  Metodo che restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale

* **URL**

        /rest/CCS/reportSaccheInviateCCS

* **Method:**
  
        GET
  
*  **URL Params**

       @QueryParam("dataInizio")String dataInizio,
       @QueryParam("dataFine")String dataFine

 * **Required:**
 
         String dataInizio nel formato [yyyy-MM-dd]
         String dataFine nel formato [yyyy-MM-dd]

* **Success Response:**
  
        Code: 200 OK
        Content: Query elaborata con successo
 
 * **Error Response:**

      Code: 400 BAD_REQUEST
      Content: Formato data errato

# reportSaccheRicevute

  Metodo che restituisce la lista dei DatiSacche relativi alle sacche che sono state ricevute dalla rete in un determinato arco temporale

* **URL**

        /rest/CCS/reportSaccheRicevuteCCS

* **Method:**
  
        GET
  
*  **URL Params**

       @QueryParam("dataInizio")String dataInizio,
       @QueryParam("dataFine")String dataFine

 * **Required:**
 
         String dataInizio nel formato [yyyy-MM-dd]
         String dataFine nel formato [yyyy-MM-dd]

* **Success Response:**
  
        Code: 200 OK
        Content: Query elaborata con successo
 
 * **Error Response:**

      Code: 400 BAD_REQUEST
      Content: Formato data errato

# giacenzaMediaCCS

  Metodo che calcola quanto è il tempo medio di giacenza delle sacche di sangue all'interno dei vari magazzini dei CTT

* **URL**

        /rest/CCS/giacenzaMediaCCS

* **Method:**
  
        GET

* **Success Response:**
  
        Code: 200 OK
        Content: Query elaborata con successo.