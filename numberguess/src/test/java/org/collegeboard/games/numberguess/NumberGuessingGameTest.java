package org.collegeboard.games.numberguess;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.collegeboard.games.numberguess.NumberGuessingGame.Command;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;

/**
 * Unit test for simple App.
 */
public class NumberGuessingGameTest {

	/**
	 * Test that the program successfully completes.
	 */
	@Test
	public void testGameCompletesSuccessfully() {
		int testGuesses[] = { 0, 1, 11, 29, 30, 31, 37, 100, 1000000 };
		for (int expectedAnswer : testGuesses) {
			CommandAcceptor commandAcceptor = mock(CommandAcceptor.class);
			when(
					commandAcceptor.accept(anyString(),
							Matchers.<Command> anyVararg())).thenReturn(
					Command.ready);
			when(
					commandAcceptor.accept(anyString(),
							AdditionalMatchers.gt(expectedAnswer),
							Matchers.<Command> anyVararg())).thenReturn(
					Command.lower);
			when(
					commandAcceptor.accept(anyString(),
							AdditionalMatchers.lt(expectedAnswer),
							Matchers.<Command> anyVararg())).thenReturn(
					Command.higher);
			when(
					commandAcceptor.accept(anyString(), eq(expectedAnswer),
							Matchers.<Command> anyVararg())).thenReturn(
					Command.yes);

			NumberGuessingGame app = new NumberGuessingGame(commandAcceptor);
			try {
				int answer = app.execute();
				// System.out.println("User mind:"+expectedAnswer+" Computer guess:"+answer);
				Assert.assertTrue("Game ended successfully.",
						answer == expectedAnswer);
			} catch (Exception e) {
				System.out.println(e.getClass().getName() + ":"
						+ e.getMessage());
				Assert.assertTrue(e.getMessage(), false);
			}
		}
	}

	/**
	 * Test that the program successfully exits upon receiving 'end' instruction.
	 */
	@Test
	public void testGameQuitsUponEndCommand() {
		int testGuesses[] = { 0, 1, 11, 29, 30, 31, 37, 100, 1000000 };
		for (int expectedAnswer : testGuesses) {
			// int expectedAnswer = 35;
			CommandAcceptor commandAcceptor = mock(CommandAcceptor.class);
			when(
					commandAcceptor.accept(anyString(),
							Matchers.<Command> anyVararg()))
					.thenReturn(Command.end).thenReturn(Command.ready)
					.thenReturn(Command.end);
			when(
					commandAcceptor.accept(anyString(),
							AdditionalMatchers.gt(expectedAnswer),
							Matchers.<Command> anyVararg())).thenReturn(
					Command.lower).thenReturn(Command.end);
			when(
					commandAcceptor.accept(anyString(),
							AdditionalMatchers.lt(expectedAnswer),
							Matchers.<Command> anyVararg()))
					.thenReturn(Command.higher).thenReturn(Command.higher)
					.thenReturn(Command.higher).thenReturn(Command.higher)
					.thenReturn(Command.end);
			when(
					commandAcceptor.accept(anyString(), eq(expectedAnswer),
							Matchers.<Command> anyVararg())).thenReturn(
					Command.end).thenReturn(Command.yes);
			for (int i = 0; i < 3; i++) {

				NumberGuessingGame app = new NumberGuessingGame(commandAcceptor);
				try {
					int answer = app.execute();
					// System.out.println("User mind:"+expectedAnswer+" Computer guess:"+answer);
					Assert.assertTrue(
							"Game quit upon user request.",
							answer == NumberGuessingGame.RETURN_VALUE_UPON_USER_REQUESTING_END);
				} catch (Exception e) {
					System.out.println(e.getClass().getName() + ":"
							+ e.getMessage());
					Assert.assertTrue(e.getMessage(), false);
				}
			}
		}
	}
}
