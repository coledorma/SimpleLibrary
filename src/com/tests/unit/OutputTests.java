package com.tests.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.model.Output;

public class OutputTests {
	Output output = null;
	Output output2 = null;
	
	@Before
	public void setup() {
		//Item for getter
		output = new Output("hello", 0);
		
		//Item for setter
		output2 = new Output("setting", 1);
		output2.setOutput("setter");
		output2.setState(5);
	}
	
	@Test
	public void testGetters() {
		assertEquals("hello", output.getOutput());
		assertEquals(0, output.getState());
	}
	
	@Test
	public void testSetters() {
		assertEquals("setter", output2.getOutput());
		assertEquals(5, output2.getState());		
	}
	
	@Test
	public void testToString() {
		assertEquals("[hello,0]", output.toString());
	}

}
