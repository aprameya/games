package org.collegeboard.games.numberguess;

import org.collegeboard.games.numberguess.NumberGuessingGame.Command;

public interface CommandAcceptor {

	/** Messages used by the game to interact with the user */
	static String INVALID_INPUT_MESSAGE = "Invalid Input. Please try again.";
	static String VALID_INPUTS_MESSAGE = " Type one of %s";

	/**
	 * Display a message to the user, and accept one of the allowed commands
	 * from the user.
	 * 
	 * @param message
	 *            The message to be displayed to the user.
	 * @param commands
	 *            The variable list of commands that are acceptable inputs from
	 *            the user.
	 * @return The Command abstraction corresponding to the user input, or null
	 *         in the event of any unexpected behavior.
	 */
	public abstract Command accept(String message, Command... commands);

	/**
	 * Display a message to the user along with a computed parameter, and accept
	 * one of the allowed commands from the user.
	 * 
	 * @see #accept(java.lang.String,
	 *      org.collegeboard.games.numberguess.App.Command)
	 * 
	 * @param message
	 *            The message to be displayed to the user.
	 * @param parameter
	 *            The additional computed parameter to be displayed to the user
	 *            along with the message.
	 * @param commands
	 *            The variable list of commands that are acceptable inputs from
	 *            the user.
	 * @return The Command abstraction corresponding to the user input, or null
	 *         in the event of any unexpected behavior.
	 */
	public abstract Command accept(String message, int parameter,
			Command... commands);

}