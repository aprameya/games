Description:
------------
This is a program in Java to play a number-guessing game.

The game works as follows:
The user chooses a number in his mind and types 'ready' to indicate to the computer that he is ready to begin playing.
The computer asks a series of questions to arrive at the number the user has in mind. The user can only respond with 'higher', 'lower' or 'yes'.
The game ends when the user responds with 'yes' or 'end'.

Instructions/Commands:
----------------------
(The below commands assume you are in the numberguess directory, underneath which you must see src directory)

To build the module using Maven:
	mvn clean install
This builds(cleans, compiles) the project, runs junit tests, and if tests pass successfully, generates the numberguess-<<version>>.jar

To run the game as a Java console aplication using Maven:
	mvn exec:java -Dexec.mainClass="org.collegeboard.games.numberguess.NumberGuessingGame"
This uses the maven-exec-plugin to run the NumberGuessingGame class that includes the main method.

To run the game as a Java console application using Java:
	mvn package
	java -cp target\numberguess-0.0.1-SNAPSHOT.jar org.collegeboard.games.numberguess.NumberGuessingGame
