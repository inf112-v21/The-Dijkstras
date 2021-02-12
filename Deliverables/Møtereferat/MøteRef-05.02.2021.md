##The Dijkstra’s ekstraordinært møte 05.02.21

Agenda:

- Innsjekk
- Spesifikasjon
- Gå gjennom MVP
- Brukerhistorier
- Parprogrammering


Innsjekk

Aleksander  
Har gått gjennom tutorial for TileLayer og sett på Oppsett for LibGDX. Han sjekke inn.
 

Trym  
Kommer rett ut av dusjen. Lest på regelboken Roborally. Jobbet med å få overblikk. Han sjekke inn

Erlend  
Sett turtorials for RoboRally og LibGDX. Fixet referatdolk i prosjektet.S Sjekke inn

Morten  
Sett youtube videor av folk spill Robo rally. Sett på regelboken. Sjekker inn.

Anas  
Syk, men følger med! Sjekker inn




Vi gikk gjennom brukerhistorier, og arbeidsoppgave fremover, sett på MVP krav. Lagt inn arbeidsoppgaver på Github prosjektet.


####Spesifikasjon:

Gjøre et brettspill om til dataspill som skal kunne være multiplayer.
2d verden der alt ligger på tiles

##MVP-krav og brukershistorier:

###Vise et spillebrett
####Brukerhistorie:
Som en spiller, ønsker jeg å kunne se og lese hele spillebrettet.
Jeg ønsker å få en tydelig oversikt over banen og hindringer, slik at jeg kan se mulige veier for å komme seg gjennom banen og til mål.
####Løsning:
Spillvinduet skal vise hele banen og ha oversiktlig design.
####Akseptansekriterier:
Applikasjonsvinduet skal skalere riktig i forhold til skjermen.
Tile-designen skal være lesbar og de ulike tiles skal kunne skilles fra hverandre.
Baneelementer må være på riktige layers og assets skal bli rendered.




###Vise brikker på spillbrett
####Brukerhistorie
Som spiller ønsker jeg en synlig brikke på bordet, slik jeg vet hvor jeg står.
####Løsningsbeskrivelse:
Brikken må bli synlig
####Akseptansekriterier:
Gitt at vi har et synlig brett med en spiller på brettet. Så skal
1. Brikken være synlig
2. Den stå på rett plassering.


###Flytte brikke (vha taster e.l. For testing)

####Brukerhistorie
Som en spiller ønsker jeg å kunne flytte brikken rundt på brettet.
For testing ønsker jeg å kunne flytte roboten rundt med taster for å kunne til slutt interacte med de forskjellige tingene man skal kunne interacte med slik at man kan se at brikken og de andre tingene reagerer på hverandre.

####Løsningsbeskrivelse.
Brikken må kunne bevege seg, og vises på skjerm for spillerne

####Akseptansekriterier
Gitt at brikken kan bevege seg så må:
Brikken ta imot kommando slik at den beveger seg.
Brikken bevege seg på skjermen slik at spillerne kan se at den beveger seg.

###Robot besøker flagg
####Brukerhistorie
Som Robot må jeg kunne besøke flagg, da det er delmålet for å vinne spillet.
####Løsningbeskrivelse:
Flagg må eksistere på brettet og robot må kunne besøke det
####Akseptansekriterier:
Gitt at det eksisterer et brett med roboter som kan bevege seg må
Flagg-tile eksistere på brett
Robot kunne gå på et flagg
Registreres besøk på et flagg.


###Robot vinner ved å besøke flagg - Trym
Som en Robot
Ønsker jeg å vinne dersom alle flagg er besøkt i kronologisk rekkefølge
Slik at roboten oppdateres med; “har besøkt flagg x” dersom flagg x-1 er besøkt ELLER x = 0. (antar man starter med første flagg er flagg_0)

####Løsningsbeskrivelse:
Dersom robot har besøkt flagg i riktig rekkefølge, skal robot vinne.


####Akseptanskriterier:
Gitt et brett med bevegelige roboter og flagg:
Flagg må besøkes i kronologisk rekkefølge
Alle flagg må besøkes
teknisk:
Denne funksjonen gjør noe slikt på roboten; robot.flag_x.visit() -> setter verdi true dersom kravene under passeres.
Her sjekkes noe viktig: if robot.flag_(x-1).visited() gir verdi false og x != 0, oppdateres ikke robot.flag_x.visited() til true. (med tanke på reglene) 
