package com.github.zapata.rover;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Plateau {

	private final int maxX;

	private final int maxY;

	private final List<Rover> rovers = new LinkedList<Rover>();

	public Plateau(int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;

		if (getMaxX() <= 0) {
			throw new IllegalArgumentException(
					"Illegal X size of the plateau (X <= 0): " + getMaxX());
		}
		if (getMaxY() <= 0) {
			throw new IllegalArgumentException(
					"Illegal Y size of the plateau (Y <= 0): " + getMaxY());
		}
	}

	protected int getMaxX() {
		return maxX;
	}

	protected int getMaxY() {
		return maxY;
	}

	public boolean isValid(int newX, int newY) {
		if (newX < 0 || newX > getMaxX()) {
			return false;
		}
		if (newY < 0 || newY > getMaxY()) {
			return false;
		}

		return !hasRoverOnPosition(newX, newY);
	}

	private boolean hasRoverOnPosition(int newX, int newY) {
		return rovers.stream().filter(r -> r.getPosition().isSame(newX, newY))
				.findAny().isPresent();
	}

	public Rover landRover(Position initialPosition) {
		Rover rover = new Rover(initialPosition);
		rovers.add(rover);
		return rover;
	}

	public List<Position> getRoverPositions() {
		return rovers.stream().map(Rover::getPosition)
				.collect(Collectors.toList());
	}
}
