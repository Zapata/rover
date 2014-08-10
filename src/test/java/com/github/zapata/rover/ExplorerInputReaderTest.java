package com.github.zapata.rover;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExplorerInputReaderTest {

	@Test(dataProvider = "plateauData")
	public void checkPlateauReading(String input, int[] expected)
			throws Throwable {
		try {
			Plateau plateau = new ExplorerInputReader(new StringReader(input))
					.readPlateau();
			assertEquals(plateau.getMaxX(), expected[0], "Max X");
			assertEquals(plateau.getMaxY(), expected[1], "Max Y");
		} catch (Throwable t) {
			if (expected != null) {
				throw t;
			}
		}
	}

	@DataProvider(name = "plateauData")
	public Object[][] getPlateauData() {
		return new Object[][] { { null, null }, { "", null }, { "5,5", null },
				{ "4.4", null }, { "5 5", new int[] { 5, 5 } }, { " 5", null },
				{ "1 2 3", null }, { "123 456", new int[] { 123, 456 } },
				{ "5 5\n4 5", new int[] { 5, 5 } } };
	}

	@Test(dataProvider = "roverData")
	public void checkRoverReading(String input, Position expectedPos,
			String expectedActions) throws Throwable {
		Itinary ite = null;
		try {
			ExplorerInputReader inputReader = new ExplorerInputReader(
					new StringReader(input));
			ite = inputReader.readNextItinary();
			while (ite != null) {
				assertEquals(ite.getInitialPosition(), expectedPos);
				assertEquals(ite.getInstructions().toString(), expectedActions);
				ite = inputReader.readNextItinary();
			}
		} catch (Throwable t) {
			if (expectedPos != null && expectedActions != null) {
				throw t;
			}
		}
	}

	@DataProvider(name = "roverData")
	public Object[][] getRoverData() {
		return new Object[][] {
				{ null, null, null },
				{ "", null, null },
				{ "\n", null, null },
				{ "1 1 E\nL", new Position(1, 1, Orientation.EAST), "[L]" },
				{ "34 46 S\nLRM", new Position(34, 46, Orientation.SOUTH),
						"[L, R, M]" }, { "4.4 5 E\n", null, null },
				{ "6 6 Z\n", null, null }, { "12 34 N\nL R M", null, null },
				{ "3 3 W\nLI", null, null } };
	}

	@Test
	public void checkCompleteRead() throws IOException {
		ExplorerInputReader inputReader = new ExplorerInputReader(
				new StringReader("5 5\n1 2 E\nLM\n3 4 N\nRRM"));
		inputReader.readPlateau();
		int i = 0;
		while (inputReader.readNextItinary() != null) {
			++i;
		}
		assertEquals(i, 2, "Nb of rover");
	}

}
