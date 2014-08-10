package com.github.zapata.rover;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NasaScenarioRunner {
	public Plateau run(String filename) throws IOException {
		ExplorerInputReader inputReader = null;
		if (filename == null) {
			inputReader = new ExplorerInputReader(System.in);
		} else {
			inputReader = new ExplorerInputReader(new File(filename));
		}

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
			print(runner.run(null).getRoverPositions());
		} else {
			for (String arg : args) {
				print(runner.run(arg).getRoverPositions());
			}
		}
	}

	private static void print(List<Position> positions) {
		for (Position pos : positions) {
			System.out.println(pos);
		}
	}
}
