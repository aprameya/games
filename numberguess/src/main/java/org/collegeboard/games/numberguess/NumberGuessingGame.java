package org.collegeboard.games.numberguess;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A number guessing game. The user chooses a number in his mind and types
 * 'ready' to begin playing. The computer asks a series of questions to arrive
 * at the number the user has in mind. The user can only respond with 'higher',
 * 'lower' or 'yes'. The game ends when the user responds with 'yes' or 'end'.
 * 
 * @author AP
 *
 */
public class NumberGuessingGame {

	/** Messages used by the game to interact with the user */
	static String READY_MESSAGE = "Chooses a positive number in your mind and type 'ready' to indicate that you are ready to begin playing. Or type 'end' to quit.";
	static String REPROMT_MESSAGE = "Is the number %d?";
	static int RETURN_VALUE_UPON_USER_REQUESTING_END = Integer.MIN_VALUE;

	/**
	 * Enumeration of commands accepted from the user by the game.
	 * 
	 */
	enum Command {
		/**
		 * 'ready, higher, lower, yes, end' are the only expected commands from
		 * the user.
		 */
		ready, higher, lower, yes, end;

		/**
		 * Converts a String to a enumerated value if there is a match. Returns
		 * null when there is no match.
		 * 
		 * @param s
		 *            The input string to be converted to one of the enumerated
		 *            values.
		 * @return The correctly enumerated value, or null if not correctly
		 *         enumerated.
		 */
		public static Command fromString(String s) {
			try {
				return valueOf(s);
			} catch (java.lang.IllegalArgumentException e) {
				return null;
			}
		}
	}

	/**
	 * Instance of the CommandAcceptor abstraction that handles the input output
	 * mechanisms of the game.
	 */
	private CommandAcceptor commandAcceptor;

	/**
	 * Sole constructor, which expects a concrete instance of CommandAcceptor
	 * interface that can handle input output mechanisms for the game.
	 * 
	 * @param commandAcceptor
	 *            an instance of a CommandAcceptor implementation implementation
	 */
	public NumberGuessingGame(CommandAcceptor commandAcceptor) {
		this.commandAcceptor = commandAcceptor;
	}

	/**
	 * Implementation of the guessing algorithm of the game. It uses a divide
	 * and conquer approach to get to the number user has in her mind based on
	 * her interaction with the game.
	 * 
	 * @return The correctly guessed number the user had in mind, upon user
	 *         confirming the correctness of the answer.
	 */
	public int execute() {
		Command c = commandAcceptor.accept(READY_MESSAGE, Command.ready,
				Command.end);

		if (Command.end.equals(c))
			return RETURN_VALUE_UPON_USER_REQUESTING_END;

		// TODO: Is there really a best set of initial values? Probably not.
		int guess = 30;
		int floor = 0, ceiling = guess * 2;
		boolean ceiling_bound = false, floor_bound = false;

		while (!c.equals(Command.yes)) {
			c = commandAcceptor.accept(REPROMT_MESSAGE, guess, Command.higher,
					Command.lower, Command.yes, Command.end);

			if (Command.end.equals(c))
				return RETURN_VALUE_UPON_USER_REQUESTING_END;

			if (ceiling_bound && floor_bound) {
				if (Command.higher.equals(c)) {
					floor = guess;
				} else if (Command.lower.equals(c)) {
					ceiling = guess;
				}
				guess = (ceiling + floor) / 2;
			} else {
				if (Command.higher.equals(c)) {
					floor_bound = true;
					floor = guess;
					guess = (guess == 0 ? 1 : guess * 2);
				} else if (Command.lower.equals(c)) {
					ceiling_bound = true;
					ceiling = guess;
					guess = (guess == 0 ? 0
							: (floor_bound ? ((ceiling + floor) / 2)
									: (ceiling / 2)));
				}
			}
		}
		return guess;
	}

	/**
	 * The commandline entry point, when the game is invoked from the
	 * commandline.
	 * 
	 * @param args
	 *            No commandline arguments expected by the game.
	 */
	public static void main(String... args) {
		NumberGuessingGame app = new NumberGuessingGame(
				new ConsoleCommandAcceptor(new Scanner(System.in),
						new PrintWriter(System.out)));
		app.execute();
	}

}
