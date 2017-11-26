@echo off

:: delete preexisting test jar file if any 
IF EXIST runTests.jar DEL runTests.jar

:: empty out tests folder 
IF EXIST %tests% DEL %tests%\*.* /f /s /q

:: compile test classes 
javac -d . -cp "lib/junit-4.12.jar;BlackjackGame.jar" src/tests/*.java

:: make a jar from the compiled classes
jar cf runTests.jar lib/* Game/*.class tests/*.class

:: run the jar 
java -cp "lib/*;runTests.jar" tests.BlackjackTestsRunner

pause