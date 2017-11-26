@echo off

:: delete preexisting jar file if any
IF EXIST BlackjackGame.jar DEL BlackjackGame.jar

:: empty out Game folder 
IF EXIST %Game% DEL %Game%\*.* /f /s /q

:: compile all Game java files and put class files into Game folder 
javac -d . src/Game/*.java

:: make a jar from the compiled class files in Game
jar cf BlackjackGame.jar Game/*.class

:: run it 
java -cp BlackjackGame.jar Game.Driver