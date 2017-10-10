package com.tests.unit;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class InputHandlerTests {
	InputHandler input = null;
	
	@Before
	public void setup() {
		input = new InputHandler();
	}
	
	@Test
	public void testProcessInput() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output out = new Output("Please Input Username and Password:'username,password'", 15);
		oo = input.processInput("user", InputHandler.FINISHWAITING);
		assertEquals(out.getOutput(), oo.getOutput());
		assertEquals(out.getState(), oo.getState());
		 
	}

}
