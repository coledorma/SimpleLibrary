package com.tests.unit;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.User;
import server.logic.tables.UserTable;

public class UserTableTests {
	UserTable userTable = null;
	UserTable userTableDelete = null;
	
	@Before
	public void setup() {
		userTable = UserTable.getInstance();
		userTableDelete = UserTable.getInstance();
	}
	
	@Test
	public void testGetUserTable() {	
		List<User> userList = userTable.getUserTable();
	 	String[] passwordList=new String[]{"Zhibo","Yu","Michelle","Kevin","Sun"};
	 	String[] usernameList=new String[]{"Zhibo@carleton.ca","Yu@carleton.ca","Michelle@carleton.ca","Kevin@carleton.ca","Sun@carleton.ca"};
	 	for(int i=0;i<usernameList.length;i++){
	 		assertEquals(i, userList.get(i).getUserid());
	 		assertEquals(usernameList[i], userList.get(i).getUsername());
	 		assertEquals(passwordList[i], userList.get(i).getPassword());
		}

	}
	
	@Test
	public void passCreateUser() {
		assertEquals(true, userTable.createuser("Cole@carleton.ca", "Cole"));
	}
	
	@Test
	public void failCreateUser() {
		assertEquals(false, userTable.createuser("Yu@carleton.ca", "Yu"));
		
	}
	
	@Test
	public void passLookup() {
		assertEquals(true, userTable.lookup(0));
	}
	
	@Test
	public void failLookup() {
		assertEquals(false, userTable.lookup(5));
		
	}
	
	@Test
	public void testDelete() {
		List<User> userListDelete = userTableDelete.getUserTable();
		userTableDelete.createuser("Cole@carleton.ca", "Cole");
		
		assertEquals("The User Does Not Exist", userTableDelete.delete(6));
		
		assertEquals("success", userTableDelete.delete(5));
		assertEquals("N/A", userListDelete.get(5).getPassword());
		
		assertEquals("Outstanding Fee Exists", userTableDelete.delete(1));
		
		assertEquals("Active Loan Exists", userTableDelete.delete(2));
	}
	
	@Test
	public void testLookupID() {		
		assertEquals(0, userTable.lookup("Zhibo@carleton.ca"));
		
		assertEquals(-1, userTable.lookup("Not existing user"));
	}
	
	@Test
	public void testCheckUser() {
		assertEquals(0, userTable.checkUser("Zhibo@carleton.ca", "Zhibo"));
		
		assertEquals(2, userTable.checkUser("Not existing", "Hmmmm"));
		
		assertEquals(1, userTable.checkUser("Zhibo@carleton.ca", "Not correct password"));
	}
	
	@Test
	public void testToString() {
		assertEquals("[Zhibo@carleton.ca,Yu@carleton.ca,Michelle@carleton.ca,Kevin@carleton.ca,Sun@carleton.ca]", userTable.toString());
	}

}
