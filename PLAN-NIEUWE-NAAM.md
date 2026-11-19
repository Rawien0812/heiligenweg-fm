# Stappenplan: nieuwe naam voor de radio

Opgesteld 19 november 2026. Nu staat "Heiligenweg FM" op 61 plekken in 14 bestanden, plus in afbeeldingen, het webadres en de tv-app.

## Nog te beslissen (open vragen)
1. **De nieuwe naam**, met de exacte spelling (hoofdletters, spaties, wel of geen "FM").
2. **Busthema houden of loslaten?** Het gaat om de voorruit, de haltes, "chauffeur" en de busroute "Heiligenweg ⇄ Lelydorp".
3. **Webadres:**
   - **A (aanbevolen):** een eigen domein met de nieuwe naam, via Cloudflare. Oude links sturen door.
   - **B:** de GitHub-repo hernoemen. Dan werken oude links en QR-codes niet meer.
4. **Nieuwe afbeeldingen met de naam:** zelf laten maken, of voorlopig een eenvoudige versie?

## Stap 1 – Naam vastleggen (Rawien)
- Bepaal de exacte spelling.
- Controleer of de naam vrij is:
  - als domein (.com / .nl / .sr);
  - als naam op Instagram, Facebook en YouTube;
  - als merk in de Benelux (boip.int, "merk zoeken") en bij het BIE in Suriname.

## Stap 2 – Alle teksten (Claude)
- Website: titel, browsertab, logo-tekst, tv-modus en copyright.
- Beheer: logo, menu, titels en uitleg.
- Downloadpagina van de tv-app (`app/index.html`), `tv/index.html`, `README.md` en `LICENSE` (copyright blijft op naam van Rawien Paltoe).
- `zenders.json`: titel, busroute en welkomsttekst.

## Stap 3 – Afbeeldingen (Rawien levert, Claude verwerkt)
- Tv-startbeeld (`images/tv-start.jpg`) en app-banner: een nieuw ontwerp met de nieuwe naam.
- App-icoon ("HFM"): opnieuw maken met de nieuwe letters.
- QR-posters en de deelkaart met code 8418252: opnieuw maken.

## Stap 4 – Webadres
- Nu: `rawien0812.github.io/heiligenweg-fm`.
- Keuze A of B, zie de open vragen.
- `radio.rawgfx.com` en `tv.rawgfx.com` blijven werken en sturen door naar het nieuwe adres.

## Stap 5 – Tv-app (Claude, daarna één keer opnieuw installeren)
- Appnaam, icoon, banner en het adres van de radio aanpassen in `tv-app/` en `.github/workflows/tv-app.yml`.
- Nieuwe app-code (nu `fm.heiligenweg.tv`), bijvoorbeeld `fm.<nieuwenaam>.tv`.
- Vaste ondertekeningssleutel, zodat updates zonder verwijderen kunnen. Dat is ook nodig voor de Amazon Appstore.
- De Downloader-code 8418252 blijft werken, want die wijst naar `tv.rawgfx.com/app.apk`.

## Stap 6 – Buiten de site
- Cloudflare-doorverwijzingen bijwerken. Daarvoor is een nieuw API-token nodig, want het vorige is verlopen.
- Firebase (de bezoekersteller) blijft zoals hij is; die naam ziet niemand.
- Social media, YouTube-kanaalnaam en eventueel later de Amazon Appstore.

## Stap 7 – Testen en aankondigen
- Alle pagina's testen.
- De nieuwe app installeren op de Fire TV.
- De nieuwe link, QR-code en Downloader-code delen met de luisteraars.
