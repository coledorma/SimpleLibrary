package com.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Fee;
import server.logic.model.Title;

public class TitleTests {
	Title title1 = null;
	Title title2 = null;
	
	@Before
	public void setup() {
		//Title for getter
		title1 = new Title("7HHHH8", "Welcome");
		
		//Title for setter
		title2 = new Title("888888", "Testing");
		title2.setISBN("111111");
		title2.setBooktitle("How");

	}
	
	@Test
	public void testGetters() {
		assertEquals("7HHHH8", title1.getISBN());
		assertEquals("Welcome", title1.getBooktitle());
	}
	
	@Test
	public void testSetters() {
		assertEquals("111111", title2.getISBN());
		assertEquals("How", title2.getBooktitle());
	}
	
	@Test
	public void testToString() {
		assertEquals("[7HHHH8,Welcome]", title1.toString());
		
	}

}
