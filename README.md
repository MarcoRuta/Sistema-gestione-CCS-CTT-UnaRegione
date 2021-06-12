<p align= "center">
<img src="https://www.unisannio.it/sites/default/files/emblema.png.pagespeed.ce.L9uvAVRynq.png" alt="Unisannio" width= 50%>
<p align="center">
    <img src="https://img.shields.io/badge/java-v11.0-blue" alt="Java">
    <img src="https://img.shields.io/badge/Unisannio-Ingegneria%20Del%20Software-blue" alt="Unisannio">
    <img src = "https://img.shields.io/badge/maven-v4.0-blue">
    <img src = "https://img.shields.io/badge/junit-v4.13-blue">
    <img src = "https://img.shields.io/badge/mongo--java--driver-v3.12.8-blue">
    <img src = "https://img.shields.io/badge/spring%20boot-v2.4.5-blue">
     <img src = "https://img.shields.io/badge/jersey-v3.0.2-blue">
</p>




# Progetto Ingegneria del Software 2021

Progetto di ingegneria del software svolto dal gruppo formato da:
- Marco La Rocca
- Marco Ruta
- Andrea Albano
- Francesco Cosimo Mazzitelli
- Francesco Pio Belperio
- Pio Antonio Perugini
- Donato Guerrera
- Salvatore Di Maria


## Come Contribuire
- Fai un Fork di questo progetto;
- Clona il tuo Fork e realizza un nuovo Branch per le funzionalità che vuoi implementare;
- Crea una Junit suite per le funzionalità implementate;
- Effettua il push sul tuo repository ed invia una pull-request;
- Se il codice è corretto sarà effettuato il merging sul main branch;



# Sommario

[1. Premessa](#premessa)

[2. ScreenGUI](#GUI)

[3. Mockup](https://centrosmistamento.000webhostapp.com/Index.html)





# Premessa:

La Regione “UnaRegione” (committente) intende dotarsi di una soluzione software avanzata per la gestione integrata della giacenze di sangue presso la rete dei Centri Trasfusionali Territoriali. Attualmente i Centri Trasfusionali Territoriali gestiscono le scorte di sangue localmente, e questo impedisce la gestione ottimale delle scorte su base Regionale. Ad esempio, uno specifico Centro Trasfusionale Territoriale che avesse bisogno di una dose di sangue di un certo gruppo, non presente localmente, non ha nessuno strumento per verificarne la presenza presso altri Centri della Regione. Similmente, se un Centro ha una sacca in scadenza a breve (es. 72 ore), non ha nessuna possibilità di allertare altri Centri che ne avessero eventualmente bisogno, con il rischio che la dose venga lasciata scadere mentre potrebbe essere utilizzata immediatamente in altre aree della Regione. Il sistema software permettere di integrare i diversi Centri Trasfusionali Territoriali (CTT) e un Centro Controllo e Smistamento (CCS) con il compito di raccogliere le informazioni nate in periferia nei CTT, ovvero trasmettere ai CTT informazioni di interesse ed alert. E’ espressamente previsto che il colloquio fra CCT e CCS avvenga in tempo reale attraverso un canale di comunicazione.

## Oggetto della Fornitura:

Il presente capitolato d’appalto ha per oggetto la fornitura del sistema software per la gestione integrata della giacenze di sangue presso la rete dei Centri Trasfusionali Territoriali di cui in premessa secondo le caratteristiche di seguito indicate. Il sistema deve articolarsi in diversi sistemi periferici (per i diversi CTT)) ed in una componente centrale (CCS); quest’ultima deve avere il compito di raccolta delle informazioni nate in periferia o della trasmissione alla periferia di informazioni ed alert; Il colloquio fra centro e periferia deve avvenire in tempo reale attraverso un canale di comunicazione; i sistemi periferici devono comunque essere in grado di garantire la loro piena operatività anche in temporanea assenza di connettività di rete, garantendo il successivo riallineamento dei dati; Le componenti CTT dovranno presentare funzionalità per: Caricare / scaricare una o più dosi di sangue dal proprio magazzino; Verificare la disponibilità di dosi di sangue di un certo gruppo localmente, ovvero in tutto il territorio Regionale ed eventualmente richiederne il trasferimento presso la propria sede, indicando un livello di priorità della richiesta; La componente CCS dovrà prevedere operazioni per: Definire un nuovo CTT o rimuovere un CTT esistente; Raccogliere e gestire i dati necessarie alla realizzazioni delle operazioni previste per i CCT; Allertare i CCT sull’avvicinarsi di date di scadenza mediante messaggi in broadcast. Il sistema dovrà essere dotato, sia nelle componenti CTT sia nella componente CCS, di interfacce utente semplici ed intuitive per la realizzazione delle operazioni previste. Tale interfaccia dovrà inoltre consentire di impostare report statistici di stampa personalizzati, memorizzabili e riutilizzabili secondo modalità di facile utilizzo per l’utente. L’installazione della componente centrale della soluzione deve avvenire in modalità on-premises, ovvero presso il data-centre della Regione; quella periferica presso le postazioni distribuite nei diversi CTT. Attualmente sul territorio Regionale sono attivi 43 CTT, come riportato nell’elenco allegato. La fornitura dovrà prevedere la configurazione di tali centri nel sistema (CCS) nonché l’istallazione delle componenti periferiche presso i CCT. L’elenco deve intendersi indicativo, potendo il committente introdurre delle modifiche sia per ciò che riguarda l’ubicazione di alcuni Centri, sia per quanto riguarda il numero dei Centri da gestire. Il sistema deve fornire le necessarie garanzie sulla sicurezza e sulla riservatezza delle informazioni e dei dati, in funzione della tipologia dei trattamenti gestiti. La fornitura dovrà comprendere la consegna, l’installazione, la configurazione e l’avvio operativo del sistema software, nonché la formazione del personale del CCS e dei CTT.

## Durata:

La durata massima del progetto, comprensiva di installazione, configurazione, formazione e collaudo, è di 15 settimane a partire dalla data di pubblicazione del seguente bando. I gruppi dovranno proporre una temporizzazione delle attività e una descrizione della struttura del progetto e della struttura organizzativa del team entro le prime due settimane. Durante il progetto, il committente si impegna ad assicurare un minimo di 3 incontri in cui sarà possibile discutere e definire l’introduzione di modifiche o nuove funzionalità del sistema di gestione. L’accettazione del prodotto avverrà a valle di una attività di collaudo condotta congiuntamente dal gruppo di lavoro e dal committente o suo rappresentante. In caso di non conformità rilevate durante il collaudo, la consegna è considerata in ritardo e una nuova data per la ripetizione del collaudo sarà concordata fra gruppo e committente. Sono possibili al massimo due ripetizioni, dopo le quali il committente si riserva di rifiutare il prodotto o di accettarlo come non conforme.

## Servizi Aggiuntivi

Il gruppo potrà proporre, dettagliandoli nella documentazione tecnica, eventuali funzionalità aggiuntive e migliorie che intende apportare rispetto a quelli espressamente richiesti nel capitolato, se pertinenti agli ambiti trattati. Funzionalità aggiuntive e/o integrative verranno prese in considerazione dal committente ai fini della valutazione finale.

## Trattamento dei dati:

Il committente si impegna al pieno rispetto della riservatezza dei dati e della documentazione che ciascun gruppo fornirà durante lo sviluppo del progetto. Ciascun gruppo ha l’obbligo di mantenere riservati i dati e le informazioni fornite dal committente, o da questi rese disponibili attraverso sistemi informativi, di non divulgarli in alcun modo e in qualsiasi forma e di non farne oggetto di utilizzazione a qualsiasi titolo per scopi diversi da quelli strettamente necessari all’esecuzione del progetto.

## Responsabilità e obblighi:

I partecipanti a ciascun gruppo di lavoro si impegnano a dare il loro personale contributo all’avanzamento dei lavori del proprio gruppo, secondo i ruoli e i compiti definiti. Eventuali difficoltà o impossibilità, anche temporanee, a portare avanti il proprio carico di lavoro, vanno immediatamente riportata al resto del gruppo di lavoro, e discusse con il committente. Tutti i partecipanti, individualmente e come gruppo, si impegnano ad evitare situazioni di plagio, a non spacciare il lavoro degli altri per il proprio lavoro e a non falsificare i risultati.

# GUI

## Pagina Di Login
<p align = "center">
    <img src = "https://i.ibb.co/sbJQtjD/Login.png" alt = "Login">
</p>

## Terminale Amministratore CCS
<p align= "center">
<img src="https://i.ibb.co/s5Mn2Hw/CCSHub.png" alt="TerminaleAmministratoreCCS">
</p>

## Terminale Amministratore CTT
<p align="center">
    <img src="https://i.ibb.co/zSFXQ2F/AMMCTThub.png" alt="TerminaleAmministratoreCTT">
</p>

## Terminale Magazziniere CTT
<p align="center">
    <img src="https://i.ibb.co/0CsYyFt/Magazz-CTT.png" alt="TerminaleMagazziniere">
</p>

## Terminale Operatore CTT
<p align="center">
    <img src="https://i.ibb.co/BZ0dmk4/OPCTTHub.png" alt="TerminaleOperatoreCTT">
</p>

## Menù di selezione Query CTT
<p align="center">
    <img src="https://i.ibb.co/1LhLsWJ/QueryCTT.png" alt="MenuSelezioneQueryCTT">
</p>

## Menù di selezione Query CCS
<p align="center">
    <img src="https://i.ibb.co/FY0ZVV8/QueryCCS.png" alt="MenuSelezioneQueryCCS">
</p>

##Form N1
<p align="center">
    <img src="https://i.ibb.co/sVQKCGM/Operatori-CTTForm.png" alt="FormN1">
</p>

##Form N2
<p align="center">
    <img src="https://i.ibb.co/fXvQSn4/Ricerca-Sacca-Form.png" alt="FormN2">
</p>

##Form N3
<p align="center">
    <img src="https://i.ibb.co/qDtyrFk/Ordina-Gruppi-Sanguigni-Per-Richieste-Form.png" alt="FormN3">
</p>