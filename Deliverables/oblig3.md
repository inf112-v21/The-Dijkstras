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


-Er dere kommet forbi MVP? 

-Forklar hvordan dere prioriterer ny funksjonalitet.

Hittil har mye av fokuset gått til å få ting til å kjøre riktig i back-end siden (spill-logikk utføres)


#### Brukerhistorie MVP-krav 12
Selve brukerhistoriene kan du finne i møtereferatebe, men er også praktisk samlet i BrukerHistorier.md i Deliverables. 
Vi har ikke skrevet brukerhistorier til MVP-krav 12, dette har gått under radaren til denne innleveringen. 
Vi har fortsatt diskutert og planlagt hvordan vi skulle løse de forskjellig kravene;
her har vi brukt møtereferatene og project-boardet for å dokumentere. 
Koden vi har utviklet har gode tester, noe som viser i det minste at vi har kontroll over akseptansekriteriene. 
Arbeidsoppgavene er også dokumentert i møtereferatene og project-boardet. 

-Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).
Kravlista er lang, men det er ikke nødvendig å levere på alle kravene hvis det ikke er realistisk. Det er
viktigere at de oppgavene som er utført holder høy kvalitet. Utførte oppgaver skal være ferdige.

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





