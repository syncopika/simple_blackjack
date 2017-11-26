# shell script to run blackjack tests

# delete preexisting test jar file if any 
rm -f runTests.jar

# empty out tests folder 
rm tests/*

# compile test classes 
javac -d . -cp "lib/junit-4.12.jar;BlackjackGame.jar" src/tests/*.java

# make a jar from the compiled classes
jar cf runTests.jar lib/* Game/*.class tests/*.class

# run the jar 
java -cp "lib/*;runTests.jar" tests.BlackjackTestsRunner
