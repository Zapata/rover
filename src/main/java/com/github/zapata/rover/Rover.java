package com.github.zapata.rover;

import java.util.List;

public class Rover {

	private Position position;

	public Rover(Position position) {
		this.position = position;
	}

	public void apply(Plateau plateau, List<Instruction> actions) {
		for (Instruction action : actions) {
			action.processOn(this, plateau);
		}
	}

	public Position getPosition() {
		return position;
	}

	public void rotate(int rotation) {
		position = position.toOrientation(position.getOrientation().add(
				rotation));
	}

	public void move(Plateau plateau) {
		int newX = position.getX() + position.getOrientation().getXMovement();
		int newY = position.getY() + position.getOrientation().getYMovement();
		if (plateau.isValid(newX, newY)) {
			position = position.toPosition(newX, newY);
		}

	}

}
