@echo off
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
echo Anwendung wird gestartet...
echo "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"

..\sdk\bin\java -classpath "..\sdk\jre\lib;" -jar dbisimp.jar User=db1111111 Password=test Datenbank=db1111111 Server=whv-fbmit3.hs-woe.de Port=1433

pause