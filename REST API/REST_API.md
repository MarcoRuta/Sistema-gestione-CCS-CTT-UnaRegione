# REST API 

## Autentificazione 

Metodi necessari per autentificarsi con il SistemaCTT/CCS

[login](#login)

[logOut](#logOut)

[cambioPassword](#cambioPassword)

[recuperoPassword](#recuperoPassword)

## CTT

Metodi presenti sull'interfaccia REST dell'OperatoreCTT

[ricercaSaccaLocale](#ricercaSaccaLocale)

[ricercaSaccheCCS](#ricercaSaccheCCS)

[prenotaSaccaAlert](#prenotaSaccaAlert)

[notifyOperatore](#notifyOperatore)

Metodi presenti sull'interfaccia REST del MagazziniereCTT

[inoltroNotificaEvasione](#inoltroNotificaEvasione)

[getPDF(smaltimento)](#getPDF(smaltimento))

[aggiuntaSaccaMagazzino](#aggiuntaSaccaMagazzino)

[evasioneSacca](#evasioneSacca)

[getPDF(evasione)](#getPDF(evasione))

Metodi presenti sull'interfaccia REST dell'AmministratoreCTT

[getPDF(aggiuntaDipendenteCTT)](#getPDF(aggiuntaDipendenteCTT))

[addDipendente](#addDipendente)

[removeDipendente](#removeDipendente)

[reportDipendentiCTT](#reportDipendentiCTT)

[reportStatisticoSacche](#reportStatisticoSacche)

[reportLocaleSaccheInviate](#reportLocaleSaccheInviate)

[reportLocaleSaccheRicevute](#reportLocaleSaccheRicevute)

[giacenzaMedia](#giacenzaMedia)

[getDipendenti](#getDipendenti)

## CCS

Metodi presenti sulle interfacce REST del CCS

[ricercaSaccaGlobale](#ricercaSaccaGlobale)

[prenotaSacca](#prenotaSacca)

[ritiroAlertCTTSacca](#ritiroAlertCTTSacca)

Metodi presenti sull'interfaccia REST dell'AmministratoreCCS

[addCTT](#addCTT)

[removeCTT](#removeCTT)

[listaCTT](#listaCTT)

[addAmministratore](#addAmministratore)

[getPDF(aggiuntaAmministratoreCCS)](#getPDF(aggiuntaAmministratoreCCS))

[removeAmministratore](#removeAmministratore)

[getAmministratori](#getAmministratori)

[reportDipendentiCCS](#reportDipendentiCCS)

[reportStatisticoSaccheRegionale](#reportStatisticoSaccheRegionale)

[reportSaccheInviate](#reportSaccheInviate)

[reportSaccheRicevute](#reportSaccheRicevute)

[giacenzaMediaSaccheRegionale](#giacenzaMediaSaccheRegionale)

[statusReteCtt](#statusReteCtt)

# login

        Metodo che permette ad un generico user di accedere alla piattaforma CTT/CCS

* **URL:**

        /rest/autentificazione

* **Method:**
  
        POST
  
*  **URL Params:**

        @FormParam username String username
        @FormParam password String password
   
* **Required:**
 
        String password formata da almeno 8 caratteri di cui almeno 1 numerico.

* **Success Response:**
        
        Ritorna l'utente loggato correttamente
        
        Code: 201 CREATED 
 
* **Error Response:**

        Code: 404 NOT_FOUND
        Content: Username o password sbagliati

# logOut

  Metodo che permette ad un generico user  di uscire dalla piattaforma CTT/CCS

* **URL:**

        /rest/autentificazione/logout

* **Method:**
  
        DELETE
  
*  **URL Params:**

        @HeaderParam(HttpHeaders.AUTHORIZATION) String header
   

* **Success Response:**
  
        Code: 200 OK
        Content: Token rimosso correttamente
 
* **Error Response:**

        Code: 404 NOT_FOUND
        Content: Impossibile rimuovere il Token

# cambioPassword

  Metodo che permette ad un generico user  di cambiare la password di accesso alla piattaforma CTT/CCS

* **URL:**

        /rest/autentificazione/cambiopassword/{cdf}

* **Method:**
  
        PUT
  
*  **URL Params:**

       @HeaderParam(HttpHeaders.AUTHORIZATION) String header,
       @PathParam("cdf")String cdf, String password

*  **Required:**
 
         String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]
         String password formata da almeno 8 caratteri di cui almeno 1 numerico.
   
* **Success Response:**
  
        Code: 200 OK
        Content: Password cambiata correttamente
 
* **Error Response:**

        Code: 400 BAD_REQUEST
        Content: Formato dati errato

        OR

        Code: 404 NOT_FOUND
        Content: Utente non trovato 

# recuperoPassword

  Metodo che permette ad un generico user  di recuperare la propria password di accesso alla piattaforma CTT/CCS

* **URL:**

        /rest/autentificazione/recuperoPassword/{cdf}

* **Method:**
  
        PUT
  
*  **URL Params:**

       @PathParam("cdf")String cdf, String username

*  **Required:**
 
         String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9].
   
* **Success Response:**
  
        Code: 200 OK
        Content: La tua nuova password è: +nuovaPassword
 
* **Error Response:**

        Code: 404 NOT_FOUND
        Content: Utente non trovato

        OR 

        Code: 403 FORBIDDEN
        Content: Username e codice fiscale non coincidono

# ricercaSaccaLocale

Metodo che ritorna una lista di sacche ricercate dall'OperatoreCTT 

* **URL:**

        /rest/operatore/ricerca

* **Method:**
  
       GET
  
*  **URL Params:**

        @QueryParam gruppoSanguigno String gruppoSanguigno 
        @QueryParam numeroSacche String numeroSacche
		@QueryParam dataArrivoMassima String dataArrivoMassima 
		@QueryParam enteRichiedente String enteRichiedente
		@QueryParam indirizzoEnte String indirizzoEnte
		@QueryParam priorità String priorita

 * **Required:**
 
         String gruppoSanguigno uno tra [Ap,Am,Bp,Bm,ABp,ABm,ZEROp,ZEROm]
         String dataArrivoMassima nel formato [yyyy-MM-dd]
         String priorita uno tra [TRUE,FALSE]

* **Success Response:**
  
        Code: 200 OK
        Content: La richiesta è stata soddisfatta completamente in locale. Una volta confermata l'evasione il magazziniere sarà notificato.
 
* **Error Response:**

        Code: 404 NOT_FOUND
        Content: Localmente non è stata trovata nessuna sacca.Inoltro la richiesta al CCS.

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
        Content: Non è stato possibile completare la richiesta per allinearsi con lo stato del CCS.Contattare il CCS per risolvere il problema.
 
# ricercaSaccheCCS

Metodo che ritorna una lista di sacche ricercate dall'OperatoreCTT dopo una richiesta proveniente dal CCS

* **URL:**

        /rest/operatore/listaSaccheCompatibili/{gs}/{data}

* **Method:**
  
       GET
  
*  **URL Params:**

        @PathParam("gs") String gruppoSanguigno,
        @PathParam("data") String dataArrivoMassima

 * **Required:**
 
         String gruppoSanguigno uno tra [Ap,Am,Bp,Bm,ABp,ABm,ZEROp,ZEROm]
         String dataArrivoMassima nel formato [yyyy-MM-dd]

* **Success Response:**
  
        Code: 200 OK
        Content: Lista delle sacche trovate

# prenotaSaccaAlert

Metodo che permette di prenotare delle sacche in scadenza provenienti da altri CTT sul terminale dell'Operatore, in seguito ad un alert inviato dal CCS. Rimuove la notifica della sacca in scadenza sui terminali degli operatoriCTT

* **URL:**

        /rest/operatore/prenotaSaccaInScadenza/{seriale}
* **Method:**
  
       POST
  
*  **URL Params:**

        @PathParam("seriale") String seriale,
        @FormParam("indirizzoEnte") String indirizzoEnte,
        @FormParam("enteRichiedente")String enteRichiedente

 * **Required:**
 
         String seriale nel seguente formato: 15 caratteri ['CTT'+numeroCTT(max 3 interi)+'-'+numeroSacca(8 interi)].

* **Success Response:**
  
        Code: 204 NO_CONTENT

# notifyOperatore

Metodo che notifica sul terminale dell'OperatoreCTT il risultato della ricerca globale

* **URL:**

        /rest/notificheOperatore/risultatiRicercaGlobale

* **Method:**
  
       POST

* **Data Params:**

        NotificaRisultatiRicerca risultatiRicerca

* **Success Response:**
  
        Notifica risultati ricerca inoltrata correttamente al terminale operatore con sessione "id sessione".
 
* **Error Response:**

        Impossibile inoltrare all'operatore con sessione "id sessione" i risultati della ricerca

        OR

        Impossibile serializzare la lista delle sacche trovate online con sessione "id sessione".

# inoltroNotificaEvasione

 Metodo che aggiunge una notifica alla lista delle notifiche delle sacche da evadere da parte del MagazziniereCTT dopo una richiesta da parte dell'OperatoreCTT
 
* **URL:**

      /rest/notifica/notificaEvasione

* **Method:**
  
      POST 
  
* **Data Params**

      NotificaEvasione notificaEvasione

* **Success Response:**
  
        Compare una notifica sul terminale del MagazziniereCTT
 
* **Error Response:**

        La notifica della sacca da evadere non è stata trovata.

        Code: 404 NOT_FOUND 

# getPDF(smaltimento)

 Metodo che genera il PDF con la lista delle sacche scadute da smaltire da parte del MagazziniereCTT
 
* **URL:**

      /rest/notifica/smaltimento/pdf

* **Method:**
  
      GET

* **Success Response:**
  
        Viene ritornato lo StreamingOutput da dove verrà aperto il pdf generato
 
* **Error Response:**

      Code: 500 INTERNAL_SERVER_ERROR
      Content: Impossibile recuperare il PDF per lo smaltimento

# aggiuntaSaccaMagazzino

 Metodo che aggiunge una sacca al database CTT, presente nell' EndpointRestMagazziniere

* **URL:**

      /rest/magazziniere/aggiuntaSacca

* **Method:**
  
      POST 
  
* **URL Params:**

       @FormParam("gruppo_sanguigno") String gruppo_sanguigno,
       @FormParam("data_scadenza") String data_scadenza,   
       @FormParam("data_produzione") String data_produzione,
       @FormParam("ente_donatore") String ente_donatore
       @Context UriInfo uriInfo

* **Required:**
 
         String gruppo_sanguigno uno tra [Ap,Am,Bp,Bm,ABp,ABm,ZEROp,ZEROm]
         String data_scadenza nel formato [yyyy-MM-dd]
         String data_produzione nel formato [yyyy-MM-dd]

* **Success Response:**

      Code: 201 CREATED
      Content: "Sacca con seriale " + *SERIALE GENERATO DAL SISTEMA* + " aggiunta correttamente"
 
* **Error Response:**

      Code: 400 BAD REQUEST 
      Content: Impossibile aggiungere una sacca scaduta

      OR

      Code: 400 BAD_REQUEST
      Content: Sacca già esistente nel database

# evasioneSacca

 Metodo che riceve una notifica evasione sacca e rimuove la sacca dal database delle sacche, aggiornando i dati sacca. Inoltre crea il PDF con i dati dell'evasione della sacca

* **URL:**

      /rest/magazziniere/evasione

* **Method:**
  
        POST 
  
* **Data Params**

        NotificaEvasione notificaEvasione,
        @Context UriInfo uriInfo

* **Success Response:**

      Code: 201 CREATED
      Content: Sacche evase correttamente

* **Error Response:**

      Code: 404 NOT_FOUND 
      Content: Sacca da evadere non trovata

# getPDF(evasione)

 Metodo che permette di ottenere i dati di una evasione sotto forma di PDF.

* **URL:**

      /rest/magazziniere/evasione/pdf/{id_evasione}

* **Method:**
  
        GET 
  
* **URL Params:**

        @PathParam("id_evasione")String id_evasione

* **Success Response:**
        
        Viene ritornato lo StreamingOutput da dove verrà aperto il pdf generato
         
* **Error Response:**

      Code: 404 NOT_FOUND 
      Content: Evasione non trovata

      OR

      Code : 500 INTERNAL_SERVER_ERROR
      Content: Impossibile creare il PDF per l'evasione con id "id evasione"

# getPDF(aggiuntaDipendenteCTT)

 Metodo che permette di ottenere i dati di accesso di un dipendente appena aggiunto sotto forma di PDF.
 
* **URL:**

      /rest/amministratore/aggiuntaDipendente/pdf/{cdf}

* **Method:**
  
      GET
  
* **URL Params:**

      @PathParam("cdf") String cdf

*  **Required:**
 
         String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]

* **Success Response:**
  
   Viene ritornato lo StreamingOutput da dove verrà aperto il pdf generato
 
* **Error Response:**

      Code: 500 INTERNAL_SERVER_ERROR 
      Content: Impossibile creare il dipendente

      OR

      Code: 404 NOT_FOUND
      Content: Impossibile creare il dipendente

# addDipendente

 Metodo che aggiunge un dipendente al database CTT

* **URL:**

        /rest/amministratore/aggiuntaDipendente

* **Method:**
  
        POST 

* **URL Params:**

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
      Content: Formato dati errato

      OR

      Code: 400 BAD REQUEST 
      Content: Condizioni sui dati non rispettate 

      OR 

      Code: 400 BAD REQUEST 
      Content: Dipendente già presente nel database

# removeDipendente

  Metodo che rimuove un dipendente dal database del CTT

* **URL:**

        /rest/amministratore/rimozioneDipendente/{cdf}

* **Method:**
  
        DELETE
  
*  **URL Params:**

       @HeaderParam(HttpHeaders.AUTHORIZATION) String header
       @PathParam("cdf") String cdf
   
*  **Required:**
 
         String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]

* **Success Response:**
  
        Code: 200 OK
        Content: Corretta rimozione del Dipendente: "cdf"
 
* **Error Response:**
  
        Code: 404 NOT_FOUND
        Content: Dipendente da rimuovere non trovato

        OR

        Code: 403 FORBIDDEN
        Content: Non puoi cancellare te stesso

# reportDipendentiCtt

  Metodo che restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto

* **URL:**

        /rest/amministratore/reportDipendentiCtt

* **Method:**
  
        GET
  
*  **URL Params:**

       @QueryParam("ruolo") String ruolo
   
*  **Required:**
 
        String ruolo uno tra [AmministratoreCTT,OperatoreCTT,MagazziniereCTT]

* **Success Response:**
  
        Code: 200 OK
        Content: risultatoQuery

# reportStatisticoSacche

  Metodo che restituisce il numero di sacche presenti di ogni tipo nel database

* **URL:**

        /rest/amministratore/reportStatisticoSacche

* **Method:**
  
        GET
  
*  **URL Params:**

       @HeaderParam(HttpHeaders.AUTHORIZATION) String headers

* **Success Response:**
  
        Code: 200 OK
        Content: risultatoQuery

# reportLocaleSaccheInviate

  Metodo che restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale

* **URL:**

        /rest/amministratore/reportLocaleSaccheInviate

* **Method:**
  
        GET
  
*  **URL Params:**

       @QueryParam("dataInizio") String dataInizio,
       @QueryParam("dataFine") String dataFine

 * **Required:**
 
         String dataInizio nel formato [yyyy-MM-dd]
         String dataFine nel formato [yyyy-MM-dd]

* **Success Response:**
  
        Code: 200 OK
        Content: risultatoQuery
 
 * **Error Response:**
  
        Code: 400 BAD_REQUEST
        Content: Formato data errato

# reportLocaleSaccheRicevute

  Metodo che restituisce la lista dei DatiSacche relativi alle sacche che sono state ricevute in un determinato arco temporale

* **URL:**

        /rest/amministratore/reportLocaleSaccheRicevute

* **Method:**
  
        GET
  
*  **URL Params:**

       @QueryParam("dataInizio") String dataInizio,
       @QueryParam("dataFine") String dataFine

 * **Required:**
 
         String dataInizio nel formato [yyyy-MM-dd]
         String dataFine nel formato [yyyy-MM-dd]

* **Success Response:**
  
        Code: 200 OK
        Content: risultatoQuery
 
 * **Error Response:**

        Code: 400 BAD_REQUEST
        Content: Formato data errato

# giacenzaMedia

  Metodo che calcola quanto è il tempo medio di giacenza delle sacche di sangue all'interno del magazzino

* **URL:**

        /rest/amministratore/giacenzaMediaSacche

* **Method:**
  
        GET

* **Success Response:**
  
        Code: 200 OK
        Content: risultatoQuery

 * **Error Response:**

        Code: 400 BAD_REQUEST
        Content: Formato data errato
      
# getDipendenti

Metodo che ritorna la lista di tutti i dipendenti che lavorano all'interno del CTT 

* **URL:**

        /rest/amministratore/dipendenti

* **Method:**
  
        GET

* **URL Params:**

        @HeaderParam(HttpHeaders.AUTHORIZATION) String header

* **Success Response:**
  
    La lista dei dipendenti

* **Error Response**

        Code: 404 NOT_FOUND
        Content: Dipendenti non trovati nel database

# ricercaSaccaGlobale

Metodo che ricerca le sacche del gruppo sanguigno richiesto cercando nei database dei CTT

* **URL:**

        /rest/CCS/ricercaGlobale

* **Method:**
  
       GET
  
*  **URL Params:**

        @QueryParam("nome") String nome,
        @QueryParam("gruppo") String gruppoSanguigno,
        @QueryParam("numero") String numeroSacche,
        @QueryParam("dataArrivoMassima") String dataArrivoMassima,
        @QueryParam("enteRichiedente") String enteRichiedente,
        @QueryParam("indirizzoEnte") String indirizzoEnte,
        @QueryParam("priorità") String priorita

 * **Required:**
 
         String gruppoSanguigno uno tra [Ap,Am,Bp,Bm,ABp,ABm,ZEROp,ZEROm]
         String dataArrivoMassima nel formato [yyyy-MM-dd]
         String priorita uno tra [TRUE,FALSE]
         
* **Success Response:**
  
        Esito ricerca globale: completata totalmente.
        Code: 204 NO_CONTENT

        OR

        Esito ricerca globale: completata parzialmente. Mancano: "numeroSaccheMancanti" sacche.
        Code: 204 NO_CONTENT

* **Error Response:**

        Nessuna sacca trovata presso i CTT della rete CCS.
        Code: 404 NOT_FOUND

        OR

        Code: 404 NOT_FOUND
        Content: Nessun CTT online

        Code: 500 INTERNAL_SERVER_ERROR
        Content: Non è stato possibile completare la richiesta per allinearsi con lo stato del CCS. Contattare il CCS per risolvere il problema.


# prenotaSacca

Metodo che accetta una delle sacche in scadenza presenti nella rete. L'iterazione del metodo termina con una richiesta di evasione presso il CTT che possiede tale sacca e una notifica di conferma per il CTT che l'ha richiesta.
	 

* **URL:**

        /rest/CCS/prenotaSaccaInScadenza/{seriale}

* **Method:**
  
       DELETE
  
*  **URL Params:**

        @PathParam("seriale") String seriale,
        @QueryParam("enteRichiedente") String ente_richiedente,
        @QueryParam("indirizzoEnte") String indirizzo

 * **Required:**
 
         String seriale nel seguente formato: 15 caratteri ['CTT'+numeroCTT(max 3 interi)+'-'+numeroSacca(8 interi)].

* **Success Response:**
  
        Code: 200 OK
        Content: Sacca in scadenza prenotata correttamente

* **Error Response:**

        Code: 404 NOT_FOUND
        Content: Sacca in scadenza non trovata

# ritiroAlertCTTSacca

Metodo che accetta il seriale di una sacca, precedentemente inviata al CCS perchè in scadenza, successivamente prenotata e quindi da rimuovere dalla lista.
	
* **URL:**

        /rest/CCS/ritiroAlertCTT/{seriale}

* **Method:**
  
       GET
  
*  **URL Params:**

        @PathParam("seriale") String seriale

 * **Required:**
 
         String seriale nel seguente formato: 15 caratteri ['CTT'+numeroCTT(max 3 interi)+'-'+numeroSacca(8 interi)].

* **Success Response:**
  
        Code: 200 Ok
        Content: Alert Ritirato.

* **Error Response:**

        Code: 404 NOT_FOUND
        Content: Sacca non trovata nel database

 # addCTT

 Metodo che aggiunge un CTT al database CCS

* **URL:**

        /rest/CCS/aggiuntaCTT

* **Method:**

        POST 
  
* **URL Params:**

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
      Content: "nomeCTT" aggiunto correttamente"
 
* **Error Response:**

      Code: 500 INTERNAL_SERVER_ERROR
      Content: CTT già presente nel database

# removeCTT

  Metodo che rimuove un CTT dal database del CCS

* **URL:**

        /rest/CCS/rimozioneCTT/{cttName}

* **Method:**
  
        DELETE
  
*  **URL Params:**

        @PathParam("cttName") String cttName
   
* **Success Response:**
  
        Code: 200 OK
        Content: "nomeCTT" rimosso correttamente
 
* **Error Response:**
 
        Code: 404 NOT_FOUND
        Content: CTT non presente nel database

        OR
        
        Code: 400 BAD_REQUEST
        Content: Dati inseriti non corretti       

# listaCTT

  Metodo che restituisce la lista di tutti i CTT presenti nel database del CCS
	 
* **URL:**

        /rest/CCS/centers

* **Method:**
  
        GET
   
* **Success Response:**
  
        La lista dei CTT presenti nel database dei CTT

# addAmministratore

 Metodo che aggiunge un amministratoreCCS al database dei dipendenti CCS

* **URL:**

        /rest/CCS/aggiuntaAmministratore

* **Method:**

        POST 
  
* **URL Params:**

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
      Content: Dati non corretti

      OR

      Code: 400 BAD_REQUEST 
      Content: Formato codice fiscale non corretto

      OR

      Code: 500 INTERNAL_SERVER_ERROR 
      Content: Dipendente già presente nel database dei dipendenti.

# getPDF(aggiuntaAmministratoreCCS)

Metodo che permette di ottenere i dati di accesso di un amministratoreCCS appena aggiunto sotto forma di PDF.

* **URL:**

        /rest/CCS/aggiuntaAmministratore/pdf/{cdf}

* **Method:**

        GET 
  
* **URL Params:**

        @PathParam("cdf") String cdf

* **Required**

        String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]

* **Success Response:**
  
        Viene ritornato lo StreamingOutput da dove verrà aperto il pdf generato
 
* **Error Response:**

      Code: 404 NOT_FOUND 
      Content: Impossibile creare il dipendente

      OR

      Code: 500 INTERNAL_SERVER_ERROR 
      Content: Impossibile creare il dipendente

# removeAmministratore

  Metodo che rimuove un AmministratoreCCS dal database dei dipendenti

* **URL:**

        /rest/CCS/rimozioneAmministratore/{cdf}

* **Method:**
  
        DELETE
  
*  **URL Params:**

        HttpHeaders.AUTHORIZATION) String header,
        @PathParam("cdf") String cdf 

* **Required**

        String cdf espressa nel seguente formato:[CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9]
   
* **Success Response:**
  
        Code: 200 OK
        Content: Corretta rimozione del Dipendente "codice fiscale".
 
* **Error Response:**
  
        Code: 403 FORBIDDEN
        Content: Non puoi cancellare te stesso

        OR

        Code: 404 NOT_FOUND
        Content: Dipendente da rimuovere non presente nel database dei dipendenti

# getAmministratori

Metodo che ritorna la lista di tutti i dipendenti che lavorano all'interno del CTT 

* **URL:**

        /rest/CCS/amministratori

* **Method:**
  
        GET

* **URL Params:**

        @HeaderParam(HttpHeaders.AUTHORIZATION) String header

* **Success Response:**
  
        La lista dei dipendenti presenti nel database dei dipendenti

* **Error Response**

        Code: 404 NOT_FOUND
        Content: Non è presente nessun dipendente nel database

# reportDipendentiCCS

  Metodo che restituisce la lista di tutti i dipendenti di tutti i CTT che occupano il ruolo scelto

* **URL:**

        /rest/CCS/reportDipendentiCCS

* **Method:**
  
        GET
  
*  **URL Params:**

       @QueryParam("ruolo") String ruolo
   
*  **Required:**
 
        String ruolo uno tra [AmministratoreCTT,OperatoreCTT,MagazziniereCTT]

* **Success Response:**
  
        Code: 200 OK
        Content: risultatoQuery

# reportStatisticoSaccheRegionale

  Metodo che restituisce il numero di sacche presenti di ogni gruppo sanguigno nella regione

* **URL:**

        /rest/CCS/reportStatisticoSaccheCCS

* **Method:**
  
        GET
  
*  **URL Params:**

       @HeaderParam(HttpHeaders.AUTHORIZATION) String headers

* **Success Response:**
  
        Code: 200 OK
        Content: risultatoQuery

# reportSaccheInviate

  Metodo che restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale

* **URL:**

        /rest/CCS/reportSaccheInviateCCS

* **Method:**
  
        GET
  
*  **URL Params:**

       @QueryParam("dataInizio") String dataInizio,
       @QueryParam("dataFine") String dataFine

 * **Required:**
 
         String dataInizio nel formato [yyyy-MM-dd]
         String dataFine nel formato [yyyy-MM-dd]

* **Success Response:**
  
        Code: 200 OK
        Content: risultatoQuery
 
 * **Error Response:**

      Code: 400 BAD_REQUEST
      Content: Formato data errato

# reportSaccheRicevute

  Metodo che restituisce la lista dei DatiSacche relativi alle sacche che sono state ricevute dalla rete in un determinato arco temporale

* **URL:**

        /rest/CCS/reportSaccheRicevuteCCS

* **Method:**
  
        GET
  
*  **URL Params:**

       @QueryParam("dataInizio") String dataInizio,
       @QueryParam("dataFine") String dataFine

 * **Required:**
 
         String dataInizio nel formato [yyyy-MM-dd]
         String dataFine nel formato [yyyy-MM-dd]

* **Success Response:**
  
        Code: 200 OK
        Content: risultatoQuery
 
 * **Error Response:**

        Code: 400 BAD_REQUEST
        Content: Formato data errato

# giacenzaMediaSaccheRegionale

  Metodo che calcola quanto è il tempo medio di giacenza delle sacche di sangue all'interno dei vari magazzini dei CTT

* **URL:**

        /rest/CCS/giacenzaMediaSaccheCCS

* **Method:**
  
        GET

  **URL Params:**

       @HeaderParam(HttpHeaders.AUTHORIZATION) String headers

* **Success Response:**
  
        Code: 200 OK
        Content: risultatoQuery.

# statusReteCtt

  Metodo che restituisce una mappa di <CTTName,boolean>, true se il CTT è online, altrimenti false

* **URL:**

        /rest/CCS/statusReteCtt

* **Method:**
  
        GET

  **URL Params:**

       @HeaderParam(HttpHeaders.AUTHORIZATION) String headers

* **Success Response:**
  
        Code: 200 OK
        Content: statusRete.