# Use cases:

[RimozioneCTT](#RimozioneCTT)

[AggiuntaCTT](#AggiuntaCTT)

[InizializzaReportStatisticiCCS](#InizializzaReportStatisticiCCS)

[OrdinaCTTPerRichieste](#OrdinaCTTPerRichieste)

[DisponibilitàRegionaleSacche](#DisponibilitàRegionaleSacche)

[SaccheInviateRicevuteCTT](#SaccheInviateRicevuteCTT)

[OperatoriCTT](#OperatoriCTT)

[InizializzaReportStatisticiCTT](#InizializzaReportStatisticiCTT)

[ReportLocaleSaccheInviateRicevute](#ReportLocaleSaccheInviateRicevute)

[ReportOperatoriCTT](#ReportOperatoriCTT)

[OrdinaGruppiSanguigniPerRichieste](#OrdinaGruppiSanguigniPerRichieste)

[Login](#Login)

[LoginException](#LoginException)

[RicercaSacche](#RicercaSacche)

[RicercaSaccaGlobale](#RicercaSaccaGlobale)

[RicercaSaccaGlobaleAltaPriorità](#RicercaSaccaGlobaleAltaPriorità)

[EvasioneSacche](#EvasioneSacche)

[AggiuntaSacche](#AggiuntaSacche)

[EliminazioneSaccheScadute](#EliminazioneSaccheScadute)

[AggiuntaNuovoDipendenteCTT/CCS](#AggiuntaNuovoDipendenteCTT/CCS)

[RimozioneDipendenteCTT/CCS/CCS](#RimozioneDipendenteCTT/CCS/CCS)

[Alert](#Alert)

[RispostaAlert](#RispostaAlert)









# RimozioneCTT

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
                <p align="right" >4. Il SistemaCCS mostra sul TerminaleAmministratoreCCS MessaggioRimozioneCTT.</p>
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

# AggiuntaCTT
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
                <p align="right" >4. Il SistemaCCS mostra sul TerminaleAmministratoreCCS MessaggioNuovoCTTCreato.
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



# InizializzaReportStatisticiCCS
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

# OrdinaCTTPerRichieste
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
                <p align="right">1. Il SistemaCCS risponde mostrando un OrdinaCTTPerRichiesteForm.</p>
                <p>2. L’AmministratoreCCS compila OrdinaCTTPerRichiesteForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">3. il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
        </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extend'>> InizializzaReportStatisticiCCS nel momento in cui l'operatore clicca su OrdinaCTTPerRichiesteButton.</p>
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

# DisponibilitàRegionaleSacche
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
                <p align="right">1. Il SistemaCCS risponde mostrando un DisponibilitàRegionaleSaccheForm.  </p>
                <p>2. L’AmministratoreCCS compila DisponibilitàRegionaleSaccheForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">3. il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extend'>> InizializzaReportStatisticiCCS nel momento in cui l'operatore clicca su DisponibilitàRegionaleSaccheButton.</p>
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

# SaccheInviateRicevuteCTT
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
                <p align="right">1. Il SistemaCCS risponde mostrando un SaccheInviateRicevuteCTTForm.</p>
                <p>2. L’AmministratoreCCS compila SaccheInviateRicevuteCTTForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">3. il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extend'>> InizializzaReportStatisticiCCS nel momento in cui l'operatore clicca su SaccheInviateRicevuteCTTButton.</p>
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

# OperatoriCTT
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
                <p align="right">1. Il SistemaCCS risponde mostrando un OperatoriCTTForm.</p>
                <p>2. L’AmministratoreCCS compila OperatoriCTTForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">3. il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extend'>> InizializzaReportStatisticiCCS nel momento in cui l'operatore clicca su OperatoriCTTButton.</p>
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

# InizializzaReportStatisticiCTT
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
                <p align="right">2. Il SistemaCTT risponde mostrando MenuSelezioneQuery.</p>
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

# ReportLocaleSaccheInviateRicevute
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
                <p align="right">1. Il Sistema CTT risponde mostrando un ReportLocaleSaccheInviateRicevuteForm.</p>
                <p>2. L’AmministratoreCTT compila ReportLocaleSaccheInviateRicevuteForm con i dati necessari e lo sottomette al sistema.</p>
                <p align="right">3. Il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extend'>> InizializzaReportStatisticiCTT nel momento in cui l'AmministratoreCTT clicca su ReportLocaleSaccheInviateRicevuteButton.</p>
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

# ReportLocaleOperatoriCTT
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportLocaleOperatoriCTT</p>
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
                <p align="right">1. Il SistemaCTT risponde mostrando un ReportOperatoriCTTForm.</p>
                <p>2. L’AmministratoreCTT compila ReportOperatoriCTTForm con i dati necessari e lo sottomette al Sistema CTT.</p>
                <p align="right">3. il SistemaCCS ritorna il risultato della query tramite un   QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extend'>> InizializzaReportStatisticiCTT nel momento in cui l'AmministratoreCTT clicca su ReportOperatoriCTTButton.</p>
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

# OrdinaGruppiSanguigniPerRichieste
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
                <p align="right">1. Il Sistema CTT risponde mostrando un OrdinaGruppiSanguigniPerRichiesteForm.</p>
                <p>2. L’AmministratoreCTT compila OrdinaGruppiSanguigniPerRichiesteForm con i dati necessari e lo sottomette al Sistema CTT.</p>
                <p align="right">3. Il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extend'>> InizializzaReportStatisticiCTT nel momento in cui l'operatore clicca su OrdinaGruppiSanguigniPerRichiesteButton.</p>
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

# Login
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
                <p align="right">4. Il SistemaCCS/CTT porta l'utente alla schermata iniziale relativa al ruolo con cui si è identificato.</p>
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

# LoginException
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


# RicercaSacche
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
                <p>Il SistemaCTT inoltra la NotificaEvasioneSacca al terminaleMagazziniereCTT</p>
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

# RicercaSaccaGlobale
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
                <p align="right">1.	A seguito di un QueryResult negativo, Il Sistema CTT spedisce la richiesta al CCS tramite una NotificaRichiestaSacca.</p>
                <p align="right">2.	Il Sistema CCS riceve la NotificaRichiestaSacca ed inoltra la query ai vari CTT ognuno dei quali fornisce una lista di sacche compatibili.</p>
                <p align="right">3.	Il sistema CCS seleziona le sacche di sangue compatibili con scadenza più vicina.</p>
                <p align="right">4.	Terminata la ricerca ,il sistema CCS invia una NotificaSaccaTrovata al CTT richiedente e una NotificaEvasioneSacca al CTT mittente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extends'>> RicercaSacca e viene inizializzato dall’operatore nel caso in cui non dovessero essere disponibili nel DataBase locale tutte le sacche di sangue richieste con priorità bassa.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il Sistema CCS invia NotificaSaccaTrovata e NotificaEvasioneSacca ai due CTT.</p>
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

# RicercaSaccaGlobaleAltaPriorità
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
                <p>Questo use case <<'Extends'>> RicercaSacca e viene inizializzato dall’operatore nel caso in cui non dovessero essere disponibili nel DataBase locale tutte le sacche di sangue richieste con priorità alta.</p>
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

# EvasioneSacche
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>EvasioneSacche</p>
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
                <p>1. Il MagazziniereCTT, clicca NotificaEvasioneSacche.</p>
                <p align="right">2.  Il SistemaCTT crea un MessaggioCorrettaEvasioneSacca e lo mostra sul TerminaleMagazziniereCTT, viene stampato il PDF da associare all'ordine appena evaso. </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Il terminale MagazziniereCTT riceve una NotificaEvasioneSacche.</p>
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

# AggiuntaSacca
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

# EliminazioneSaccheScadute
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
                <p align="right">1.	Il SistemaCTT, durante un controllo periodico del database si accorge che una determinata sacca è scaduta e crea una NotificaScadenzaSacca contenente i dati della sacca da rimuovere. La notifica viene inviata su TerminaleMagazziniereCTT in modo da poterla eliminare fisicamente. I dati relativi a tale sacca vengono eliminati automaticamente dal database.</p>
                <p align="right">2.	Il SistemaCTT aggiorna i Datisacca relativi alla sacca eliminata aggiungendo ad EnteRichiedente la stringa “scaduta” </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Durante un controllo del database viene individuata una sacca in scadenza.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>I dati relativi alla sacca scaduta vengono eliminati e la sacca viene smaltita.</p>
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

# AggiuntaNuovoDipendenteCTT/CCS
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>AggiuntaNuovoDipendenteCTT/CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCTT / AmministratoreCCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	L’AmministratoreCTT/CCS apre il proprio TerminaleAmministratore inserendo le proprie credenziali e clicca su AggiuntaNuovoDipendenteButton.</p>
                <p align="right">2. Il Sistema mostra AggiuntaNuovoDipendente Form.</p>
                <p>3. L’AmministratoreCTT/CCS compila il AggiuntaNuovoDipendenteCTT con i dati relativi al dipendente da aggiungere.</p>
                <p align="right">4. Il Sistema mostra un MessaggioCorrettaAggiuntaNuovoDipendente e crea un documento pdf conentente le credenziali assegnate al nuovo utente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCTT/CCS clicca su AggiuntaDipendenteButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCTT mostra il MessaggioCorrettaAggiuntaNuovoDipendente crea il documento contenente le credenziali.</p>
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

# RimozioneDipendenteCTT/CCS
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>RimozioneDipendenteCTT/CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Partecipating actors:</p>
            </td>
            <td>
                <p>AmministratoreCTT/CCS</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Flow of events:</p>
            </td>
            <td width="900px">
                <p>1.	L’AmministratoreCTT/CCS apre il proprio TerminaleAmministratoreCTT/CCS inserendo le proprie credenziali e clicca su RimozioneDipendenteButton.</p>
                <p align="right">2. Il SistemaCTT mostra RimozioneDipendenteCTTForm.</p>
                <p>3. L’AmministratoreCTT/CCS compila il RimozioneDipendenteForm con i dati relativi al DipendenteCTT da rimuovere.</p>
                <p align="right">4. Dopo aver eliminato il DipendenteCTT dal dababase il Sistema mostra un MessaggioCorrettaRimozioneDipendente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’amministratoreCTT/CCS clicca su RimozioneDipendenteButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il sistema mostra il MessaggioCorrettaRimozioneDipendente.</p>
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

# Alert
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
                <p align="right">2.	Il CCS aggiunge alla lista delle sacche in scadenza la sacca inviata dal CTT.</p>
                <p>3.La lista delle sacche in scadenza, visualizzata sul terminale di ogni operatoreCTT viene aggiornata.</p>
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
                <p>La sacca viene aggiunta alla lista SaccheInScadenza e viene visualizzata sui terminali dell'operatore</p>
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

# RispostaAlert
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
                <p>2.	L’OperatoreCTT apre il proprio TerminaleOperatoreCTT e clicca su una NotificaSaccaInScadenza </p>
                <p align="right">3. Il sistema mostra un AccettaSaccaForm in cui si chiede l’ente richiedente e l'indirizzo.</p>
                <p>4. L’operatoreCTT compila il form e lo sottomette.</p>
                <p align="right">5. Il SistemaCTT crea una notificaRichiestaSacca e la inoltra al CCS</p>
                <p align="right">6. Il SistemaCCS elimina la sacca da ListaSaccheInScadenza e crea una notificaAffidamentoSacca che inoltra sul TerminaleOperatoreCTT richiedente e una NotificaEvasioneSacca che inoltra sul TerminaleMagazziniereCTT Mittente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’operatore CTT clicca sulla NotificaSaccaInScadenza.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il sistema CCS inoltra notificaAffidamentoSacca  sul TerminaleOperatoreCTT richiedente e NotificaEvasioneSacca sul terminale del magazziniere che possiede la sacca in scadenza..</p>
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

