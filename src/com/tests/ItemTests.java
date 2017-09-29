package com.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Item;

public class ItemTests {
	Item testItem = null;
	Item testItem2 = null;
	
	@Before
	public void setup() {
		//Item for getter
		testItem = new Item(1, "3N6892F", "0");
		
		//Item for setter
		testItem2 = new Item(5, "555555H", "1");
		testItem2.setISBN("333333F");
		testItem2.setItemid(7);
		testItem2.setCopynumber("5");
		
	}
	
	@Test
	public void testGetters() {
		assertEquals(1, testItem.getItemid());
		assertEquals("3N6892F", testItem.getISBN());
		
		assertEquals("0", testItem.getCopynumber());
		
	}
	
	@Test
	public void testSetters() {
		assertEquals(7, testItem2.getItemid());
		assertEquals("333333F", testItem2.getISBN());
		
		assertEquals("5", testItem2.getCopynumber());
	}

}

