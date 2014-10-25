package com.github.zapata.rover;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.zapata.rover.Orientation;

public class OrientationTest {

	@Test
	public void testFromLabel() {
		for (Orientation orientation : Orientation.values()) {
			assertEquals(Orientation.fromLabel(orientation.getLabel()),
					orientation);
		}
	}

	@Test(dataProvider = "calculationData")
	public void checkCalculation(Orientation initial, int rotation,
			Orientation expected) {
		try {
			assertEquals(initial.add(rotation), expected);
		} catch (IllegalArgumentException iae) {
			if (expected != null) {
				throw iae;
			}
		}
	}

	@DataProvider(name = "calculationData")
	public Object[][] getCalculationData() {
		return new Object[][] { { Orientation.EAST, 90, Orientation.SOUTH },
				{ Orientation.EAST, 180, Orientation.WEST },
				{ Orientation.WEST, 180, Orientation.EAST },
				{ Orientation.NORTH, -270, Orientation.EAST },
				{ Orientation.NORTH, -90, Orientation.WEST },
				{ Orientation.WEST, 30, null }, };
	}
}
