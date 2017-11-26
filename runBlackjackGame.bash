# shell scipt 
# remove jar file first if exists 
rm -f BlackjackGame.jar

# empty the game folder 
rm -f Game/*

# compile all Game java files and put class files into Game folder 
javac -d . src/Game/*.java

# make a jar from the compiled class files in Game
jar cf BlackjackGame.jar Game/*.class

# run it 
java -cp BlackjackGame.jar Game.Driver