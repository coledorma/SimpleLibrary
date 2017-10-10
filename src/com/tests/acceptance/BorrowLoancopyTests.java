package com.tests.acceptance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class BorrowLoancopyTests {
	InputHandler inH = null;
	OutputHandler outH = null;
	
	@Before
	public void setup() {
		inH = new InputHandler();
		outH = new OutputHandler();
	}
	
	@Test
	public void passBorrowLoancopyCase1() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Success!", 3);
		
		oo = inH.processInput("USER", InputHandler.USERLOGIN);
		
		o = outH.userLogin("Kevin@carleton.ca,kevin");
		
		//State is now USER
		oo = inH.processInput("borrow", o.getState());
		
		//State is now BORROW
		oo = inH.processInput("Kevin@carleton.ca,9781442616899,1", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passBorrowLoancopyCase2() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("ISBN Invalid!", 3);
		
		oo = inH.processInput("USER", InputHandler.USERLOGIN);
		
		o = outH.userLogin("Kevin@carleton.ca,kevin");
		
		//State is now USER
		oo = inH.processInput("borrow", o.getState());
		
		//State is now BORROW
		oo = inH.processInput("Kevin@carleton.ca,1919191919191,1", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passBorrowLoancopyCase3() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Please pay your fine first because Outstanding Fee Exists!", 13);
		
		oo = inH.processInput("USER", InputHandler.USERLOGIN);
		
		o = outH.userLogin("Zhibo@carleton.ca,zhibo");
		
		//State is now USER
		oo = inH.processInput("borrow", o.getState());
		
		//State is now BORROW
		oo = inH.processInput("Zhibo@carleton.ca,9781442667181,1", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
				 
	}
	
	
	@Test
	public void passBorrowLoancopyCase4() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("The Item is Not Available!", 3);
		
		oo = inH.processInput("USER", InputHandler.USERLOGIN);
		
		o = outH.userLogin("Zhibo@carleton.ca,zhibo");
		
		//State is now USER
		oo = inH.processInput("borrow", o.getState());
		
		//State is now BORROW
		oo = inH.processInput("Zhibo@carleton.ca,9781442616899,1", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passBorrowLoancopyCase5() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("The Item is Not Available!", 3);
		
		oo = inH.processInput("USER", InputHandler.USERLOGIN);
		
		o = outH.userLogin("Zhibo@carleton.ca,zhibo");
		
		//State is now USER
		oo = inH.processInput("borrow", o.getState());
		
		//State is now BORROW
		oo = inH.processInput("Zhibo@carleton.ca,9781442616899,1", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}

}
