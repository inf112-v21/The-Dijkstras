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