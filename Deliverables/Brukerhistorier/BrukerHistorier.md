## Brukerhistorier samling 

Disse brukerhistoriene skal du finne fra de respektive møtereferatene

### Fra MøteRef-05.02.2021.md:

## MVP-krav og brukershistorier:

### Vise et spillebrett
#### Brukerhistorie:
Som en spiller, ønsker jeg å kunne se og lese hele spillebrettet.
Jeg ønsker å få en tydelig oversikt over banen og hindringer, slik at jeg kan se mulige veier for å komme seg gjennom banen og til mål.
#### Løsning:
Spillvinduet skal vise hele banen og ha oversiktlig design.
#### Akseptansekriterier:
Applikasjonsvinduet skal skalere riktig i forhold til skjermen.
Tile-designen skal være lesbar og de ulike tiles skal kunne skilles fra hverandre.
Baneelementer må være på riktige layers og assets skal bli rendered.




### Vise brikker på spillbrett
#### Brukerhistorie
Som spiller ønsker jeg en synlig brikke på bordet, slik jeg vet hvor jeg står.
#### Løsningsbeskrivelse:
Brikken må bli synlig
#### Akseptansekriterier:
Gitt at vi har et synlig brett med en spiller på brettet. Så skal
1. Brikken være synlig
2. Den stå på rett plassering.


### Flytte brikke (vha taster e.l. For testing)

#### Brukerhistorie
Som en spiller ønsker jeg å kunne flytte brikken rundt på brettet.
For testing ønsker jeg å kunne flytte roboten rundt med taster for å kunne til slutt interacte med de forskjellige tingene man skal kunne interacte med slik at man kan se at brikken og de andre tingene reagerer på hverandre.

#### Løsningsbeskrivelse.
Brikken må kunne bevege seg, og vises på skjerm for spillerne

#### Akseptansekriterier
Gitt at brikken kan bevege seg så må:
Brikken ta imot kommando slik at den beveger seg.
Brikken bevege seg på skjermen slik at spillerne kan se at den beveger seg.

### Robot besøker flagg
#### Brukerhistorie
Som Robot må jeg kunne besøke flagg, da det er delmålet for å vinne spillet.
#### Løsningbeskrivelse:
Flagg må eksistere på brettet og robot må kunne besøke det
#### Akseptansekriterier:
Gitt at det eksisterer et brett med roboter som kan bevege seg må
Flagg-tile eksistere på brett
Robot kunne gå på et flagg
Registreres besøk på et flagg.


### Robot vinner ved å besøke flagg - Trym
Som en Robot
Ønsker jeg å vinne dersom alle flagg er besøkt i kronologisk rekkefølge
Slik at roboten oppdateres med; “har besøkt flagg x” dersom flagg x-1 er besøkt ELLER x = 0. (antar man starter med første flagg er flagg_0)

#### Løsningsbeskrivelse:
Dersom robot har besøkt flagg i riktig rekkefølge, skal robot vinne.


#### Akseptanskriterier:
Gitt et brett med bevegelige roboter og flagg:
Flagg må besøkes i kronologisk rekkefølge
Alle flagg må besøkes
teknisk:
Denne funksjonen gjør noe slikt på roboten; robot.flag_x.visit() -> setter verdi true dersom kravene under passeres.
Her sjekkes noe viktig: if robot.flag_(x-1).visited() gir verdi false og x != 0, oppdateres ikke robot.flag_x.visited() til true. (med tanke på reglene) 


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

### Fra MøteRef-21.02.2021.md

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


### Spille fra flere maskiner
#### Brukerhistorie:
Som spiller ønsker jeg å kunne spille mot andre spillere slik at jeg har mulighet til å vinne.

#### Løsningsbeskrivelse:
Flere spillere kan delta i spillet samtidig.
Spillere kan utføre handlinger etter tur.

#### Akseptansekriterier:
Gitt flere spillere på en lokal tilkobling må:
- En av spillerne må kjøre server.
- Spillere ha mulighet til å spille mot samme server.
- Spillere gi informasjon til server
- Spillere få informasjon fra server

#### Arbeidsoppgave:
Setup(backend/frontend)
Server applikasjon
Client applikasjon
Kommunikasjon mellom klienter og server.


#### Brukerhistorie:
Som spiller ønsker jeg å se de andre spillerne sine bevegelser på brettet slik at jeg kan planlegge min neste tur.

#### Løsningsbeskrivelse:
Motstanderens bevegelser må vises på
eget brett underveis.
Spiller mottar informasjon om motstanderens bevegelser.

#### Akseptansekriterier:
Gitt en et spill med fungerende server/client må:
Spillet vise motstanderne og mine egne bevegelser i rett rekkefølge.
Server ta i mot kort fra de andre clientene, og dele informasjon.
GUI vise tydelig hvilke bevegelse som blir gjort.

#### Arbeidsoppgaver:
- GUI håndterer flere spillere
- Server deler andre spillere sine kort med client
- Client sender inn kort til server
- Tydelig å se hvem som er hvem, og hva som blir gjort.
