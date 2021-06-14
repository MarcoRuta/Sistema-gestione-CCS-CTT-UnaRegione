# Introduzione al sistema

## Definizione del dominio del problema [qui](#Definizione-del-problema)

<p align= "center">
<img src="https://www.unisannio.it/sites/default/files/emblema.png.pagespeed.ce.L9uvAVRynq.png" alt="Unisannio" width= 50%>

Il sistema progettato è composto da due diversi entità ben definite:

>**CTT**: magazzino dedito alla gestione di scorte di sangue.  
>**CCS**: sistema di controllo dei vari CTT distribuiti all'interno della regione.    

Il sistema è composto da più CTT, opportunamente distribuiti sulla rete, ed un CCS che contribuisce ad una gestione ottimale delle scorte di sangue a livello regionale.

Le funzionalità offerte da un singolo CTT sono:

>[Aggiungere una nuova sacca sul sistema](#Aggiungere-una-nuova-sacca-sul-sistema)  
>[Effettuare una ricerca di sacche per conto di un ente esterno](#Effettuare-una-ricerca-di-sacche-per-conto-di-un-ente-esterno)   
>[Evadere un ordine verso un ente esterno](#Evadere-un-ordine-verso-un-ente-esterno)   
>[Aggiungere un nuovo dipendente al CTT](#Aggiungere-un-nuovo-dipendente-al-CTT)  
>[Rimuovere un dipendente dal CTT](#Rimuovere-un-dipendente-dal-CTT)  
>[Rimuovere sacche scadute ed avvisare il CCS di sacche in scadenza](#Rimuovere-sacche-scadute-e-avvisare-il-CCS-di-sacche-in-scadenza)  
>[Accettare sacche in scadenza presenti sulla rete](#Accettare-sacche-in-scadenza-presenti-sulla-rete)  
>[Effettuare query a livello locale](#Effettuare-query-a-livello-locale)

Le funzionalità offerte dal CCS sono:

>[Aggiungere una nuovo CTT sulla rete](#Aggiungere-un-nuovo-CTT)  
>[Rimuovere un CTT dalla rete](#Rimuovere-un-CTT-dalla-rete)   
>[Estendere una ricerca per conto di un CTT sulla rete](#Estendere-una-ricerca-per-conto-di-un-CTT-sulla-rete)   
>[Notificare tutti i CTT delle sacche in scadenza presenti in rete](#Notificare-tutti-i-CTT-delle-sacche-in-scadenza-presenti-in-rete)   
>[Aggiungere un nuovo Amministratore del CCS](#Aggiungere-un-nuovo-Amministratore-del-CCS)  
>[Rimuovere un Amministratore del CCS](#Rimuovere-un-Amministratore-del-CCS)<br>
>[Effettuare query a livello regionale](#Effettuare-query-a-livello-regionale)

I dipendenti presenti all'interno di un CTT sono:
>[AmministratoreCTT](#AmministratoreCTT)  
>[OperatoreCTT](#OperatoreCTT)   
>[MagazziniereCTT](#MagazziniereCTT)

L'unico dipendente del CCS è:  
>[AmministratoreCCS](#AmministratoreCCS)  

Breve cenno sulle collezioni dati permanenti:
>[Collezioni dati permanenti](#Collezioni-dati-permanenti)

Breve cenno sulle scelte architetturali prese:
>[Scelte architetturali](#Scelte-architetturali)


Strumenti utilizzati:

>[Tools e framework](#Tools-e-framework)

>[Linguaggi](#Linguaggi)

>[IDE](#IDE)

>[Controllo della versione e canali di comunicazione](#Controllo-della-versione-e-comunicazione)


# Definizione del problema:

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

# Aggiungere una nuova sacca sul sistema

L'operazione di *AggiuntaSacca* viene inizializzata dal **MagazziniereCTT** nel momento in cui un carico di sacche arriva in magazzino.  
I carichi di sacche provengono da Enti autorizzati e quindi non sono necessarie verifiche sui dati delle sacche.   

Le informazioni relative ad ogni singola sacca sono stampate su un'apposita etichetta che contiene:
- Data di produzione
- Data di scadenza
- Gruppo sanguigno
- Ente donatore 

Il magazziniere si occupa di caricare sul sistema le sacche una per volta.
Nel momento in cui viene caricata una sacca il sistema le assegna un nuovo seriale.  
Il seriale rapresenta l'identificativo univoco della sacca all'interno dell'intera rete dei CTT. 
Tutti i seriali presentano la medesima struttura: 15 caratteri di cui i primi 6 caratteri indicano quale CTT ha caricato tale sacca sul sistema, un trattino e i restanti 8 il numero progressivo della sacca.

        *************************************************************************************
        Arriva un carico di sacche presso il CTT002

        Il magazziniere del CTT002 carica una ad una le sacche sul sistema

        Ogni sacca viene registrata con un seriale progressivo ma avente la radice comune CTT002
        es: CTT002-00000001
        *************************************************************************************

# Effettuare una ricerca di sacche per conto di un ente esterno

L'operazione di *RicercaSacca* viene inizializzata dall' **OperatoreCTT** nel momento in cui un ente esterno, attraverso una richiesta formale (fatta per vie telematiche o di persona), richiede al CTT una fornitura di un certo numero di sacche.

La ricerca di sacche non sempre coinvolge il singolo CTT.
Nel momento in cui l'ente contatta un CTT (il più vicino, l'affiliato etc...) e quest'ultimo non riesca a soddisfare in pieno la richiesta, il CCS si occupa di completare la ricerca sull'intera rete dei CTT.

La ricerca coinvolge quindi l'intera *rete* CTT/CCS.

Se la richiesta non è prioritaria la *rete* si impegna a consegnare all'ente le sacche entro la data massima di arrivo prevista, richiedendo, in caso di ricerca locale non completa, le sacche ai CTT che ne hanno maggior disponibilità del tipo richiesto. 

Se la richiesta è prioritaria la *rete* si impegna a consegnare all'ente le sacche entro la data massima di arrivo prevista, richiedendo, in caso di ricerca locale non completa, le sacche ai CTT più vicini al CTT a cui è stata fatta la richiesta. 

I dati necessari per poter effettuare una ricerca sono:

- Gruppo sanguigno
- Numero di sacche
- Data massima di arrivo
- Ente richiedente
- Indirizzo ente
- Priorità

Il sistema si occupa di ricercare sacche compatibili con la richiesta all'interno del Database.
Le sacche vengono ricercate prima dello stesso gruppo sanguigno richiesto e poi dei gruppi sanguigni compatibili, dando priorità alle sacche con data di scadenza più vicina.

`Se il CTT riesce a soddisfare completamente la richiesta dell'ente` al termine dell'operazione di ricerca vengono mostrati all'operatore i seriali delle sacche trovate, in modo che egli possa confermare l'ordine.

Dopo che l'operatore ha confermato l'ordine, viene inoltrata sul terminale del magazziniere una notifica contenente i dati necessari per l'evasione:

- Lista dei seriali da evadere
- Ente richiedente
- Indirizzo ente  

        *************************************************************************************
        L'ospedale Rummo richiede 15 sacche A+ al CTT005 che devono essere consegnate entro il 26/07/2021.
        L'operatore del CTT005 inizializza l'operazione di ricerca.

        A seguito della richiesta vengono trovate 13 sacche A+ e 2 Compatibili.
        L'operatore visualizza i seriali delle sacche trovate e conferma l'ordine.

        Viene inoltrata al magazziniere la notifica contenente i dati dell'evasione.
        *************************************************************************************

`Se il CTT riesce a soddisfare parzialmente la richiesta dell'ente` al termine dell'operazione di ricerca vengono mostrati all'operatore i seriali delle sacche trovate, in modo che egli possa confermare l'ordine.

Viene informato l'operatore che è stato contattato il **CCS** per completare l'ordine e viene inizializzata un'operazione di [ricerca sacca sull'intera rete](#Estendere-una-ricerca-per-conto-di-un-CTT-sulla-rete) 

Dopo che l'operatore ha confermato l'ordine, viene inoltrata sul terminale del magazziniere una notifica contenente i dati necessari per l'evasione.

- Lista dei seriali da evadere
- Ente richiedente
- Indirizzo ente

Il CCS una volta terminata la ricerca globale informerà l'operatore del CTT dell'esito della ricerca globale.

        *************************************************************************************
        L'ospedale Rummo richiede 15 sacche A+ al CTT005 che devono essere consegnate entro il 26/07/2021.

        L'operatore del CTT005 inizializza l'operazione di ricerca.
        A seguito della richiesta vengono trovate 8 sacche Ap e 4 Compatibili.

        L'operatore visualizza i seriali delle sacche trovate e conferma l'ordine.

        L'operatore viene avvisato che è stato contattato il CCS per completare l'ordine.

        Viene inoltrata al magazziniere la notifica contenente i dati dell'evasione.

        L'operatore visualizza i risultati della ricerca globale.
        *************************************************************************************

`Se il CTT non riesce a soddisfare  la richiesta dell'ente` al termine dell'operazione di ricerca viene avvisato l'operatore del fatto che non è stata trovata nessuna sacca.

Viene informato l'operatore che è stato contattato il **CCS** per completare l'ordine e viene inizializzata un'operazione di [ricerca sacca sull'intera rete](#Estendere-una-ricerca-per-conto-di-un-CTT-sulla-rete) 

Il CCS una volta terminata la ricerca globale informerà l'operatore del CTT dell'esito della ricerca globale.

        *************************************************************************************
        L'ospedale Rummo richiede 5 sacche A+ al CTT005 che devono essere consegnate entro il 26/07/2021.

        L'operatore del CTT005 inizializza l'operazione di ricerca.

        A seguito della richiesta non vengono trovate sacche compatibili.
        L'operatore viene avvisato che è stato contattato il CCS per completare l'ordine.

        L'operatore visualizza i risultati della ricerca globale.
        *************************************************************************************        

# Evadere un ordine verso un ente esterno

L'operazione di *EvasioneOrdine* viene inizializzata dal **MagazziniereCTT** nel momento in cui viene visualizzata sul suo terminale una notifica di evasione.

Il magazziniere si occupa di rimuovere fisicamente le sacche dal magazzino del CTT e di preparare l'ordine da consegnare al corriere.
Queste operazioni vengono eseguite appena arriva una notifica, in modo da poter consegnare gli ordini agli enti richiedenti il prima possibile. 

I dati presenti sulla notifica sono:

- Lista dei seriali da evadere
- Ente richiedente
- Indirizzo ente richiedente

Quando il magazziniere interagisce con una notifica il sistema stampa un PDF contenenti tutti i dati dell'evasione, utilizzabile come lettera di vettura per il corriere.

        *************************************************************************************
        Il magazziniere del CTT001 riceve una notifica di evasione delle sacche:
        CTT001-0000000012,CTT001-0000000041,CTT001-0000000018
        verso l'ospedale Rummo situato a Via Pacevecchia, 53, 82100 Benevento BN.
                
        Il magazziniere interagisce con la notifica e stampa la lettera di vettura.

        Il magazziniere si occupa di rimuovere le sacche dal magazzino e di preparare l'ordine.
        *************************************************************************************

# Aggiungere un nuovo dipendente al CTT

L'operazione di *AggiuntaDipendenteCTT* viene inizializzata dall'**AmministratoreCTT** nel momento in cui deve essere aggiunto del personale all'interno del CTT.

L'operatore si occupa di registrare sul sistema i dati fornitogli dal nuovo dipendente.

I dati da inserire sono:

- Codice fiscale
- Nome
- Cognome
- Data di nascita 
- Ruolo
- Username

Quando l'amministratore conferma, viene generato dal sistema un PDF contenente tutti i dati del dipendente completi di username e password autogenerata.
Questo pdf viene affidato al dipendente in modo che egli possa accedere alla propria area personale e impostare una nuova password.

        *************************************************************************************
        L'amministratore del CTT001 deve aggiungere un nuovo Magazziniere.
                
        L'amministratore inserisce tutti i dati del nuovo dipendente e conferma l'inserimento.

        Viene consegnato al dipendente un PDF contenente i dati(username e password) per accedere al sistema. 
        *************************************************************************************

# Rimuovere un dipendente dal CTT

L'operazione di *RimozioneDipendenteCTT* viene inizializzata dall'**AmministratoreCTT** nel momento in cui deve essere rimosso del personale dal CTT.

L'operatore si occupa di ricercare il dipendente all'interno del sistema e di confermare il licenziamento.

Una volta confermato, tutti i dati relativi al dipendente vengono rimossi dal sistema.

        *************************************************************************************
        L'amministratore deve rimuovere l'operatore Mario Rossi.
                
        L'amministratore ricerca Mario Rossi all'interno del sistema tramite il codice fiscale.

        Vengono rimossi tutti i dati relativi a Mario Rossi.
        *************************************************************************************

# Rimuovere sacche scadute e avvisare il CCS di sacche in scadenza

L'operazione di *GestioneSaccheInScadenza* viene inizializzata in automatico dal sistemaCTT ogni 24h alle ore 01:00 di notte.

Vengono individuate tutte le **sacche scadute** presenti nel magazzino e vengono automaticamente rimosse dal DataBase.
Viene inoltrata al magazziniere una notifica di rimozione sacche scadute contenente i seriali delle sacche da rimuovere dal magazzino.

Oltre alle sacche scadute vengono individuate anche le **sacche in scadenza** nelle prossime 72h e la lista di tali sacche viene inoltrata al CCS, in modo da renderle fruibili su tutta la rete.

        *************************************************************************************
        Durante un controllo periodico vengono individuate 3 sacche scadute e 12 in scadenza nelle prossime 72h.

        Le 3 sacche scadute vengono rimosse dal Database e viene notificato il magazziniere di smaltirle.

        Viene notificato il CCS delle 12 sacche in scadenza presenti nel CTT.
        *************************************************************************************

# Accettare sacche in scadenza presenti sulla rete

L'operazione di *AccettazioneSaccaInScadenza* viene inizializzata dall' **OperatoreCTT** nel momento in cui un ente esterno, attraverso una richiesta formale (fatta per vie telematiche o di persona), richiede al CTT una fornitura di un certo numero di sacche e l'operatore individua nella lista delle sacche in scadenza presenti in rete una sacca compatibile.

L'operatore interagisce con la notifica inviatagli dal CCS relativa alla sacca in scadenza ed inserisce i dati necessari per far si che quella sacca venga evasa:

- Ente richiedente
- Indirizzo ente

Una volta confermata la richiesta, il CCS si impegna ad inoltrare tale richiesta al CTT che mantiene la sacca in scadenza, in modo da poterla far evadere.

        *************************************************************************************
        ​L'ospedale Rummo richiede 1 sacca di tipo A+ al CTT005 che deve essere consegnata entro il 26/07/2021.

        L'operatore del CTT002, a seguito di una richiesta di 1 sacca di tipo A+, nota che nella lista delle sacche
        in scadenza è presente una sacca di tipo A+ del CTT005

        L'operatore del CTT002 richiede la sacca inserendo i dati necessari.

        Viene notificato il CCS, il quale si occuperà di notificare il CTT005 di evadere quella sacca.
        *************************************************************************************
# Effettuare query a livello locale

 L'operazione di *inizializzaReportStatisticiCTT*  viene inizializzata dall' **AmministratoreCCT** nel momento in cui è necessario ottenere dei report sull'andamento del CTT sulla rete. Le query effettuabili sono:

- Visualizzare tutti i dipendenti che lavorano nel CTT
- Verificare la disponibilità delle sacche
- Verificare quante e quali sacche sono state inviate
- Verificare quante e quali sacche sono state ricevute
- Verificare quali e per quanto tempo le sacche sono rimaste in magazzino

# Aggiungere una nuovo CTT sulla rete

L'operazione di *AggiuntaCTT* viene inizializzata dall' **AmministartoreCCS** nel momento in cui riceve la richiesta di aggiungere un nuovo CTT sulla rete regionale.  
Ogni CTT può scegliere liberamente di partecipare alla rete regionale o continuare ad esserne distaccato. 
Le informazioni relative ad ogni singolo CTT sono:

- Città
- Indirizzo
- Telefono
- Email
- Latitudine
- Longitudine

L'amministratore si occcupa di caricare sul sistema le informazioni del CTT e sottomettere la domanda.
Nel momento in cui viene caricato un CTT esso sarà inserito nella mappa dei CTT e da quel momento in poi può interagire attivamente nella gestione delle scorte di sangue.  

        *************************************************************************************
        Arriva la richiesta da parte del CTT numero 1 di essere inserito nella rete.

        L'amministratore del CCS inserisce i dati nel sistema.

        Il sistema risponde con una notifica CTT aggiunto correttamente.
        *************************************************************************************

# Rimuovere un CTT dalla rete
L'operazione di *RimozioneCTT* viene inizializzata dall'**AmministratoreCCS** nel momento in cui deve essere rimosso dal suo sistema uno specifico CTT.
L'amministratore si occupa di ricercare il CTT all'interno del sistema e di confermare la sua rimozione.
Una volta confermata, tutti i dati relativi al CTT vengono rimossi dal suo sistema.
A questo punto il CTT rimosso dal sistema CCS può continuare la sua funzione unicamente in locale.
Nonostante la rimozione, il sistema CCS è abilitato ad accettare richieste di adesione da CTT rimossi in passato.

        *************************************************************************************
        L'amministratore deve rimuovere il CTT001.
                
        L'amministratore ricerca il CTT001 all'interno del sistema tramite il nome.

        Vengono rimossi tutti i dati relativi al CTT001.
        *************************************************************************************

# Estendere una ricerca per conto di un CTT sulla rete

L'operazione di RicercaGlobale viene fatta partire dal CCS nel momento in cui la RicercaLocale, avviata da un'operatore di un CTT online, non va a buon fine.
Le motivazioni possono essere o l'assenza completa di sacche compatibili con la ricerca in locale  oppure la parziale presenza delle sacche ricercate.

L'operatore del CTT richiedente riceve sul suo terminale una notifica secondo cui è stata inoltrata la stessa richiesta eseguita localmente sugli altri CTT online.
Dopo pochi secondi viene mostrato il risultato della ricerca all'interno di un pulsante a comparsa adottato solo per le ricerche globali.

In caso di ricerca prioritaria le sacche verranno ricercate fra i CTT più vicini al richiedente. Mentre, se la ricerca non è prioritaria, le sacche saranno ricercate fra i CTT che ne possiedono di più di quel tipo.

In ogni caso la ricerca globale può finire in tre modi:

- la richiesta è stata soddisfatta in toto. In questo caso verranno notificati: l'operatore del CTT richiedente per informarlo che le sacche sono state trovate e che arriveranno il prima possibile; ogni magazziniere del/dei CTT che dovranno inviarle.
- la richiesta è stata soddisfatta parzialmente. In questo caso verranno notificati: l'operatore del CTT richiedente per informarlo che alcune sacche sono state trovate e che arriveranno il prima possibile; ogni magazziniere del/dei CTT che dovranno inviarle.
- la richiesta non è stata soddisfatta da nessun CTT e l'iterazione finisce lì.

         *************************************************************************************
        L'operatore del CTT001 effettua una ricerca locale non prioritaria per 7 sacche compatibili col gruppo sanguigno 0+. 
                
        Nel database locale vengono trovate solo 4 sacche compatibili con la ricerca. A questo punto il CCS provvede ad inoltrare la richiesta a livello regionale. 

        L'operatore riceve la notifica che le 3 sacche rimanenti saranno inviate dal CTT003 e che il magazziniere di quest'ultimo è stato contattato per avviare l'evasione.
        *************************************************************************************

        L'operatore del CTT001 effettua una ricerca locale prioritaria per 7 sacche compatibili col gruppo sanguigno 0-. 
                
        Nel database locale vengono trovate solo 5 sacche compatibili con la ricerca. A questo punto il CCS provvede ad inoltrare la richiesta a livello regionale. 

        L'operatore riceve la notifica che è stata trovata una sola sacca, che sarà inviata dal CTT005 e che il magazziniere di quest'ultimo è stato contattato per avviare l'evasione.
        *************************************************************************************

        L'operatore del CTT001 effettua una ricerca locale non prioritaria per 12 sacche compatibili col gruppo sanguigno AB+. 
                
        Nel database locale vengono trovate solo 4 sacche compatibili con la ricerca. A questo punto il CCS provvede ad inoltrare la richiesta a livello regionale. 

        L'operatore riceve la notifica che non sono state trovate sacche compatibili con la ricerca.
        *************************************************************************************

# Notificare tutti i CTT delle sacche in scadenza presenti in rete 

Ogni CTT alle 01:00 effettua il controllo delle sacche in scadenza. Ogni volta che una sacca arriva a 72h o meno dalla data di scadenza, viene avvisato il CCS della presenza di tale sacca. Il CCS prende le informazioni di questa sacca e le inoltra sui terminali di tutti gli operatori dei vari CTT online, in modo che possa essere prenotata da chiunque ne necessiti. 

        *************************************************************************************

        Il CTT003 individua una sacca in scadenza fra tre giorni ed avvisa il CCS.

        Il CCS inoltra i dati della sacca ai vari CTT. 

        La notifica con i dati della sacca arriva sul terminale dell'operatore del CTT002, che decide di prenotarla.
        *************************************************************************************

# Aggiungere un nuovo Amministratore del CCS

L'operazione di *AggiuntaDipendenteCCS* viene inizializzata dall'**AmministratoreCCS** nel momento in cui deve essere aggiunto del personale all'interno del CCS.
L'amministratore si occupa di registrare sul sistema i dati fornitogli dal nuovo dipendente.
I dati da inserire sono:

- Codice fiscale
- Nome
- Cognome
- Data di nascita 
- Ruolo
- Username

Quando l'amministratore conferma, viene generato dal sistema un PDF contenente tutti i dati del dipendente completi di username e password autogenerata.
Questo pdf viene affidato al dipendente in modo che egli possa accedere alla propria area personale e impostare una nuova password.

        *************************************************************************************
        L'amministratore del CCS deve aggiungere un nuovo AmministratoreCCS.

        L'amministratore inserisce tutti i dati del nuovo dipendente e conferma l'inserimento.

        Viene consegnato al dipendente un PDF contenente i dati(username e password) per accedere al sistema. 
        *************************************************************************************

# Rimuovere un Amministratore del CCS

L'operazione di *RimozioneDipendenteCCS* viene inizializzata dall'**AmministratoreCCS** nel momento in cui deve essere rimosso un amministratore dal CCS.
L'amministratore si occupa di ricercare il dipendente all'interno del sistema e di confermare il licenziamento.
Una volta confermato, tutti i dati relativi al dipendente vengono rimossi dal sistema.

        *************************************************************************************
        L'amministratore deve rimuovere l'amministratore Pietro Neri.

        L'amministratore ricerca Pietro Neri all'interno del sistema tramite il codice fiscale.

        Vengono rimossi tutti i dati relativi a Pietro Neri.
        *************************************************************************************
# Effettuare query a livello regionale
  L'operazione di *inizializzaReportStatisticiCCS* viene inizializzata dall' **AmministratoreCCS** nel momento in cui è necessario ottenere dei report 
  sull'andamento di tutti i CTT presenti sulla rete. Le [query](#Effettuare-query-a-livello-locale) effettuabili corrispondono a quelle presenti in locale ma estese all'intera rete di distribuzione delle sacche di sangue.

# AmministratoreCTT

L'**amministratoreCTT** è un dipendente di un Centro Tasfusionale Territoriale.
Possiede i requisiti necessari per poter accedere al terminale dell'amministratore e si occupa della gestione del CTT attraverso delle funzionalità specificatamente designate per il suo ruolo: 

- Aggiungere/rimuovere DipendentiCTT dal CTT presso cui lavora.
- Effettuare dei report statistici per avere una visione globale dell'andamento del CTT tramite un sistema di query dinamico. Grazie ad appositi elementi dell'interfaccia grafica, gli è consentito di visualizzare risultati, istogrammi e grafici a torta in tempo reale.

# OperatoreCTT

L'**operatoreCTT** è un dipendente di un Centro Trasfusionale Territoriale.
Possiede i requisiti necessari per poter accedere al terminale dell'operatore e si occupa della gestione delle comunicazioni con gli enti autorizzati ad effettuare richieste al CTT.
Le funzionalità specificatamente designate per il 
suo ruolo sono:

- Accettare delle sacche in scadenza a seguito di un Alert.
- Ricercare sacche a livello locale/regionale tramite un apposito form.

# MagazziniereCTT

Il **magazziniereCTT** è un dipendente di un Centro Trasfusionale Territoriale.
Possiede i requisiti neccessari per poter accedere al terminale del magazziniere e si occupa della gestione del magazzino tramite interazioni con il database locale del CTT. Le funzionalità specificatamente designate per il suo ruolo sono:

- Aggiungere sacche
- Evadere sacche
- Smaltire le sacche scadute

# AmministratoreCCS

L'**amministratoreCCS** e un dipendente di un Centro Controllo e smistamento .
Possiede i requisiti neccessari per poter accedere al terminale del magazziniere
e si occupa della gestione dell'intera rete di distribuzione di sacche di sangue.
Le funzionalità specificatamente designate per il suo ruolo sono:

- Aggiungere/rimuovere CTT dalla rete
- Aggiungere/rimuovere AmministratoriCCS
- Effettuare dei report statistici per avere una visione globale dell'andamento dell'intera rete tramite un sistema di query dinamico. Grazie ad appositi elementi dell'interfaccia grafica, gli è consentito di visualizzare risultati, istogrammi e grafici a torta in tempo reale.



# Scelte architetturali
Al fine di avere un sistema che fosse `leggero e veloce` quindi utilizzabile al meglio anche su computer di fascia medio bassa, abbiamo deciso di utilizzare ad un alto livello di astrazione una Architettura Client/Server Three Tier a Client leggero.



Nella nostra visione il CTT è un server che espone diversi `EndPointRest`. Il CTT avrà poi a disposizione diversi client che implementano Interface Layer tramite i quali potranno richiedere i servizi esposti dal CTT.

Affinché il sistema possa mantenere le informazioni sulle sacche, e funzionare anche in assenza di connessione, abbiamo deciso di creare un `Database NO-R` in locale data la necessità di salvare pochi dati e di effettuare su di essi delle query abbastanza semplici che non necessitano dell’uso di un database relazionale.

Gli EndPointRest del sistema sono gli unici che interagisco con il database in maniera indiretta tramite un `DataManager`. Concentrandoci quindi solo su questa porzione del sistema, abbiamo strutturato quest’ultima sfruttando una architettura a repository.
 
Ogni `EndPointRest` implementa le funzionalità di un `singolo dipendente`. Quindi esso è stato implementato in modo tale che possa essere acceduto solo da un dipendente precedentemente autentificato e autorizzato.

Il sistema `CTT` presenta poi un'altra componente che è in grado di comunicare con il `CCS` nel momento in cui è possibile stabilire una connessione. Dato che il CTT deve inoltrare le sue *`sacche in scadenza`* al CCS, in modo che esso possa farne il broadcast agli altri CTT, ma allo stesso tempo deve essere in grado di ricevere delle sacche in scadenza dal CCS senza previa richiesta, abbiamo deciso di realizzare questa parte del sistema sfruttando un'architettura `Peer to Peer`.

Per realizzare questo tipo di architettura abbiamo utilizzato il protocollo `WebSocket` cosicchè tra CTT e CCS ci fosse un canale di comunicazione che permettesse ai due di ricevere senza nessuna richiesta iniziale ed in `tempo reale` la lista sempre aggiornata delle sacche in scadenza.

Per quanto riguarda la *`ricerca globale`* essa è stata modellata sempre sfruttando un'architettura Client/Server in stile `REST` in quanto in questo caso è necessario fornire delle sacche solo dopo aver effettuato una richiesta da parte del client.

Per quanto riguarda i client abbiamo sfruttato lo stile `MVC` implementandolo utilizzando l’`observer pattern` così da ridurre il coupling tra model e view. 
Difatti i vari client oltre agli esiti delle richieste rest sono interessati anche alle `sacche in scadenza`, alle `sacche da smaltire` e agli `esiti delle ricerche globali`. 
Per far si che essi ricevano queste notifiche in tempo reale abbiamo realizzato dei `sottosistemi` che presentassero dei server endpoint collegati a delle `websocket` collegate ai vari client. 

Il `CCS` nella nostra visione è quindi sia un `Server` che espone `endPointRest` per la ricerca globale, la prenotazione di sacche in scadenza e le funzionalità dell’amministratore del CCS, ma anche un `Client`.

Per evitare `inconsistenze`, il CCS non ha il mirroring dei dati dei vari CTT ma può accedervi tramite una richiesta Rest. Così facendo esso è in grado di fornire sempre una lista di sacche realmente presenti nei DB nel momento in cui viene contattato per una ricerca globale.


# Tools e framework
<img alt="MongoDB" src ="https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white"/>

<img alt="Bootstrap" src="https://img.shields.io/badge/bootstrap-%23563D7C.svg?style=for-the-badge&logo=bootstrap&logoColor=white"/>   

<img alt="Spring" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"/>

<img alt="Apache" src="https://img.shields.io/badge/apache-%23D42029.svg?style=for-the-badge&logo=apache&logoColor=white"/>

# Linguaggi

<img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white"/>

<img alt="JavaScript" src="https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E"/>

<img alt="HTML5" src="https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white"/>
<img alt="CSS3" src="https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white"/>

<img alt="Markdown" src="https://img.shields.io/badge/markdown-%23000000.svg?style=for-the-badge&logo=markdown&logoColor=white"/>

# IDE

<img alt="Visual Studio Code" src="https://img.shields.io/badge/VisualStudioCode-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white"/>
<img alt="IntelliJ IDEA" src="https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white"/>

# Controllo della versione e comunicazione

<img alt="GitHub" src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"/>
<img alt="Discord" src="https://img.shields.io/badge/%3CServer%3E-%237289DA.svg?style=for-the-badge&logo=discord&logoColor=white"/>