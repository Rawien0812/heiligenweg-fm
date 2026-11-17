# Heiligenweg FM

Een nostalgische muzieksite van **Rawien Paltoe**: je zit voorin een Surinaamse lijnbus van Heiligenweg naar Lelydorp, en de chauffeur draait baithak gana, kaseko en Trafassi.

- Eén bestand: `index.html` (geen build nodig)
- Muziek via YouTube-embeds. Een nummer dat niet ingesloten mag worden, wordt automatisch overgeslagen.
- Nummers aanpassen: bewerk de lijst `STATIONS` onderaan in `index.html` (YouTube-video-ID, titel, artiest).

## Online zetten met GitHub Pages
1. Push deze map naar een GitHub-repo.
2. Ga naar Settings → Pages → Source: *Deploy from a branch*, kies `main` en `/ (root)`.

## Screenshots
![Desktop](screenshots/preview-desktop.png)

## Beheer
Zenders, muziek en teksten pas je aan op **/beheer/** (bijvoorbeeld `https://rawien0812.github.io/heiligenweg-fm/beheer/`).
Alles staat in `zenders.json`; de beheerpagina slaat wijzigingen via de GitHub-API op in deze repo, waarna de site vanzelf opnieuw online gaat.
Inloggen gaat met een fine-grained GitHub-token met alleen toegang tot deze repo (Contents: Read and write).

## Tv-modus
Open `https://rawien0812.github.io/heiligenweg-fm/tv` (of de site met `#tv` erachter) voor een tv-weergave: video schermvullend, nu-speelt-info, Top 5 met QR-code. Bediening met de afstandsbediening: OK = afspelen/pauze, ← → = vorige/volgende, ↑ ↓ = volume.
De QR-code wordt gemaakt met `js/qrcode.js` (qrcode-generator van Kazuhiko Arase, MIT-licentie).

## Rechten
© 2026 Rawien Paltoe. Alle rechten voorbehouden. Deze code, vormgeving en inhoud mogen niet zonder schriftelijke toestemming van Rawien Paltoe worden gekopieerd, gewijzigd of hergebruikt. Zie [LICENSE](LICENSE).

## Tv-app (Fire TV / Android TV)
Het Android-project staat in `tv-app/`. GitHub bouwt de app automatisch (workflow *Tv-app bouwen*) en zet `HeiligenwegFM.apk` bij de release **tv-app**.
Downloadpagina en installatie-uitleg: `https://rawien0812.github.io/heiligenweg-fm/app`.
Voor een vaste ondertekeningssleutel (updates zonder eerst verwijderen) kun je de repo-geheimen `TV_KEYSTORE_B64` en `TV_KEYSTORE_PW` instellen.
