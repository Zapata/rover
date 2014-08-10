package com.github.zapata.rover;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExplorerInputReader {

	private static final String ERROR_ROVER_ACTIONS = "Can't read rover actions.";

	private static final String ERROR_ROVER_POSITION = "Can't read rover landing position.";

	private static final String ERROR_PLATEAU_PARSING = "Can't read plateau size.";

	private static final String INPUT_SEPARATOR = " ";

	private final BufferedReader inputReader;

	public ExplorerInputReader(InputStream in) {
		this(new InputStreamReader(in));
	}

	public ExplorerInputReader(String input) {
		this(new StringReader(input));
	}

	public ExplorerInputReader(File file) throws FileNotFoundException {
		this(new FileReader(file));
	}

	private ExplorerInputReader(Reader input) {
		inputReader = new BufferedReader(input);
	}

	public Plateau readPlateau() throws IOException {
		String plateauLine = checkLine(inputReader.readLine(),
				ERROR_PLATEAU_PARSING);
		String[] sizeAsString = plateauLine.split(INPUT_SEPARATOR);
		try {
			return new Plateau(fromCharacterToNumber(sizeAsString));
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException(ERROR_PLATEAU_PARSING, nfe);
		}
	}

	public Itinary readNextItinary() throws IOException {
		Position initialPosition = getInitialPosition(inputReader.readLine());
		List<Instruction> instructions = getInstructions(inputReader.readLine());
		if (initialPosition != null) {
			return new Itinary(initialPosition, instructions);
		}
		return null;
	}

	private Position getInitialPosition(String positionInput)
			throws IOException {
		if (positionInput == null) {
			return null;
		}

		String[] splittedPositionInput = positionInput.split(INPUT_SEPARATOR);
		if (splittedPositionInput.length < 3) {
			throw new IllegalArgumentException(ERROR_ROVER_POSITION);
		}
		try {
			int x = Integer.valueOf(splittedPositionInput[0]);
			int y = Integer.valueOf(Integer.valueOf(splittedPositionInput[1]));
			Orientation orientation = Orientation
					.fromLabel(splittedPositionInput[2]);
			return new Position(x, y, orientation);
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException(ERROR_ROVER_POSITION, nfe);
		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException(ERROR_ROVER_POSITION, iae);
		}
	}

	private List<Instruction> getInstructions(String instructions)
			throws IOException {
		if (instructions == null) {
			return Collections.emptyList();
		}

		String[] splittedInstructions = instructions.split("\\B");
		try {
			List<Instruction> actions = new ArrayList<Instruction>(
					splittedInstructions.length);
			for (String actionInput : splittedInstructions) {
				actions.add(Instruction.fromLabel(actionInput));
			}
			return actions;
		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException(ERROR_ROVER_ACTIONS, iae);
		}
	}

	private String checkLine(String line, String errorMsg) throws IOException {
		if (line == null) {
			throw new IllegalArgumentException("Unexpected end of file: "
					+ errorMsg);
		}
		return line;
	}

	private int[] fromCharacterToNumber(String[] sizeAsString) {
		int[] sizeAsInt = new int[sizeAsString.length];
		for (int i = 0; i < sizeAsString.length; ++i) {
			sizeAsInt[i] = Integer.valueOf(sizeAsString[i]);
		}
		return sizeAsInt;
	}

}
