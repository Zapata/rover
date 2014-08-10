package com.github.zapata.rover;

public enum Instruction {
	ROTATE_LEFT("L") {
		@Override
		public void processOn(Rover rover, Plateau plateau) {
			rover.rotate(-90);
		}
	},
	ROTATE_RIGHT("R") {
		@Override
		public void processOn(Rover rover, Plateau plateau) {
			rover.rotate(90);
		}
	},
	MOVE("M") {
		@Override
		public void processOn(Rover rover, Plateau plateau) {
			rover.move(plateau);
		}
	};

	private final String label;

	private Instruction(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}

	public abstract void processOn(Rover rover, Plateau plateau);

	public static Instruction fromLabel(String label) {
		if (label != null) {
			for (Instruction roverAction : values()) {
				if (roverAction.label.equals(label)) {
					return roverAction;
				}
			}
		}
		throw new IllegalArgumentException("Invalid label for instruction : "
				+ label);
	}
}
