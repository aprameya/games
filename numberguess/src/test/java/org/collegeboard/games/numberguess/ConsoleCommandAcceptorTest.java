package org.collegeboard.games.numberguess;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import org.collegeboard.games.numberguess.NumberGuessingGame.Command;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the ConsoleCommandAcceptor implementation of CommandAcceptor
 * interface.
 */
public class ConsoleCommandAcceptorTest {

	/**
	 * Test that the program accepts only the correct inputs from the user.
	 */
	@Test
	public void testUserInputAcceptanceOnValidInput() {
		String[] commands = { "ready", "higher", "lower", "yes", "end" };
		StringWriter output = new StringWriter();
		for (String command : commands) {
			CommandAcceptor commandAcceptor = new ConsoleCommandAcceptor(
					new Scanner(command), new PrintWriter(output));
			Command c = commandAcceptor.accept(
					"Enter a valid command, one of [" + command + "]",
					Command.fromString(command));
			// System.out.println("Expected command:"+command+" Received:"+c);
			Assert.assertTrue(output.toString().contains(command));
			Assert.assertTrue(Command.fromString(command).equals(c));
		}
	}

	/**
	 * Test that the program can display parameterized message and accept the
	 * correct input from the user.
	 */
	@Test
	public void testUserInputAcceptanceOnParameterizedMessage() {
		String[] commands = { "higher", "lower", "yes" };
		StringWriter output = new StringWriter();
		for (String command : commands) {
			CommandAcceptor commandAcceptor = new ConsoleCommandAcceptor(
					new Scanner(command), new PrintWriter(output));
			int parameter = (int) (Math.random());
			Command c = commandAcceptor.accept(
					NumberGuessingGame.REPROMT_MESSAGE, parameter,
					Command.fromString(command));
			// System.out.println("Expected command:"+command+" Received:"+c);
			Assert.assertTrue(output.toString().contains(
					String.valueOf(parameter)));
			Assert.assertTrue(Command.fromString(command).equals(c));
		}
	}

	/**
	 * Test that the program rejects incorrect inputs from the user.
	 */
	@Test
	public void testUserInputRejectionOnInvalidInput() {
		String[] commands = { "testNegative", "ready", "\n", "1", "-1", "1.23",
				"-0.01", "1234567890123456789012345678901234567890" };
		StringWriter output = new StringWriter();
		for (String command : commands) {
			CommandAcceptor commandAcceptor = new ConsoleCommandAcceptor(
					new Scanner(command), new PrintWriter(output));
			Command c = commandAcceptor.accept("Any invalid command",
					Command.end);
			// System.out.println("Expected command:"+command+" Received:"+c);
			Assert.assertTrue(output.toString().contains(
					CommandAcceptor.INVALID_INPUT_MESSAGE));
			Assert.assertTrue(c == null);
		}
	}
}
