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
		testItem = new Item(1, "3N6892F");
		
		testItem2 = new Item(5, "555555H");
		testItem2.setISBN("333333F");
		testItem2.setItemid(7);
	}
	
	@Test
	public void testGetters() {
		assertEquals(1, testItem.getItemid());
		assertEquals("3N6892F", testItem.getISBN());
	}
	
	@Test
	public void testSetters() {
		assertEquals(7, testItem2.getItemid());
		assertEquals("333333F", testItem2.getISBN());
	}

}

