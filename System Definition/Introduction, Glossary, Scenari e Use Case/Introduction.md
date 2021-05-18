# Progetto per la fornitura di un sistema software per la gestione integrata delle scorte di sangue per la rete dei centri trasfusionali territoriali di UnaRegione

## 1. Introduction

La Regione “UnaRegione” (committente) intende dotarsi di una soluzione software avanzata per la gestione integrata della giacenze di sangue presso la rete dei Centri Trasfusionali Territoriali.
Problemi da risolvere:

-	Attualmente i Centri Trasfusionali Territoriali gestiscono le scorte di sangue localmente, e questo impedisce la gestione ottimale delle scorte su base Regionale. 

-	Ad esempio, uno specifico Centro Trasfusionale Territoriale che avesse bisogno di una dose di sangue di un certo gruppo, non presente localmente, non ha nessuno strumento per verificarne la presenza presso altri Centri della Regione. 

-	Similmente, se un Centro ha una sacca in scadenza a breve (es. 72 ore), non ha nessuna possibilità di allertare altri Centri che ne avessero eventualmente bisogno, con il rischio che la dose venga lasciata scadere mentre potrebbe essere utilizzata immediatamente in altre aree della Regione.

Il sistema software dovrà possedere le caratteristiche di seguito indicate:
1.	Permette di integrare i diversi Centri Trasfusionali Territoriali (CTT) e un Centro Controllo e Smistamento (CCS) con il compito di raccogliere le informazioni nate in periferia nei CTT e trasmettere ai CTT informazioni di interesse ed alert.
2.	Il colloquio fra centro e periferia deve avvenire in tempo reale attraverso un canale di comunicazione; i sistemi periferici devono comunque essere in grado di garantire la loro piena operatività anche in temporanea assenza di connettività di rete, garantendo il successivo riallineamento dei dati;
3.	Le componenti CTT dovranno presentare funzionalità per:

- a.	Caricare / scaricare una o più dosi di sangue dal proprio magazzino;

- b.	Verificare la disponibilità di dosi di sangue di un certo gruppo localmente e in tutto il territorio Regionale ed eventualmente richiederne il trasferimento presso la propria sede, indicando un livello di priorità della richiesta;

- c.	Aggiungere/rimuovere operatori e magazzinieri

4.	La componente CCS dovrà prevedere operazioni per:

- a.	Definire un nuovo CTT o rimuovere un CTT esistente;

- b.	Raccogliere e gestire i dati necessari alla realizzazione delle operazioni previste per i CTT;

- c.	Allertare gli altri CTT sull’avvicinarsi di date di scadenza mediante messaggi in broadcast.

5.	Il sistema dovrà essere dotato, sia nelle componenti CTT, sia nella componente CCS, di interfacce utente semplici ed intuitive per la realizzazione delle operazioni previste. Tali interfacce dovranno inoltre consentire di impostare report statistici di stampa personalizzati, memorizzabili e riutilizzabili secondo modalità di facile utilizzo per l’utente.

6.	L’installazione della componente centrale della soluzione deve avvenire in modalità on-premises, ovvero presso il data-centre della Regione; quella periferica presso le postazioni distribuite nei diversi CTT. Attualmente sul territorio Regionale sono attivi 43 CTT. La fornitura dovrà prevedere la configurazione di tali centri nel sistema (CCS) nonché l’installazione delle componenti periferiche presso i CCT. 

7.	Il sistema deve fornire le necessarie garanzie sulla sicurezza e sulla riservatezza delle informazioni e dei dati, in funzione della tipologia dei trattamenti gestiti. La fornitura dovrà comprendere la consegna, l’installazione, la configurazione e l’avvio operativo del sistema software, nonché la formazione del personale del CCS e dei CTT.

### 1.1 Osservazioni

-	Se un CTT avvisa il CCS che non ha abbastanza sacche per poter soddisfare una richiesta, il CCS deve essere in grado di scegliere da quale CTT recuperare le sacche, basandosi su criteri di vicinanza e di priorità delle richieste.

Riguardo evasione e ricezione di sacche di sangue, bisogna specificare chi si occupa dell’aggiornamento del database. È necessaria la figura del magazziniere che si occupa di aggiungere/rimuovere le sacche dal database solo quando fisicamente arrivano/lasciano il magazzino, in modo da evitare problemi di inconsistenza.

-	CTT devono funzionare localmente, quindi devono gestire i loro DB autonomamente ed in maniera indipendente dal CCS a cui sono collegati, dunque nel momento in cui una sacca è in scadenza, sarà il CTT che la contiene a notificare il CCS, il quale si occupa di inoltrare a tutti i CTT che possono ricevere tale sacca una notifica. Non può occuparsi il CCS della gestione delle scadenze, altrimenti i CTT non si accorgerebbero di sacche scadure in caso di assenza di connessione.

In caso di sacca prossima alla scadenza, l’alert non viene inviato più volte, è una notifica che rimane nel sistema fino a quando la sacca non viene aggiudicata da un CTT o scade.

La sacca in scadenza viene assegnata dal primo CTT che la richiede, in modo da minimizzare il rischio di farla scadere.

-	Siccome per qualunque operazione non automatica del CTT è previsto un login, l’amministratore del CTT deve essere in grado di aggiungere e rimuovere operatori e tener traccia di username e password. 

-	Lo statement specifica che è possibile impostare report statistici di stampa sia per i CTT, sia per i CCS. In entrambi i casi le query sono effettuate da un amministratore del sistema.

-	Nello statement si afferma che è possibile rimuovere un CTT dalla rete regionale del CCS, bisogna considerare di rimuovere le sacche presenti nel database “attivo” in modo che tali sacche non risulteranno più disponibili per il CCS.



<div style="height: 50px"></div>

## 2. Il metodo seguito
<p style="font-size: 16px">Il metodo di analisi, progettazione e sviluppo prevede i seguenti passi:</p>

### 1. Analisi dei requisiti

-	Costruzione del modello di dominio
-	Specifica dei requisiti funzionali
-	Analisi dei casi d’uso
-	Validazione requisiti–casi d’uso

<div style="Height: 20px"></div>

### 2. Analisi/Progetto preliminare

-	Analisi di robustezza
-	Rifinitura dei casi d’uso e eventuali aggiornamenti al modello di dominio

<div style="Height: 20px"></div>

### 3. Progetto dettagliato
-	Diagrammi di sequenza
-	Assegnazione delle responsabilità (operazioni) alle classi

<div style="Height: 20px"></div>

### 4. Realizzazione

<div style="height: 50px"></div>

## Ruoli CTT
_MagazziniereCTT_ → registra le sacche che arrivano nel magazzino ed evade sacche verso enti esterni.

_OperatoreCTT_ → riceve richieste da enti esterni al sistema e le soddisfa interrogando il sistema .

_AmministratoreCTT_ → aggiunge nuovi operatori al sistema e può eseguire report statistici sul sistema CTT. 

<div style="height: 50px"></div>

## Ruoli CCS
_AmministratoreCCS_ → aggiunge/rimuove CTT e può eseguire report statistici sul sistema CCS.

<div style="height: 50px"></div>

## Rationale:

-	I CTT devono funzionare localmente, quindi devono gestire i loro DB autonomamente ed in maniera indipendente dal CCS a cui sono collegati. Gli scenari di invio e ricezione per renderli possibili anche in assenza di rete (purchè la richiesta sia stata effettuata in precendenza al problema di rete).

-	L’operazione di evasione sacche presso un CTT viene sempre inizializzata dalla lettura di una notifica, inviata dal sistema CTT o CCS sul terminale magazziniereCTT. Nella notifica è specificato il seriale delle sacche da evadere e i dati dell’ente richiedente. 

-	L’operazione di ricezione sacca presso un CTT viene inizializzata dal magazziniere quando arriva fisicamente un carico di sacche dall’esterno. Il magazziniere si occupa di inserire i dati presenti sull’etichetta della sacca sul sistema in modo da poter aggiornare il database.

-	L’operazione di ricerca sacca avviene sempre a seguito di una richiesta proveniente dall’esterno (non è possibile per un CTT ordinare sacche senza necessità), se le sacche richieste sono presenti nel database del CTT da cui si effettua la ricerca, attraverso una notifica si avverte il magazziniere di inviare tali sacche all’ente richiedente. Se nel database locale non sono presenti abbastanza sacche per poter soddisfare la richiesta, attraverso una notifica si avverte il CCS di estendere la ricerca a tutti i database dei CTT presenti in rete. Il CCS dopo aver individuato le sacche, attraverso una notifica avverte i magazzinieri dei CTT che le possiedono di inviare tali sacche direttamente all’ente richiedente.

-	Abbiamo deciso che per garantire la massima operatività offline, sono i CTT ad occuparsi del controllo delle sacche in scadenza per poi, connessione permettendo, avvisare il CCS. In questo modo in caso di una sacca scaduta presso un CTT, il CTT stesso avviserà i magazzinieri di smaltire tale sacca. Usiamo data di scadenza piuttosto che data di produzione perche non siamo sicuri che la durata (di vita) sia la stessa per sacche dello stesso tipo.

-	Per poter tenere traccia dello storico sacche di ogni CTT abbiamo modellato il DataBase delle sacche costituito da due parti. Una parte “Attiva”, dove sono mantenuti i dati delle sacche attualmente presenti in magazzino e una parte “Passiva”, dove sono presenti dati sull’ affidamento/ricezione di tutte le sacche passate per quel CTT.




-	Per ogni CTT possiamo pensare di tenere traccia di:
	le sacche presenti fisicamente nel magazzino, prenotate o no (Sacca)
	lo storico delle sacche che sono passate per il magazzino, complete di informazioni sull’ente che ha fornito e sull’ente che ha ottenuto la sacca. (DatiSacca)

-	La divisione in ruoli è stata necessaria per garantire una buona distribuzione delle attività all’interno dei CTT.

-	Il CCS è una rete che interconnette i magazzini dei diversi CTT e che raccoglie dati dai vari CTT per fornire dei report statistici globali. Il CCS deve gestire una comunicazione in broadcast verso i CTT in modo da avvisarli in caso di sacche in scadenza e permettere la fruizione di tali sacche. Il CCS deve riuscire a fornire al singolo CTT una visione “estesa” del suo magazzino, in caso di una ricerca sacche in locale fallita esso inoltra tale ricerca a tutti gli altri CTT in modo da soddisfarla. Dal punto di vista del “cliente”, ovvero degli enti esterni che richiedono sacche presso i CTT, a seguito di una richiesta o il CTT a cui si è fatta domanda o un altro CTT della rete invieranno la sacca richiesta entro una data compatibile.

-	Il CCS può ricevere richieste prioritarie o meno:
	in caso di richieste prioritarie fornisce all'ente richiedente le sacche proveniente dai CTT più vicini a quello da cui è partita la richiesta.
	in caso di richieste non prioritarie fornisce all'ente richiedente sacche provenienti dai CTT che hanno molte sacche di quel tipo in magazzino. Delle richieste che arrivano al CCS è noto: il tipo di sangue richiesto, la data di affidamento e l’ente richiedente (con il suo indirizzo), il CCS in base a questa informazioni sceglie le sacche da inviare.
     

-	Dobbiamo pensare che CTT e CSS sono degli strumenti che gestiscono solamente le sacche nei magazzini e i loro spostamenti, non l’utilizzo o il prelievo delle sacche ma solo la ricezione e l’affidamento di sacche da e ad enti esterni.



