package com.github.zapata.rover;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.github.zapata.rover.NasaScenarioRunner;
import com.github.zapata.rover.Plateau;
import com.github.zapata.rover.Position;

public class NasaScenarioRunnerTest {
	@Test
	public void checkFullScenario() throws IOException {
		Plateau result = new NasaScenarioRunner().run(getClass().getResource(
				"completeScenario.txt").getFile());
		List<Position> positions = result.getRoverPositions();
		assertEquals(positions.size(), 2);
		assertEquals(positions.get(0).toString(), "1 3 N");
		assertEquals(positions.get(1).toString(), "5 1 E");
	}
}
