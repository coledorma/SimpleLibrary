package com.tests.acceptance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class MonitorSystemTests {
	InputHandler inH = null;
	OutputHandler outH = null;
	
	@Before
	public void setup() {
		inH = new InputHandler();
		outH = new OutputHandler();
	}
	
	@Test
	public void passMonitorSystemCase1() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("[Zhibo@carleton.ca,Yu@carleton.ca,Michelle@carleton.ca,Kevin@carleton.ca,Sun@carleton.ca]\n" + 
				"[By the grace of God,Dante's lyric poetry ,Courtesy lost,Writing for justice,The act in context]", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("admin");
		
		//State is now CLERK
		oo = inH.processInput("monitor", o.getState());
		
		//State is now MONITOR
		oo = inH.processInput("", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
				
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	
	@Test
	public void failMonitorSystem() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Wrong Password!Please Input The Password:", 2);
		
		oo = inH.processInput("CLERK", InputHandler.CLERKLOGIN);
		
		o = outH.clerkLogin("hey");
		
		//State is now CLERK
		oo = inH.processInput("monitor", o.getState());
		
		//State is now MONITOR
		oo = inH.processInput("7777777777689", oo.getState());

		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		 
	}
}

