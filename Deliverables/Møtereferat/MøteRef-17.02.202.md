## Dijkstra's Møte 17.02.21

Insjekk:
Erlend: Sett

Aleksander: Mye å gjøre for tiden!

Trym: UML tutorial.

Morten: Gjort hjemmeleksa. PP med Anas

Anas: Gjort hjemmeleksa PP med morten

MVP KRAV


6. Spille fra flere maskiner (vise brikker for alle spillere, flytte brikker for alle spillere) ### NIET. Syka blyat
7. Dele ut kort - Anas
8. Velge 5 kort - Trym
9. Bevege robot ut fra valgte kort. Morten
10. Brettet har hull. - Aleksander
11. Robot flytter på andre roboter. Erlend

Lekse til neste gang: skrive ut en brukerhistorie, akseptansekriterie, Løsningsbeskrivelse

###Robot besøker flagg
####Brukerhistorie:
Som Robot må jeg kunne besøke flagg, da det er delmålet for å vinne spillet.

####Løsningsbeskrivelse:
Flagg må eksistere på brettet og robot må kunne besøke det.

####Akseptansekriterier:
Gitt at det eksisterer et brett med roboter som kan bevege seg må:
Flagg-objektet eksistere på brett,
Robot kunne besøke et flagg.
Besøket må registreres.

####Arbeidsoppgaver:
Flag counter i Robot klasse
Flagget må ha en verdi
Lage Flag-klasse

GAMEBOARD-KLASSSE
Gameboard klassen Implementerer robot-rally funksjonalitet på et gridobjekt.

GAMEHANDLER-KLASSE
Gamehandler klassen håndterer actioninput og endrer på gameboard.



RETROSPEKTIV
Mandag 13:15. Siste halvdel

Rekkefølge ting skal skje
A. Reveal Program Cards
B. Robots Move
C. Board Elements Move
D. Lasers Fire
E. Touch Checkpoints
