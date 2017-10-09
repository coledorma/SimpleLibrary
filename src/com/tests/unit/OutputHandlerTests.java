package com.tests.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.model.Item;
import server.logic.model.Title;
import server.logic.model.User;
import server.logic.tables.ItemTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class OutputHandlerTests {
	
	OutputHandler outH = null;
	UserTable userTable = null;
	TitleTable titleTable = null;
	ItemTable itemTable = null;
	
	@Before
	public void setup() {
		outH =  new OutputHandler();
		userTable = UserTable.getInstance();
		titleTable = TitleTable.getInstance();
		itemTable = itemTable.getInstance();
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
	
	@Test
	public void testCreateItem() {
		Output out = new Output("Your input should in this format:'ISBN',ISBN should be a 13-digit number",6);
		assertEquals(out.getOutput(), outH.createItem("hey").getOutput());
		assertEquals(out.getState(), outH.createItem("hey").getState());
		
		Output out2 = new Output("Success!",2);
		Output create = outH.createItem("7777777777777");
		assertEquals(out2.getOutput(), create.getOutput());
		assertEquals(out2.getState(), create.getState());
		
		Output out3 = new Output("The Title Does Not Exist! Please create a title first:",5);
		assertEquals(out3.getOutput(), outH.createItem("7777777777778").getOutput());
		assertEquals(out3.getState(), outH.createItem("7777777777778").getState());
		
		List<Item> items = itemTable.getItemTable();
		assertEquals("7777777777777", items.get(items.size()-1).getISBN());
	}
	
	@Test
	public void testDeleteUser() {
		Output out = new Output("Your input should in this format:'useremail'",7);
		assertEquals(out.getOutput(), outH.deleteUser("hey").getOutput());
		assertEquals(out.getState(), outH.deleteUser("hey").getState());
		
		Output out2 = new Output("Success!",2);
		Output delete = outH.deleteUser("cole@carleton.ca");
		assertEquals(out2.getOutput(), delete.getOutput());
		assertEquals(out2.getState(), delete.getState());
		
		Output out3 = new Output("The User Does Not Exist!",2);
		assertEquals(out3.getOutput(), outH.deleteUser("noone@carleton.ca").getOutput());
		assertEquals(out3.getState(), outH.deleteUser("noone@carleton.ca").getState());
		
		List<User> users = userTable.getUserTable();
		assertEquals("N/A", users.get(users.size()-1).getUsername());
		assertEquals("Sun@carleton.ca", users.get(users.size()-2).getUsername());
	}
	
	@Test
	public void testDeleteTitle() {
		Output out = new Output("Your input should in this format:'ISBN',ISBN should be a 13-digit number",8);
		assertEquals(out.getOutput(), outH.deleteTitle("hey").getOutput());
		assertEquals(out.getState(), outH.deleteTitle("hey").getState());
		
		Output out2 = new Output("Success!",2);
		Output delete = outH.deleteTitle("9781317594277");
		assertEquals(out2.getOutput(), delete.getOutput());
		assertEquals(out2.getState(), delete.getState());
		
		Output out3 = new Output("The Title Does Not Exist!",2);
		assertEquals(out3.getOutput(), outH.deleteTitle("7777777777778").getOutput());
		assertEquals(out3.getState(), outH.deleteTitle("7777777777778").getState());
		
		List<Title> titles = titleTable.getTitleTable();
		assertEquals("Writing for justice", titles.get(titles.size()-1).getBooktitle());
	}
	
	
}
