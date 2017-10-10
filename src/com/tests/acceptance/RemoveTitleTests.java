package com.tests.acceptance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class RemoveTitleTests {
	InputHandler inH = null;
	OutputHandler outH = null;
	
	@Before
	public void setup() {
		inH = new InputHandler();
		outH = new OutputHandler();
	}
	
	@Test
	public void passRemoveTitleCase1() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Success!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("delete title", o.getState());
		
		//State is now DELETETITLE
		oo = inH.processInput("9781442616899", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passRemoveTitleCase2() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("The Title Does Not Exist!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("delete title", o.getState());
		
		//State is now DELETETITLE
		oo = inH.processInput("7777777777689", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passRemoveTitleCase3() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Active Loan Exists!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("delete title", o.getState());
		
		//State is now DELETETITLE
		oo = inH.processInput("9781442668584", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void failRemoveTitle() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Your input should in this format:'ISBN',ISBN should be a 13-digit number", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("delete title", o.getState());
		
		//State is now DELETETitle
		oo = inH.processInput("7777777777689,1", oo.getState());

		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		 
	}
}
