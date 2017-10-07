package com.tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.User;
import server.logic.tables.UserTable;

public class UserTableTests {
	UserTable userTable = null;
	
	@Before
	public void setup() {
		userTable = UserTable.getInstance();
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
}
