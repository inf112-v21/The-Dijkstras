## Deloppgave 1: Team og prosjekt

----

#### Gruppen om prosjektet
Gruppen synes at prosjektet har vært lærerikt. Vi fikk en treig start, men ettersom ting ble oversiktlig og 
vi ble bedre kjent, jobbet vi godt sammen. 

En av fordelene vi har hatt med prosjektet har vært å kunne få se andres prosjekter, prate med andre grupper om hvordan de jobber,
og ha nær kontakt med gruppeledere som har gjort dette før. Det har vært greit da det prosjektet helhetlig har 
hatt ganske frie tøyler. 

#### Team Roller
I retrospektivet snakket vi om at de rollene som passet godt
ble veldig fremtredende, og de rollene som ikke passet ble passive. Roller som teamleder og gitansvarlig har vært
veldig tydelige, men produksjonsansvarlig, testansvarlig,  er roller som har hatt få konkrete ansvar.

Etterhvert i prosjektet ble det styrkene og svakhetene våre mer tydelige, og vi ble flinkere å delegere
oppgaver som passet hverandr, slik at vi alle kunne bidra på vår måte.

#### Gode & Dårlige Valg:
Et av valgene var å starte utvikling med fokus på back-end utvikling, som vi håpte vi kunne koble opp til en gui.
Vi la ikke så godt rette til denne overgangen når vi utviklen back-end, som førte til en del problemer i sammenkoblingen.
Her burde vi sikkert satt av tid på starten av prosjektet for å lete etter/diskutere en løsning som hadde vært mer integrert med libgdx gui
Problemet fikk store konsekvenser for teamet vårt gjennom prosjektet, noe som ga grunn til ettertanke.

En passivt valg var GUI-ansvarlig jobbende alene på LibGDX over en lengre periode. I etterkant
ser vi dette som litt uheldig, da han trives med å jobbe i lag med andre. I tillegg er LibGDX litt komplekst, og det kan
være greit å ha en sparringspartner. Med passivt valg mener jeg at vi ikke gjor noe 
aktivt for å løse situsjonen.

Et av de gode valgene vi tok var bruk av gitflow metodikk. Det skapte god flyt i arbeidet. Det krevde av oss litt planlegging 
før man branchet, som gjor at vi fikk mindre problemer igjen da vi skulle samle prosjektet. Sammen GitKraken var det et verktøy 
som lot oss jobbe parallelt på en tryggere måte.

Et annet bra valg var bruk av parprogrammering, som har vært den dominerende arbeidsformen
i gruppen. Vi dokumenterte en del i starten, for å få innsikt i hvordan gruppen parprogrammerte, for å lære av hverandre.
Syklusen mellom aktivitet, dokumentasjon, og refleksjon ble en kontinuerlig læringsoverføring for oss som gruppe.


#### Gruppedynamikk og kommunikasjon, start vs. nå:
I starten var vi usikre på mye; hvordan vi skal jobbe og hvem som jobber med hva. 
Etter hvert har vi blitt bedre på å bli bedre kjent med styrkene og svakhetene til hverandre, noe som har gjort gruppearbeidet mer effektivt.
Etter Sprint 2 satt vi opp Kanban board, som hjalp oss å sette opp en plan for 
arbeidet opp mot arbeidskravene. 

Vi har brukt Parprogrammeringer aktivt gjennom hele prosjektet, men hvordan og hvorfor vi bruker dem har endret seg.
I starten var det utrolig nyttig i en digital hverdag å få noen en til en, for å bli kjent både personlig og faglig. Da tenkte
vi at det veiet opp mot at det var mindre effektivt enn å programmere alene.
Videre i prosjektet brukte vi det dat vi synes det var det bra for å lære av hverandre, da forskjellige i gruppen var
flinke på forskjellige ting. Mot slutten av prosjektet var parprogrammeringer brukt hovedsaklig for å få ting gjort. 
Vi så det at planlagte arbeidsøkter i par
var en av de mest produktive arbeidsmetodene vi hadde. Så ble det sosiale, og kunnskapsoverføringen bare et pluss.

Kodemøtene i sprint fire har hjulpet oss å få mer oversikt.
Vi starter møtet med at alle går igjennom produksjonsprogresjonen over forrige uke, der vi ser på hverandres
utfordringer og prøver å finne løsningner.

Måten vi starter sprintene har også endret seg. Den første sprinten var vi veldig usikre hva vi gikk inn i og det var vanskelig å 
å planlegge godt. Som reaksjon på lite struktur i Sprint brukte vi en del tid på å lage planer,
bare for å finne ut senere at det å utføre planen var mer krevende enn forventet.  I sprint 3 ble ble det lettere å se for seg
ukene mot neste oblig og hva man får tid til. Vi var også flinkere å sette små mål for parprorgrammeringer, og realistiske
mål for perioden.

### Forbedringer for teamet

Hvis vi skulle blitt bedre som team burde vi har fokusert mer på å bli enda mer strukturert i arbeidet vårt.
Det vil si at vi blir flinkere å planlegge i og følge opp Kanbanbrettet, er mer nøye med bruk av SCRUM ikke bare for 
å ha progresjon i arbeidet, men for progresjon i teamet som utviklere og samarbeidspartnere.

Om vi startet på ny er gruppen enig om at struktur og arbeidsmetodikk er hovedfokus fra start av.
Få på Scrum/Kanban med gitflow, med fast møtestrukter og klare mål for ukene


## Deloppgave 2: Krav

Her er våre nåværende MVP krav:
- [x] 1.Vise et spillebrett
- [x] 2.Vise brikke på spillebrett
- [x] 3.Flytte brikke (vha taster e.l. for testing)
- [x] 4.Robot besøker flagg
- [ ] 5.Robot vinner ved å besøke flagg
- [x] 6.Dele ut kort
- [ ] 7.Robot flytter på robot
- [x] 8.Bevege robot ut fra valgte kort.  
- [x] 9.Velge 5 kort
- [x] 10.Brettet har hindringer.
- [ ] 11.Spille fra flere maskiner (vise brikker for alle spillere, flytte brikker for alle spillere)
- [ ] 12.Utfører runde etter spilleregler.


#### Fokus i Sprint 4
Vi jobbet mest med krav 12 og 11, men måtte sjekke at de tidligere kravene var oppfylt underveis.

#### Ufullstendig Krav
Vi har vurdert at der vi er nå har vi ikke klart å nå krav 5, 7, 11 og 12. 
5 og 7 er krav som er ufullstendige som resultat av forsinkelse rundt GUI. Det vil si at både 5 og 7 passerer i backend, men
er enda ikke implementert med GUI.
Multiplayer(krav 11) ble laget da vi ønsket å få oversikt hva som trengtes for å implementere 

#### Arbeidskrav 12
Krav 12 ble laget for å sette fokus på forbindelsen mellom GUI og backend.
Forrige sprint jobbet vi med å kunne kjøre en full runde med 5 faser i backend.
Denne sprinten koblet vi backendlogikken opp mot GUI, og har implementert mye GUI funksjonalitet. 
Vi er enda ikke helt ferdige, men har jobbet mot et punkt der vi kan implementere nye utfordringer.

#### Arbeidskrav 11
Multiplayer(krav 11) ble et fokus da vi ønsket å få oversikt hva som trengtes for å implementere multiplayer.
Vi har ordnet en server-tråd som kan ta imort flere clienter og har mulighet for å sende og motta Json filer.
Videre arbeid vil vær å lage en spilllogikk der client er en del av spillet og spør servertråd om nødvendig data.

#### Brukerhistorier sprint 4
Brukerhistorie 11 og 12 er lagt ved i `Deliverables/Brukerhistorier`

## Deloppgave 3: Produktleveranse og kodekvalitet

#### Main
Her finner du Main\
`src/main/java/inf112/skeleton/app/main.java`

Om Intellij har problemer med å bygge prosjektet kan du prøve å markere assets som resources root,
src.main.java som Sources root og src.test.java som Test Sources root

#### Tester
`src/test/java/inf112/skeleton` \
finner du alle våre automatiske tester, alle disse testene skal passere ved denne innleveringen.

Manuell test for spillet ligger i `/Deliverables` Mer info finner du der!

