package com.tests.acceptance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class AddUserTests {
	InputHandler inH = null;
	OutputHandler outH = null;
	
	@Before
	public void setup() {
		inH = new InputHandler();
		outH = new OutputHandler();
	}
	
	@Test
	public void passAddUserCase1() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Success!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("create user", o.getState());
		
		//State is now CREATEUSER
		oo = inH.processInput("coledorma@carleton.ca,cole", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passAddUserCase2() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("The User Already Exists!", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("create user", o.getState());
		
		//State is now CREATEUSER
		oo = inH.processInput("Zhibo@carleton.ca,zhibo", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void failAddUser() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Your input should in this format:'username,password'", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("create user", o.getState());
		
		//State is now CREATEUSER
		oo = inH.processInput("7777777777689", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		 
	}
}