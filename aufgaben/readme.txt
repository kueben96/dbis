create_jar.bat - Erstellt ein Jar-Archiv f�r Ihre Applikation

create_jar_sdk.bat - Erstellt ein Jar-Archiv f�r Ihre Applikation (nutzt Standard Java Edition aus dem �bergeordneten Verzeichnis 'sdk')

start.bat - Startet Ihre Applikation aus Ihrem Jar-Archiv, das mit create_jar.bat bzw. mit create_jar_sdk.bat erstellt wurde

start_sdk.bat - Startet Ihre Applikation aus Ihrem Jar-Archiv, das mit create_jar.bat bzw. mit create_jar_sdk.bat erstellt wurde (nutzt Standard Java Edition aus dem �bergeordneten Verzeichnis 'sdk')
----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------



FALL: NUR WENN SIE KEIN �BERGEORDNETES VERZEICHNIS 'SDK' IM ORDNER 'DBIS' HABEN!
----------------------------------------------------------------------------------------------
Nutzen Sie in diesem Fall create_jar.bat UND start.bat. Andernfalls 
create_jar_sdk.bat UND start_sdk.bat
----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------

Esteller: Larissa Janssen

Es wird vorausgesetzt, dass JVM lokal installiert ist.
Es wurde mit Java Version "1.6." getestet.


Erzeugung des JAR-Archiv f�r Ihr Programm
1.
Um ein JAR-Archiv zu erzeugen, wird das Javatool JAR ben�tigt. Das JAR-Tool kann wie folgt aufgerufen werden:
Start/Alle Programme/ FH-E-Software/Weitere Entwicklungsumgebungen/Java/Sun Java Development Kit. Es wird Konsolenfenster von Windows angezeigt.

2.
Wechseln Sie in das Projektverzeichnis, wo Ihr Java-Importprogramm liegt:
chdir /d ...

Z.B. chdir /d Z:\daten\eclipse34\dbis\dwProgramm

3. F�hren Sie die ausf�hrbare Datei create_jar.bat aus:
create_jar.bat

4. Im 3. Schritt muss ein Jar-Archiv Namens dw.jar f�r Ihr Programm automatisch erzeugt werden.
Anschlie�end kann das Importprogramm mit Hilfe von start.bat gestartet werden.
