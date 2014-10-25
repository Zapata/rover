package com.github.zapata.rover;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.stream.Stream;

public class NasaScenarioRunner {
	public Plateau run(Reader reader) {
		ExplorerInputReader inputReader = new ExplorerInputReader(reader);

		Plateau plateau = inputReader.readPlateau();
		inputReader.forEach(itinary -> {
			Rover rover = plateau.landRover(itinary.getInitialPosition());
			rover.apply(plateau, itinary.getInstructions());
		});

		return plateau;
	}

	public static void main(String[] args) throws IOException {
		NasaScenarioRunner runner = new NasaScenarioRunner();
		buildReadersStream(args)
				.map(r -> runner.run(r).getRoverPositions())
				.forEach(System.out::println);
	}

	private static Stream<Reader> buildReadersStream(String[] args) {
		if (args.length == 0) {
			return Arrays.<Reader> asList(new InputStreamReader(System.in))
					.stream();
		}
		return Arrays.stream(args)
				.map(s -> {
					try {
						return new FileReader(s);
					} catch (FileNotFoundException e) {
						throw new RuntimeException(e.getMessage(), e);
					}
				});
	}
}
