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
