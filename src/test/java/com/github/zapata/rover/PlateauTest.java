package com.github.zapata.rover;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.zapata.rover.Plateau;

public class PlateauTest {

	@Test(dataProvider = "initializationData")
	public void checkCreation(int[] size, String expectedError) {
		try {
			new Plateau(size);
		} catch (IllegalArgumentException iae) {
			if (!iae.getMessage().contains(expectedError)) {
				throw iae;
			}
		}
	}

	@DataProvider(name = "initializationData")
	public Object[][] getInitializationData() {
		return new Object[][] {
				{ new int[] { 5, 5, 5 }, "Illegal dimention of the plateau" },
				{ new int[] { -3, 5 }, "Illegal X size of the plateau" },
				{ new int[] { 5, -4 }, "Illegal Y size of the plateau" },

		};
	}
}
