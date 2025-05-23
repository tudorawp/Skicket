DOCUMENTAȚIE TEHNICĂ – APLICAȚIA SKICKET
1. INTRODUCERE
1.1 Context
În România, iubitorii sporturilor de iarnă se confruntă cu dificultăți în accesarea informațiilor despre biletele skipass disponibile în diverse stațiuni montane. În prezent, nu există o aplicație unificată care să permită compararea, vizualizarea și achiziționarea centralizată a acestor bilete.
1.2 Scopul aplicației
Skicket este o aplicație mobilă dezvoltată pentru Android, care simplifică procesul de selecție a biletelor de tip skipass în România...
2. PROBLEMA ABORDATĂ
* Lipsa unei platforme centralizate de informare și achiziție pentru skipass-uri.
* Lipsa vizibilității pentru tipurile de bilete (zilnic, pe puncte, de noapte etc.).
* Lipsa unei experiențe digitale moderne pentru turiștii de iarnă.
3. SOLUȚIA PROPUSĂ
* Aplicație mobilă dezvoltată în Kotlin.
* Afișare dinamică a biletelor disponibile pe domenii schiabile...
* Design adaptabil pentru extindere ulterioară...
4. PUBLIC ȚINTĂ
* Turiști sezonieri.
* Practicanți de ski și snowboard...
* Administratorii de domenii schiabile interesați să se alăture platformei.
5. FUNCȚIONALITĂȚI
Funcționalități principale:
* Vizualizare domenii schiabile disponibile...
* Confirmare simulată a plății.
* Feedback și notificări prin Toasts și layout-uri dedicate.

Funcționalități planificate (Roadmap):
* Hărți interactive ale pârtiilor...
* Istoric bilete achiziționate.
6. ARHITECTURĂ ȘI TEHNOLOGII
* Limbaj: Kotlin...
* Structură modulară:
  * MainActivity – Hub-ul de pornire...
  * ConfirmPaymentActivity – Ecranul de confirmare a plății.

7. DESIGN ȘI INTERFAȚĂ
* Schema de culori: Albastru închis (#003A5A)...
* Utilizare de Toast-uri pentru notificări...
     
8. MOD DE UTILIZARE
Utilizatorul pornește aplicația și este primit cu un mesaj de introducere. Datorită interfaței prietenoase, utilizatorul poate să își înceapă navigarea de bilete prin apăsarea butonului “Domenii Schiabile”
După achiziționarea biletelor, utilizatorul poate să își vadă ce bilete au fost adaugate într-un coș de cumpărături electronic prin apăsarea butonului “Coș de cummpărături”
În coșul de cumpărături, dacă utilizatorul nu dorește să cumpere un anummit skipass, acesta poate goli coșul (pe viitor se va implementa o metodă înn care se poate remedia câte bilete trebuie)
Confirmă plata (simulată) și primește un mesaj de succes.
Pe viitor vor exista două versiuni : Utilizator și Admin (se va face printr-un sistem de log in)
Ca Admin (Administrator) poți modifica biletele, informațiile referitoare la domeniu schiabil. Va avea posibilitatea de a accesa baza de date a tranzacțiilor. Pentru a remedia probleme sau goluri în baza de date.
Această tehnologie va fi apllicată pe viitor prin intrermediul unui proiect SQL sau prin metoda FireBase de la Google.
9. SALVAREA DATELOR
* Se utilizează SharedPreferences pentru a salva coșul sub forma unui JSONArray...
* Exemplu de obiect JSON salvat în coș:
{
"domain": "Poiana Brașov",
"type": "puncte",
"label": "+1 Adult",
"startDate": "23/05/2025",
"quantity": 1
}
10. JUSTIFICARE TEHNOLOGII
* Kotlin: limbaj modern, concis, ușor de utilizat și optimizat pentru Android...
* Android Studio: platformă robustă pentru dezvoltare și debugging.
11. TESTARE ȘI BUG FIXING
* Testare manuală pe emulator și pe dispozitive reale...
* Handlere de erori pentru lipsă fișiere JSON, activități nedeclarate.
12. OPINIA DEZVOLTATORULUI
„Skicket este un pas real spre digitalizarea serviciilor oferite pe domeniile schiabile. O modalitate eficientă pentru a economisi timp și resurse precum plasticul si cartonul” 
13. ROADMAP (Funcție de adăugat pe viitor)
* Integrare Google Maps pentru afișarea pârtiilor... (Printr-un API)
* Lansare pe Google Play Store.
14. FEEDBACK UTILIZATORI
„Rapid și clar, exact ce as avea nevoie!” (Părinte)
“Nu este rea pentru un începător” (Coleg de bancă)
“Are potențial!” (Părinte)
“Interesantă idee!” (Profesoară)
15. CONCLUZIE
Skicket este o aplicație inovatoare care digitalizează procesul de achiziționare a skipass-urilor în România, ceea ce ajută la economisirea timpului, din punctul de vedere al utilizatorului, dar și din punctul de vedere ale domeniilor schiabile. 
