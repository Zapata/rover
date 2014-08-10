package com.github.zapata.rover;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Plateau {

	private final int[] size;

	private final List<Rover> rovers = new LinkedList<Rover>();

	public Plateau(int[] size) {
		this.size = size;
		if (size.length != 2) {
			throw new IllegalArgumentException(
					"Illegal dimention of the plateau: " + size.length
							+ " expected 2.");
		}
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
		return size[0];
	}

	protected int getMaxY() {
		return size[1];
	}

	public boolean isValid(int newX, int newY) {
		if (newX < 0 || newX > getMaxX()) {
			return false;
		}
		if (newY < 0 || newY > getMaxY()) {
			return false;
		}

		for (Rover rover : rovers) {
			if (rover.getPosition().isSame(newX, newY)) {
				return false;
			}
		}

		return true;
	}

	public Rover landRover(Position initialPosition) {
		Rover rover = new Rover(initialPosition);
		rovers.add(rover);
		return rover;
	}

	public List<Position> getRoverPositions() {
		List<Position> positions = new ArrayList<Position>(rovers.size());
		for (Rover rover : rovers) {
			positions.add(rover.getPosition());
		}
		return positions;
	}
}
