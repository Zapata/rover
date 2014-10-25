package com.github.zapata.rover;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PlateauTest {

	@Test(dataProvider = "initializationData")
	public void checkCreation(int nbRows, int nbColumns, String expectedError) {
		try {
			new Plateau(nbRows, nbColumns);
		} catch (IllegalArgumentException iae) {
			if (!iae.getMessage().contains(expectedError)) {
				throw iae;
			}
		}
	}

	@DataProvider(name = "initializationData")
	public Object[][] getInitializationData() {
		return new Object[][] { { -3, 5, "Illegal X size of the plateau" },
				{ 5, -4, "Illegal Y size of the plateau" },

		};
	}
}
