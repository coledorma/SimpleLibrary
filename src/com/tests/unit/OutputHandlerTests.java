package com.tests.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.model.Title;
import server.logic.model.User;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class OutputHandlerTests {
	
	OutputHandler outH = null;
	UserTable userTable = null;
	TitleTable titleTable = null;
	
	@Before
	public void setup() {
		outH =  new OutputHandler();
		userTable = UserTable.getInstance();
		titleTable = TitleTable.getInstance();
	}
	
	@Test
	public void testCreateUser() {
		Output out = new Output("Your input should in this format:'username,password'",4);
		assertEquals(out.getOutput(), outH.createUser("hey").getOutput());
		assertEquals(out.getState(), outH.createUser("hey").getState());
		
		Output out2 = new Output("Success!",2);
		Output create = outH.createUser("cole@carleton.ca,me");
		assertEquals(out2.getOutput(), create.getOutput());
		assertEquals(out2.getState(), create.getState());
		
		Output out3 = new Output("The User Already Exists!",2);
		assertEquals(out3.getOutput(), outH.createUser("Zhibo@carleton.ca,zhibo").getOutput());
		assertEquals(out3.getState(), outH.createUser("Zhibo@carleton.ca,zhibo").getState());
		
		List<User> users = userTable.getUserTable();
		assertEquals("cole@carleton.ca", users.get(users.size()-1).getUsername());
	}
	
	@Test
	public void passIsInteger() {
		assertEquals(true, OutputHandler.isInteger("7777777777777"));
	}
	
	@Test
	public void failIsInteger() {
		assertEquals(false, OutputHandler.isInteger("13"));
		assertEquals(false, OutputHandler.isInteger("hey"));
	}
	
	@Test
	public void passIsNumber() {
		assertEquals(true, OutputHandler.isNumber("7777777777777"));
		assertEquals(true, OutputHandler.isNumber("13"));
	}
	
	@Test
	public void failIsNumber() {
		assertEquals(false, OutputHandler.isNumber("hey"));
	}
	
	@Test
	public void testCreateTitle() {
		Output out = new Output("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number",5);
		assertEquals(out.getOutput(), outH.createTitle("hey").getOutput());
		assertEquals(out.getState(), outH.createTitle("hey").getState());
		
		Output out2 = new Output("Success!",2);
		Output create = outH.createTitle("7777777777777,coledorma");
		assertEquals(out2.getOutput(), create.getOutput());
		assertEquals(out2.getState(), create.getState());
		
		Output out3 = new Output("The Title Already Exists!",2);
		assertEquals(out3.getOutput(), outH.createTitle("9781442668584,By the grace of God").getOutput());
		assertEquals(out3.getState(), outH.createTitle("9781442668584,By the grace of God").getState());
		
		List<Title> titles = titleTable.getTitleTable();
		assertEquals("coledorma", titles.get(titles.size()-1).getBooktitle());
	}
	
	
}
