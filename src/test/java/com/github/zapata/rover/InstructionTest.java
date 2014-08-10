package com.github.zapata.rover;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.github.zapata.rover.Instruction;

public class InstructionTest {
	@Test
	public void testFromLabel() {
		for (Instruction action : Instruction.values()) {
			assertEquals(Instruction.fromLabel(action.getLabel()), action);
		}
	}

}
