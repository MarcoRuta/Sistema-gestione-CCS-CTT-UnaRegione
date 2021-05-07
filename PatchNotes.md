# Patch Notes 1.2
## Release System Definition Document

***Introduction:*** 
- Introduction
       - Osservazioni sullo statement
       - Il metodo seguito
- Ruoli all'interno dei sistemi CTT e CCS
- Rationale
  

***Scenari:***
- Aggiunta sacca da fonte esterna
- Aggiunta Centro Trasfusionale Territoriale (CTT)
- Eliminazione CTT #5 
- Aggiunta personale al CTT #4
- Rimozione personale al CTT #4
- Controllo per Alert di una sacca di sangue in scadenza non prenotata, in seguito ad una richiesta urgente
- Controllo per Alert di una sacca di sangue in scadenza non prenotata
- Controllo per Alert di una sacca di sangue in scadenza non prenotata, senza riscontro
- Invio sacca
- Controllo rendimento dei CTT
- Report statistico regionale tipi di sacche
- Report statistico regionale di operatori CTT
- Report statistico sacche inviate e ricevute dal CTT #3 nell’ultimo mese
- Controllo locale operatori del CTT
- Report di stampa con numero di sacche per tipo, livello locale
- Report sul gruppo sanguigno più richiesto, livello locale
- Richiesta NON urgente di una sacca di sangue, con riscontro locale positivo
- Richiesta urgente di una sacca di sangue, con riscontro locale positivo
- Richiesta NON urgente di una sacca di sangue, con riscontro locale negativo e regionale - positivo
- Richiesta urgente di una sacca di sangue, con riscontro locale negativo e regionale positivo
- Richiesta NON urgente di una sacca di sangue, con riscontro locale negativo e regionale - negativo
- Richiesta urgente di una sacca di sangue, con riscontro locale negativo e regionale negativo
- Rimozione sacca causa trasfusione
- Rimozione sacca scaduta

***UseCase Diagrams:*** 
- Aggiunta CTT
- Rimozione CTT
- Report Statistici CCS
- Report Statistici CTT
- Login
- Ricerca Sacche
- Evasione Sacche
- Aggiunta Sacche
- Eliminazione Sacche Scadute
- Aggiunta Dipendente 
- Rimozione Dipendente
- Inoltro Alert
- Risposta Alert
  
***Sequence Diagrams:*** 
- AggiuntaCTT
- RimozioneCTT
- ReportStatisticiCCS
- ReportStatisticiCTT
- Login
- RicercaSaccaConESenzaPriorità
- EvasioneSacca
- AggiuntaSaccaMagazziniere
- EliminazioneSaccaScaduta
- AggiuntaNuovoDipendenteCTT 
- RimozioneDipendenteCTT
- InoltroAlert
- RispostaAlert

***Class Diagrams:*** 
- AggiuntaRimozioneCTT
- AggiuntaRimozioneDipendenteCTT
- AggiuntaSaccaMagazziniere
- EliminazioneSaccaScaduta
- EvasioneSacca
- InoltroAlert
- Login
- ReportStatisticiCCS
- ReportStatisticiCTT
- RicercaSaccaConESenzaPriorità
  
***Glossary***  

***System Mapping***  

***Resource Access Definition*** 

## GUI HTML
- Assets
- AggiungiSaccaForm.html
- AggiungiNuovoCTTForm.html
- AggiuntaNuovoDipendenteCCTForm.html
- AmministratoreCCS.html
- AmministratoreCTT.html
- DisponibilitàRegionaleSaccheForm.html
- EvasioneSaccaForm.html
- Login.html
- MagazziniereCTT.html
- MenùDiSelezioneQueryCCS.html
- MenùDiSelezioneQueryCTT.html
- OperatoreCTT.html
- OperatoriCTTForm.html
- OrdinaCTTPerRichiesteForm.html
- OrdinaGruppiSanguigniPerRichiesteForm.html
- ReportLocaleSaccheinviateRicevuteCTTForm.html
- ReportOperatoriCTTForm.html
- RicercaSaccaFrom.html
- RimozioneCTTForm.html
- RimozioneDipendenteCTTForm.html
- SaccheInviateRicevuteCTTForm.html

## CTT_DataBase

- Prima versione del DataBaseManagerCTT con prime funzionalità offline


