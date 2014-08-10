package com.github.zapata.rover;

public enum Orientation {
	NORTH("N", 0, 0, 1),

	SOUTH("S", 180, 0, -1),

	EAST("E", 90, 1, 0),

	WEST("W", 270, -1, 0);

	private final String label;
	private final int degree;
	private final int xMovement;
	private final int yMovement;

	private Orientation(String label, int degree, int xMovement, int yMovement) {
		this.label = label;
		this.degree = degree;
		this.xMovement = xMovement;
		this.yMovement = yMovement;
	}

	public String getLabel() {
		return label;
	}

	public int getDegree() {
		return degree;
	}

	public int getXMovement() {
		return xMovement;
	}

	public int getYMovement() {
		return yMovement;
	}

	@Override
	public String toString() {
		return label;
	}

	public static Orientation fromLabel(String label) {
		if (label != null) {
			for (Orientation orientation : values()) {
				if (orientation.label.equals(label)) {
					return orientation;
				}
			}
		}
		throw new IllegalArgumentException("Invalid label for Orientation : "
				+ label);
	}

	public Orientation add(int degreeToAdd) {
		if (degreeToAdd % 90 != 0) {
			throw new IllegalArgumentException("Unhandled radius: "
					+ degreeToAdd);
		}
		int newDegree = (360 + degree + degreeToAdd) % 360;
		for (Orientation orientation : values()) {
			if (orientation.getDegree() == newDegree) {
				return orientation;
			}
		}
		throw new IllegalStateException("Should find an orientation");
	}
}
