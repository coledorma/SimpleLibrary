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

}
