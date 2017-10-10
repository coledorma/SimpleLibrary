package com.tests.acceptance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class CollectFineTests {
	InputHandler inH = null;
	OutputHandler outH = null;
	
	@Before
	public void setup() {
		inH = new InputHandler();
		outH = new OutputHandler();
	}
	
	@Test
	public void passCollectFineCase1() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Borrowing Items Exist!", 3);
		
		oo = inH.processInput("USER", InputHandler.USERLOGIN);
		
		o = outH.userLogin("Zhibo@carleton.ca,zhibo");
		
		//State is now USER
		oo = inH.processInput("pay fine", o.getState());
		
		//State is now PAYFINE
		oo = inH.processInput("Zhibo@carleton.ca", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		
		//State is now RETURN
		oo = inH.processInput("return", o.getState());
		
		//State is now PAYFINE
		oo = inH.processInput("Zhibo@carleton.ca,9781442668584,1", oo.getState());
		
		oo = inH.processInput("USER", InputHandler.USERLOGIN);
		
		//State is now USER
		oo = inH.processInput("pay fine", o.getState());
				
		//State is now PAYFINE
		oo = inH.processInput("Zhibo@carleton.ca", oo.getState());
		
		actual = new Output(oo.getOutput(), oo.getState());
		expected = new Output("Success!", 3);
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passCollectFineCase2() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Success!", 3);
		
		oo = inH.processInput("USER", InputHandler.USERLOGIN);
		
		o = outH.userLogin("Kevin@carleton.ca,kevin");
		
		//State is now USER
		oo = inH.processInput("pay fine", o.getState());
		
		//State is now BORROW
		oo = inH.processInput("Kevin@carleton.ca", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
}