package com.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Item;
import server.logic.tables.ItemTable;

public class ItemTableTests {
	ItemTable itemTable = null;
	
	@Before
	public void setup() {
		itemTable = ItemTable.getInstance();
		
	}
	
	@Test
	public void testGetItemTable() {
		List<Item> itemList = itemTable.getItemTable();
		String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910"};
		for (int i = 0; i < ISBNList.length; i++) {
			assertEquals(ISBNList[i], itemList.get(i).getISBN());
			assertEquals(i, itemList.get(i).getItemid());
			assertEquals("1", itemList.get(i).getCopynumber());
		}
	}
	
	@Test
	public void passLookup() {
		assertEquals(true, itemTable.lookup("9781442668584", "1"));
	}
	
	@Test
	public void failLookup() {
		assertEquals(false, itemTable.lookup("hey", "99"));
	}
	
	@Test
	public void passCreateItem() {
		assertEquals(true, itemTable.createitem("9781442668584"));
	}
	
	@Test
	public void failCreateItem() {
		assertEquals(false, itemTable.createitem("739867"));
	}

}
