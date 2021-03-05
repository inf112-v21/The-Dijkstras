## Dijkstra's Møte 22.02.21

Innsjekk

Alexander
Jobbe med kode.
Anas
Har veldig fint. Gjort brukerhistorie
Morten
Litt syk. Gjort brukerhistorier.
Erlend
Litt sliten etter
Trym
20 min sen, ellers klar




Innleveringsrutine.
Teste fra forskjellige pcer/test verktøy
Sjekke at github dokumentene ser bra ut.


Klassediagram








### Dele ut kort
####Brukerhistorie:
Som en spiller trenger jeg å få utdelt min kvote av kort og at kun jeg (ikke andre spillerne) som kan se på dem, for å kunne bestemme hvordan jeg kan programmere roboten min.

#### Løsningsbeskrivelse:
Hver spiller får et riktig antall kort og bare selve spilleren som kan se sine kort
#### Akseptansekriterier:
Gitt en kortstokk og to eller mange spillere:
1. Det må være klart hvem av spillerne er aktiv og hvem er annonsert Power Down som ikke skal motta noe kort.
2. Det må bestemmes hvor mange kort hver spiller skal ha for neste runde (9 kort i første runde og deretter ta hensyn til eventuelle skader roboten til denne spilleren har fått i tidligere runder)
3. Alle aktive spillerne skal få utdelt et bestemt mengde av tilfeldige programmeringskort.
4. Programmeringskortene skal være synlinge kun til selve spiller

#### Arbeidsoppgaver:
Lage en klass for programmeringskort\
Ordne kortstokk\
Få oversikt over tilstand til hver spiller (Om spilleren er aktiv eller ikke, hvor mange skader spilleren har)\
Finne ut hvor mange kort hver spiller skal ha\
Dele ut i tilfeldig rekkefølge kortene til spillerne\
Hver aktiv spiller få se en rekke av kort i begynnelse av et runde



### Robot flytter på andre roboter
#### Brukerhistorie:
Som spiller ønsker jeg at min robot skal kunne dytte andre roboter for å fordelaktig ødelegge motspillere sin plan for runden.

#### Løsningsbeskrivelse:
Robot skal dytte annen robot i det den kommer på samme felt

#### Akseptansekriterier:
Gitt 2 roboter på et horisontalt brett og at robot 1 beveger seg mot robot 2 skal:

Robot 2 bevege seg videre i samme retning som robot 1.
Om Robot 2 ikke er bevegelig, vil heller ikke robot1 bevege seg

#### Arbeidsoppgaver:
BoxCheck for hvert spillertrekk
PlayerCollision event.


9. Bevege robot ut fra valgte kort
   Brukerhistorie:
   Som spiller ønsker jeg at roboten skal bevege seg etter hvilke kort jeg velger for å utføre planen min.

Løsningsbeskrivelse:
Robot må utføre kortets kommando

Akseptansekriterier:
Gitt at en spiller velger et gitt kort så skal roboten utføre valgte kommando.
Må kunne skille mellom de forskjellige kortene.


Arbeidsoppgaver:
Deck class med forskjellige kommandoer/kort
Robot må kunne ta imot valgte kommando fra kort
Robot må kunne utføre kommando.



Velge 5 kort brukerhistorie
Brukerhistorie
Som spiller ønsker jeg å kunne velge kort hver runde slik at jeg kan kontrollere roboten.

Løsningsbeskrivelse
Kort må være utdelt til spiller og spiller skal kunne velge de gjennom GUI.

Akseptansekriterier:
Gitt at det er en spiller sin tur til å velge kort etter å ha blitt delt ut en mengde kort:
Spilleren skal kunne velge eksakt 5 kort gjennom GUI
De 5 kortene lagres i kronologisk rekkefølge

Arbeidsoppgaver:
Implementere funksjonalitet til GUI som lar spiller velge kortene
Lagre brukte kort som variabel i spillerklasse eller robotklasse (?)
Lage klasser for de forskjellige kort typene


Brettet har hull:
Som Spller må jeg prøve å ikke tape ved å la robot falle ut av banen gjennom et hull.

Løsningsbeskrivelse:
Hull må eksistere på banen og være synlinge slik at spiller kan prøve å unngå de
Gitt at det eksisterer et brett med roboter som kan bevege seg, må:
Hull objektet eksistere på brett og være synlig.

Akseptansekriterier:
Robot må kunne unngå hullene.
Fall inn i hull må registeres som tap av et liv eller game over.

Arbeidsoppgaver:
Implementere liv-system og sjekk for fall. Trekke et liv i spillklassen.









RETROSPEKTIV 21.02.21 DEL 1


Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamleaerd eller kundekontakt?
Erlend
- Teamleder, og møtedokumentasjon.

Morten (Melde seg til kundeansvarlig)
- GUI ansvarlig

Aleksander og Trym(Dokumentasjonsavnsvarlig)(Produksjonsansvarlig)

Anas	(Git og Test-ansvarlig)
	
------------

Trenger dere andre roller? Skriv ned noen linjer om hva de ulike rollene faktisk innebærer for dere.



 
-------------
Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?
Jobb opp i mot git
-	Kronglete i starten.
     Testdreven utvikling
     parprogrammering
