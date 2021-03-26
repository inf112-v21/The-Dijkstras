## Deloppgave 1: Team og prosjekt

---

### Tanker fra Retrosepktiv

#### Roller i teamet
Teamet sine roller har blitt farget av hvordan vi jobber. 
I starten tenkte vi nok at **produksjonsavnsvarlige** skulle ha en mer oversiktsrolle over koden, men de har heller
tatt mer ansvar for selve kodeproduksjonen.
**Git-ansvarlig Anas** har blitt en mer sentral rolle enn vi først anså. Git-flow og bruk av
branches har gitt oss noen større utfordringer og da er det greit med noen som rydder opp. Han har i tillegg tatt et
stort ansvar når det gjelder oversikt over, og implementering i backend.
Etterhvert ble det tydelig at Morten ikke hadde like mye erfaring med Java som de andre, så vi har prøvd å legge opp til
å bruke han i alternative oppgaver, som refactoring og dokumentasjon, og som sparringspartner i parprogrammeringer.
**Produksjonsansvarlig Aleksander** har jobbet mer med Libgdx enn noen andre og er sentral i videre arbeid mot gui.

#### Klare mål
En av de tingene vi har gjort bra denne perioden har vært å sette klare mål for ukene og parprogrammeringene. Vi bruke
som regel den siste halvtimen av møtene å gå inn i projectboard å bli enige hva vi ønsker å få gjort. Så deler vi ut 
individuelle ansvar, og belyser oppgaver som kan være lure for parprogrammeringer. Vi var tidlig ute å bestemte at
at vi måtte ha bedre deadlines mot slutten av sprinten, slik at vi fikk en mykere landing mot innlevering.

#### Samarbeid
Samarbeidet innad i gruppen vist seg å være en verdi i seg selv. Gjennom parprogrammeringer og felles utfordringer
har vi lært av hverandres tilegnede kunnskaper. Eksempler på dette er hvordan vi jobber med Git, generics, testing,
og småtriks i Java. Det er mye taus kunnskap i koding, noe som er veldig tydelig når du møter noen som er veldig
god innen sitt fag. 

Mandagsmøtene har blitt gjort om til codereview der vi ser på hverandres kode. Dette gir oss bedre over sikt 
og så får man konstruktive tilbakemeldinger på kode og tips til videre arbeid.
Det samarbeidet er også med på å bygge oss opp som team, og som utviklere.

#### Utfordringer

Det at vi har gått på en flaskehals mellom back/end front end, har gjort at arbeidsoppgaver til brukerhistoriene våre som vi har lagt i
prosjektboardet sliter med å bli gjort ferdig. Dette har gjort at prosjektboard har blitt litt uoversiktlig, som hemmer oss som team.
En annen ting er at i slutten av  sprinten fokuserer mer på implementere ønsket funksjonaliet, enn å kvalitetssikre koden.
Dette blir teknisk gjeld som kan være vanskelig å ha god oversikt over. 

Vi ble enige om å ha en nytt arbeidskrav, men så glemte vi å lage brukerhistorie, og fikk da utfordringer med å få 
overikt over oppgaven. Anas la Arbeidskravet inn som et flag i Prosjektboardet og der han skapte litt av den samme
oversikten som brukerhistoriene gir oss. Det var bra vi gjengjente at det var et hull i arbeidsmønsteret,
men vi burde nok blir strenger i hvordan vi jobber med brukerhistoriene og backlog i planleggingsdelen av sprinten. 

#### Til neste sprint:
- Ryddigere prosjektboard.
- Fullføre brukerhistorier, ikke implementer ny funksjonalitet
- Gjøre Scrum og Kanban mer nøye for å unngå at ting faller ut.

---

## Deloppgave 2: Krav

Her er våre nåværende MVP krav:
1. Vise et spillebrett
2. Vise brikke på spillebrett
3. Flytte brikke (vha taster e.l. for testing)
4. Robot besøker flagg
5. Robot vinner ved å besøke flagg
6. Spille fra flere maskiner (vise brikker for alle spillere, flytte brikker for alle spillere) - Ikke en del av oblig3
7. Dele ut kort
8. Velge 5 kort
9. Bevege robot ut fra valgte kort.
10. Brettet har hindringer.
11. Robot flytter på andre roboter.
12. Utfører runde etter spilleregler. 

#### Arbeidskravene i Sprint 3
Her har MVP krav 12 vært et stort fokus for denne innleveringen, noe som vi har fått til i back-end delen.\
Krav 9 har vi fått til i back-end. \
Ved krav 10 har vi introdusert vegger (i back-end), men mangler fortsatt hull og lasere. \
Krav 11 har vi kommet i gang med i back-end, men kravet er ikke godt nok testet som å anses ferdig.

Mye av disse kravene har vi fått til kun i back-end; så snart visualisering av spillbrett er oppnåd vil disse kravene
mer eller mindre automatisk være oppfyllt for front-end i tillegg til back-end.


#### Fokus på MVP 12
Hittil har mye av fokuset gått til å få ting til å kjøre riktig i back-end siden (spill-logikk utføres).
Spesielt dette med krav 12; utføre runder. 
Dette mener vi har vært et en veldig sentral del av spillet som burde være på plass før vi beveger oss videre i prosjektet. 
Siden det har vært så stor fokus på dette "back-end" har vi slitt noe med utvikling av LIBGDX GUI siden (front-end), 
hittil har det vært kun en person ansvarlig for utvikling av dette (Alexander), 
så dette kommer vi til å sette mer ressurser på framover.


#### Brukerhistorie MVP-krav 12
Selve brukerhistoriene kan du finne i møtereferatebe, men er også praktisk samlet i BrukerHistorier.md i Deliverables. 
Vi har ikke skrevet brukerhistorier til MVP-krav 12, dette har gått under radaren til denne innleveringen. 
Vi har fortsatt diskutert og planlagt hvordan vi skulle løse de forskjellig kravene;
her har vi brukt møtereferatene og project-boardet for å dokumentere. 
Koden vi har utviklet har gode tester, noe som viser i det minste at vi har kontroll over akseptansekriteriene. 
Arbeidsoppgavene er også dokumentert i møtereferatene og project-boardet. 


Kjente bugs finner du i README.md 
Det er verdt å peke ut at kravet "roboter flytter" andre roboter er begynt utvikling på, 
men ikke skrevet tester for, så det kan være bugs til stede ved denne funksjonaliteten. 

---

## Deloppgave 3: Produktleveranse og kodekvalitet
#### Main
Main ligger i:
`src/main/java/inf112/skeleton/app/main.java` 

Om Intellij har problemer med å bygge prosjektet kan du prøve å markere assets som resources root,
src.main.java som Sources root og src.test.java som Test Sources root

#### Tester
Under src.test finner du alle våre automatiske tester, alle disse testene skal passere ved denne innleveringen. 

Vi har en form for manuel testing ved bruk av **GameRunner** klassen i game pakken under src.main. 
GameRunner gjør en Runde-simulasjon i Game klassen for å vise eksakt hva som skjer når runder kjører.
Her bruker robotene en enkel random strategi; de velger tilfeldige kort vær runde. 
I klassene Game, GameBoard og RoundHandler finner du debugPrint som skriver ut hendelser i konsollen;
hvilke kort blir brukt, hvor robotene beveger seg på kartet. 
debugPrint er da hovedsakelig brukt for GameRunner. 





