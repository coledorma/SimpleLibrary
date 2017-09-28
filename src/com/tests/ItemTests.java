package com.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Item;

public class ItemTests {
	Item testItem = null;
	
	@Before
	public void setup() {
		testItem = new Item(1, "3N6892F");	
	}
	
	@Test
	public void testGetters() {
		assertEquals(1, testItem.getItemid());
		assertEquals("3N6892F", testItem.getISBN());
	}

}

