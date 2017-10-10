package com.tests.acceptance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class RemoveUserTests {
	InputHandler inH = null;
	OutputHandler outH = null;
	
	@Before
	public void setup() {
		inH = new InputHandler();
		outH = new OutputHandler();
	}
	
	@Test
	public void passRemoveUserCase1() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Success!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("delete user", o.getState());
		
		//State is now DELETEUSER
		oo = inH.processInput("Kevin@carleton.ca", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passRemoveUserCase2() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("The User Does Not Exist!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("delete user", o.getState());
		
		//State is now DELETEUSER
		oo = inH.processInput("coledorma@carleton.ca", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passRemoveUserCase3() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Outstanding Fee Exists!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("delete user", o.getState());
		
		//State is now DELETEUSER
		oo = inH.processInput("Zhibo@carleton.ca", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passRemoveUserCase4() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Active Loan Exists!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("delete user", o.getState());
		
		//State is now DELETEUSER
		oo = inH.processInput("Sun@carleton.ca", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void failRemoveUser() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Your input should in this format:'useremail'", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("delete user", o.getState());
		
		//State is now DELETEUSER
		oo = inH.processInput("7777777777689,1", oo.getState());

		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		 
	}
}
