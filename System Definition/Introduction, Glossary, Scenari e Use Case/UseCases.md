# Use Cases

## Aggiunta/RimozioneCTT

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>RimozioneCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	L’AmministratoreCCS apre il TerminaleAmministratoreCCS e clicca su RimozioneCTTButton.</p>
                <p align="right">2. Il SistemaCCS risponde con RimozioneCTTForm.</p>
                <p>3. L’AmministratoreCCS compila RimozioneCTTForm con i dati relativi al CTT da rimuovere.</p>
                <p align="right" style="width: 400px; margin: auto">4. Il SistemaCCS mostra sul TerminaleAmministratoreCCS MessaggioRimozioneCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCCS clicca su RimozioneCTTButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il TerminaleAmministratoreCCS mostra MessaggioRimozioneCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>AggiuntaNuovoCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	L’AmministratoreCCS apre il TerminaleAmministratoreCCS e clicca su AggiuntaNuovoCTTButton.</p>
                <p align="right">2. Il SistemaCCS risponde con AggiuntaNuovoCTTForm.</p>
                <p>3. L’AmministratoreCCS compila AggiuntaNuovoCTTForm con i dati relativi al CTT da aggiungere.</p>
                <p align="right" style="width: 400px; margin: auto">4. Il SistemaCCS mostra sul TerminaleAmministratoreCCS MessaggioNuovoCTTCreato.
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCCS clicca su AggiuntaNuovoCTTButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il TerminaleAmministratoreCCS mostra MessaggioNuovoCTTCreato.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Report Statistici CCS
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>InizializzaReportStatisticiCCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	L’AmministratoreCCS apre il TerminaleAmministratoreCCS e clicca su ReportStatisticiCCSButton.</p>
                <p align=right>2. il SistemaCCS risponde mostrando MenuSelezioneQuery.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCCS accede all’area report statistici del proprio TerminaleAmministratoreCCS.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCCS risponde mostrando una serie di possibili query da effettuare.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>OrdinaCTTPerRichieste</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1. <<'Include'>> InizializzaReportStatisticiCCS.</p>
                <p>2. L’AmministratoreCCS clicca su OrdinaCTTPerRichiesteButton.</p>  
                <p align="right">3. Il SistemaCCS risponde mostrando un OrdinaCTTPerRichiesteForm.</p>
                <p>4. L’AmministratoreCCS compila OrdinaCTTPerRichiesteForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">5. il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
        </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCCS clicca su OrdinaCTTPerRichiesteButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCCS visualizza a video il risultato della query.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>La ricerca deve avvenire in meno di cinque secondi.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>DisponibilitàRegionaleSacche</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1. <<'include'>>InizializzaReportStatisticiCCS.</p>
                <p>2.	L’Amministratore CCS clicca su DisponibilitàRegionaleSaccheButton.</p>
                <p align="right">3. Il SistemaCCS risponde mostrando un DisponibilitàRegionaleSaccheForm.  </p>
                <p>4. L’AmministratoreCCS compila DisponibilitàRegionaleSaccheForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">5. il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCCS clicca su DisponibilitàRegionaleSaccheButton</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCCS visualizza a video il risultato della query.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>La ricerca deve avvenire in meno di cinque secondi.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>SaccheInviateRicevuteCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	<<'include'>> InizializzaReportStatisticiCCS.</p>
                <p>2.	L’Amministratore CCS clicca su SaccheInviateRicevuteCTTButton.</p>
                <p align="right">3. Il SistemaCCS risponde mostrando un SaccheInviateRicevuteCTTForm.</p>
                <p>4. L’AmministratoreCCS compila SaccheInviateRicevuteCTTForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">5. il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCCS clicca su SaccheInviateRicevuteCTTButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCCS visualizza a video il risultato della query.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>La ricerca deve avvenire in meno di cinque secondi.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>OperatoriCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	<<'include'>> InizializzaReportStatisticiCCS.</p>
                <p>2.	L’AmministratoreCCS clicca su OperatoriCTTButton.</p>
                <p align="right">3. Il SistemaCCS risponde mostrando un OperatoriCTTForm.</p>
                <p>4. L’AmministratoreCCS compila OperatoriCTTForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">5. il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCCS clicca su OperatoriCTTButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCCS visualizza a video il risultato della query.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>La ricerca deve avvenire in meno di cinque secondi.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Report Statistici CTT
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>InizializzaReportStatisticiCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	L’AmministratoreCTT apre il TerminaleAmministratoreCTT e clicca su ReportStatisticiCTTButton.</p>
                <p align="right">2. il SistemaCTT risponde mostrando MenuSelezioneQuery.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCTT accede all’area report statistici del proprio TerminaleAmministratoreCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCTT risponde mostrando una serie di possibili query da effettuare</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportLocaleSaccheInviateRicevute</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	<<'Include'>> InizializzaReportStatisticiCTT.</p>
                <p>2.	L’AmministratoreCTT clicca su ReportLocaleSaccheInviateRicevuteButton.</p>
                <p align="right">3. Il Sistema CTT risponde mostrando un ReportLocaleSaccheInviateRicevuteForm.</p>
                <p>4. L’AmministratoreCTT compila ReportLocaleSaccheInviateRicevuteForm con i dati necessari e lo sottomette al sistema.</p>
                <p align="right">5. il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’Amministratore CTT clicca su ReportLocaleSaccheInviateRicevuteButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il Sistema CTT visualizza a video il risultato della query.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportOperatoriCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	<<'Include'>> InizializzaReportStatisticiCTT.</p>
                <p>2.	L’AmministratoreCTT clicca su ReportOperatoriCTTButton.</p>
                <p align="right">3. Il SistemaCTT risponde mostrando un ReportOperatoriCTTForm.</p>
                <p>4. L’AmministratoreCTT compila ReportOperatoriCTTForm con i dati necessari e lo sottomette al Sistema CTT.</p>
                <p align="right">5. il SistemaCCS ritorna il risultato della query tramite un   QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCTT clicca su ReportOperatoriCTTButton</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il Sistema CTT visualizza a video il risultato della query.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>OrdinaGruppiSanguigniPerRichieste</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	<<'Include'>> InizializzaReportStatisticiCTT.</p>
                <p>2.	L’AmministratoreCTT clicca su OrdinaGruppiSanguigniPerRichiesteButton.</p>
                <p align="right">3. Il Sistema CTT risponde mostrando un OrdinaGruppiSanguigniPerRichiesteForm.</p>
                <p>4. L’AmministratoreCTT compila OrdinaGruppiSanguigniPerRichiesteForm con i dati necessari e lo sottomette al Sistema CTT.</p>
                <p align="right">5. il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCTT clicca su OrdinaGruppiSanguigniPerRichiesteButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il Sistema CTT visualizza a video il risultato della query.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Login
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>Login</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>User</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	L’User clicca LoginButton.</p>
                <p align="right"> 2. Il SistemaCCS/CTT risponde mostrando un LoginForm</p>
                <p>3. L’User compila LoginForm con i propri dati di login.</p>
                <p align="right">4. Il SistemaCCS/CTT mostra sul TerminaleUser MessaggioLoginEffettuato.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’User clicca su LoginButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCCS/CTT mostra su TerminaleUser MessaggioLoginEffettuato.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>LoginException</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>Tempo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p align="right">1. Il SistemaCCS/CTT mostra sul TerminaleUser MessaggioDatiLoginErrati e riporta l’User alla schermata principale.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'extends'>> Login nel momento in cui i dati di login inseriti dall’User non sono corretti.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCCS/CTT riporta l’User alla schermata principale.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>FillUpException</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>Tempo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p align="right">1. Il SistemaCCS/CTT mostra un messaggio d’errore causa compilazione errata del form e riporta l’User alla schermata principale.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'extends'>> AggiuntaNuovoCTT e RimozioneCTT nel momento in cui i dati inseriti dall’User nel form non siano corretti.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCCS/CTT riporta l’User alla schermata principale.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>QueryException</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>Tempo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p align="right" style="margin: auto">1. Il SistemaCCS/CTT mostra un messaggio d’errore causa compilazione errata del form relativo alla personalizzazione di una query e riporta l’AmministratoreCCS/CTT alla schermata di selezione delle query.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'extends'>> OrdinaCTTPerRichieste, DisponibilitàSacche, OperatoriCTT, SaccheInviateRicevute nel momento in cui i dati inseriti dall’User nel form di personalizzazione delle query non siano corretti.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCCS/CTT riporta l’User alla schermata di selezione delle query.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Ricerca Sacche
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>RicercaSacche</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>OperatoreCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	A seguito di una richiesta proveniente dall’esterno, l’OperatoreCTT tramite TerminaleOperatoreCTT clicca su RicercaSaccaButton.</p>
                <p align="right">2.	Il Sistema CTT risponde con un RicercaSaccaForm in cui richiede i dati necessari per effettuare la ricerca, il numero di sacche richieste, l'ente richiedente, il suo indirizzo, e la priorità di quest’ultima.</p>
                <p>3.	L’OperatoreCTT compila RicercaSaccaForm fornendo tutte le informazioni sulla sacca di sangue da ricercare e lo sottomette.</p>
                <p align="right">4.	Il Sistema CTT restituisce una QueryResult per informare del risultato della ricerca. A seguito di un QueryResult positivo il SistemaCTT modifica lo stato delle sacche in “prenotate”, crea una NotificaEvasioneSacca contenente l’ente richiedente ed il seriale delle sacche da inviare e la inoltra sul terminaleMagazziniereCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’OperatoreCTT tramite TerminaleOperatoreCTT clicca su RicercaSaccaButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCTT che possiede la inoltra la NotificaEvasioneSacca al terminaleMagazziniereCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Velocità ed efficienza del sistema poiché potrebbero esserci casi da risolvere tempestivamente.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>RicercaSaccaGlobale</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>Tempo</p>
                <p>OperatoreCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p align="right">1.	A seguito di un QueryResult negativo, Il Sistema CTT spedisce la richiesta al CCS tramite NotificaRichiestaSacca.</p>
                <p align="right">2.	Il Sistema CCS riceve la NotificaRichiestaSacca ed inoltra la query ai vari CTT ognuno dei quali fornisce una lista di sacche compatibili.</p>
                <p align="right">3.	Il sistema CCS seleziona le sacche di sangue compatibili con scadenza più vicina.</p>
                <p align="right">4.	Terminata la ricerca ,il sistema CCS invia una NotificaSaccaTrovata al CTT richiedente e una NotificaEvasioneSacca al CTT mittente.</p>
                <p align="right">5.	Il sistemaCTT che possiede le sacche modifica lo stato di ognuna di esse in “prenotata”.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extends'>> RicercaSacca e viene inizializzato dall’operatore nel caso in cui non dovesse essere disponibile nel DataBase locale le sacche di sangue richieste con priorità bassa.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il Sistema CTT modifica lo stato della sacca in prenotata.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Velocità ed efficienza del sistema poiché potrebbero esserci casi da risolvere tempestivamente.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>RicercaSaccaGlobaleAltaPriorità</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>Tempo</p>
                <p>OperatoreCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p align="right">1.	A seguito di un QueryResult negativo, Il Sistema CTT spedisce la richiesta al CCS tramite NotificaRichiestaAltaPrioritàSacca.</p>
                <p align="right">2.	Il Sistema CCS riceve la NotificaRichiestaAltaPriorità ed inoltra la query ai vari CTT ognuno dei quali fornisce una lista di sacche compatibili.</p>
                <p align="right">3.	Il sistema CCS seleziona le sacche di sangue compatibili più vicine al richiedente.</p>
                <p align="right">4.	Terminata la ricerca, il sistema CCS invia una NotificaSaccaTrovata al CTT richiedente e una NotificaEvasioneSacca al TerminaleMagazziniereCTT mittente.</p>
                <p align="right">5.	Il sistemaCTT che possiede le sacche modifica lo stato di ognuna di esse in “prenotata”.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extends'>> RicercaSaccaAltaPriorità e viene inizializzato dall’OperatoreCTT nel caso in cui non dovese essere disponibile nel DataBase locale le sacche di sangue richieste con priorità elevata.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il Sistema CTT modifica lo stato della sacca in “prenotata”</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Velocità ed efficienza del Sistema poiché potrebbero esserci casi da risolvere tempestivamente.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Evasione Sacche
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>EvasioneSaccaCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>OperatoreCTT</p>
                <p>MagazziniereCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1. Il MagazziniereCTT, dopo aver visto la notifica EvasioneSacca sul suo terminale clicca su EvasioneSaccaButton</p>
                <p align="right">2.  Il SistemaCTT mostra sul TerminaleMagazziniereCTT EvasioneSaccaForm.</p>
                <p>3.  Il MagazziniereCTT compila EvasioneSaccaForm inserendo il seriale relativo ad ogni sacca da inviare l’ente richiedente e la data di affidamento.</p>
                <p align="right">4. Il SistemaCTT genera un documento stampabile con i dati da applicare sulle sacche.</p> 
                <p align="right">5.  Il SistemaCTT crea un MessaggioCorrettaEvasioneSacca e lo mostra sul TerminaleMagazziniereCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Il SistemaCCS inoltra sul terminale dell’operatore CTT una richiesta di trasferimento sacche presso un altro CTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCCS notifica l’operatore dell’avvenuta evasione.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Aggiunta/Eliminazione Sacche Scadute
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>AggiuntaSacca</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>MagazziniereCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1. Arrivata una sacca nel magazzino di un CTT, il MagazziniereCTT apre il proprio TerminaleMagazziniereCTT e clicca su AggiungiSaccaButton.</p>
                <p align="right">2. Il SistemaCTT mostra sul TerminaleMagazziniereCTT AggiungiSaccaForm.</p>
                <p>3.  Il MagazziniereCTT inserisce su AggiungiSaccaForm i dati relativi alla sacca da aggiungere (Tipo di sacca, data scadenza, data arrivo in magazzino, donatore) e lo sottomette al sistema.</p>
                <p align="right">4.  Il sistema, dopo aver aggiunto tale sacca al proprio database, visualizza su TerminaleMagazziniereCTT una NotificaCorrettaAggiuntaSacca.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Arriva una sacca nel magazzino di un CTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il MagazziniereCTT aggiorna i dati della sacca.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>I dati vengano inseriti correttamente.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>EliminazioneSaccaScaduta</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>Tempo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p align="right">1.	Il SistemaCTT si accorge che una determinata sacca è scaduta e crea una NotificaScadenzaSacca contenente i dati della sacca da rimuovere. La notifica viene inviata su TerminaleMagazziniereCTT in modo da poterla eliminare fisicamente. I dati relativi a tale sacca vengono eliminati automaticamente dal database.</p>
                <p align="right">2.	Il SistemaCTT aggiorna i Datisacca relativi alla sacca eliminata aggiungendo ad EnteRichiedente la stringa “scaduta” </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Una determinata sacca scade.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>I dati relativi alla sacca scaduta vengono eliminati.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Aggiunta Rimozione Dipendente
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>AggiuntaNuovoDipendenteCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	L’AmministratoreCTT apre il proprio TerminaleAmministratoreCTT inserendo le proprie credenziali e clicca su AggiuntaNuovoDipendenteCTTButton.</p>
                <p align="right">2. Il SistemaCTT mostra AggiuntaNuovoDipendenteCTT Form.</p>
                <p>3. L’AmministratoreCTT compila il AggiuntaNuovoDipendenteCTT con i dati relativi al dipendente da aggiungere.</p>
                <p align="right">4. Il SistemaCTT mostra un MessaggioCorrettaAggiuntaNuovoDipendenteCTT e le credenziali assegnate al nuovo utente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCTT clicca su AggiuntaDipendenteCTTButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCTT mostra il MessaggioCorrettaAggiuntaNuovoDipendenteCTT e le credenziali.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>RimozioneDipendenteCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	L’AmministratoreCTT apre il proprio TerminaleAmministratoreCTT inserendo le proprie credenziali e clicca su RimozioneDipendenteCTTButton.</p>
                <p align="right">2. Il SistemaCTT mostra RimozioneDipendenteCTTForm.</p>
                <p>3. L’AmministratoreCTT compila il RimozioneDipendenteCTTForm con i dati relativi al DipendenteCTT da rimuovere.</p>
                <p align="right">4. Dopo aver eliminato il DipendenteCTT dal dababase il SistemaCTT mostra un MessaggioCorrettaRimozioneDipendenteCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’amministratore clicca su RimozioneDipendenteCTTButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il sistema mostra il MessaggioCorrettaRimozioneDipendenteCTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

## Alert
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>AlertScadenzaSacche</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>Tempo</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p align="right">1.	Il SistemaCTT ogni 24 ore avvia una procedura di controllo periodica delle sacche in scadenza. Per ogni sacca di sangue non prenotata e con scadenza pari o inferiore a 72 h, il CCS viene informato dell’esistenza di tale sacca tramite NotificaSaccaInScadenza.</p>
                <p align="right">2.	Il CCS controlla (in base alla distanza e ai tempi necessari per l’utilizzo) quali CTT possono riceverla in tempo per poi inviargli un alert in broadcast; per ogni sacca di sangue già prenotata viene inviato un MessaggioSaccaInScadenzaPrenotata solo al CTT in questione.</p>
                <p>3.L’alert viene visualizzato sui terminali di tutti i CTT conformi alla ricerca precedente, dove sarà visualizzato da un operatore CTT.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Il SistemaCTT avvia la procedura di controllo delle scadenze periodica.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>L’alert viene ricevuto da tutti i CTT conformi OR viene inviato un MessaggioSaccaInScadenza.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Il controllo della scadenza e l’invio dell’alert o del MessaggioSaccaInScadenza vengano eseguiti velocemente.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>

<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>RispostaAlert</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>OperatoreCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	<<'Include'>> AlertScadenzaSacche.</p>
                <p>2.	L’OperatoreCTT apre il proprio TerminaleOperatoreCTT e clicca sul AccettaSaccaButton. </p>
                <p align="right">3. Il sistema mostra un AccettaSaccaForm in cui si chiede l’ente richiedente.</p>
                <p>4. L’operatoreCTT compila il form e lo sottomette.</p>
                <p align="right">5. Il SistemaCTT crea una notificaRichiestaSacca e la inoltra al CCS</p>
                <p align="right">6. Il SistemaCCS crea una notificaAffidamentoSacca che inoltra sul TerminaleOperatoreCTT richiedente e una NotificaEvasioneSacca che inoltra sul TerminaleMagazziniereCTT Mittente.</p>
                <p align="right">7. Il SistemaCTT mittente modifica lo stato della sacca in “prenotata”</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’operatore CTT clicca sul bottone AccettaSaccaButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il sistema CCS inoltra notificaAffidamentoSacca  sul TerminaleOperatoreCTT richiedente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Quality requirements:</p>
            </td>
            <td>
                <p>Rapidità dell’elaborazione.</p>
            </td>
        </tr>
    </table>
</div>

<div style="Height: 30px"></div>



