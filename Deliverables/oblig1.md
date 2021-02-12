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

[Morten]()  
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

En kort beskrivelse av det overordnede målet for applikasjonen

En liste over brukerhistorier til systemet basert på MVP-kravene.

For hver brukerhistorie, skal dere ha akseptansekriterier og arbeidsoppgaver, samt beskrivelse av hvilke krav brukerhistorien oppfyller (dette lager dere kun for historier dere er ferdige med, holder på med, eller skal til å begynne med)

En prioritert liste over hvilke brukerhistorier dere vil ha med i første iterasjon (altså frem til levering av denne oppgaven, se deloppgave 4 for forslag).


###Spesifikasjon:  
Gjøre et brettspill om til dataspill som skal kunne være multiplayer.

---
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


##OPPGAVE 4
Oppsummering // TODO

Utfør et kort prosjekt-retrospektiv og diskuter hva som gikk bra, hva som ikke fungerte helt som forventet, 
hva (om noe) som ikke virket i det hele tatt, og eventuelle nye aktiviteter eller verktøy som teamet vil prøve ut i løpet av neste
obligatoriske oppgave. Diskuter hvorfor ting fungerte eller ikke fungerte. Skriv opp en kort oppsummering 
av diskusjonen, og last opp til team repo-et.
På slutten av denne oppgaven kan dere gjøre en liten vurdering av hvor bra dere traff på oppgaven. Dette kan dere bruke til å justere hvor mange oppgaver dere tenker å få inn i neste iterasjon, som da leveres med obligatorisk oppgave 2.




