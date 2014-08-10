package com.github.zapata.rover;

public class Position {

	private final int x;
	private final int y;
	private final Orientation orientation;

	public Position(int x, int y, Orientation orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isSame(int otherX, int otherY) {
		return x == otherX && y == otherY;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public Position toOrientation(Orientation newOrientation) {
		return new Position(x, y, newOrientation);
	}

	public Position toPosition(int newX, int newY) {
		return new Position(newX, newY, orientation);
	}

	@Override
	public String toString() {
		return x + " " + y + " " + orientation.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (orientation == null ? 0 : orientation.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Position other = (Position) obj;
		if (orientation != other.orientation) {
			return false;
		}
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
}
