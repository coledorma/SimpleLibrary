package com.tests.acceptance;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;
import server.logic.tables.LoanTable;

public class ReturnLoancopyTests {
	InputHandler inH = null;
	OutputHandler outH = null;
	LoanTable loanList = null;
	
	@Before
	public void setup() {
		inH = new InputHandler();
		outH = new OutputHandler();
		loanList = LoanTable.getInstance();
	}
	
	@Test
	public void passReturnLoancopyCase1() {	
		loanList.createloan(3, "9781442667181", "1", new Date(), "9");
		
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("Success!", 3);
		
		oo = inH.processInput("USER", InputHandler.USERLOGIN);
		
		o = outH.userLogin("Kevin@carleton.ca,kevin");
		
		//State is now USER
		oo = inH.processInput("return", o.getState());
		
		//State is now RETURN
		oo = inH.processInput("Kevin@carleton.ca,9781442667181,1", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}
	
	@Test
	public void passReturnLoancopyCase2() {	
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		Output expected = new Output("The Loan Does Not Exist!", 3);
		
		oo = inH.processInput("USER", InputHandler.USERLOGIN);
		
		o = outH.userLogin("Kevin@carleton.ca,kevin");
		
		//State is now USER
		oo = inH.processInput("return", o.getState());
		
		//State is now RETURN
		oo = inH.processInput("Kevin@carleton.ca,9781442667181,1", oo.getState());
		
		Output actual = new Output(oo.getOutput(), oo.getState());
		
		assertEquals(expected.getOutput(), actual.getOutput());
		assertEquals(expected.getState(), actual.getState());
		 
	}

}
