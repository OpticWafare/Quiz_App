# Quiz_App

Konzept und Rahmenbedingungen
1.1	Ziele des Anbieters 
Programmieren einer Android App, mit welcher Quizze erstellt werden können. Die Quizze können danach von Freunden und Freundinnen beantwortet werden.
Ein Quiz besteht aus mehreren Fragen. Jede Frage hat vier vorgegebene Antwortmöglichkeiten, von welchen eine richtig ist. 
Alle User und Quizze werden in eine lokale Datenbank gespeichert, auf die alle User via der App Zugriff haben. Jeder User kann Quizze erstellen. Die erstellten Quizze werden dann in einem Reiter im Hauptmenü der App aufgelistet und können ausgewählt und dargestellt werden. 
User können mittels Android Push-Notifications über neue Quizze benachrichtigt werden.
Je nachdem wie viele richtige Fragen beantwortet wurden wird eine dementsprechende Punktezahl vergeben. Eine richtig beantwortete Frage ergibt einen Punkt. Ebenso bekommt der User sofort nach der Beantwortung natürlich selbst optisches Feedback wie viele Fragen er richtig beantwortet hat. Nach erfolgreichem Beantworten der Fragen wird der Ersteller darauf hingewiesen wie viele Punkte der Teilnehmer erlangt hat. 
Soll Kriterien
1.	Einstellungen
•	User sollen sich mit Username und Passwort registrieren und einloggen können.
•	User sollen die IP Adresse und den Port des Servers, mit dem sie sich verbinden wollen, in der App einstellen können. 
2.	Quizze
•	User sollen Quizze erstellen und speichern können.
•	Alle erstellten Quizze sollen in einem Reiter in der Hauptansicht aufgelistet werden. Beim Klicken auf eines dieser Quizze soll die Quiz-Ansicht für dieses Quiz geöffnet werden.
•	Eine Quiz-Ansicht (für ein Quiz) soll vorhanden sein. 
o	Dort soll der Quiz-Ersteller die Fragen und dazugehörigen richtigen/falschen Antworten des gewählten Quiz nochmals einsehen können. 
o	Die Quiz-Teilnehmer sollen dort das gewählte Quiz beantworten können (wenn sie es noch nicht beantwortet haben). Wenn sie es bereits beantwortet haben, sollen sie ihre gewählten Antworten und die tatsächlich richtigen Antworten einsehen können. 
•	Sobald ein Quiz erstellt wurde, soll eine Quizanfrage an jeden User gesendet werden. Diese ruft die User dazu auf, am Quiz teilzunehmen.
•	Nutzer sollen Android Pushmitteilungen erhalten, sobald sie eine Quizanfrage erhalten haben.
•	Wenn ein Quiz-Teilnehmer ein Quiz beantwortet hat, soll der Quiz-Ersteller eine Android Pushmitteilung über den erreichten Punktestand erhalten. 

Kann Kriterien
•	Schicken von Push-Mitteilungen (Android) bei Erreichen eines neuen Highscores 
Schicken von Push-Mitteilungen (Android) bei Erreichen voller Punktezahl
•	Multiple Choice Fragen
•	Variable Anzahl an möglichen Antworten pro Frage 
•	Anzahl der zu bekommenden Punkte für eine richtig beantwortete Frage pro Frage einzeln einstellbar  
•	Bewertungssystem für erstellte Quizze einrichten
•	Beim Auflisten der Quizze in der App optisch hervorheben ob ein Quiz
o	von dem User selbst erstellt wurde (oder nicht)
o	von dem User bereits ausgefüllt wurde (oder nicht)
•	Rangliste der teilnehmenden User mit Punkte in App anzeigen
•	Nur bestimmte User für ein Quiz auswählen
•	Dokumentation des Codes 
•	Zusätzlicher „Zeitbomben“-Modus
o	Beim Erstellen des Quiz kann der User einen Zeitpunkt angeben, an dem das Quiz für die anderen Spieler freigegeben werden soll (anstatt sofortiger Freigabe)
o	Den Zeitpunkt kann der Ersteller frei festlegen und geheim halten
o	Somit wissen die anderen Teilnehmer nie, wann sie plötzlich die nächste Quizanfrage bekommen
o	Damit es spannender wird, bekommen die Teilnehmer Zusatzpunkte, wenn sie das Quiz schnell lösen
	Der Erste, der das Quiz beendet, bekommt am meisten Zusatzpunkte.
	Der Zweite, der das Quiz beendet, bekommt am zweit-meisten Zusatzpunkte.
	…
o	Die Spieler müssen einen Kompromiss finden zwischen „schnell aber vielleicht falsch“ oder „langsam aber sicher“
o	Für einen zusätzlichen Spaßfaktor kann die Uhrzeit der Freigabe auch in unpassenden Momenten eingestellt werden (z.B. während einer Unterrichtsstunde)

1.2	Ziele und Nutzen des Anwenders
Sie soll ein guter Zeitvertreib für zwischendurch darstellen und z.B.: zum Überbrücken von Wartezeiten genutzt werden.
Da die Quizze von Freunden und Freundinnen selbst erstellt werden, können auch Freundschafts-Quizze erstellt werden. Dort wird auf die Probe gestellt, wie gut die Freunde und Freundinnen sich gegenseitig kennen. 
Zusätzlich kann die App zum Lernen für die Schule verwendet werden, indem Schüler und Schülerinnen Quizze über den Lernstoff erstellen.
 
1.3	Ressourcen
1.3.1	Technologien
•	Java mit Servlets (für Server)
•	Java mit Android SDK (für App)
•	MySQL als relationale Datenbank (zum Speichern von User- und Quizdaten)
•	EclipseLink JPA (als ORM Provider um eine Verbindung zwischen Java und MySQL zu ermöglichen)
•	Google Firebase (für Android Pushmitteilungen)
•	Git (als Versionskontrollsystem für die Programmierung)

1.3.2	Software Programme
•	Eclipse (für Server-Programmierung)
•	Android Studio (für App-Programmierung)
•	Git Client (zur Steuerung von Git mittels Konsolenbefehlen)
•	GitHub (als Git Server)

1.4	Technische Vorgehensweise für Nutzer
•	Der Java Servlet Server wird installiert (am Rechner), welcher mittels ORM-Technologien eine Verbindung mit dem lokal laufenden Datenbank Verwaltungssystem (am Rechner) aufnimmt.
•	Die Nutzer der App verbinden sich mit dem Java Server indem sie die IP Adresse und den Port des Servers (=lokaler Rechner) in der App einstellen. Der Server dient somit als Schnittstelle zwischen der lokalen Datenbank und den Nutzern. 
•	Damit alle Nutzer sich mit dem Server verbinden können, müssen sie sich alle im selben Netzwerk wie der Server befinden.
•	Wenn die Nutzer Einsicht auf die Datenbank haben wollen, können sie die MySQL Datenbank mittels Konsolenbefehlen erreichen. Grundsätzlich ist dies jedoch nicht notwendig, da sich der Java Server um die Datenbank kümmert. 

 









 
