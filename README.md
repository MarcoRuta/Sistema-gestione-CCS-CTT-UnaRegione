#
# Introduzione al sistema

Il sistema progettato è composto da due diversi entità ben definite:

>**Sistema CTT**: magazzino dedito alla gestione di scorte di sangue.  
>**Sistema CCS**: sistema di controllo dei vari CTT distribuiti all'interno della regione.    

Il sistema è composto da più CTT ed unico CCS, opportunamente distribuiti in rete, i quali contribuiscono ad una gestione ottimale delle scorte di sangue a livello regionale.

Le funzionalità offerte da un singolo CTT sono:

>[Aggiungere una nuova sacca sul sistema](#Aggiungere-una-nuova-sacca-sul-sistema)  
>[Effettuare una ricerca di sacche per conto di un ente esterno](#Effettuare%20una%20ricerca%20di%20sacche%20per%20conto%20di%20un%20ente%20esterno)   
>[Evadere un ordine di sacche verso un ente esterno](#Evadere%20un%20ordine%20di%20sacche%20verso%20un%20ente%20esterno)   
>[Aggiungere un nuovo dipendente al CTT](#Aggiungere%20un%20nuovo%20dipendente%20al%20CTT)  
>[Rimuovere un dipendente dal CTT](#Rimuovere%20un%20dipendente%20dal%20CTT)  
>[Rimuovere sacche scadute e avvisare il CCS di sacche in scadenza](#Rimuovere%20sacche%20scadute%20e%20avvisare%20il%20CCS%20di%20sacche%20in%20scadenza)  
>[Accettare sacche in scadenza presenti sulla rete](#Accettare%20sacche%20in%20scadenza%20presenti%20sulla%20rete)  

I dipendenti presenti all'interno di un CTT sono:
>[AmministratoreCTT](#AmministratoreCTT)  
>[OperatoreCTT](#OperatoreCTT)   
>[MagazziniereCTT](#MagazziniereCTT) 

Le funzionalità offerte dal CCS sono:

>[Aggiungere una nuovo CTT sulla rete](#Aggiungere%20un%20nuovo%20CTT)  
>[Rimuovere un CTT dalla rete](#Rimuovere%20un%20CTT%20dalla%20rete)   
>[Estendere una ricerca per conto di un CTT sulla rete](#Estendere%20una%20ricerca%20per%20conto%20di%20un%20CTT%20sulla%20rete)   
>[Notificare tutti i CTT delle sacche in scadenza presenti in rete](#Notificare%20tutti%20i%20CTT%20delle%20sacche%20in%20scadenza%20presenti%20in%20rete)   
>[Aggiungere un nuovo Amministratore del CCS](#Aggiungere%20un%20nuovo%20Amministratore%20del%20CCS)  
>[Rimuovere un Amministratore del CCS](#Rimuovere%20un%20Amministratore%20del%20CCS)  

L'unico dipendente del CCS è:
>[AmministratoreCCS](#AmministratoreCCS)  


Strumenti utilizzati:

>[Tools e framework](#Tools%20e%20framework)

>[Linguaggi](#Linguaggi)

>[IDE](#IDE)

>[Controllo della versione e canali di comunicazione](#Controllo%20della%20versione%20e%20comunicazione)

Team di sviluppo:

>[Know the team](#Know%20the%20team)


# Aggiungere una nuova sacca sul sistema

L'operazione di *AggiuntaSacca* viene inizializzata dal **MagazziniereCTT** nel momento in cui un carico di sacche arriva in magazzino;  
I carichi di sacche provengono da Enti autorizzati e quindi non sono necessarie verifiche sui dati delle sacche.   

Le informazioni relative alla sacca sono stampate su un'apposita etichetta che contiene:
- Data di produzione
- Data di scadenza
- Gruppo sanguigno
- Ente donatore 

Il magazziniere si occcupa di caricare sul sistema le singole sacche.
Nel momento in cui viene caricata una sacca il sistema le assegna un nuovo seriale.  
Il seriale è identificativo univoco della sacca all'interno dell'intera rete dei CTT, i primi 6 caratteri indicano quale CTT ha caricato tale sacca sul sistema.

        *************************************************************************************
        Arriva un carico di sacche presso il CTT002

        Il magazziniere del CTT002 carica un ad una le sacche sul sistema

        Ogni sacca viene registrata con un seriale progressivo ma avente radice comune CTT002
        es: CTT002-00000001
        *************************************************************************************





# Effettuare una ricerca di sacche per conto di un ente esterno

L'operazione di *RicercaSacca* viene inizializzata dall' **OperatoreCTT** nel momento in cui un ente esterno, attraverso una richiesta formale (fatta per vie telematiche o di persona), richiede al CTT una fornitura di un certo numero di sacche.

La ricerca di sacche non coinvolge il singolo CTT.
L'ente contatta un CTT (il più vicino, l'affiliato etc...) ma nel caso in cui quel CTT non riesca a soddisfare la richiesta, il CCS si occupa di completare la ricerca sull'intera rete dei CTT.

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

Il sistema si occupa di ricercare sacche compatibili con la ricerca all'interno del Database.
Le sacche vengono ricercate prima dello stesso gruppo sanguigno richiesto e poi dei gruppi sanguigni compatibili, dando priorità alle sacche con data di scadenza più vicina.

`Se il CTT riesce a soddisfare completamente la richiesta dell'ente` al termine dell'operazione di ricerca vengono mostrati all'operatore i seriali delle sacche trovate, in modo che egli possa confermare l'ordine.

Dopo che l'operatore ha confermato l'ordine, viene inoltrata sul terminale del magazziniere una notifica contenente i dati necessari per l'evasione:

- Lista dei seriali da evadere
- Ente richiedente
- Indirizzo ente  

        *************************************************************************************************
        L'ospedale Rummo richiede 15 sacche Ap al CTT005 che devono essere consegnate entro il 26/07/2021
        L'operatore del CTT005 inizializza l'operazione di ricerca

        A seguito della richiesta vengono trovate 13 sacche Ap e 2 Compatibili
        L'operatore visualizza i seriali delle sacche trovate e conferma l'ordine

        Viene inoltrata al magazziniere la notifica contenente i dati dell'evasione
        *************************************************************************************************

        

`Se il CTT riesce a soddisfare parzialmente la richiesta dell'ente` al termine dell'operazione di ricerca vengono mostrati all'operatore i seriali delle sacche trovate, in modo che egli possa confermare l'ordine.

Viene informato l'operatore che è stato contattato il **CCS** per completare l'ordine e viene inizializzata un'operazione di [ricerca sacca sull'intera rete](#Estendere%20una%20ricerca%20per%20conto%20di%20un%20CTT%20sulla%20rete) 

Dopo che l'operatore ha confermato l'ordine, viene inoltrata sul terminale del magazziniere una notifica contenente i dati necessari per l'evasione.


- Lista dei seriali da evadere
- Ente richiedente
- Indirizzo ente

Il CCS una volta terminata la ricerca globale informerà l'operatore del CTT dell'esito della ricerca globale.

        *************************************************************************************************
        L'ospedale Rummo richiede 15 sacche Ap al CTT005 che devono essere consegnate entro il 26/07/2021

        L'operatore del CTT005 inizializza l'operazione di ricerca
        A seguito della richiesta vengono trovate 8 sacche Ap e 4 Compatibili

        L'operatore visualizza i seriali delle sacche trovate e conferma l'ordine

        L'operatore viene avvisato che è stato contattato il CCS per completare l'ordine

        Viene inoltrata al magazziniere la notifica contenente i dati dell'evasione

        L'operatore visualizza i risultati della ricerca globale
        *************************************************************************************************

`Se il CTT non riesce a soddisfare  la richiesta dell'ente` al termine dell'operazione di ricerca viene avvisato l'operatore del fatto che non è stata trovata nessuna sacca.

Viene informato l'operatore che è stato contattato il **CCS** per completare l'ordine e viene inizializzata un'operazione di [ricerca sacca sull'intera rete](#Estendere%20una%20ricerca%20per%20conto%20di%20un%20CTT%20sulla%20rete) 

Il CCS una volta terminata la ricerca globale informerà l'operatore del CTT dell'esito della ricerca globale.

        *************************************************************************************************
        L'ospedale Rummo richiede 5 sacche Ap al CTT005 che devono essere consegnate entro il 26/07/2021

        L'operatore del CTT005 inizializza l'operazione di ricerca

        A seguito della richiesta non vengono trovate sacche compatibili
        L'operatore viene avvisato che è stato contattato il CCS per completare l'ordine

        L'operatore visualizza i risultati della ricerca globale
        *************************************************************************************************        





# Evadere un ordine di sacche verso un ente esterno

L'operazione di *EvasioneOrdine* viene inizializzata dal **MagazziniereCTT** nel momento in cui viene visualizzata sul suo terminale una notifica di evasione.

Il magazziniere si occupa di rimuovere fisicamente le sacche dal magazzino del CTT e di preparare l'ordine da consegnare al corriere.
Queste operazioni vengono eseguite appena arriva una notifica, in modo da poter consegnare gli ordini agli enti richiedenti il prima possibile. 

I dati presenti sulla notifica sono:

- Lista dei seriali da evadere
- Ente richiedente
- Indirizzo ente richiedente

Quando il magazziniere interagisce con una notifica il sistema stampa un PDF contenenti tutti i dati dell'evasione, utilizzabile come lettera di vettura per il corriere.

        ***************************************************************************************
        Il magazziniere del CTT001 riceve una notifica di evasione delle sacche:
        CTT001-0000000012,CTT001-0000000041,CTT001-0000000018
        verso l'ospedale Rummo situato a Via Pacevecchia, 53, 82100 Benevento BN
                
        Il magazziniere interagisce la notifica e stampa la lettera di vettura.

        IL magazziniere si occupa di rimuovere le sacche dal magazzino e di preparare l'ordine.
        ***************************************************************************************


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

        **********************************************************************************************************
        L'amministratore del CTT001 deve aggiungere un nuovo Magazziniere
                
        L'amministratore inserisce tutti i dati del nuovo dipendente e conferma l'inserimento

        Viene consegnato al dipendente un PDF contenente i suoi dati e username e password per accedere al sistema 
        ***********************************************************************************************************

# Rimuovere un dipendente dal CTT

L'operazione di *RimozioneDipendenteCTT* viene inizializzata dall'**AmministratoreCTT** nel momento in cui deve essere rimosso del personale dal CTT.

L'operatore si occupa di ricercare il dipendente all'interno del sistema e di confermare il licenziamento.

Una volta confermato, tutti i dati relativi al dipendente vengono rimossi dal sistema.

        ************************************************************
        L'amministratore deve rimuovere l'operatore Mario Rossi
                
        L'amministratore ricerca Mario Rossi all'interno del sistema

        Vengono rimossi tutti i dati relativi a Mario Rossi
        ************************************************************

# Rimuovere sacche scadute e avvisare il CCS di sacche in scadenza

L'operazione di *GestioneSaccheInScadenza* viene inizializzata in automatico dal sistemaCTT ogni 24h.

Vengono individuate tutte le **sacche scadute** presenti nel magazzino e vengono automaticamente rimosse dal DataBase.
Viene inoltrata al magazziniere una notifica di rimozione sacche scadute contenente i seriali delle sacche da rimuovere dal magazzino.

Oltre alle sacche scadute vengono individuate anche le **sacche in scadenza** nelle prossime 72h e la lista di tali sacche viene inoltrata al CCS, in modo da renderle fruibili da tutta la rete.

        *******************************************************************************************************
        Durante un controllo periodico vengono individuate 3 sacche scadute e 12 in scadenza nelle prossime 72h

        Le 3 sacche scadute vengono rimosse dal Database e viene notificato il magazziniere di smaltirle

        Viene notificato il CCS delle 12 sacche in scadenza presenti nel CTT
        ********************************************************************************************************



# Accettare sacche in scadenza presenti sulla rete

L'operazione di *AccettazioneSaccaInScadenza* viene inizializzata dall' **OperatoreCTT** nel momento in cui un ente esterno, attraverso una richiesta formale (fatta per vie telematiche o di persona), richiede al CTT una fornitura di un certo numero di sacche e l'operatore individua nella lista delle sacche in scadenza presenti in rete una sacca compatibile.

L'operatore interagisce con la notifica inviatagli dal CCS relativa alla sacca in scadenza ed inserisce i dati necessari per far si che quella sacca venga evasa:

- Ente richiedente
- Indirizzo ente

Una volta confermata la richiesta il CCS si impegna ad inoltrare tale richiesta al CTT che mantiene la sacca in scadenza, in modo da poterla far evadere.

        *******************************************************************************************************
        ​L'ospedale Rummo richiede 1 saca di tipo Ap al CTT005 che deve essere consegnata entro il 26/07/2021

        L'operatore del CTT002 a seguito di una richiesta di 1 sacca di tipo Ap nota che nella lista delle sacche
        in scadenza è presente una sacca di tipo AP del CTT005

        L'operatore del CTT002 richiede la sacca inserendo i dati necessari.

        Viene notificato il CCS il quale si occuperà di notificare il CTT005 di evadere quella sacca.
        ********************************************************************************************************

# AmministratoreCTT

# OperatoreCTT

# MagazziniereCTT


# Aggiungere una nuovo CTT sulla rete

# Rimuovere un CTT dalla rete

# Estendere una ricerca per conto di un CTT sulla rete

# Notificare tutti i CTT delle sacche in scadenza presenti in rete 

# Aggiungere un nuovo Amministratore del CCS

# Rimuovere un Amministratore del CCS

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

# Know the team
