### Robot vinner ved å besøke flagg
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