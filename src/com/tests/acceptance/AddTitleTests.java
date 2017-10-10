package com.tests.acceptance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class AddTitleTests {
	InputHandler inH = null;
	OutputHandler outH = null;
	
	@Before
	public void setup() {
		inH = new InputHandler();
		outH = new OutputHandler();
	}
	
	@Test
	public void passAddTitleCase1() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Success!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("create title", o.getState());
		
		//State is now CREATETITLE
		oo = inH.processInput("1000000000002,The Life Of Cole Dorma", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passAddTitleCase2() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("The Title Already Exists!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("create title", o.getState());
		
		//State is now CREATETITLE
		oo = inH.processInput("9781442668584,By the grace of God", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passAddTitleCase3() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number", 5);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("create title", o.getState());
		
		//State is now CREATEUSER
		oo = inH.processInput("10007,hi", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void failAddTitle() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number", 5);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("create title", o.getState());
		
		//State is now CREATEUSER
		oo = inH.processInput("7777777777689", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		 
	}
}