package com.github.zapata.rover;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.testng.annotations.Test;

public class NasaScenarioRunnerTest {
	@Test
	public void checkFullScenario() throws IOException {
		Reader input = Files.newBufferedReader(Paths
				.get("../scenarios/complete.txt"));
		Plateau result = new NasaScenarioRunner().run(input);
		List<Position> positions = result.getRoverPositions();
		assertEquals(positions.size(), 2);
		assertEquals(positions.get(0).toString(), "1 3 N");
		assertEquals(positions.get(1).toString(), "5 1 E");
	}
}
