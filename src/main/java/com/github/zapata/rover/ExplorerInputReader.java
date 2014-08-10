package com.github.zapata.rover;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ExplorerInputReader {

	private final Scanner scanner;

	public ExplorerInputReader(Reader r) {
		scanner = new Scanner(r);
	}

	public Plateau readPlateau() throws IOException {
		try {
			return new Plateau(scanner.nextInt(), scanner.nextInt());
		} catch (InputMismatchException ime) {
			throw new IllegalArgumentException(
					"Can't read plateu from input, expecting integers.");
		} catch (NoSuchElementException nsee) {
			throw new IllegalArgumentException(
					"Can't read plateu from input, expecting 2 numbers.");
		}
	}

	public Itinary readNextItinary() throws IOException {
		if (!scanner.hasNext()) {
			return null;
		}
		Position initialPosition = getInitialPosition();
		List<Instruction> instructions = getInstructions();
		return new Itinary(initialPosition, instructions);
	}

	private Position getInitialPosition() {
		return new Position(scanner.nextInt(), scanner.nextInt(),
				Orientation.fromLabel(scanner.next()));
	}

	private List<Instruction> getInstructions() {
		String rawInstructions = scanner.next();
		List<Instruction> instructions = new ArrayList<Instruction>(
				rawInstructions.length());

		for (int i = 0; i < rawInstructions.length(); i++) {
			instructions.add(Instruction.fromLabel(Character
					.toString(rawInstructions.charAt(i))));
		}
		return instructions;
	}

}
