package org.collegeboard.games.numberguess;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.collegeboard.games.numberguess.NumberGuessingGame.Command;

/**
 * A concrete implementation of the CommandAcceptor abstraction to interact with
 * text based interfaces such as system console. It relies on Scanner to read
 * user inputs, and PrintWriter to prompt messages to the user.
 * 
 * @author AP
 *
 */
public class ConsoleCommandAcceptor implements CommandAcceptor {

	/** The Scanner instance to read user input from. */
	private Scanner scanner;
	/** The PrintWriter instance to write user messages to. */
	private PrintWriter output;

	/**
	 * The sole constructor initializing the CommandAcceptor with a Scanner and
	 * a PrintWriter.
	 * 
	 * @param scanner
	 *            The Scanner instance to read inputs from the user.
	 * @param output
	 *            The PrintWriter instance to write messages to the user.
	 */
	public ConsoleCommandAcceptor(Scanner scanner, PrintWriter output) {
		this.scanner = scanner;
		this.output = output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.collegeboard.games.numberguess.CommandAcceptor#accept(java.lang.String
	 * , org.collegeboard.games.numberguess.App.Command)
	 */
	public Command accept(String message, Command... commands) {
		List<Command> expectedCommands = new ArrayList<Command>(
				Arrays.asList(commands));
		prompt(message
				+ String.format(VALID_INPUTS_MESSAGE, Arrays.asList(commands)));
		try {
			String input = scanner.next();
			while (input == null || (Command.fromString(input) == null)
					|| !expectedCommands.contains(Command.fromString(input))) {
				prompt(INVALID_INPUT_MESSAGE
						+ String.format(" Expected %s", Arrays.asList(commands)));
				input = scanner.next();
			}
			return Command.fromString(input);
		} catch (java.util.NoSuchElementException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.collegeboard.games.numberguess.CommandAcceptor#accept(java.lang.String
	 * , int, org.collegeboard.games.numberguess.App.Command)
	 */
	public Command accept(String message, int parameter, Command... commands) {
		return accept(String.format(message, parameter), commands);
	}

	private void prompt(String message) {
		output.println(message);
		output.flush();
	}

}
