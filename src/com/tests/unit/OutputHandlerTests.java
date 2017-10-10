package com.tests.unit;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.model.Item;
import server.logic.model.Loan;
import server.logic.model.Title;
import server.logic.model.User;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class OutputHandlerTests {
	
	OutputHandler outH = null;
	UserTable userTable = null;
	TitleTable titleTable = null;
	ItemTable itemTable = null;
	LoanTable loanTable = null;
	
	@Before
	public void setup() {
		outH =  new OutputHandler();
		userTable = UserTable.getInstance();
		titleTable = TitleTable.getInstance();
		itemTable = itemTable.getInstance();
		loanTable = loanTable.getInstance();
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
	
	@Test
	public void testDeleteItem() {
		Output out = new Output("Your input should in this format:'ISBN,copynumber',ISBN should be a 13-digit number",9);
		assertEquals(out.getOutput(), outH.deleteItem("hey").getOutput());
		assertEquals(out.getState(), outH.deleteItem("hey").getState());
		
		Output outcop = new Output("Your input should in this format:'ISBN,copynumber',Copynumber should be a number",9);
		assertEquals(outcop.getOutput(), outH.deleteItem("7777777777777,hey").getOutput());
		assertEquals(outcop.getState(), outH.deleteItem("7777777777777,hey").getState());
		
		
		Output out2 = new Output("Success!",2);
		Output delete = outH.deleteItem("9781442667181,1");
		assertEquals(out2.getOutput(), delete.getOutput());
		assertEquals(out2.getState(), delete.getState());
		
		Output out3 = new Output("The Item Does Not Exist!",2);
		assertEquals(out3.getOutput(), outH.deleteItem("7777777777778,1").getOutput());
		assertEquals(out3.getState(), outH.deleteItem("7777777777778,1").getState());
		
		List<Item> items = itemTable.getItemTable();
		System.out.println(items.size());
		assertEquals("7777777777777", items.get(items.size()-1).getISBN());
	}
	
	@Test
	public void testBorrow() {
		Output out = new Output("Your input should in this format:'useremail,ISBN,copynumber'",10);
		assertEquals(out.getOutput(), outH.borrow("hey").getOutput());
		assertEquals(out.getState(), outH.borrow("hey").getState());
		
		Output outcop = new Output("Your input should in this format:'useremail,ISBN,copynumber'",10);
		assertEquals(outcop.getOutput(), outH.borrow("Zhibo@carleton.ca,7777777777777,hey").getOutput());
		assertEquals(outcop.getState(), outH.borrow("Zhibo@carleton.ca,7777777777777,hey").getState());
		
		Output outcop2 = new Output("Your input should in this format:'useremail,ISBN,copynumber'",10);
		assertEquals(outcop2.getOutput(), outH.borrow("Zhibo@carleton.ca,hey,1").getOutput());
		assertEquals(outcop2.getState(), outH.borrow("Zhibo@carleton.ca,hey,1").getState());
		
		Output out2 = new Output("Success!",3);
		Output borrow = outH.borrow("Michelle@carleton.ca,9781442616899,1");
		assertEquals(out2.getOutput(), borrow.getOutput());
		assertEquals(out2.getState(), borrow.getState());
		
		Output outCopy = new Output("Copynumber Invalid!",3);
		Output borrow2 = outH.borrow("Kevin@carleton.ca,9781442667181,3");
		assertEquals(outCopy.getOutput(), borrow2.getOutput());
		assertEquals(outCopy.getState(), borrow2.getState());
		
		Output outCopy2 = new Output("ISBN Invalid!",3);
		Output borrow3 = outH.borrow("Kevin@carleton.ca,9781442667189,1");
		assertEquals(outCopy2.getOutput(), borrow3.getOutput());
		assertEquals(outCopy2.getState(), borrow3.getState());
		
		Output out3 = new Output("Please pay your fine first because Outstanding Fee Exists!",13);
		assertEquals(out3.getOutput(), outH.borrow("Zhibo@carleton.ca,9781611687910,1").getOutput());
		assertEquals(out3.getState(), outH.borrow("Zhibo@carleton.ca,9781611687910,1").getState());
		
		Output out5 = new Output("The Item is Not Available!",3);
		assertEquals(out5.getOutput(), outH.borrow("Zhibo@carleton.ca,9781442616899,1").getOutput());
		assertEquals(out5.getState(), outH.borrow("Zhibo@carleton.ca,9781442616899,1").getState());
		
		Output out4 = new Output("The User Does Not Exist!",10);
		assertEquals(out4.getOutput(), outH.borrow("cole@me.com,7777777777778,1").getOutput());
		assertEquals(out4.getState(), outH.borrow("cole@me.com,7777777777778,1").getState());
		
		List<Loan> loanList = loanTable.getLoanTable();
    		Loan loan1=new Loan(1,"7777777777779","1",new Date(),"0","17");
    		Loan loan3=new Loan(1,"7777777777773","1",new Date(),"0","8");
    		Loan loan2=new Loan(1,"7777777777771","1",new Date(),"0","9");
    		loanList.add(loan1);
    		loanList.add(loan2);
    		loanList.add(loan3);
    		
    		Output out9 = new Output("The Maximun Number of Items is Reached!",3);
    		assertEquals(out9.getOutput(), outH.borrow("Yu@carleton.ca,7777777777777,1").getOutput());
    		assertEquals(out9.getState(), outH.borrow("Yu@carleton.ca,7777777777777,1").getState());
	}
	
	@Test
	public void testRenew() {
		Output out = new Output("Your input should in this format:'useremail,ISBN,copynumber'",11);
		assertEquals(out.getOutput(), outH.renew("hey").getOutput());
		assertEquals(out.getState(), outH.renew("hey").getState());
		
		Output outcop = new Output("Your input should in this format:'useremail,ISBN,copynumber'",11);
		assertEquals(outcop.getOutput(), outH.renew("Zhibo@carleton.ca,7777777777777,hey").getOutput());
		assertEquals(outcop.getState(), outH.renew("Zhibo@carleton.ca,7777777777777,hey").getState());
		
		Output outcop2 = new Output("Your input should in this format:'useremail,ISBN,copynumber'",11);
		assertEquals(outcop2.getOutput(), outH.renew("Zhibo@carleton.ca,hey,1").getOutput());
		assertEquals(outcop2.getState(), outH.renew("Zhibo@carleton.ca,hey,1").getState());
		
		/*outH.borrow("Michelle@carleton.ca,9781317594277,1");
		Loan loan2=new Loan(2,"9781317594277","1",new Date(),"0","9");
		Output outSuc = new Output("Success!",3);
		Output renewSuc = outH.renew("Michelle@carleton.ca,9781442616899,1");
		assertEquals(outSuc.getOutput(), renewSuc.getOutput());
		assertEquals(outSuc.getState(), renewSuc.getState());*/
		
		Output out2 = new Output("The loan does not exist!",3);
		Output renew = outH.renew("Kevin@carleton.ca,9781442616899,1");
		assertEquals(out2.getOutput(), renew.getOutput());
		assertEquals(out2.getState(), renew.getState());
		
		Output out3 = new Output("Please pay your fine first because Outstanding Fee Exists!",13);
		assertEquals(out3.getOutput(), outH.renew("Zhibo@carleton.ca,9781611687910,1").getOutput());
		assertEquals(out3.getState(), outH.renew("Zhibo@carleton.ca,9781611687910,1").getState());
		
		Output out4 = new Output("The User Does Not Exist!",11);
		assertEquals(out4.getOutput(), outH.renew("cole@me.com,7777777777778,1").getOutput());
		assertEquals(out4.getState(), outH.renew("cole@me.com,7777777777778,1").getState());
		
		List<Loan> loanList = loanTable.getLoanTable();
    		loanList.get(3).setRenewstate("8");
    		
    		Output out9 = new Output("The Maximun Number of Items is Reached!",3);
    		assertEquals(out9.getOutput(), outH.borrow("Kevin@carleton.ca,7777777777777,1").getOutput());
    		assertEquals(out9.getState(), outH.borrow("Kevin@carleton.ca,7777777777777,1").getState());
	}
	
	
}
