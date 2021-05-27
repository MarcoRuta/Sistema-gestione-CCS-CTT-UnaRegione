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
 