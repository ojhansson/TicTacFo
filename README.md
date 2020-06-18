# Tic Tac Fo’
Tic Tac Fo’(4) er en Tic Tac Toe-app hvor man kan spille 4 på rad enten mot mobilen eller i to-spiller-modus lokalt på samme mobil.

## Instruksjoner
• Start opp appen
• Hvis du vil spille mot AI, skriv inn ditt navn som «player 1» og huk av «Play against
computer». Hvis dere er to spillere, fyll inn begges navn og trykk «Play»
• Prøv å få 4 på rad. Spill igjen ved å trykke «Play Again»
• Sjekk dine resultater ved å gå tilbake til menyen og trykk på «Leaderboard»

## Spillet
Når spillet startes, starter MenuFragment som kjøres via MainActivity. Her kan man legge inn navn, velge om man skal spille mot AI eller ikke eller gå til Leaderboard.
Herifra kan man starte GameActivity
Appen har funksjonalitet til å spille både mot mobilen eller å spille to personer. Logikken for spillet er veldig lik, men hvis «Play against computer» blir huket av, vil spillet aktivere player 2 som cpu og gjøre at mobilen tar trekk istedenfor en annen spiller. Hvis player 2 er AI vil player 2 automatisk bli kalt «TTTBot»

Når man er i spillet er det en tekst som forteller navnet til den spilleren sin tur det er. Under dette har man en timer som viser hvor lenge det er siden spillet startet.
Så fort en spiller har fått 4 på rad sjekker appen hvem som vant, brettet deaktiveres og resutlatet sendes til leaderboard via databasen.

## Database
Appen inneholder en database som lagrer hver gang en spiller vinner. Denne dataen lagres på tvers av restart av app og telefon.
Hver gang en spiller vinner sjekker databasen om navnet på spilleren allerede finnes i databasen via LiveData. Livedata sørger for at disse dataene kan være tilgjengelig til enhver tid hvis man kjører i gang en «Observer» som vil observere endringer i dataen. Hvis spilleren finnes, hentes den nåværende poengsummen til spilleren ut. Så kjører den en ny tråd for å legge inn spilleren med enten ett poeng hvis spilleren ikke finnes på leaderboardet, ellers legger den på 1 til spillerens nåværende poengsum. Hvis AI vinner vil dens navn «TTTBot» bli lagt til/få oppdatert poengsum i leaderboard.

Databasen er en Room database som er en «framework» for å gjøre det mer effektivt å jobbe med SQLite i Android. Når appen skal snakke med databasen går den først gjennom en ViewModel (ScoreModel) som kan sørge for at data kan lagres utenfor aktiviteter eller
fragmenter sine lifecycles. Denne gjør kall til repository (ScoreRepository), som er der handlinger utføres før den kaller på DAO. I Repository kan man for eksempel utføre en async task som gjør et kall på en ny tråd.

I ScoreDao defineres selve SQL-logikken og det er denne som kaller direkte til databasen «ScoreDatabase» for å få ut informasjon.
Score-klassen er «Entityen» eller tabellen som setter hvilke kolonner som skal finnes og andre regler for tabellen.

## Leaderboard
Leaderboardet viser poengsummene til alle som har spilt i et RecyclerView. Leaderboard henter LiveData om poengsummer i databasen og legger de inn i adapteret som gjør at de vises i recyclerView. Leaderboard blir sortert etter hvem som har flest poeng først.

## AI/TTTBot
Den kunstige intelligensen i spillet når man spiller mot mobilen baserer seg på random genererte nummere. Hvis man velger at player 2 er cpu, vil appen registrere det etter at player 1 har gjort sitt trekk. Da vil en metode plukke en tilfeldig knapp som ikke er allerede fylt for så å legge brikken sin der.

## Fragments
Når appen startes, kjører MainActivity i gang et fragment «MenuFragment» som dekker hele skjermen. Dette er den eneste fragmenten jeg har siden jeg ikke hadde noen andre steder der det fungerte for meg å lage fragment.
Jeg har ikke fragment i spillet men heller en GameActivity. Dette er fordi med logikken for hvilken «celle» på brettet blir klikket på styres via OnClick metoder som viste seg å ikke oversette veldig bra til fragments. Jeg fikk da ikke til å legge det over i fragment og gikk tilbake til å ha spillet i en activity.
Leaderboard var også problematisk å få inn i et fragment. Da jeg prøvde å legge denne i et fragment klarte den ikke å hente informasjonen fra databasen til recyclerview, men dette fungerte i Activity.
Det kan være det ikke var nødvendig å ha et fragment for menyen siden det er bare et fragment og det kunne like godt vært i MainActivity. Jeg valgte likevel å ha det med for å vise at jeg kan det å bruke fragments.
