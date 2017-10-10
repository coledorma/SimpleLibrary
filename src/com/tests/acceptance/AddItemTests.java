package com.tests.acceptance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class AddItemTests {
	InputHandler inH = null;
	OutputHandler outH = null;
	
	@Before
	public void setup() {
		inH = new InputHandler();
		outH = new OutputHandler();
	}
	
	@Test
	public void passAddItemCase1() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Success!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("create item", o.getState());
		
		//State is now CREATEITEM
		oo = inH.processInput("9781442668584", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passAddItemCase2() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Success!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("create item", o.getState());
		
		//State is now CREATEITEM
		oo = inH.processInput("7777777777689", oo.getState());
		
		//State is now CREATETITLE
		oo = inH.processInput("7777777777689,Cole's Book", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
}
