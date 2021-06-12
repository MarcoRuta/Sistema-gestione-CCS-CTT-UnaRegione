# Non functional requirements

`PERFORMANCE`:
- Dato che, nei vari CTT, non c’è garanzia di avere dei PC performanti, il sistema deve essere in grado di funzionare al meglio su computer che siano di fascia medio bassa.
  
- L’iterazione che il sistema offre verso il database, ed eventualmente verso il CCS, deve essere veloce, non sono tollerabili attese di più di 10 secondi dopo aver avviato una ricerca, una evasione, una query o qualsiasi altra funzionalità fornita dal sistema.
  
- Il sistema deve essere in grado di funzionare anche in assenza di connessione trascurando, ovviamente, le funzionalità legate al CCS per le quali la connessione è strettamente necessaria.
  
`DEPENDABILITY`:	
- Il sistema deve garantire una comunicazione affidabile con il CCS nel momento in cui il CTT è online.
  
- Non sono tollerabili dei Crash del sistema nel momento in cui si effettuano ricerche locali, globali, query locali, globali, evasioni, aggiunta di sacche nel magazzino e aggiunta/rimozione di dipendenti dal database.
  
- Il sistema deve essere in grado di funzionare senza perdita di prestazioni, senza utilizzare troppa RAM per almeno un turno lavorativo di 8 ore.
  
- Nel momento in cui il CCS è offline il CTT non deve essere in grado di prenotare delle sacche di altri CTT perché si rischierebbe inconsistenza, e viceversa.
  
- Le password del database devono essere criptate per evitare che ci possa essere un furto di account e di conseguenza rischio di evasioni, aggiunta sacche e perdita di dati non desiderate.
  
- L’accesso al sistema deve essere garantito solo ai singoli dipendenti del CTT.
  
- Ogni dipendente deve poter accedere alle sole proprie funzionalità.
  
`END USER`:
- Il sistema deve essere semplice da utilizzare. I dipendenti di un CTT non devono avere una conoscenza approfondita di nessun particolare software.
