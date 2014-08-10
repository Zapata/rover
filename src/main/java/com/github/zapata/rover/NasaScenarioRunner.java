package com.github.zapata.rover;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class NasaScenarioRunner {
	public Plateau run(Reader reader) throws IOException {
		ExplorerInputReader inputReader = new ExplorerInputReader(reader);

		Plateau plateau = inputReader.readPlateau();

		Itinary itinary = inputReader.readNextItinary();
		while (itinary != null) {
			Rover rover = plateau.landRover(itinary.getInitialPosition());
			rover.apply(plateau, itinary.getInstructions());
			itinary = inputReader.readNextItinary();
		}
		return plateau;
	}

	public static void main(String[] args) throws IOException {
		NasaScenarioRunner runner = new NasaScenarioRunner();
		if (args.length == 0) {
			print(runner.run(new InputStreamReader(System.in))
					.getRoverPositions());
		} else {
			for (String arg : args) {
				print(runner.run(new FileReader(arg)).getRoverPositions());
			}
		}
	}

	private static void print(List<Position> positions) {
		for (Position pos : positions) {
			System.out.println(pos);
		}
	}
}
