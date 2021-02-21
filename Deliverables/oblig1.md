#The Dijkstras ObligDoc 1

---
##OPPGAVE 1


Vi har kartlagt teamet vårt!:

[Erlend](https://github.com/HaugPixel)  
Programmeringspråk: Java, Python, Haskell
Grunnleggende Algoritmer, Git, Sykt hyggelig  

[Trym](https://github.com/Tryrn)  
Programmeringspråk: Java, Python, Haskell  
Cmd, gitbash
Ikke særlig flink med gui, men kan lære :)  
Masse matte som sikkert er ubrukelig i dette emnet  

[Morten](https://github.com/Mortenhavs)  
Programmeringspråk: Java, python helt ok  
Basics i cmd/Linux  

[Aleksander](https://github.com/AleksanderGlowacki)  
Programmeringspråk: Python, Java. Haskell
Git PHP og HTML  

[Anas](https://github.com/anasalmelhem)  
Programmeringspråk: Python, Java. Haskell  
Git, Algoritmer, God bakgrunn i matematikk  
Litt bakgrunn om HTML, CSS, Javascript

---

Vi har valgt Teamleder, Produksjon-ansvarlig, Test-ansvarlig og grafikk-ansvarlig som våre roller i Teamet.

1. Team-leder trengte for å drifte møtekultur og tenke med kommunikasjonen og arbeidsmetoden. 
2. Produksjons-ansvarlig ville vi ha for å sikre framgang i prosjektet og kvalitetsikre kode og kodestilen og få oversikt.
3. Test-ansvarlig ønsket vi for å kvalitetsikre tester og bruk av tester. 
4. Vi ville ha en grafikk ansvarlig
for å ha noen som hadde ansvar for utseendet på sluttproduktet.  
   

I den første perioden prøver vi ut denne fordelingen av roller:  

| | |
| --- | --- |
| Teamleder | Erlend |
| ProduksjonsAnsvarlige | Trym & Aleksander |
| TestAnsvarlig | Anas
| GrafikkAnsvarlig | Morten  


---

##OPPGAVE 2

###Veien Framover

Vi valgte å ha testorientert programmering og bruk prosjektbrettet for å systematisere
arbeidet videre. Det ble planlagt å ha litt ekstra møter i starten for å kunne organisere 
oss og sette oss inn i oppaven. Vi tok utgangspunkt i brukerhistorier og laget arbeidsoppgavene
vi skulle jobbe videre med. Det har blitt laget et klassediagram for å få

Vi planla å ha en parprogrammering hver i uken. Da vi er fem studenter må en av oss ha 2 parprogrammeringer 
i løpet av en uke. Dette vil gå på rundgang


Som hovedplatform for kommunikasjon bruker vi Discord, men vi har også tilgang på mobilnummer til hverandre.

Vi ønsker å bruke Github til lagring av prosjektet, og ønsker å få endring på masteren først of fremst gjennom branching og pull requests 


---

##OPPGAVE 3

###Spesifikasjon:  
Vi ønsker å gjøre brettspillet RobotRally om til et digitalt multiplayerspill, som andre spillere synes er moro å spille.


##MVP-krav og brukerhistorier:

###Vise et spillebrett
####Brukerhistorie: 
Som en spiller ønsker jeg å kunne se spillebrettet for å få oversikt over hindringer og lage meg en plan.

####Løsningbeskrivelse:
Vise brett ved hjelp av GUI verktøy

####Akseptansekriterier:
Applikasjons--vinduet skal skalere riktig i forhold til skjermen.
Tile-design skal være lesbar og de ulike tiles skal kunne skilles fra hverandre.
Baneelementer må være på riktige layers

####Arbeidsoppgaver:
Ordne GUI ved LibGDX og Tiled tutorial.

---

###Vise brikker på spillbrett
####Brukerhistorie:  
Som spiller ønsker jeg en synlig brikke på bordet, slik jeg vet hvor jeg står.

####Løsningsbeskrivelse:  
Gjøre spiller synlig, og plassere den på riktig startposisjon

####Akseptansekriterier: 
Gitt at vi har et synlig brett med en spiller på brettet. Så skal Brikken vår være synlig, og den står på rett plassering iht startposisjon.
####Arbeidsoppgaver:
Lage Grid
Lage logisk spillerKlasse
Koble Grid opp mot GUI

---
###Flytte brikke (vha taster e.l. For testing)
Brukerhistorie:  
Som en utvikler ønsker jeg å kunne flytte brikken rundt på brettet, ved hjelp av piltastene for å teste.

Løsningsbeskrivelse:  
Brikken må kunne bevege seg, og vises på skjerm for spillerne

Akseptansekriterier:  
Gitt at brikken kan bevege seg så må:
Brikken ta imot kommando slik at den beveger seg i korrekt retning.
Brikken bevege seg på skjermen slik at spillerne kan se at den beveger seg.


Arbeidsoppgaver:
Input til bevegelses kommando
Bevegelsesfunksjon
Directionsklasse

---

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
---
###Robot vinner ved å besøke flagg
####Brukerhistorie:  
Som en robot vil jeg besøke et flagg slik at jeg kan vinne og spillet blir fullført.

####Løsningsbeskrivelse: 
Dersom robot har besøkt flagg i riktig rekkefølge, skal robot vinne.
Dersom robot har besøkt alle tidligere flagg og besøker siste så skal den roboten vinne.

####Akseptansekriterier:  
Gitt et scenario der flag er registrert av en robot i kronologisk rekkefølge må
Spillet avsluttes
Vinner melding vises på skjerm

####Arbeidsoppgaver:
Siste verdi på flagg som sier at spillet blir slutt.
Trigger funksjon som avslutter spillet.
Flagg i stigende rekkefølge ved spillstart.

---


##Kode
Vi har gjort ferdig LIBGTDX turtorial. Samt har vi lagt noen logiske klasser med tester. Grid-packen i prosjektet 
vårt er hentet ut fra INF101-kodebase fra i fjor, men vi har refaktorert og endret den etter behov.

##Retrospektiv

Vi har opplevd at det er utfordrende og jobbe sammen om et prosjekt. Med en gang vi er flere involvert blir det mindre
fleksibilitet og det blir vanskelig å få oversikt. Det som kanskje har spist mest tid, er tekniske problemer med 
IntelliJ og Maven. 

En av utfordringene vi har hatt er git branching, og gode rutiner for arbeid mot master-branchen. Når vi er flere om et 
prosjekt er det viktig at vi blir samkjørte om hvordan vi bruke git. Da vi ikke satte faste krav og regler om hvordan 
vi håndtere arbeid mot masterbranching har vi fått lære hva som kan gå galt.

Vi har hatt en positiv opplevelse med parprogrammering. Det går ikke alltid like strukturert for seg, men er lettere å
være to om avgjørelser. I tillegg hjelper det når det er god kommunikasjonsflyt. En ting vi savnet var en bedre struktur 
for hva vi skulle jobbe med. Tenker å flinkere å sette mål for hver ParProgrammering.

Git-Krakken har blitt brukt av et fåtall av gruppen, men vi tenker å innføre dette verktøyet i teamet og bruke tid for
lære det sammen.

Etterhvert ønsker vi å jobbe tettere opp mot en prosjektmodell som Kanban. Dette vil vi gjøre for å gjøre det lettere å
gjøre individuelt arbeid opp mot prosjektet. I tilegg gir det en mer strukturert måte å arbeide med git.


###Arbeid mot Oblig1
Vi har fått gjort oss erfaringer som gruppe, men vi ble ikke helt ferdig med alt vi tenkte å få gjort. 




