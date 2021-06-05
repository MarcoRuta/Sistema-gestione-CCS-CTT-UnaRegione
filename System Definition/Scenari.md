
# Scenari:


[AggiuntaSacca](#AggiuntaSacca)

[AggiuntaCTT](#AggiuntaCTT)

[RimozioneCTT](#RimozioneCTT)

[AggiuntaPersonale](#AggiuntaPersonale)

[Rimozionepersonale](#RimozionePersonale)

[NotificaAlert](#NotificaAlert)

[EvasioneSacca](#EvasioneSacca)

[ReportStatisticiCCS](#ReportStatisticiCCS)

[ReportStatisticiCTT](#ReportStatisticiCTT)

[RicercaSacca](#RicercaSacca)

[RimozioneSaccheScadute](#RimozioneSaccheScadute)

# AggiuntaSacca:


<div>
    <table id>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Aggiunta sacca proveniente da fonte esterna</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Antonio: magazziniere CTT002</u></p>
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
        2.	Antonio, il magazziniere, ricava i dati di ogni sacca dalle loro etichette e le registra nel database attraverso un form.
      <p>
        3.	Per ogni sacca inserita correttamente il sistema dà un feedback ad Antonio attraverso una notifica.
      <p>
        4.  Dopo aver registrato correttamente tutto il carico le sacche vengono fisicamente portate in magazzino.

</td>
        </tr>
    </table>
</div>
<div style="Height:30px">
</div>

# AggiuntaCTT:
<div>
    <table>
        <tr>
            <td width="300">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Aggiunta di un Centro Trasfusionale Territoriale (CTT) sulla rete regionale</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Gianpiero: Amministratore CCS</u></p>
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

# RimozioneCTT:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Eliminazione CTT005 </u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Luca: Amministratore CCS</u></p>
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
        3.	Luca elimina il CTT 005 dalla rete regionale dei CTT.
      <p>
        4.  Il sistema fornisce un feedback all'amministratore.

</td>
        </tr>
    </table>
</div>
<div style="Height:30px">
</div>


# AggiuntaPersonale:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Assunzione personale CTT004</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Giacomo: Amministratore CTT004</u></p>
                <p><u>Marco: Magazziniere CTT004</u></p>
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
                <p><u>Aggiunta personale Amministratore CCS</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Luca: Amministratore CCS</u></p>
                <p><u>Francesco: Amministratore CCS</u></p>
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

# RimozionePersonale:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Rimozione personale presso il CTT004</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Giacomo: Amministratore CTT004</u></p>
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
                <p><u>Rimozione personale al CCS</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Mattia: Amministratore CCS</u></p>
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

# NotificaAlert:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Notifica Alert da parte del CTT008 di una sacca di sangue in scadenza accettato dal CTT036</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Pina: Operatore del CTT036</u> </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        1.  Pina, operatore del CTT036, accede al proprio terminale e vede le notifiche delle sacche in scadenza, avendo bisogno di una sacca di gruppo sanguigno A+, clicca sulla notifica di una sacca in scadenza di gruppo A+ del CTT.
      <p>
        1.  Pina, inserisce l'ente richiedente e l'indirizzo dell'ente tramite un form e conferma.
      <p>
        1.  Il CCS notifica il CTT036 che la sua richiesta è stata accettata e che la sacca verrà evasa il prima possibile dal CTT008.

</td>
        </tr>
    </table>
</div>

# EvasioneSacca:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Invio sacca</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Antonio: magazziniere CTT002</u></p>
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

# ReportStatisticiCCS:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Report sacche inviate dal CTT003 nell’ultimo mese</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Francesco: Amministratore CCS</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	La direzione regionale vuole informarsi sul numero di sacche che il CTT003 ha inviato verso l'esterno nell'ultimo mese.
      <p>
        2.	L’amministratore Francesco apre il terminale, esegue il login al Sistema CCS inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema.
      <p>
        3.	Francesco richiede al sistema il numero di sacche inviate nell’ultimo mese dal CTT003.
      <p>
        4.  Il terminale mostra una schermata dove sono presenti le sacche inviate dal CTT003 nell’ultimo mese. 

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
                <p><u>Report sacche ricevute dal CTT009 nell’ultimo mese</u>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Francesco: Amministratore CCS</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	La direzione regionale vuole informarsi sul rendimento del CTT009, in particolare vuole individuare le sacche ricevute nell'ultimo mese da quest'ultimo.
      <p>
        2.	L’amministratore Francesco apre il terminale, esegue il login al Sistema CCS inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema.
      <p>
        3.	Francesco richiede al sistema il numero di sacche ricevute nell’ultimo mese dal CTT009.
      <p>
        4.  Il terminale mostra una schermata dove sono presenti le sacche ricevute dal CTT009 nell’ultimo mese.

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
                <p><u>Indagine regionale dello stato dei magazzini</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Francesco: Amministratore CCS</u></p>
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
                <p><u>Report statistico regionale dei magazzinieriCTT</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Francesco: Amministratore CCS</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	La direzione regionale vuole ricavare informazioni relative al numero di magazzinieri presenti nei vari CTT;
      <p>
        2.	L’amministratore Francesco apre il terminale, esegue il login al Sistema CCS inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        3.	Compilando un form, Francesco richiede la lista di tutti gli magazzinieri a livello regionale;
      <p>
        4.  Il terminale mostra una schermata dove sono riportati tutti i magazzinieri con le relative infomazioni.

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
                <p><u>Report statistico regionale degli operatori CTT</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Francesco: Amministratore CCS</u></p>
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

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Report statistico giacenza media sacche</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Francesco: Amministratore CCS</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	La direzione regionale, al fine di monitorare lo stato dei magazzini, richiede all'amministratoreCCS Francesco di effettuare ricerche relative al tempo medio di giacenza delle sacche nei vari CTT;
      <p>
        2.	L’amministratore Francesco apre il terminale, esegue il login al Sistema CCS inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        3.	Compilando un form, Francesco richiede la lista delle giacenze medie delle sacche nei vari CTT;
      <p>
        4.  Il terminale mostra una schermata dove sono riportate per ogni CTT la giacenza media delle sacche che immagazzina.

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

# ReportStatisticiCTT:


<div style="Height:30px">
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Controllo locale operatori del CTT006</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Francesco: Amministratore CTT006</u></p>
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
        3.	Francesco, clicca sulla Query Dipendenti, poi cliccando su un bottone richiede la lista di tutti gli operatori del CTT006
      <p>
        1.  Il terminale mostra una schermata dove sono riportati tutti gli operatori con le relative infomazioni.  

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
                <p><u>Report delle scorte di sangue A+ presenti nel magazzino del CTT034</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Roberto: Amministratore CTT034</u></p>
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
        3.	Roberto clicca sulla Query Disponibilità Sacche;
      <p>
        4.  Roberto visualizza un istogramma raffigurante il numero di sacche di tipo A+ presenti nel magazzino al momento della ricerca.

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
                <p><u>Report delle scorte di sangue A+ presenti nel magazzino del CTT034</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Roberto: Amministratore CTT034</u></p>
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
        3.	Roberto clicca sulla Query Disponibilità Sacche;
      <p>
        4.  Roberto visualizza un istogramma raffigurante il numero di sacche di tipo A+ presenti nel magazzino al momento della ricerca.

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
                <p><u>Analisi delle richieste dei vari gruppi di sangue ricevute dal CTT004 nell'ultimo mese</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Giorgia: Amministratore CTT004</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Giorgia vuole fare un controllo a livello locale per capire il numero di sacche di sangue ricevute dal CTT004;
      <p>
        2.	L’amministratoreCTT Giorgia apre il terminale, esegue il login al Sistema CTT inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        3.	Giorgia clicca sulla Query Sacche Ricevute;
      <p>
        4.  Giorgia inserisce l'intervallo temporale (Data inizio intervallo e data fine intervallo) in cui sono state ricevute le sacche.
      <p>
        5.  Giorgia visualizza il risultato della query.

</td>
        </tr>
    </table>
</div>

<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Analisi delle richieste dei vari gruppi di sangue ricevute dal CTT004 nell'ultimo mese</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Piero: Amministratore CTT004</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Piero vuole fare un controllo a livello locale per capire il numero di sacche di sangue ricevute dal CTT004;
      <p>
        2.	L’amministratoreCTT Piero apre il terminale, esegue il login al Sistema CTT inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        3.	Piero clicca sul Report Permanenza Sacche;
      <p>
        1.  Piero visualizza un istogramma raffigurante il numero di sacche di tipo A+ presenti nel magazzino al momento della ricerca.

</td>
        </tr>
    </table>
</div>

<div style="Height:30px">
</div>

# RicercaSacca:
<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Richiesta NON urgente di tre sacche di sangue, con riscontro locale positivo</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Pietro: operatore CTT003</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT003 per tre sacche di sangue compatibili con il tipo A+ che verrà trasfusa in data “30-03-2021”.
      <p>
        2.	Pietro l’operatore del CTT003 riceve la richiesta.
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT003 inserendo i propri dati e tramite il clic su un bottone, accede alla sezione dedicata alla Ricerca Sacca.
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il gruppo A+ presenti sul database CTT003.
      <p>
        5. Le tre sacche di tipo A+ nel magazzino, vengono prenotate e viene inoltrata una notifica al magazziniere per evadere l'ordine e un riscontro positivo all'operatore Pietro.

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
                <p><u>Richiesta urgente di cinque sacche di sangue di gruppo A-, con riscontro locale positivo</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Pietro: operatore CTT003</u></p>
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
        3.	Pietro apre il terminale, esegue il login al Sistema CTT003 inserendo i propri dati e tramite il clic su un bottone, accede alla sezione dedicata alla Ricerca Sacca.
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il gruppo A- presenti sul database CTT003.
      <p>
        5. Le cinque sacche di tipo A- nel magazzino, vengono prenotate e viene inoltrata una notifica al magazziniere per evadere l'ordine e un riscontro positivo all'operatore Pietro.

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
                <p><u>Richiesta NON urgente di una sacca di sangue, con riscontro locale negativo e regionale positivo</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Pietro: operatore CTT004</u></p>
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
        3.	Pietro apre il terminale, esegue il login al Sistema CTT003 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        4.  Compilando un form, Pietro ricerca la sacca compatibile con il gruppo A+ presenti sul database CTT004;
      <p>
        5.  Viene mostrata una notifica a schermo sul Terminale dell'OperatoreCTT Pietro, che avvisa che verrà contattato il CCS per ricercare la sacca a livello regionale;
      <p>
        6.  Il CCS notifica il CTT003 che l'ordine è stato evaso con successo e che gli arriverà la sacca dal CTT011.


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
                <p><u>Richiesta urgente di una sacca di sangue di tipo A+, con riscontro locale negativo e regionale positivo</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Pietro: operatore CTT003</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT03 per una richiesta di una sacca di sangue compatibile con il tipo A- che verrà trasfusa in data odierna;
      <p>
        2.	Pietro riceve la richiesta;
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT003 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        4.  Compilando un form, Pietro ricerca la sacca compatibile con il gruppo A+ presenti sul database CTT004;
      <p>
        5.  Viene mostrata una notifica a schermo sul Terminale dell'OperatoreCTT Pietro, che avvisa che verrà contattato il CCS per ricercare la sacca a livello regionale;
      <p>
        6.  Il CCS notifica il CTT003 che l'ordine è stato evaso con successo e che gli arriverà la sacca dal CTT011
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
                <p><u>Richiesta urgente di sei sacche di sangue di tipo A+, con riscontro locale negativo e regionale positivo</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Pietro: operatore CTT003</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT003 per sei sacche di sangue compatibili con il tipo A+ che verranno trasfuse in data odierna;
      <p>
        2.	Pietro riceve la richiesta;
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT003 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il gruppo A+ presenti sul database CTT004;
      <p>
        5.  Pietro visualizza la notifica che afferma che non sono state trovate sacche compatibili localmente e che le restanti sei sacche verranno richieste a livello regionale tramite CCS;
      <p>
        6.  Il CCS notifica il CTT003 che l'ordine è stato evaso con successo e che gli arriverà la sacca dal CTT011
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
                <p><u>Richiesta NON urgente di sei sacche di sangue di tipo A+, con riscontro locale negativo e regionale positivo</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Pietro: operatore CTT003</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT004 per sei sacche di sangue compatibili con il tipo A+ che verranno trasfuse in data “28-03-2021”;
      <p>
        2.	Pietro riceve la richiesta;
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT003 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il gruppo A+ presenti sul database CTT004;
      <p>
        5.  Pietro visualizza la notifica che afferma che non sono state trovate sacche compatibili localmente e che le restanti sei sacche verranno richieste a livello regionale tramite CCS;
      <p>
        6.  Il CCS notifica il CTT003 che l'ordine è stato evaso con successo e che gli arriverà la sacca dal CTT011
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
                <p><u>Richiesta NON urgente di otto sacche di sangue, con riscontro locale parziale e regionale negativo</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Pietro: operatore CTT004</u></p>
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
        5.  Pietro visualizza la notifica che afferma che sono state trovate solo cinque sacche compatibili e che le restanti 3 sacche verranno richieste a livello regionale tramite CCS. 
      <p>
        6.  Il Sistema CCS spedisce un messaggio tramite la piattaforma al CTT004, notificando che le sacche mancanti non sono state trovate.  

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
                <p><u>Richiesta urgente di otto sacche di sangue, con riscontro locale parziale e regionale negativo</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Pietro: operatore CTT004</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT003 per otto sacche di sangue compatibili con il tipo A+ che verranno trasfuse in data odierna;
      <p>
        2.	Pietro riceve la richiesta;
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT004 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il tipo A+ presenti sul database CTT004;  
      <p>
        5.  Pietro visualizza la notifica che afferma che sono state trovate solo cinque sacche compatibili e che le restanti 3 sacche verranno richieste a livello regionale tramite CCS. 
      <p>
        6.  Il Sistema CCS spedisce un messaggio tramite la piattaforma al CTT004, notificando che le sacche mancanti non sono state trovate.  

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
                <p><u>Richiesta urgente di tre sacche di sangue, con riscontro locale negativo e regionale negativo</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Pietro: operatore CTT004</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT004 per tre sacche di sangue compatibili con il tipo 0- che verranno trasfuse in data odierna;
      <p>
        2.	Pietro riceve la richiesta;
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT004 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il tipo 0- presenti sul database CTT004;
      <p>
        5.  Pietro visualizza la notifica che afferma che non sono state trovate sacche compatibili localmente e che le restanti tre sacche verranno richieste a livello regionale tramite CCS.
      <p>
        6.  Il Sistema CCS spedisce un messaggio tramite la piattaforma al CTT004, notificando che nessuna sacca non è stata trovata.

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
                <p><u>Richiesta NON urgente di tre sacche di sangue, con riscontro locale negativo e regionale negativo</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p> <u>Pietro: operatore CTT004 </u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il personale medico contatta il CTT004 per tre sacche di sangue compatibili con il tipo A+ che verranno trasfuse in data “28-03-2021”;
      <p>
        2.	Pietro riceve la richiesta;
      <p>
        3.	Pietro apre il terminale, esegue il login al Sistema CTT004 inserendo i propri dati e attraverso un’interfaccia naviga fino ad una sezione dedicata alle interrogazioni del sistema;
      <p>
        4.  Compilando un form, Pietro ricerca le sacche compatibili con il tipo 0- presenti sul database CTT004;
      <p>
        5.  Pietro visualizza la notifica che afferma che non sono state trovate sacche compatibili localmente e che le restanti tre sacche verranno richieste a livello regionale tramite CCS.
      <p>
        6.  Il Sistema CCS spedisce un messaggio tramite la piattaforma al CTT004, notificando che nessuna sacca non è stata trovata.

</td>
        </tr>
    </table>
</div>

# RimozioneSaccheScadute:


<div>
    <table>
        <tr>
            <td width="300px">
                <p style="font-style: italic; font-size: 16px">Scenario name:</p>
            </td>
            <td>
                <p><u>Rimozione sacche scadute </u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Partecipating actor instances:</p>
            </td>
            <td>
                <p><u>Tempo</u></p>
                <p><u>Andrea: magazziniere CTT002</u></p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: italic; font-size: 16px">Flow of events:</p>
            </td>
            <td>
        <p>
        1.	Il CTT002 dopo il controllo schedulato all'1 di notte, si accorge che una lista di sacche di sangue sono scadute;
      <p>
        2.	Il CTT002 rimuove automaticamente le sacche dal database e invia una o più notifiche sul terminale del magazziniere per permetterne lo smaltimento.
        <p>
        3. Andrea, il magazziniere del CTT002 appena inizia il suo turno lavorativo alle 8:30, si occupa di smaltire le sacche scadute accedendo all'apposita sezione  e stampando il PDF dei vari seriali.

</td>
        </tr>
    </table>
</div>
