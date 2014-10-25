package com.github.zapata.rover;

import java.util.List;

public class Itinary {
	private final Position initialPosition;
	private final List<Instruction> instructions;

	public Itinary(Position initialPosition, List<Instruction> instructions) {
		super();
		this.initialPosition = initialPosition;
		this.instructions = instructions;
	}

	public Position getInitialPosition() {
		return initialPosition;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

}
