package com.tests.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.User;

public class UserTests {
	User user1 = null;
	User user2 = null;
	
	@Before
	public void setup() {
		//User for getter
		user1 = new User(1899, "cole", "1234");
		
		//User for setter
		user2 = new User(9898, "harold", "8888");
		user2.setUserid(9999);
		user2.setUsername("harry");
		user2.setPassword("7777");

	}
	
	@Test
	public void testGetters() {
		assertEquals(1899, user1.getUserid());
		assertEquals("cole", user1.getUsername());
		assertEquals("1234", user1.getPassword());
	}
	
	@Test
	public void testSetters() {
		assertEquals(9999, user2.getUserid());
		assertEquals("harry", user2.getUsername());
		assertEquals("7777", user2.getPassword());

	}
	
	@Test
	public void testToString() {
		assertEquals("[1899,cole,1234]", user1.toString());
	}
}
