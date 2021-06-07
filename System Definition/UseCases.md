# Use cases:

[RimozioneCTT](#RimozioneCTT)

[AggiuntaCTT](#AggiuntaCTT)

[InizializzaReportStatisticiCCS](#InizializzaReportStatisticiCCS)

[ReportSaccheInviateCTT](#ReportSaccheInviateCTT)

[ReportSaccheRicevuteCTT](#ReportSaccheRicevuteCTT)

[ReportDisponibilitàRegionaleSacche](#ReportDisponibilitàRegionaleSacche)

[ReportDipendentiCTT](#ReportDipendentiCTT)

[ReportGiacenzaMediaCTT](#ReportGiacenzaMediaCTT)

[InizializzaReportStatisticiCTT](#InizializzaReportStatisticiCTT)

[ReportSaccheInviateLocale](#ReportSaccheInviateLocale)

[ReportSaccheRicevuteLocale](#ReportSaccheRicevuteLocale)

[ReportDisponibilitàSaccheLocale](#ReportDisponibilitàSaccheLocale)

[ReportDipendentiLocale](#ReportDipendentiLocale)

[ReportGiacenzaMediaLocale](#ReportGiacenzaMediaLocale)

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
                <p align=right>2. Il SistemaCCS risponde mostrando QueryMenu.</p>
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

# ReportSaccheInviateCTT
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportSaccheInviateCTT</p>
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
                <p align="right">1. Il SistemaCCS risponde mostrando un ReportSaccheInviateCTTForm.</p>
                <p>2. L’AmministratoreCCS compila ReportSaccheInviateCTTForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">3. Il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Include'>> InizializzaReportStatisticiCCS nel momento in cui l'operatore clicca su ReportSaccheInviateCTTButton.</p>
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

# ReportSaccheRicevuteCTT
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportSaccheRicevuteCTT</p>
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
                <p align="right">1. Il SistemaCCS risponde mostrando un ReportSaccheRicevuteCTTForm.</p>
                <p>2. L’AmministratoreCCS compila ReportSaccheRicevuteCTTForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">3. Il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
        </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Include'>> InizializzaReportStatisticiCCS nel momento in cui l'operatore clicca su ReportSaccheRicevuteCTTButton.</p>
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

# ReportDisponibilitàRegionaleSacche
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportDisponibilitàRegionaleSacche</p>
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
                <p align="right">1. Il SistemaCCS risponde mostrando un ReportDisponibilitàRegionaleSaccheForm.  </p>
                <p>2. L’AmministratoreCCS compila ReportDisponibilitàRegionaleSaccheForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">3. Il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Include'>> InizializzaReportStatisticiCCS nel momento in cui l'operatore clicca su ReportDisponibilitàRegionaleSaccheButton.</p>
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


# ReportDipendentiCTT
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportDipendentiCTT</p>
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
                <p align="right">1. Il SistemaCCS risponde mostrando un ReportDipendentiCTTForm.</p>
                <p>2. L’AmministratoreCCS compila ReportDipendentiCTTForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">3. Il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Include'>> InizializzaReportStatisticiCCS nel momento in cui l'operatore clicca su ReportDipendentiCTTButton.</p>
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

# ReportGiacenzaMediaCTT
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportGiacenzaMediaCTT</p>
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
                <p align="right">1. Il SistemaCCS risponde mostrando un ReportGiacenzaMediaCTTForm.</p>
                <p>2. L’AmministratoreCCS compila ReportGiacenzaMediaCTTForm con i dati necessari e lo sottomette al SistemaCCS.</p>
                <p align="right">3. Il SistemaCCS ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Include'>> InizializzaReportStatisticiCCS nel momento in cui l'operatore clicca su ReportGiacenzaMediaCTTButton.</p>
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
                <p>1. L’AmministratoreCTT apre il TerminaleAmministratoreCTT e clicca su ReportStatisticiCTTButton.</p>
                <p align=right>2. Il SistemaCTT risponde mostrando QueryMenu.</p>
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
                <p>Il SistemaCTT risponde mostrando una serie di possibili query da effettuare.</p>
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

# ReportSaccheInviateLocale
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportSaccheInviateLocale</p>
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
                <p align="right">1. Il SistemaCTT risponde mostrando un ReportSaccheInviateLocaleForm.</p>
                <p>2. L’AmministratoreCTT compila ReportSaccheInviateLocaleForm con i dati necessari e lo sottomette al SistemaCTT.</p>
                <p align="right">3. Il SistemaCTT ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Include'>> InizializzaReportStatisticiCTT nel momento in cui l'operatore clicca su ReportSaccheInviateLocaleButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCTT visualizza a video il risultato della query.</p>
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

# ReportSaccheRicevuteLocale
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportSaccheRicevuteLocale</p>
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
                <p align="right">1. Il SistemaCTT risponde mostrando un ReportSaccheRicevuteLocaleForm.</p>
                <p>2. L’AmministratoreCTT compila ReportSaccheRicevuteLocaleForm con i dati necessari e lo sottomette al SistemaCTT.</p>
                <p align="right">3. Il SistemaCTT ritorna il risultato della query tramite un QueryResult.</p>
        </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Include'>> InizializzaReportStatisticiCTT nel momento in cui l'operatore clicca su ReportSaccheRicevuteLocaleButton.</p>
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

# ReportDisponibilitàSaccheLocale
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportDisponibilitàSaccheLocale</p>
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
                <p align="right">1. Il SistemaCTT risponde mostrando un ReportDisponibilitàSaccheLocaleForm.  </p>
                <p>2. L’AmministratoreCTT compila ReportDisponibilitàSaccheLocale con i dati necessari e lo sottomette al SistemaCTT.</p>
                <p align="right">3. Il SistemaCTT ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Include'>> InizializzaReportStatisticiCTT nel momento in cui l'operatore clicca su ReportDisponibilitàSaccheLocaleButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCTT visualizza a video il risultato della query.</p>
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

# ReportDipendentiLocale
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportDipendentiLocale</p>
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
                <p align="right">1. Il SistemaCTT risponde mostrando un ReportDipendentiLocaleForm.</p>
                <p>2. L’AmministratoreCTT compila ReportDipendentiLocaleForm con i dati necessari e lo sottomette al SistemaCTT.</p>
                <p align="right">3. Il SistemaCTT ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Include'>> InizializzaReportStatisticiCTT nel momento in cui l'operatore clicca su ReportDipendentiLocaleButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCTT visualizza a video il risultato della query.</p>
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

# ReportGiacenzaMediaLocale
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>ReportGiacenzaMediaLocale</p>
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
                <p align="right">1. Il SistemaCTT risponde mostrando un ReportGiacenzaMediaLocaleForm.</p>
                <p>2. L’AmministratoreCTT compila ReportGiacenzaMediaLocaleForm con i dati necessari e lo sottomette al SistemaCTT.</p>
                <p align="right">3. Il SistemaCTT ritorna il risultato della query tramite un QueryResult.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Include'>> InizializzaReportStatisticiCTT nel momento in cui l'operatore clicca su ReportGiacenzaMediaLocaleButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCTT visualizza a video il risultato della query.</p>
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
                <p align="right"> 2. Il SistemaCCS/CTT risponde mostrando un LoginForm.</p>
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
                <p>Questo use case <<'Extends'>> Login nel momento in cui i dati di login inseriti dall’User non sono corretti.</p>
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
                <p>1.	A seguito di una richiesta proveniente dall’esterno, l’OperatoreCTT tramite TerminaleOperatoreCTT clicca su RicercaSaccheButton.</p>
                <p align="right">2.	Il Sistema CTT risponde con un RicercaSaccheForm in cui richiede i dati necessari per effettuare la ricerca.</p>
                <p>3.	L’OperatoreCTT compila RicercaSaccheForm fornendo tutte le informazioni sulle sacche di sangue da ricercare e lo sottomette.</p>
                <p align="right">4.	Il Sistema CTT restituisce una QueryResult per informare del risultato della ricerca. A seguito di un QueryResult positivo il SistemaCTT modifica lo stato delle sacche in “prenotate”, crea una NotificaEvasioneSacche contenente l’ente richiedente ed il seriale delle sacche da inviare e la inoltra sul terminaleMagazziniereCTT</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’OperatoreCTT tramite TerminaleOperatoreCTT clicca su RicercaSaccheButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCTT inoltra la NotificaEvasioneSacche al terminaleMagazziniereCTT</p>
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

# RicercaSaccheGlobale
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>RicercaSaccheGlobale</p>
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
                <p align="right">1.	A seguito di un QueryResult negativo, Il Sistema CTT spedisce la richiesta al CCS tramite una NotificaRichiestaSacche.</p>
                <p align="right">2.	Il Sistema CCS riceve la NotificaRichiestaSacche ed inoltra la query ai vari CTT ognuno dei quali fornisce una lista di sacche compatibili.</p>
                <p align="right">3.	Il sistema CCS seleziona le sacche di sangue compatibili con scadenza più vicina.</p>
                <p align="right">4.	Terminata la ricerca ,il sistema CCS invia una NotificaSaccheTrovate al CTT richiedente e una NotificaEvasioneSacche al CTT mittente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extends'>> RicercaSacche e viene inizializzato dall’operatore nel caso in cui non dovessero essere disponibili nel DataBase locale tutte le sacche di sangue richieste con priorità bassa.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il Sistema CCS invia NotificaSaccheTrovate e NotificaEvasioneSacche ai due CTT.</p>
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

# RicercaSaccheGlobaleAltaPriorità
<div>
    <table>
        <tr>
            <td width="200px">
                <p style="font-style: 16px">Use case name:</p>
            </td>
            <td>
                <p>RicercaSaccheGlobaleAltaPriorità</p>
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
                <p align="right">1.	A seguito di un QueryResult negativo, Il Sistema CTT spedisce la richiesta al CCS tramite NotificaRichiestaAltaPrioritàSacche.</p>
                <p align="right">2.	Il Sistema CCS riceve la NotificaRichiestaAltaPrioritàSacche ed inoltra la query ai vari CTT ognuno dei quali fornisce una lista di sacche compatibili.</p>
                <p align="right">3.	Il sistema CCS seleziona le sacche di sangue compatibili più vicine al richiedente.</p>
                <p align="right">4.	Terminata la ricerca, il sistema CCS invia una NotificaSaccheTrovate al CTT richiedente e una NotificaEvasioneSacche al TerminaleMagazziniereCTT mittente.</p>
                <p align="right">5.	Il sistemaCTT che possiede le sacche modifica lo stato di ognuna di esse in “prenotata”.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Questo use case <<'Extends'>> RicercaSacche e viene inizializzato dall’operatore nel caso in cui non dovessero essere disponibili nel DataBase locale tutte le sacche di sangue richieste con priorità alta.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il Sistema CTT modifica lo stato di ogni sacca in “prenotata”</p>
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
                <p>1. Il MagazziniereCTT clicca NotificaEvasioneSacche.</p>
                <p align="right">2.  Il SistemaCTT crea un MessaggioCorrettaEvasioneSacche e lo mostra sul TerminaleMagazziniereCTT, viene stampato il PDF da associare all'ordine appena evaso. </p>
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
                <p>3.  Il MagazziniereCTT inserisce su AggiungiSaccaForm i dati relativi alla sacca da aggiungere e lo sottomette al sistema.</p>
                <p align="right">4.  Il sistemaCTT, dopo aver aggiunto tale sacca al proprio database, visualizza su TerminaleMagazziniereCTT una NotificaCorrettaAggiuntaSacca.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Arriva un carico di sacche o una sacca al magazzino di un CTT.</p>
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
                <p>Rapidità dell’elaborazione.</p>
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
                <p>EliminazioneSaccheScadute</p>
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
                <p align="right">1.	Il SistemaCTT, durante un controllo periodico del database, si accorge che delle determinate sacche sono scadute e crea una NotificaScadenzaSacche contenente i dati delle sacche da rimuovere. La notifica viene inviata su TerminaleMagazziniereCTT in modo da poterla eliminare fisicamente. I dati relativi a tale sacca vengono eliminati automaticamente dal database.</p>
                <p align="right">2.	Il SistemaCTT aggiorna i DatiSacche relativi alla sacche eliminate aggiungendo ad EnteRichiedente, all'IndirizzoEnte ed a DataAffidamento di ogni sacca la stringa “scaduta”. </p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>Durante un controllo del database viene individuata almeno una sacca in scadenza.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>I dati relativi alle sacche scadute vengono eliminati e le sacche vengono smaltite.</p>
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
                <p align="right">2. Il Sistema mostra AggiuntaNuovoDipendenteForm.</p>
                <p>3. L’AmministratoreCTT/CCS compila l' AggiuntaNuovoDipendenteForm con i dati relativi al dipendente da aggiungere.</p>
                <p align="right">4. Il Sistema mostra un MessaggioCorrettaAggiuntaNuovoDipendente e crea un documento PDF contenente le credenziali assegnate al nuovo utente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’AmministratoreCTT/CCS clicca su AggiuntaNuovoDipendenteButton.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il SistemaCTT/CCS mostra il MessaggioCorrettaAggiuntaNuovoDipendente e crea il documento contenente le credenziali.</p>
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
                <p align="right">2. Il SistemaCTT mostra RimozioneDipendenteForm.</p>
                <p>3. L’AmministratoreCTT/CCS compila il RimozioneDipendenteForm con i dati relativi al DipendenteCTT /CCS da rimuovere.</p>
                <p align="right">4. Dopo aver eliminato il DipendenteCTT/CCS dal dababase il Sistema mostra un MessaggioCorrettaRimozioneDipendente.</p>
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
                <p>3. La lista delle sacche in scadenza, visualizzata sul terminale di ogni operatoreCTT, viene aggiornata.</p>
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
                <p>La sacca viene aggiunta alla lista SaccheInScadenza e viene visualizzata sui terminali di ogni OperatoreCTT.</p>
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
                <p>2.	L’OperatoreCTT accede al proprio TerminaleOperatoreCTT e clicca su una NotificaSaccaInScadenza. </p>
                <p align="right">3. Il sistema mostra un AccettaSaccaForm in cui si chiede l’ente richiedente e l'indirizzo.</p>
                <p>4. L’ OperatoreCTT compila il form e lo sottomette.</p>
                <p align="right">5. Il SistemaCTT crea una NotificaRichiestaSacca e la inoltra al CCS.</p>
                <p align="right">6. Il SistemaCCS elimina la sacca da ListaSaccheInScadenza e crea una NotificaAffidamentoSacca che inoltra sul TerminaleOperatoreCTT richiedente e una NotificaEvasioneSacca che inoltra sul TerminaleMagazziniereCTT mittente.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Entry condition:</p>
            </td>
            <td>
                <p>L’ Operatore CTT clicca sulla NotificaSaccaInScadenza.</p>
            </td>
        </tr>
        <tr>
            <td>
                <p style="font-style: 16px">Exit condition:</p>
            </td>
            <td>
                <p>Il sistema CCS inoltra NotificaAffidamentoSacca  sul TerminaleOperatoreCTT richiedente e NotificaEvasioneSacca sul terminale del magazziniere che possiede la sacca in scadenza..</p>
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

