@echo off
echo Wenn Sie diese Bat-Datei ueber die Dos-Box starten, dann 
echo wechseln Sie zuvor in das Verzeichnis, wo die Bat-Datei liegt!

if exist dbisimp.jar del /q dbisimp.jar
if exist .\classes\dbisimp.jar del /q .\classes\dbisimp.jar

cd classes

jar cvmf ../mft/manifest.mft dbisimp.jar *

copy dbisimp.jar ..\

if exist dbisimp.jar del /q dbisimp.jar

cd ..

pause