### Spille fra flere maskiner

---

#### Brukerhistorie:
Som spiller ønsker jeg å kunne spille mot andre spillere slik at jeg har mulighet til å vinne.

#### Løsningsbeskrivelse:
Flere spillere kan delta i spillet samtidig.
Spillere kan utføre handlinger etter tur.

#### Akseptansekriterier:
Gitt flere spillere på en lokal tilkobling må:
- En av spillerne må kjøre server.
- Spillere ha mulighet til å spille mot samme server.
- Spillere gi informasjon til server
- Spillere få informasjon fra server

#### Arbeidsoppgave:
Setup(backend/frontend)
Server applikasjon
Client applikasjon
Kommunikasjon mellom klienter og server.

---

#### Brukerhistorie:
Som spiller ønsker jeg å se de andre spillerne sine bevegelser på brettet slik at jeg kan planlegge min neste tur.

#### Løsningsbeskrivelse:
Motstanderens bevegelser må vises på
eget brett underveis.
Spiller mottar informasjon om motstanderens bevegelser.

#### Akseptansekriterier:
Gitt en et spill med fungerende server/client må:
Spillet vise motstanderne og mine egne bevegelser i rett rekkefølge.
Server ta i mot kort fra de andre clientene, og dele informasjon.
GUI vise tydelig hvilke bevegelse som blir gjort.

#### Arbeidsoppgaver:
- GUI håndterer flere spillere
- Server deler andre spillere sine kort med client
- Client sender inn kort til server
- Tydelig å se hvem som er hvem, og hva som blir gjort.
