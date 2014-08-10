package com.github.zapata.rover;

import java.io.Reader;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ExplorerInputReader implements Iterable<Itinary> {

	private final Scanner scanner;

	public ExplorerInputReader(Reader r) {
		scanner = new Scanner(r);
	}

	public Plateau readPlateau() {
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

	public boolean hasNextItinary() {
		return scanner.hasNext();
	}

	public Itinary readNextItinary() {
		if (!hasNextItinary()) {
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

		return rawInstructions.chars()
				.mapToObj(c -> Character.toString((char) c))
				.map(Instruction::fromLabel)
				.collect(Collectors.toList());
	}

	public static final class ItinaryIterator implements Iterator<Itinary> {

		private final ExplorerInputReader inputReader;

		public ItinaryIterator(ExplorerInputReader inputReader) {
			this.inputReader = inputReader;
		}

		@Override
		public boolean hasNext() {
			return inputReader.hasNextItinary();
		}

		@Override
		public Itinary next() {
			return inputReader.readNextItinary();
		}

	}

	@Override
	public Iterator<Itinary> iterator() {
		return new ItinaryIterator(this);
	}

}
