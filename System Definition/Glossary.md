# Glossary

<div style="Height: 35px"></div>

## Actors:
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">AmministratoreCCS:</p>
            </td>
            <td width="900px">
                <p>Per “AmministratoreCCS” si intende la persona che possiede i privilegi necessari per interagire con il TerminaleAmministratoreCCS. Ha il permesso di aggiungere/rimuovere CTT dal sistema, di aggiungere/rimuovere AmministratoriCCS  ed inoltre può ottenere report statistici dal SistemaCCS.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">DipendenteCTT:</p>
            </td>
            <td>
                <p>Per “DipendenteCTT” si intende una qualsiasi persona che lavora presso un CTT. Esso può ricoprire il ruolo di MagazziniereCTT, di OperatoreCTT o di AmministratoreCTT. </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">AmministratoreCTT:</p>
            </td>
            <td>
                <p>Per “AmministratoreCTT” si intende una persona che possiede i privilegi necessari per interagire con il TerminaleAmministratoreCTT. Ha il permesso di aggiungere/rimuovere DipendentiCTT dal sistema e di ottenere report statistici attraverso un’interrogazione del SistemaCTT tramite interfaccia grafica.</p>
            </td>
        </tr>
        <tr>
            <td>
               <p style="font-size: 16px">OperatoreCTT:</p>
            </td>
            <td>
                <p>Per “OperatoreCTT” si intende una persona che possiede i privilegi necessari per interagire con il TerminaleOperatoreCTT. Ha il permesso di accettare le sacche in scadenza in seguito a un alert, ricercare sacche a livello locale se possibile, altrimenti regionale.</p>    
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">MagazziniereCTT:</p>
            </td>
            <td>
                <p>Per “MagazziniereCTT” si intende una persona che possiede i privilegi necessari per interagire con il TerminaleMagazziniereCTT. Ha il permesso di aggiungere e di evadere sacche dal database del proprio CTT e di smaltire le sacche scadute.</p>    
            </td>
        </tr>
        <tr>
            <td>
               <p style="font-size: 16px">Tempo:</p>
            </td>
            <td>
                <p>Per “Tempo” si intende il controllo periodico sulla scadenza delle sacche di sangue nel magazzino.</p>    
            </td>
        </tr>
          <tr>
            <td>
                <p style="font-size: 16px">User:</p>
            </td>
            <td>
                <p>Per “User” si intende una qualsiasi persona che può effettuare il login dal TerminaleUser. A seconda delle credenziali inserite (uniche per ogni User) sarà riconosciuto come:  AmministratoreCCS, AmministratoreCTT, OperatoreCTT o MagazziniereCTT. </p>
            </td>
        </tr>
    </table>
</div>

<div style="height: 30px"></div>

## Terminali:
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">TerminaleUser:</p>
            </td>
            <td width="900px">
                <p>Per “TerminaleUser” si intende il sistema software che consente di eseguire determinate operazioni ad un generico User.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">TerminaleAmministratoreCCS:</p>
            </td>
            <td>
                <p>Per “TerminaleAmministratoreCCS” si intende il sistema software che consente di eseguire determinate operazioni ad un AmministratoreCCS. </p>
            </td>
        </tr>
         <tr>
            <td>
                <p style="font-size: 16px">TerminaleAmministratoreCTT:</p>
            </td>
            <td>
                <p>Per “TerminaleAmministratoreCTT” si intende il sistema software che consente di eseguire determinate operazioni ad un AmministratoreCTT.</p>
            </td>
        </tr>
         <tr>
            <td>
                <p style="font-size: 16px">TerminaleOperatoreCTT</p>
            </td>
            <td>
                <p>Per “TerminaleOperatoreCTT” si intende il sistema software che consente di eseguire determinate operazioni ad un OperatoreCTT. </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">TerminaleMagazziniereCTT:</p>
            </td>
            <td>
                <p>Per “TerminaleMagazziniereCTT”  si intende il sistema software che consente di eseguire determinate operazioni ad un MagazziniereCTT. </p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Generals
<div>
    <table>
     <tr>
            <td width="420px">
                <p style="font-size: 16px">CCS:</p>
            </td>
            <td width="900px">
                <p>Per "CCS" si intende il Centro Controllo e Smistamento, si occupa della gestione centrale dei vari CTT connessi alla rete. In particolare le sue funzioni principali sono: permettere la ricerca delle sacche tra i vari CTT ed evitare che le sacche vadano in scadenza.</p>
            </td>
        </tr>
         <tr>
            <td width="420px">
                <p style="font-size: 16px">CTT:</p>
            </td>
            <td width="900px">
                <p>Per “CTT" si intende il Centro Trasfusionale Territoriale, si occupa della gestione delle sacche di sangue in locale ed in uscita verso altri enti. In particolare le sue funzioni principali sono: l'aggiunta di sacche di sangue al proprio magazzino, rimozione delle sacche (se scadute) e l'invio delle sacche. Esso può essere collegato o meno alla rete dei vari CTT sfruttando il servizio messo a disposizione dal CCS.</p>
            </td>
        </tr>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">Sacca:</p>
            </td>
            <td width="900px">
                <p>Per “Sacca” si intende una generica sacca di sangue utilizzabile per le trasfusioni. La sacca è possibile che sia di gruppo sanguigno A+, A-, B+, B-, AB+, AB-, 0+ o 0-. Ogni sacca ha un seriale assegnato dal primo CTT presso il quale viene depositata, la data di produzione e la data di scadenza assegnate dal centro presso la quale è imbustata e sterilizzata.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">DatiSacca:</p>
            </td>
            <td>
                <p>Per "DatiSacca" si intendono i dati permanenti di una sacca che può essere presente o non presente in magazzino. Includono seriale , gruppo sanguigno, data di affidamento, data di arrivo, ente richiedente con relativo indirizzo e ente donatore.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">Magazzino:</p>
            </td>
            <td>
                <p>Per “Magazzino” si intende la struttura fisica di un CTT dedita alla conservazione delle sacche di sangue.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">Query:</p>
            </td>
            <td>
                <p>Per “Query” si intende una generica interrogazione effettuata sul database del singolo CTT o di tutti i CTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">Button:</p>
            </td>
            <td>
                <p>Per “Button” si intende un generico bottone, elemento dell’interfaccia grafica, che permette la navigazione all’interno delle diverse sezioni del sistema.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## EvasioneSacche:
<div>
    <table>
        <tr>
            <td>
                <p style="font-size: 16px">EvasioneSaccheButton:</p>
            </td>
            <td>
                <p>Per “EvasioneSaccheButton” si intende un bottone presente sul TerminaleMagazziniereCTT. Esso è utilizzato nel momento in cui il MagazziniereCTT riceve notifica per l’evasione di sacche da parte del CCT di cui è dipendente. Cliccandolo viene creata e visualizzata la LetteraDiEvasionePDF.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">LetteraDiEvasionePDF:</p>
            </td>
            <td>
                <p>Per “LetteraDiEvasionePDF” si intende un file .PDF che contiene le seguenti informazioni: il seriale della sacca, l'ente richiedente ed il suo indirizzo.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">EvasioneSaccheControl:</p>
            </td>
            <td>
                <p>Per “EvasioneSaccheControl” si intende un control object che gestisce la funzionalità di EvasioneSacche sul TerminaleMagazziniereCTT. Questo oggetto è creato quando il MagazziniereCTT clicca su EvasioneSaccheButton. Esso crea "LetteraDiEvasionePDF" e lo presenta al MagazziniereCTT. Dopo aver stampato la lettera di evasione, questo oggetto crea una MessaggioCorrettaEvasioneSacche e la inoltra sul TerminaleMagazziniereCTT , aggiorna i DatiSacca aggiungendo l’ente richiedente e la data di arrivo,  ed infine cancella la sacca dal database.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">MessaggioCorrettaEvasioneSacche:</p>
            </td>
            <td>
                <p>Per “MessaggioCorrettaEvasioneSacche” si intende un avviso che il SistemaCTT inoltra sul TerminaleMagazziniereCTT nel momento in cui le sacche sono state evase correttamente. Il messaggio contiene il seriale delle sacche rimosse.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## RicercaSacche:
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">RicercaSaccheButton:</p>
            </td>
            <td width="900px">
                <p>Per “RicercaSaccheButton” si intende un bottone presente sul TerminaleOperatoreCTT. Cliccandolo avvia una procedura di ricerca di sacche di sangue.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">RicercaSaccheControl:</p>
            </td>
            <td>
                <p>Per “RicercaSaccheControl” si intende un control object che gestisce la funzionalità di RicercaSacche sul TerminaleOperatoreCTT. Questo oggetto viene creato quando l’OperatoreCTT clicca su RicercaSaccheButton. Esso crea RicercaSaccheForm e lo presenta all’ OperatoreCTT. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo, crea una QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">RicercaSaccheForm:</p>
            </td>
            <td>
                <p>Per “RicercaSaccheForm” si intende un modulo da compilare con le caratteristiche delle sacche desiderate. Questo form è presentato all’ OperatoreCTT sul TerminaleOperatoreCTT quando clicca RicercaSaccheButton. RicercaSaccheForm contiene tutti i dati necessari (ente richiedente con il proprio indirizzo, gruppo sanguigno, data di arrivo massima, numero di sacche desiderate, stato di priorità) per personalizzare la ricerca.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">QueryResult:</p>
            </td>
            <td>
                <p>Risultato di della query RicercaSacche effettuata da un OperatoreCTT sul magazzino del corrispettivo CTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">NotificaEvasioneManage:</p>
            </td>
            <td>
                <p>Per “NotificaEvasioneManage” si intende un control object che gestisce la funzionalità di EvasioneSacche sul TerminaleMagazziniereCTT. Questo oggetto diventa operativo quando riceve una NotificaEvasioneSacche. Esso mostra la notifica sul TerminaleMagazziniereCTT.</p>
            </td>
        </tr>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">NotificaEvasioneSacche:</p>
            </td>
            <td width="900px">
                <p>Per “NotificaEvasioneSacche” si intende l’oggetto che può essere creato dal CCS nella ricerca globale o dal CTT nella ricerca locale, per informare il MagazziniereCTT del CTT mittente con i dati dell’ente a cui inviare le sacche ed i dati delle sacche da inviare.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">RicercaSaccheGlobaleManage:</p>
            </td>
            <td>
                <p>Per “RicercaSaccheGlobaleManage” si intende un control object che gestisce la funzionalità di RicercaSacche sul SistemaCCS. Questo oggetto viene creato quando QueryResult non contiene il numero richiesto di sacche . Esso inoltra la query precedentemente effettuata in locale a tutti i CTT della rete attraverso interfaccia REST. La ricerca globale delle sacche viene eseguita selezionando quelle con scadenza più vicina. Al termine della ricerca viene creata una NotificaSaccheTrovate (da inoltrare al TerminaleOperatore del CTT richiedente) e una NotificaEvasioneSacche (da  inoltrare al TerminaleMagazziniere del CTT mittente).</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">NotificaRichiestaSacche:</p>
            </td>
            <td>
                <p>Per “NotificaRichiestaSacche” si intende l’oggetto creato dal RicercaSaccheControl quando la ricerca locale non restituisce il numero richiesto di sacche. Una volta creata viene spedita verso il RicercaSaccheGlobaleManage del CCS.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">NotificaRichiestaAltaPrioritàSacche:</p>
            </td>
            <td>
                <p>Per “NotificaRichiestaAltaPrioritàSacche” si intende l’oggetto creato dal RicercaSaccheControl quando la ricerca prioritaria locale non restituisce il numero richiesto di sacche. Una volta creata viene spedita verso il RicercaSaccheGlobaleManage del CCS. </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">NotificaSaccheTrovate:</p>
            </td>
            <td>
                <p>Per “NotificaSaccheTrovate” si intende l'oggetto creato dal RicercaSaccheGlobaleManage al termine della ricerca globale. Viene inviata dal RicercaSaccheGlobaleManage del CCS al TerminaleOperatoreCTT richiedente per avvertirlo che determinate sacche sono state assegnate ed arriveranno il prima possibile. La notifica contiene i seriali delle sacche.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## InoltroAlert:
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">SaccheInScadenzaCTTControl:</p>
            </td>
            <td width="900px">
                <p>Per “SaccheInScadenzaCTTControl” si intende un control object che periodicamente gestisce la lista delle sacche in scadenza nel sistema CTT. Esso crea NotificaSaccheInScadenza e lo inoltra al ManageAlert.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ManageAlert:</p>
            </td>
            <td>
                <p>Per “ManageAlert” si intende un control object del CCS che gestisce la funzionalità di inoltro della NotificaSaccheInScadenza tra CTT e CCS. Tramite un algoritmo, ricerca tra i CTT connessi quelli che potrebbero ricevere in tempo le sacche ed inoltra loro la notifica. Esso inoltre, non appena riceve una risposta all’alert inoltrato, crea una notificaAffidamentoSacca (che inoltra al CTT richiedente) e una notificaEvasioneSacca (che inoltra al CTT con la sacca in scadenza).</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">NotificaSaccheInScadenza:</p>
            </td>
            <td>
                <p>Per “NotificaSaccheInScadenza” si intende l’oggetto che contiene la lista di sacche creato dal SaccheInScadenzaCTTControl e inviato al CCS.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## RispostaAlert:
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">AccettaSaccaButton:</p>
            </td>
            <td width="900px">
                <p>Per “AccettaSaccaButton” si intende un bottone presente sul TerminaleOperatoreCTT. Esso è utilizzato nel momento in cui, l’OperatoreCTT, in seguito alla ricezione di una notifica di alert inviata dal CCS, ha necessità di rispondere a tale alert. Cliccandolo viene visualizzato AccettaSaccaForm sul TerminaleOperatoreCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">AccettaSaccaControl:</p>
            </td>
            <td>
                <p>Per “AccettaSaccaControl” si intende un control object che gestisce la funzionalità di AccettaSacca sul TerminaleOperatoreCTT. Questo oggetto viene creato quando l’OperatoreCTT clicca su AccettaSaccaButton. Esso crea  AccettaSaccaForm, ne raccoglie i dati e crea una NotificaRichiestaSacca e la inoltra verso il ManageAlert. Inoltre riceve la NotificaAffidamentoSacca dal ManageAlert e la mostra sul TerminaleOperatoreCTT richiedente. </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">AccettaSaccaForm:</p>
            </td>
            <td>
                <p>Per “AccettaSaccaForm” si intende un form creato da AccettaSaccaControl. Esso richiede le informazioni riguardanti l’ente richiedente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">NotificaRichiestaSacca:</p>
            </td>
            <td>
                <p>Per “NotificaRichiestaSacca” si intende l’oggetto creato dall' AccettaSaccheControl quando viene sottomesso l’AccettaSaccheForm. Una volta creata viene spedita verso il ManageAlert del CCS.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">NotificaAffidamentoSacca:</p>
            </td>
            <td>
                <p>Per "NotificaAffidamentoSacca" si intende un messaggio creato dal ManageAlert per avvertire il CTT richiedente che la sacca da esso prenotata arriverà in una determinata data.</p>
            </td>
        </tr>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">NotificaEvasioneSacca:</p>
            </td>
            <td width="900px">
                <p>Per “NotificaEvasioneSacca” si intende l’oggetto creato dal ManageAlert durante la RispostaAlert per notificare il CTT con le sacche in scadenza l'avvenuta prenotazione delle ultime da parte del CTT richiedente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ManageEvasioneSacca:</p>
            </td>
            <td>
                <p>Per "ManageEvasioneSacca" si intende il control object che gestisce la NotificaEvasioneSacca sul TerminaleMagazziniereCTT. Esso modifica lo stato delle sacche in “prenotata” e mostra la NotificaEvasioneSacca proveniente dal CCS sul TerminaleMagazziniereCTT.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## EliminazioneSaccaScaduta:
<div>
    <table>
        <tr>
            <td>
                <p style="font-size: 16px">NotificaScadenzaSacca:</p>
            </td>
            <td>
                <p>Per "NotificaScadenzaSacca" si intende la notifica inviata dal sistema CCT al MagazziniereCTT per avvertirlo della scadenza di una sacca che quindi va rimossa.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">NotificaScadenzaSaccaControl:</p>
            </td>
            <td>
                <p>Per "NotificaScadenzaSaccaControl" si intende il control object che gestisce la funzionalità di ScadenzaSacca sul TerminaleMagazziniereCTT. Esso crea la NotificaScadenzaSacca e la mostra sul TerminaleMagazziniereCTT. Esso elimina la sacca scaduta dal database e aggiorna i DatiSacca di quest’ultima.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Login
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">LoginButton:</p>
            </td>
            <td width="900px">
                <p>Per “LoginButton” si intende un bottone presente sul TerminaleUser. Esso è utilizzato nel momento in cui  lo user ha necessità di eseguire l’accesso al SistemaCTT/CCS. Cliccandolo viene visualizzato LoginForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">LoginControl:</p>
            </td>
            <td>
                <p>Per “LoginControl” si intende un control object che gestisce la funzionalità di Login sul TerminaleUser. Questo oggetto viene creato quando lo user clicca su LoginButton. Esso crea LoginForm e lo presenta allo user. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo e in base al risultato dell’operazione crea un  MessaggioLoginEffettuato o un MessaggioDatiLoginErrati.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">LoginForm:</p>
            </td>
            <td>
                <p>Per “LoginForm” si intende un modulo da compilare con i dati necessari per effettuare il login sul SistemaCTT/CCS. Questo form è presentato allo user sul TerminaleUser quando viene cliccato il LoginButton. LoginForm contiene tutti i dati necessari  (Username user, Password user) per effettuare il login sul sistema.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">MessaggioLoginEffettuato:</p>
            </td>
            <td>
                <p>Per “MessaggioLoginEffettuato” si intende un avviso che il SistemaCTT/CCS inoltra sul TerminaleUser nel momento in cui il login è andato a buon fine. Il messaggio contiene il nome dello user che ha effettuato il login e il ruolo con cui si è autentificato.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">MessaggioDatiLoginErrati:</p>
            </td>
            <td>
                <p>Per “MessaggioDatiLoginErrati” si intende un avviso contenente un messaggio d'errore che il SistemaCTT/CCS inoltra sul TerminaleUser nel momento in cui il login non è andato a buon fine. </p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## AggiuntaNuovoDipendente
<div>
    <table>
        <tr>
            <td width=420px">
                <p style="font-size: 16px">AggiuntaNuovoDipendenteButton:</p>
            </td>
            <td width="900px">
                <p>Per “AggiuntaNuovoDipendenteButton” si intende un bottone presente sul TerminaleAmministratoreCTT/CCS. Esso è utilizzato nel momento in cui l’AmministratoreCTT/CCS, in seguito ad una assunzione, ha necessità di inserire il profilo nel proprio database. Cliccandolo viene visualizzato l’AggiuntaNuovoDipendenteForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">AggiuntaNuovoDipendenteControl:</p>
            </td>
            <td>
                <p>Per “AggiuntaNuovoDipendenteControl” si intende un control object che gestisce la funzionalità di AggiuntaNuovoDipendente sul TerminaleAmministratoreCTT/CCS. Questo oggetto è creato quando l’AmministratoreCTT/CCS clicca su AggiuntaNuovoDipendenteButton. Esso crea AggiuntaNuovoDipendenteForm e lo presenta all’AmministratoreCTT/CCS. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo, mostra un MessaggioCorrettaAggiuntaNuovoDipendente e crea il DatiDipendentePDF con le informazioni di accesso al SistemaCTT/CCS del dipendente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">AggiuntaNuovoDipendenteForm:</p>
            </td>
            <td>
                <p>Per “AggiuntaNuovoDipendenteForm” si intende un modulo da compilare con i dati di un DipendenteCTT/CCS. Questo form è presentato all’AmministratoreCTT/CCS sul TerminaleAmministratoreCTT/CCS quando viene cliccato l' AggiuntaNuovoDipendenteButton. AggiuntaNuovoDipendenteForm contiene tutti i campi da compilare con i dati che contraddistinguono un dipendente dall'altro.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">MessaggioCorrettaAggiuntaNuovoDipendente:</p>
            </td>
            <td>
                <p>Per "MessaggioCorrettaAggiuntaNuovoDipendente" si intendete il messaggio mostrato sul TerminaleAmministratoreCTT/CCS in seguito alla corretta aggiunta di un nuovo dipendente al SistemaCTT/CCS.</p>
            </td>
        </tr>
         <tr>
            <td>
                <p style="font-size: 16px">DatiDipendentePDF:</p>
            </td>
            <td>
                <p>Per “DatiDipendentePDF” si intende il file PDF generato a seguito di una corretta AggiuntaNuvoDipendente contenente le informazioni di accesso al SistemaCTT/CCS da parte del nuovo dipendente.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## RimozioneDipendente
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">RimozioneDipendenteButton:</p>
            </td>
            <td width="900px">
                <p>Per “RimozioneDipendenteButton” si intende un bottone presente sul TerminaleAmministratoreCTT/CCS. Esso è utilizzato nel momento in cui l’AmministratoreCTT/CCS, ha necessità di eliminare il profilo di un dipendente dal proprio database. Cliccandolo viene visualizzato il RimozioneDipendenteForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">RimozioneDipendenteControl:</p>
            </td>
            <td>
                <p>Per “RimozioneDipendenteControl” si intende un control object che gestisce la funzionalità di RimozioneDipendente sul TerminaleAmministratoreCTT/CCS. Questo oggetto è creato quando l’AmministratoreCTT/CCS clicca su RimozioneDipendenteButton. Esso crea RimozioneDipendenteForm e lo presenta all’AmministratoreCTT/CCS. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo, crea un MessaggioCorrettaRimozioneDipendente e rimuove il profilo dal database.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">RimozioneDipendenteForm:</p>
            </td>
            <td>
                <p>Per “RimozioneDipendenteForm” si intende un modulo da compilare con i dati di un DipendenteCTT/CCS. Questo form è presentato all’AmministratoreCTT/CCS sul TerminaleAmministratoreCTT/CCS quando viene cliccato il RimozioneDipendenteButton. RimozioneDipendenteForm contiene tutti i campi da compilare con i dati che contraddistinguono un dipendente dall'altro.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">MessaggioCorrettaRimozioneDipendente:</p>
            </td>
            <td>
                <p>Per "MessaggioCorrettaRimozioneDipendente" si intendete il messaggio mostrato sul TerminaleAmministratoreCTT/CCS in seguito alla corretta rimozione di un dipendente dal SistemaCTT/CCS.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Aggiunta/RimozioneCTT
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">AggiuntaNuovoCTTButton:</p>
            </td>
            <td width="900px">
                <p>Per “AggiuntaNuovoCTTButton” si intende un bottone presente sul TerminaleAmministratoreCCS. Esso è utilizzato nel momento in cui, l’ AmministratoreCCS, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di aggiungere un nuovo CTT alla rete dei centri trasfusionali. Cliccandolo viene visualizzato AggiuntaNuovoCTTForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">AggiuntaNuovoCTTControl:</p>
            </td>
            <td>
                <p>Per “AggiuntaNuovoCTTControl” si intende un control object che gestisce la funzionalità di AggiuntaNuovoCTT sul TerminaleAmministratoreCCS. Questo oggetto viene creato quando l’ AmministratoreCCS clicca su AggiuntaNuovoCTTButton. Esso crea AggiuntaNuovoCTTForm e lo presenta all’ AmministratoreCCS. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo e crea un MessaggioNuovoCTTCreato.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">AggiuntaNuovoCTTForm:</p>
            </td>
            <td>
                <p>Per “AggiuntaNuovoCTTForm” si intende un modulo da compilare con i dati relativi ad un CTT da aggiungere al sistema. Questo form è presentato all’ AmministratoreCCS sul TerminaleAmministratoreCCS quando viene cliccato il AggiuntaNuovoCTTButton. AggiuntaNuovoCTTForm contiene tutti i dati necessari  (denominazione, provincia, città, telefono, indirizzo, email, latitudine, longitudine) affinché un CTT possa essere aggiunto alla lista dei CTT attivi.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">MessaggioNuovoCTTCreato:</p>
            </td>
            <td>
                <p>Per “MessaggioNuovoCTTCreato” si intende un avviso che il sistema inoltra sul TerminaleAmministratoreCCS nel momento in cui un CTT è stato aggiunto correttamente alla rete. Il messaggio contiene l’identificativo del nuovo CTT creato.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">RimozioneCTTButton:</p>
            </td>
            <td>
                <p>Per “RimozioneCTTButton” si intende un bottone presente sul TerminaleAmministratoreCCS. Esso è utilizzato nel momento in cui, l’ AmministratoreCCS, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di rimuovere un CTT dalla rete dei centri trasfusionali. Cliccandolo viene visualizzato RimozioneCTTForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">RimozioneCTTControl:</p>
            </td>
            <td>
                <p>Per “RimozioneCTTControl” si intende un control object che gestisce la funzionalità di RimozioneCTT sul TerminaleAmministratoreCCS. Questo oggetto viene creato quando l’ AmministratoreCCS clicca su RimozioneCTTButton. Esso crea RimozioneCTTForm e lo presenta all’ AmministratoreCCS. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’utilmo, crea una MessaggioRimozioneCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">RimozioneCTTForm:</p>
            </td>
            <td>
                <p>Per “RimozioneCTTForm” si intende un modulo da compilare con i dati relativi ad un CTT da rimuovere dal sistema. Questo form è presentato all’AmministratoreCCS sul TerminaleAmministratoreCCS quando viene cliccato RimozioneCTTButton. RimozioneCTTForm contiene tutti i dati necessari  (Identificativo) affinché un CTT possa essere rimosso dalla lista dei CTT attivi.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">MessaggioRimozioneCTT:</p>
            </td>
            <td>
                <p>Per “MessaggioRimozioneCTT” si intende un avviso che il sistema inoltra sul TerminaleAmministratoreCCS nel momento in cui un CTT è stato rimosso correttamente dalla rete. Il messaggio contiene l’identificativo del CTT rimosso.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## ReportStatisticiCCS
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">ReportStatisticiCCSButton:</p>
            </td>
            <td width="900px">
                <p>Per “ReportStatisticiCCSButton” si intende un bottone presente sul TerminaleAmministratoreCCS. Esso è utilizzato nel momento in cui, l’ AmministratoreCCS, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query sul SistemaCCS. Cliccandolo viene visualizzato MenuSelezioneQuery.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportStatisticiCCSControl:</p>
            </td>
            <td>
                <p>Per “ReportStatisticiCCSControl” si intende un control object che gestisce la funzionalità di ReportStatisticiCCS sul TerminaleAmministratoreCCS. Questo oggetto viene creato quando l’ AmministratoreCCS clicca su ReportStatisticiCCSButton. Esso crea MenuSelezioneQuery e lo presenta all’AmministratoreCCS. </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">MenuSelezioneQuery:</p>
            </td>
            <td>
                <p>Per “MenuSelezioneQuery” si intende un elemento dell’interfaccia grafica che, attraverso dei bottoni, mostra all’utente tutte le possibili query effettuabili. Questo elemento è presentato all’ AmministratoreCCS  sul TerminaleAmministratoreCCS quando viene cliccato ReportStatisticiCCSButton oppure all’AmministratoreCTT sul TerminaleAmministratoreCTT quando viene cliccato ReportStatisticiCTTButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDisponibilitàRegionaleSaccheButton:</p>
            </td>
            <td>
                <p>Per “ReportDisponibilitàRegionaleSaccheButton” si intende un bottone presente sul TerminaleAmministratoreCCS, all’interno di MenuSelezioneQuery. Esso è utilizzato nel momento in cui, l’ AmministratoreCCS, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query di tipo ReportDisponibilitàRegionaleSacche. Cliccandolo viene visualizzato ReportDisponibilitàRegionaleSaccheForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDisponibilitàRegionaleSaccheControl:</p>
            </td>
            <td>
                <p>Per “ReportDisponibilitàRegionaleSaccheControl” si intende un control object che gestisce la funzionalità di ReportDisponibilitàRegionaleSacche sul TerminaleAmministratoreCCS. Questo oggetto viene creato quando l’ AmministratoreCCS clicca su ReportDisponibilitàRegionaleSaccheButton. Esso crea una QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDisponibilitàRegionaleSaccheForm:</p>
            </td>
            <td>
                <p>Per “ReportDisponibilitàRegionaleSaccheForm” si intende un modulo da compilare con i dati necessari per personalizzare un ReportDisponibilitàRegionaleSacche Query. Questo form è presentato all’AmministratoreCCS sul TerminaleAmministratoreCCS quando viene cliccato il ReportDisponibilitàRegionaleSaccheButton. ReportDisponibilitàRegionaleSaccheForm contiene tutti i dati necessari  (tipologia di sangue d’interesse, Periodo temporale di interesse) per personalizzare la Query ReportDisponibilitàRegionaleSacche.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportSaccheInviateCTTButton:</p>
            </td>
            <td>
                <p>Per “ReportSaccheInviateCTTButton” si intende un bottone presente sul TerminaleAmministratoreCCS, all’interno di MenuSelezioneQuery. Esso è utilizzato nel momento in cui, l’ AmministratoreCCS, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query di tipo ReportSaccheInviateCTT. Cliccandolo viene visualizzato ReportSaccheInviateCTTForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportSaccheInviateCTTControl:</p>
            </td>
            <td>
                <p>Per “ReportSaccheInviateCTTControl” si intende un control object che gestisce la funzionalità di ReportSaccheInviateCTT sul TerminaleAmministratoreCCS. Questo oggetto viene creato quando l’ AmministratoreCCS clicca su ReportSaccheInviateCTTButton. Esso crea ReportSaccheInviateCTTForm e lo presenta all’ AmministratoreCCS. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo e crea una QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportSaccheInviateCTTForm:</p>
            </td>
            <td>
                <p>Per “ReportSaccheInviateCTTForm” si intende un modulo da compilare con i dati necessari per personalizzare una ReportSaccheInviateCTT Query. Questo form è presentato all’ AmministratoreCCS sul TerminaleAmministratoreCCS quando viene cliccato il ReportSaccheInviateCTTButton. ReportSaccheInviateCTTForm contiene tutti i dati necessari  (Periodo temporale di interesse) per personalizzare la Query ReportSaccheInviateCTT.</p>
            </td>
        </tr>
          <tr>
            <td>
                <p style="font-size: 16px">ReportSaccheRicevuteCTTButton:</p>
            </td>
            <td>
                <p>Per “ReportSaccheRicevuteCTTButton” si intende un bottone presente sul TerminaleAmministratoreCCS, all’interno di MenuSelezioneQuery. Esso è utilizzato nel momento in cui, l’ AmministratoreCCS, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query di tipo ReportSaccheRicevuteCTT. Cliccandolo viene visualizzato ReportSaccheRicevuteCTTForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportSaccheRicevuteCTTControl:</p>
            </td>
            <td>
                <p>Per “ReportSaccheRicevuteCTTControl” si intende un control object che gestisce la funzionalità di ReportSaccheRicevuteCTT sul TerminaleAmministratoreCCS. Questo oggetto viene creato quando l’ AmministratoreCCS clicca su ReportSaccheRicevuteCTTButton. Esso crea ReportSaccheRicevuteCTTForm e lo presenta all’ AmministratoreCCS. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo, crea una QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportSaccheRicevuteCTTForm:</p>
            </td>
            <td>
                <p>Per “ReportSaccheRicevuteCTTForm” si intende un modulo da compilare con i dati necessari per personalizzare una ReportSaccheRicevuteCTT Query. Questo form è presentato all’ AmministratoreCCS sul TerminaleAmministratoreCCS quando viene cliccato il ReportSaccheRicevuteCTTButton. ReportSaccheRicevuteCTTForm contiene tutti i dati necessari  (Periodo temporale d’interesse) per personalizzare la Query ReportSaccheRicevuteCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDipendentiCTTButton:</p>
            </td>
            <td>
                <p>Per “ReportDipendentiCTTButton” si intende un bottone presente sul TerminaleAmministratoreCCS, all’interno di MenuSelezioneQuery. Esso è utilizzato nel momento in cui, l’ AmministratoreCCS, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query di tipo ReportDipendentiCTT. Cliccandolo viene visualizzato ReportDipendentiCTTForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDipendentiCTTControl:</p>
            </td>
            <td>
                <p>Per “ReportDipendentiCTTControl” si intende un control object che gestisce la funzionalità di ReportDipendentiCTT sul TerminaleAmministratoreCCS. Questo oggetto viene creato quando l’ AmministratoreCCS clicca su ReportDipendentiCTTButton. Esso crea ReportDipendentiCTTForm e lo presenta all’AmministratoreCCS. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo, crea una QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDipendentiCTTForm:</p>
            </td>
            <td>
                <p>Per “ReportDipendentiCTTForm” si intende un modulo da compilare con i dati necessari per personalizzare una ReportDipendentiCTT Query. Questo form è presentato all’ AmministratoreCCS sul TerminaleAmministratoreCCS quando viene cliccato il ReportDipendentiCTTButton. ReportDipendentiCTTForm contiene tutti i dati necessari  (Ruolo del dipendente CTT da filtrare) per personalizzare la Query ReportDipendentiCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportGiacenzaMediaButton:</p>
            </td>
            <td>
                <p>Per “ReportGiacenzaMediaButton” si intende un bottone presente sul TerminaleAmministratoreCCS, all’interno di MenuSelezioneQuery. Esso è utilizzato nel momento in cui, l’ AmministratoreCCS, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query di tipo ReportGiacenzaMedie. Cliccandolo viene visualizzato ReportGiacenzaMediaForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportGiacenzaMediaControl:</p>
            </td>
            <td>
                <p>Per “ReportReportGiacenzaMediaControl” si intende un control object che gestisce la funzionalità di ReportGiacenzaMedia sul TerminaleAmministratoreCCS. Questo oggetto viene creato quando l’ AmministratoreCCS clicca su ReportGiacenzaMediaButton. Esso crea ReportGiacenzaMediaForm e lo presenta all’ AmministratoreCCS. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo, crea una QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportGiacenzaMediaForm:</p>
            </td>
            <td>
                <p>Per “ReportGiacenzaMediaForm” si intende un modulo da compilare con i dati necessari per personalizzare una ReportGiacenzaMedia Query. Questo form è presentato all’ AmministratoreCCS sul TerminaleAmministratoreCCS quando viene cliccato il ReportGiacenzaMediaButton. ReportGiacenzaMediaForm ritorna un istogramma che mostra il tempo di giacenza medio delle sacche all'interno dei magazzini dei vari CTT.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## ReportStatisticiCTT
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">ReportStatisticiCTTButton:</p>
            </td>
            <td width="900px">
                <p>Per “ReportStatisticiCTTButton” si intende un bottone presente sul TerminaleAmministratoreCTT. Esso è utilizzato nel momento in cui, l’ AmministratoreCTT, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query sul SistemaCTT. Cliccandolo viene visualizzato MenuSelezioneQuery.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportStatisticiCTTControl:</p>
            </td>
            <td>
                <p>Per “ReportStatisticiCTTControl” si intende un control object che gestisce la funzionalità di ReportStatisticiCTT sul TerminaleAmministratoreCTT. Questo oggetto viene creato quando l’ AmministratoreCTT clicca su ReportStatisticiCTTButton. Esso crea MenuSelezioneQuery e lo presenta all’AmministratoreCTT. </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDisponibilitàSaccheLocaleButton:</p>
            </td>
            <td>
                <p>Per “ReportDisponibilitàSaccheLocaleButton” si intende un bottone presente sul TerminaleAmministratoreCTT, all’interno di MenuSelezioneQuery. Esso è utilizzato nel momento in cui, l’ AmministratoreCTT, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query di tipo ReportDisponibilitàSaccheLocale. Cliccandolo viene visualizzato ReportDisponibilitàSaccheLocaleForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDisponibilitàSaccheLocaleControl:</p>
            </td>
            <td>
                <p>Per “ReportDisponibilitàSaccheLocaleControl” si intende un control object che gestisce la funzionalità di ReportDisponibilitàSaccheLocale sul TerminaleAmministratoreCTT. Questo oggetto viene creato quando l’ AmministratoreCTT clicca su ReportDisponibilitàSaccheLocaleButton. Esso crea ReportDisponibilitàSaccheLocaleForm e lo presenta all’AmministratoreCTT. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo e crea una QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDisponibilitàSaccheLocaleForm:</p>
            </td>
            <td>
                <p>Per “ReportDisponibilitàSaccheLocaleForm” si intende un modulo da compilare con i dati necessari per personalizzare un ReportDisponibilitàSaccheLocale Query. Questo form è presentato all’AmministratoreCTT sul TerminaleAmministratoreCTT quando viene cliccato il ReportDisponibilitàSaccheLocaleButton. ReportDisponibilitàSaccheLocaleForm contiene tutti i dati necessari  (tipologia di sangue d’interesse, Periodo temporale di interesse) per personalizzare la Query ReportDisponibilitàSaccheLocale.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportLocaleSaccheInviateCTTButton:</p>
            </td>
            <td>
                <p>Per “ReportLocaleSaccheInviateCTTButton” si intende un bottone presente sul TerminaleAmministratoreCTT, all’interno di MenuSelezioneQuery. Esso è utilizzato nel momento in cui, l’AmministratoreCTT, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query di tipo ReportLocaleSaccheInviateCTT. Cliccandolo viene visualizzato ReportLocaleSaccheInviateCTTForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportLocaleSaccheInviateCTTControl:</p>
            </td>
            <td>
                <p>Per “ReportLocaleSaccheInviateCTTControl” si intende un control object che gestisce la funzionalità di ReportLocaleSaccheInviateCTT sul TerminaleAmministratoreCTT. Questo oggetto viene creato quando l’ AmministratoreCTT clicca su ReportLocaleSaccheInviateCTTButton. Esso crea ReportLocaleSaccheInviateCTTForm e lo presenta all’AmministratoreCTT. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo e crea una QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportLocaleSaccheInviateCTTForm:</p>
            </td>
            <td>
                <p>Per “ReportLocaleSaccheInviateCTTForm” si intende un modulo da compilare con i dati necessari per personalizzare una ReportLocaleSaccheInviateCTT Query. Questo form è presentato all’ AmministratoreCTT sul TerminaleAmministratoreCTT quando viene cliccato il ReportLocaleSaccheInviateCTTButton. ReportLocaleSaccheInviateCTTForm contiene tutti i dati necessari  (Periodo temporale di interesse) per personalizzare la Query ReportLocaleSaccheInviateCTT.</p>
            </td>
        </tr>
          <tr>
            <td>
                <p style="font-size: 16px">ReportLocaleSaccheRicevuteCTTButton:</p>
            </td>
            <td>
                <p>Per “ReportLocaleSaccheRicevuteCTTButton” si intende un bottone presente sul TerminaleAmministratoreCTT, all’interno di MenuSelezioneQuery. Esso è utilizzato nel momento in cui, l’AmministratoreCTT, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query di tipo ReportLocaleSaccheRicevuteCTT. Cliccandolo viene visualizzato ReportLocaleSaccheRicevuteCTTForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportLocaleSaccheRicevuteCTTControl:</p>
            </td>
            <td>
                <p>Per “ReportLocaleSaccheRicevuteCTTControl” si intende un control object che gestisce la funzionalità di ReportLocaleSaccheRicevuteCTT sul TerminaleAmministratoreCTT. Questo oggetto viene creato quando l’ AmministratoreCTT clicca su ReportLocaleSaccheRicevuteCTTButton. Esso crea ReportLocaleSaccheRicevuteCTTForm e lo presenta all’AmministratoreCTT. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo, crea una QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportLocaleSaccheRicevuteCTTForm:</p>
            </td>
            <td>
                <p>Per “ReportLocaleSaccheRicevuteCTTForm” si intende un modulo da compilare con i dati necessari per personalizzare una ReportLocaleSaccheRicevuteCTT Query. Questo form è presentato all’ AmministratoreCTT sul TerminaleAmministratoreCTT quando viene cliccato il ReportLocaleSaccheRicevuteCTTButton. ReportLocaleSaccheRicevuteCTTForm contiene tutti i dati necessari  (Periodo temporale d’interesse) per personalizzare la Query ReportLocaleSaccheRicevuteCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDipendentiLocaleButton:</p>
            </td>
            <td>
                <p>Per “ReportDipendentiLocaleButton” si intende un bottone presente sul TerminaleAmministratoreCTT, all’interno di MenuSelezioneQuery. Esso è utilizzato nel momento in cui, l’ AmministratoreCTT, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query di tipo ReportDipendentiLocale. Cliccandolo viene visualizzato ReportDipendentiLocaleForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDipendentiLocaleControl:</p>
            </td>
            <td>
                <p>Per “ReportDipendentiLocaleControl” si intende un control object che gestisce la funzionalità di ReportDipendentiLocale sul TerminaleAmministratoreCTT. Questo oggetto viene creato quando l’ AmministratoreCTT clicca su ReportDipendentiLocaleButton. Esso crea ReportDipendentiLocaleForm e lo presenta all’AmministratoreCTT. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo, crea una QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportDipendentiLocaleForm:</p>
            </td>
            <td>
                <p>Per “ReportDipendentiLocaleForm” si intende un modulo da compilare con i dati necessari per personalizzare una ReportDipendentiLocale Query. Questo form è presentato all’ AmministratoreCTT sul TerminaleAmministratoreCTT quando viene cliccato il ReportDipendentiLocaleButton. ReportDipendentiLocaleForm contiene tutti i dati necessari  (Ruolo del dipendente CTT da filtrare) per personalizzare la Query ReportDipendentiLocale.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportGiacenzaMediaLocaleButton:</p>
            </td>
            <td>
                <p>Per “ReportGiacenzaMediaLocaleButton” si intende un bottone presente sul TerminaleAmministratoreCTT, all’interno di MenuSelezioneQuery. Esso è utilizzato nel momento in cui, l’ AmministratoreCTT, in seguito ad una richiesta/predisposizione proveniente dall’esterno, ha necessità di eseguire una query di tipo ReportGiacenzaMediaLocale. Cliccandolo viene visualizzato ReportGiacenzaMediaLocaleForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportGiacenzaMediaLocaleControl:</p>
            </td>
            <td>
                <p>Per “ReportReportGiacenzaMediaLocaleControl” si intende un control object che gestisce la funzionalità di ReportGiacenzaMediaLocale sul TerminaleAmministratoreCTT. Questo oggetto viene creato quando l’ AmministratoreCTT clicca su ReportGiacenzaMediaLocaleButton. Esso crea ReportGiacenzaMediaLocaleForm e lo presenta all’ AmministratoreCTT. Dopo aver sottomesso il form, questo oggetto raccoglie le informazioni da quest’ultimo, crea una QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">ReportGiacenzaMediaLocaleForm:</p>
            </td>
            <td>
                <p>Per “ReportGiacenzaMediaLocaleForm” si intende un modulo da compilare con i dati necessari per personalizzare una ReportGiacenzaMediaLocale Query. Questo form è presentato all’ AmministratoreCTT sul TerminaleAmministratoreCTT quando viene cliccato il ReportGiacenzaMediaLocaleButton. ReportGiacenzaMediaLocaleForm ritorna un istogramma che mostra il tempo di giacenza medio delle sacche all'interno dei magazzini dei vari CTT.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## AggiuntaSaccaMagazziniere
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-size: 16px">AggiungiSaccaButton:</p>
            </td>
            <td width="900px">
                <p>Per “AggiungiSaccaButton” si intende un bottone presente sul TerminaleMagazziniereCTT. Esso è utilizzato nel momento in cui, il Magazziniere CTT, in seguito all’arrivo di una sacca in magazzino, ha necessità di aggiungerla al database del CTT. Cliccandolo viene visualizzato AggiungiSaccaForm.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">AggiungiSaccaControl:</p>
            </td>
            <td>
                <p>Per “AggiungiSaccaControl” si intende un control object che gestisce la funzionalità di AggiungiSacca sul TerminaleMagazziniereCTT. Questo oggetto viene creato quando il Magazziniere CTT clicca su AggiungiSaccaButton. Esso crea AggiungiSaccaForm e lo presenta al MagazziniereCTT sul proprio TerminaleMagazziniereCTT. Dopo aver sottomesso il form,  questo oggetto raccoglie le informazioni da quest’ultimo, crea DatiSacca e la Sacca nel Database ed infine crea un MessaggioCorrettaAggiuntaSacca che viene visualizzato sul TerminaleMagazziniereCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">AggiungiSaccaForm:</p>
            </td>
            <td>
                <p>Per “AggiungiSaccaForm” si intende un modulo da compilare con i dati relativi ad una sacca da aggiungere al database. Questo form è presentato al MagazziniereCTT sul TerminaleMagazziniereCTT quando viene cliccato il AggiungiSaccaButton. AggiungiSaccaForm contiene tutti i dati necessari (Gruppo sanguigno, data produzione, data scadenza,ente donatore) affinché una sacca possa essere aggiunta al database del CTT e possano essere creati i relativi DatiSacca.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-size: 16px">MessaggioCorrettaAggiuntaSacca:</p>
            </td>
            <td>
                <p>Per “MessaggioCorrettaAggiuntaSacca” si intende l’oggetto creato dall'AggiuntaSaccaControl quando una sacca e i rispettivi DatiSacca vengono aggiunti con successo al database del CTT.
                </p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Appendice
<div>
    <table>
        <tr>
            <td width="420px">
                <p style="font-style: 30px">Form:</p>
            </td>
            <td width="900px">
                <p>Per “Form” si intende un modulo da compilare per l'inserimento di dati contenuta in una pagina web, elemento dell’interfaccia grafica, che permette manualmente di inserire dati per trasmetterli al sistema.  Può essere composto da caselle di testo, menu a tendina, checkbox, text area.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 30px">Login:</p>
            </td>
            <td>
                <p>Per “Login” si intende l’azione di autentificazione di un utente con il sistema attraverso credenziali di accesso personali. L'operazione può andare a buon fine o meno.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 30px">LetteraDiEvasionePDF:</p>
            </td>
            <td>
                <p>Per "LetteraDiEvasionePDF" si intende un oggetto contenente il  numero di sacche, l'ente richiedente con il proprio indirizzo, la data di affidamento, il gruppo sanguigno. Il PDF generato sarà posizionato come etichetta sopra la sacca da spedire.
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 30px">ReportStatistici:</p>
            </td>
            <td>
                <p>Per “ReportStatistici” si intendono delle indagini sullo stato dei database dei SistemiCTT/CCS richiedibili attraverso delle query predefinite e personalizzabili. </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 30px">InizializzazioneReport:</p>
            </td>
            <td>
                <p>Per “Inizializzazione Report” si intende l’operazione preliminare alla selezione della query da eseguire. Attraverso il bottone ReportStatisticiCCS/CTTButton si può navigare fino a questa sezione (MenuSelezioneQuery) dove è presente l’elenco di tutte le query disponibili sul sistema.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 30px">Broadcast:</p>
            </td>
            <td>
                <p>Per “Broadcast” si intende una notifica inviata dal CCS ad un certo numero di CTT in contemporanea.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 30px">Alert:</p>
            </td>
            <td>
                <p>Per “Alert” si intende una notifica inviato dal CCS in broadcast ad un certo numero di CTT quando una sacca è prossima alla scadenza(si suppone che la sacca stia per scadere quando mancano 72h o meno).</p>
            </td>
        </tr>
    </table>
</div>
