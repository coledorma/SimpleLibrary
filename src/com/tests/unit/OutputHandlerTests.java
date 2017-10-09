package com.tests.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class OutputHandlerTests {
	
	OutputHandler outH = null;
	
	@Before
	public void setup() {
		outH =  new OutputHandler();
	}
	
	@Test
	public void testCreateUser() {
		Output out = new Output("Your input should in this format:'username,password'",4);
		assertEquals(out.getOutput(), outH.createUser("hey").getOutput());
		assertEquals(out.getState(), outH.createUser("hey").getState());
		
		Output out2 = new Output("Success!",2);
		assertEquals(out2.getOutput(), outH.createUser("me@carleton.ca,me").getOutput());
		assertEquals(out2.getState(), outH.createUser("me@carleton.ca,me").getState());
		
		Output out3 = new Output("The User Already Exists!",2);
		assertEquals(out3.getOutput(), outH.createUser("Zhibo@carleton.ca,zhibo").getOutput());
		assertEquals(out3.getState(), outH.createUser("Zhibo@carleton.ca,zhibo").getState());
		
	}
	
	@Test
	public void passIsInteger() {
		assertEquals(true, OutputHandler.isInteger("7777777777777"));
	}
	
	@Test
	public void failIsInteger() {
		assertEquals(false, OutputHandler.isInteger("13"));
		assertEquals(false, OutputHandler.isInteger("hey"));
	}
	
	@Test
	public void passIsNumber() {
		assertEquals(true, OutputHandler.isNumber("7777777777777"));
		assertEquals(true, OutputHandler.isNumber("13"));
	}
	
	@Test
	public void failIsNumber() {
		assertEquals(false, OutputHandler.isNumber("hey"));
	}
}
