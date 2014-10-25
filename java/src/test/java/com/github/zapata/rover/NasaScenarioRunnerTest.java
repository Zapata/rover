package com.github.zapata.rover;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.testng.annotations.Test;

public class NasaScenarioRunnerTest {
	@Test
	public void checkFullScenario() throws IOException {
		InputStreamReader input = new InputStreamReader(getClass()
				.getResourceAsStream("completeScenario.txt"));
		Plateau result = new NasaScenarioRunner().run(input);
		List<Position> positions = result.getRoverPositions();
		assertEquals(positions.size(), 2);
		assertEquals(positions.get(0).toString(), "1 3 N");
		assertEquals(positions.get(1).toString(), "5 1 E");
	}
}
