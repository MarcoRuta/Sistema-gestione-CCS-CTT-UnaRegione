
# Scenari:


[Aggiunta sacca](#Aggiunta%20Sacca)

[Aggiunta CTT](#Aggiunta%20CTT)

[Rimozione CTT](#Rimozione%20CTT)

[Aggiunta CTT](#evasioneSacca)

[Aggiunta personale](#Aggiunta%20personale)

[Rimozione personale](#Rimozione%20personale)

[Controllo alert](#Controllo%20Alert)

[Evasione sacca](#Evasione%20Sacca)

[Report statistici CCS](#Report%20Statistici%20CCS)

[Report statistici CTT](#Report%20Statistici%20CTT)

[Ricerca sacca](#Ricerca%20Sacca)

[Rimozione sacca scaduta](#Rimozione%20sacca%20scaduta)

# Aggiunta sacca:


<div>
    <table id>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Aggiunta sacca proveniente da fonte esterna</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Antonio: magazziniere CTT002</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Arriva un carico di sacche proveniente dall'Avis di Benevento presso il magazzino del CTT002.
      <p>
        2.	Antonio, il magazziniere, ricava i dati delle sacche dalle loro etichiette e li registra nel database attraverso un form.
      <p>
        3.	Per ogni sacca inserita correttamente il sistema da un feedback ad Antonio attraverso una notifica.
      <p>
        4.  Dopo aver registrato correttamente tutto il carico le sacche vengono fisicamente portato in magazzino.

</td>
        </tr>
    </table>
</div>
<div style="Height:30px">
</div>

# Aggiunta CTT:
<div>
    <table>
        <tr>
            <td width="300">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Aggiunta di un Centro Trasfusionale Territoriale (CTT) sulla rete regionale</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Gianpiero: Amministratore CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Nella provincia di Benevento viene aperto un nuovo CTT. La direzione regionale incarica Gianpiero, l’amministratore del CCS di iniziare la pratica per l’aggiunta del nuovo CTT alla rete.
      <p>
        2.	Gianpiero accede al Sistema CCS e attraverso un’interfaccia naviga fino alla sezione di aggiuntaCTT e inserisce i dati necessari.
      <p>
        3.	Il Sistema CCS, dopo la creazione del CTT, fornisce un riscontro positivo a Gianpiero.

</td>
        </tr>
    </table>
</div>
<div style="Height:30px">
</div>

# Rimozione CTT:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Eliminazione CTT005 </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Luca: Amministratore CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	A causa di un carico di lavoro troppo basso, l'amministrazione decide di accorpare i due CTT di Benevento,il CTT005 e il CTT004. 
        Luca viene incaricato di rimuovere il CTT005 dalla rete regionale, in quanto verrò chiuso.
      <p>
        2.	Luca accede alla piattaforma del CCS, procede con il login e naviga fino alla sezione di rimozione CTT.
      <p>
        3.	Luca elimina dalla rete regionale dei CTT il CTT 005.
      <p>
        4.  Il sistema fornisce un feedback all'amministratore.

</td>
        </tr>
    </table>
</div>
<div style="Height:30px">
</div>


# Aggiunta personale:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Assunzione personale CTT004</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Giacomo: Amministratore CTT004</p>
                <p>Marco: Magazziniere CTT004</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Giacomo, l’amministratore del CTT004, deve registrare sul sistema del CTT004 Marco, il nuovo magazziniere.
      <p>
        2.	Giacomo esegue il login nel Sistema del CTT004 inserendo i propri dati e tramite interfaccia grafica naviga fino alla sezione per l’aggiunta di personale al Sistema CTT.
      <p>
        3.	Giacomo compila un form in cui specifica le generalità di Marco, qualificato a ricoprire l’incarico di magazziniere.
      <p>
        4.  Giacomo riceve un messaggio di corretto inserimento del personale nel sistema e viene stampato un PDF contenente le credenziali di accesso assegnate al nuovo operatore.
         

</td>
        </tr>
    </table>
</div>
<div style="Height:30px">
</div>
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Aggiunta personale Amministratore CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Luca: Amministratore CCS</p>
                <p>Francesco: Amministratore CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Giacomo, amministratore del CCS, deve registrare sul sistema Francesco, un nuovo amministratore CCS.
      <p>
        2.	Luca esegue il login nel Sistema del CCS inserendo i propri dati e tramite interfaccia grafica naviga fino alla sezione per l’aggiunta di personale al Sistema CCS;
      <p>
        3.	Luca compila un form in cui specifica le generalità di Mattia, qualificato a ricoprire l’incarico di Amministratore;
      <p>
        4.  Luca riceve un messaggio di corretto inserimento del personale nel sistema e viene stampato un PDF contenente le credenziali di accesso assegnate al nuovo operatore. 

</td>
        </tr>
    </table>
</div>
<div style="Height:30px">
</div>

# Rimozione personale:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Rimozione personale presso il CTT004</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Giacomo: Amministratore CTT004</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Giacomo, l’amministratore del CTT004, decide di licenziare un membro del personale a causa di un necessario taglio del personale;
      <p>
        2.	Giacomo esegue il login nel Sistema del CTT004 inserendo i propri dati e tramite interfaccia grafica naviga fino alla sezione per la rimozione del personale al sistema;
      <p>
        3.	Giacomo compila un form inserendo i dati di Saverio, l’operatore da licenziare;
      <p>
        4.  Giacomo riceve un messaggio di corretta rimozione del personale dal sistema. 

</td>
        </tr>
    </table>
</div>
<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Rimozione personale al CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Mattia: Amministratore CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Mattia, uno degli amministratori del CCS, decide di licenziare un membro del personale a causa di un necessario taglio del personale;
      <p>
        2.	Mattia esegue il login nel Sistema CCS inserendo i propri dati e tramite interfaccia grafica naviga fino alla sezione per la rimozione del personale al sistema;
      <p>
        3.	Mattia compila un form inserendo i dati di Marco, l’amministratore da licenziare;
      <p>
        4.  Mattia riceve un messaggio di corretta rimozione del personale dal sistema. 

</td>
        </tr>
    </table>
</div>
<div style="Height:30px">
</div>





# Controllo alert:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Alert da parte del CTT008 di una sacca di sangue in scadenza accettato dal CTT036</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Tempo
                <p>Pina: Operatore del CTT036
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il CTT008 dopo un controllo del database locale trova una sacca di tipo A+ che scade tra 68h.
      <p>
        2.	Il CTT invia i dati della sacca in scadenza al CCS.
      <p>
        3.	Il CCS aggiunge alla propria lista delle sacche in scadenza la sacca inviata dal CTT008.
      <p>
        4.  Pina, operatore del CTT036, riceve una richiesta da parte dell'ospedale di Avellino di una sacca di sangue tipo A+.
        Pina accede al proprio terminale e vede la notifica della sacca in scadenza inviata dal CCS, essendo compatibile la accetta cliccando sulla notifica e inserendo i dati dell'ente richiedente.
      <p>
        5.  Il CCS in seguito alla richiesta invia una notifica al terminale del magazziniere del CTT008 in cui è presente l’avviso di invio della sacca con opportune informazioni sull’ ente richiedente.
      <p>
        6.  Il CCS notifica il CTT036 che la sua richiesta è stata accettata e che la sacca verrà evasa il prima possibile dal CTT008.

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>


<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Alert da parte del CTT005 di una sacca di sangue in scadenza accettato dal CTT021</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Tempo
                <p>Gianni: Operatore del CTT021
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.  Il CTT005 dopo un controllo del database locale trova una sacca di tipo AB- che scade tra 70h.
      <p>
        2. Il CTT invia i dati della sacca in scadenza al CCS.
      <p>
       3. Il CCS aggiunge alla propria lista delle sacche in scadenza la sacca inviata dal CTT005.
      <p>
        4. Gianni, operatore del CTT021, riceve una richiesta da parte dell'ospedale di Avellino di una sacca di sangue tipo AB-. Gianni accede al proprio terminale e vede la notifica della sacca in scadenza inviata dal CCS, essendo compatibile la accetta cliccando sulla notifica e inserendo i dati dell'ente richiedente.
      <p>
        5.  Nel momento in cui il CCS riceve la richiesta da parte di Gianni invia una notifica al terminale del magazziniere del CTT005 in cui è presente l’avviso di invio della sacca con opportune informazioni sull’ente richiedente.
      <p>
        6.  Il CCS notifica il CTT021 che la sua richiesta è stata accettata e che la sacca verrà evasa il prima possibile.

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Alert da parte del CTT001 di una sacca di sangue in scadenza non accettato da nessun CTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Tempo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
      <p>
       1.  Il CTT001 dopo un controllo del database locale trova una sacca di tipo 0+ che scade tra 61h.
      <p>
       2. Il CTT invia i dati della sacca in scadenza al CCS.
      <p>
       3. Il CCS aggiunge alla propria lista delle sacche in scadenza la sacca inviata dal CTT001.
      <p>
       4. Una volta passate le 61h, dato che non è arrivata nessuna richiesta, la sacca viene eliminata dalla lista delle sacche in scadenza e smaltita presso il CTT001.

</td>
        </tr>
    </table>
</div>

# Evasione sacca:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Invio sacca</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Antonio: magazziniere CTT002</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	A seguito di una richiesta da parte del CTT003 di una sacca di tipo AB+, il CCS effettua una ricerca sulla rete di una sacca con tali caratteristiche e la trova presso il CTT002.
        Il sistema CCS crea una notifica di evasione presso il CTT002.
      <p>
        2.	Antonio il magazziniere riceve la notifica dell’evasione sacca e la clicca. Il sistema crea la lettera di vettura per tale sacca, contenente i dati della sacca e quelli del destinatario.

</td>
        </tr>
    </table>
</div>

<div style="Height:100px">
</div>

# Report statistici CCS:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Report sacche inviate e ricevute dal CTT003 nell’ultimo mese</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Francesco: Amministratore CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	La direzione regionale vuole informarsi sul rendimento del CTT003.
      <p>
        2.	L’amministratore Francesco apre il terminale, esegue il login al Sistema CCS inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema.
      <p>
        3.	Compilando un form, Francesco richiede al sistema il numero di sacche inviate e il numero di sacche ricevute nell’ultimo mese dal CTT003.
      <p>
        4.  Il terminale mostra una schermata dove sono presenti le sacche inviate e ricevute dal CTT003 nell’ultimo mese. 

</td>
        </tr>
    </table>
</div>
<div style="Height:30px">
</div>
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Controllo delle richieste soddisfatte dalla rete regionale nell'ultimo mese
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Francesco: Amministratore CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	La direzione regionale vuole informarsi sul rendimento dei CTT, in particolare si vogliono individuare quali sono i CTT che eseguono molte richieste, in modo da poter attivare campagne di prelievo localizzate.
      <p>
        2.	L’amministratore Francesco apre il terminale, esegue il login al Sistema CCS inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema.
      <p>
        3.	Compilando un form, Francesco richiede la lista di tutti i CTT ordinati per numero di richieste.
      <p>
        4.  Il terminale mostra una schermata dove sono ordinati i CTT in base al numero di richieste soddisfatte nel periodo indicato, in ordine crescente.  

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Indagine regionale dello stato dei magazzini</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Francesco: Amministratore CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	La direzione regionale vuole ricavare informazioni relative al quantitativo di sacche di sangue presenti nella regione in modo da poter fornire indicazioni utili per la programmazione della campagna di prelievo. In particolare, ci si vuole informare sulle eventuali carenze di alcuni tipi;
      <p>
        2.	L’amministratore Francesco apre il terminale, esegue il login al Sistema CCS inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        3.	Compilando un form, Francesco richiede la lista di tutte le tipologie di sangue accompagnate dal numero che rappresenta le scorte a livello regionale;
      <p>
        4.  Il terminale mostra una schermata dove sono ordinati i vari tipi di sangue in ordine crescente, in base alle scorte.

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Report statistico regionale degli operatori CTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Francesco: Amministratore CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	La direzione regionale vuole ricavare informazioni relative al numero di operatori presenti nei vari CTT;
      <p>
        2.	L’amministratore Francesco apre il terminale, esegue il login al Sistema CCS inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        3.	Compilando un form, Francesco richiede la lista di tutti gli operatori a livello regionale;
      <p>
        4.  Il terminale mostra una schermata dove sono riportati tutti gli operatori con le relative infomazioni.

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

# Report statistici CTT:


<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Controllo locale operatori del CTT006</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Francesco: Amministratore CTT006</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Francesco vuole fare un controllo a livello locale per capire la lista degli operatori nel CTT006.
      <p>
        2.	L’amministratore Francesco apre il terminale, esegue il login al Sistema CTT inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        3.	Francesco, compilando un form, richiede la lista di tutti gli operatori del CTT006
      <p>
        4.  Il terminale mostra una schermata dove sono riportati tutti gli operatori con le relative infomazioni.  

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Report delle scorte di sangue A+ presenti nel magazzino del CTT034</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Roberto: Amministratore CTT034</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Roberto vuole fare un controllo a livello locale per capire il numero di sacche di sangue del gruppo A+ presenti nel magazzino del CTT034 in vista di un intervento che si effettuerà qualche giorno dopo;
      <p>
        2.	L’amministratore Roberto apre il terminale, esegue il login al Sistema CTT inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        3.	Roberto compila un form in cui specifica che richiede solo il numero di sacche del gruppo sanguigno A+;
      <p>
        4.  Roberto visualizza il numero di sacche di tipo A+ presenti nel magazzino al momento della ricerca.

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Analisi delle richieste dei vari gruppi di sangue ricevute dal CTT004 nell'ultimo mese</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Giorgia: Amministratore CTT004</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Giorgia vuole fare un controllo a livello locale per capire il gruppo sanguigno più ricercato nel CTT004.
      <p>
        2.	L’amministratore Giorgia apre il terminale, esegue il login al Sistema CTT inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        3.	Giorgia seleziona il report sul gruppo sanguigno più richiesto e compila il form inserendo l’intervallo temporale in cui ricercare;o;
      <p>
        4.  Giorgia visualizza il risultato del report.

</td>
        </tr>
    </table>
</div>

# Ricerca sacca:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Richiesta NON urgente di tre sacche di sangue, con riscontro locale positivo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Pietro: operatore CTT003</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT003 per tre sacche di sangue compatibile con il tipo A+ che verrà trasfusa in data “30-03-2021”.
      <p>
        2.	Pietro l’operatore del CTT003 riceve la richiesta.
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT003 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema.
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il gruppo A+ presenti sul database CTT003.
      <p>
        5.  Pietro trova le tre sacche di tipo A+ nel magazzino, vengono prenotate e viene inoltrata la notifica al magazziniere per evadere l'ordine.

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Richiesta urgente di cinque sacche di sangue di gruppo A-, con riscontro locale positivo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Pietro: operatore CTT003</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT03 per una richiesta di cinque sacche di sangue compatibile con il tipo A- che verrà trasfusa in data odierna.
      <p>
        2.	Pietro l’operatore del CTT003 riceve la richiesta.
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT003 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema.
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il gruppo A- presenti sul database CTT003 specificando una priorità alta.
      <p>
        5.  Pietro trova le cinque sacche di tipo A- nel magazzino, vengono prenotate e viene inoltrata la notifica al magazziniere per evadere l'ordine.

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Richiesta NON urgente di una sacca di sangue, con riscontro locale negativo e regionale positivo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Pietro: operatore CTT004</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT004 per una sacca di sangue compatibile con il tipo A+ che verrà trasfusa in data “28-03-2021”;
      <p>
        2.	Pietro riceve la richiesta;
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT004 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il gruppo A+ presenti sul database CTT004; 
      <p>
        5.  Non essendo presente una sacca compatibile, il sistema CTT004 spedisce una richiesta con priorità bassa al CCS;
      <p>
        6.  Il Sistema CCS, riceve la richiesta e seleziona tra i diversi CTT che hanno una sacca compatibile, quello che ha la sacca con la scadenza più vicina, il CTT004;
      <p>
        7.  Il Sistema CCS spedisce un messaggio al CTT004, notificando che la sacca è stata trovata e un messaggio al CTT004 per inizializzare l’invio della sacca;


</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Richiesta urgente di sei sacche di sangue di tipo A+, con riscontro locale negativo e regionale positivo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Pietro: operatore CTT003</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT003 per sei sacche di sangue compatibile con il tipo A+ che verranno trasfuse in data odierna;
      <p>
        2.	Pietro riceve la richiesta;
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT003 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il gruppo A+ presenti sul database CTT003; 
      <p>
        5.  Non essendo presente neanche una sacca compatibile, il sistemaCTT spedisce una richiesta con priorità elevata al CCS;
      <p>
        6.  Il Sistema CCS, riceve la richiesta e ricerca tra i diversi CTT le sacche richieste fino al raggiungimento della quantità giusta(6), partendo sempre da quello che si trova a distanza minore dal centro richiedente;
      <p>
        7. A seguito della ricerca il Sistema CCS spedisce un messaggio al CTT011, notificando che sono state trovate quattro sacche da spedire e un messaggio al CTT004, per la spedizione delle restanti due. In fine notifica il CTT003 che l'ordine è stato evaso con successo e che gli arriveranno le sacche dal CTT011 e CTT004;
</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Richiesta NON urgente di otto sacche di sangue, con riscontro locale parziale e regionale negativo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Pietro: operatore CTT004</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT004 per otto sacche di sangue compatibile con il tipo A+ che verranno trasfuse in data “28-03-2021”;
      <p>
        2.	Pietro riceve la richiesta;
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT004 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il tipo A+ presenti sul database CTT004;  
      <p>
        5.  Pietro scopre che all'interno del magazzino sono presenti solo cinque sacche compatibili, vengono prenotate e viene inoltrata la notifica al magazziniere per aggiornare i dati sull’affidamento della sacca. A questo punto spedisce una richiesta con priorità bassa al CCS;  
      <p>
        6.  Il Sistema CCS, riceve la richiesta e ricerca tra i diversi CTT le sacche richieste fino al raggiungimento della quantità giusta(3), partendo sempre da quello che si trova a distanza minore dal centro richiedente;
      <p>
        7.  Il Sistema non riesce a trovare un CTT che abbia una sacca compatibile;  
      <p>
        8.  Il Sistema CCS spedisce un messaggio tramite la piattaforma al CTT004, notificando che la sacca non è stata trovata.  

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Richiesta urgente di una sacca di sangue, con riscontro locale negativo e regionale negativo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Pietro: operatore CTT004</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT004 per una sacca di sangue compatibile con il tipo 0- che verrà trasfusa in data odierna;
      <p>
        2.	Pietro riceve la richiesta;
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT004 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il tipo 0- presenti sul database CTT004;
      <p>
        5.  Non essendo presente una sacca compatibile, Pietro spedisce una richiesta con priorità elevata al CCS;
      <p>
        6.  Il Sistema CCS riceve la richiesta e ricerca tra i diversi CTT che hanno una sacca compatibile, quello che si trova a distanza minore dal centro richiedente;
      <p>
        7.  Il Sistema non riesce a trovare un CTT che abbia una sacca compatibile;
      <p>
        8.  Il Sistema CCS spedisce un messaggio tramite la piattaforma al CTT004, notificando che la sacca non è stata trovata.

</td>
        </tr>
    </table>
</div>


# Rimozione sacca scaduta:


<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p>Rimozione sacca scaduta</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p>Tempo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il CTT002 durante un controllo si accorge che la sacca di sangue CTT002-00000016 è scaduta;
      <p>
        2.	Il CTT002 rimuove automaticamente la sacca CTT002-00000016 dal database e invia una notifica sul terminale del magazziniere per permetterne lo smaltimento.

</td>
        </tr>
    </table>
</div>
