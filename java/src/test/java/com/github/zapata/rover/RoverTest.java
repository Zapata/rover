package com.github.zapata.rover;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RoverTest {

	@Test(dataProvider = "actionData")
	public void checkActionProcessing(Position initialPos,
			Instruction[] actions, Position finalPos) {
		Rover rover = new Rover(initialPos);
		rover.apply(new Plateau(5, 5), Arrays.asList(actions));
		assertEquals(rover.getPosition(), finalPos);
	}

	@DataProvider(name = "actionData")
	public Object[][] getActionData() {
		return new Object[][] {
				{ new Position(0, 0, Orientation.NORTH), new Instruction[] {},
						new Position(0, 0, Orientation.NORTH) },
				{ new Position(0, 0, Orientation.NORTH),
						new Instruction[] { Instruction.MOVE },
						new Position(0, 1, Orientation.NORTH) },
				{ new Position(0, 0, Orientation.NORTH),
						new Instruction[] { Instruction.ROTATE_RIGHT },
						new Position(0, 0, Orientation.EAST) },
				{ new Position(0, 0, Orientation.NORTH),
						new Instruction[] { Instruction.ROTATE_LEFT },
						new Position(0, 0, Orientation.WEST) },
				{
						new Position(0, 0, Orientation.NORTH),
						new Instruction[] { Instruction.ROTATE_RIGHT,
								Instruction.MOVE, Instruction.ROTATE_RIGHT },
						new Position(1, 0, Orientation.SOUTH) },
				{ new Position(3, 3, Orientation.SOUTH),
						new Instruction[] { Instruction.MOVE },
						new Position(3, 2, Orientation.SOUTH) },
				{ new Position(3, 3, Orientation.WEST),
						new Instruction[] { Instruction.MOVE },
						new Position(2, 3, Orientation.WEST) }, };
	}

	@Test(dataProvider = "limitData")
	public void checkLimits(Position initialPos) {
		Rover rover = new Rover(initialPos);
		rover.apply(new Plateau(5, 5),
				Arrays.asList(new Instruction[] { Instruction.MOVE }));
		assertEquals(rover.getPosition(), initialPos);
	}

	@DataProvider(name = "limitData")
	public Object[][] getLimitData() {
		return new Object[][] { { new Position(3, 0, Orientation.SOUTH) },
				{ new Position(0, 3, Orientation.WEST) },
				{ new Position(3, 5, Orientation.NORTH) },
				{ new Position(5, 3, Orientation.EAST) }, };
	}

	@Test
	public void checkCollision() {
		Plateau plateau = new Plateau(5, 5);
		plateau.landRover(new Position(2, 2, Orientation.SOUTH));
		Position initialPosition = new Position(2, 1, Orientation.NORTH);
		Rover rover = plateau.landRover(initialPosition);
		rover.apply(plateau,
				Arrays.asList(new Instruction[] { Instruction.MOVE }));
		assertEquals(rover.getPosition(), initialPosition);
	}

}
