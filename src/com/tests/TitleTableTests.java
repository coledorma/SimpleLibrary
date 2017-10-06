package com.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Title;
import server.logic.tables.TitleTable;

public class TitleTableTests {
	TitleTable titleTable = null;
	
	@Before
	public void setup() {
		titleTable = TitleTable.getInstance();
		
	}
	
	@Test
	public void testGetTitleTable() {
		List<Title> titleList = titleTable.getTitleTable();
		String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910","9781317594277"};
		String[] titlenameList=new String[]{"By the grace of God","Dante's lyric poetry ","Courtesy lost","Writing for justice","The act in context"};
		for (int i = 0; i < ISBNList.length; i++) {
			assertEquals(ISBNList[i], titleList.get(i).getISBN());
			assertEquals(titlenameList[i], titleList.get(i).getBooktitle());
		}
	}
	
	@Test
	public void passCreateTitle() {
		assertEquals(true, titleTable.createtitle("100967", "The story of Cole Dorma"));
	}
	
	@Test
	public void failCreateTitle() {
		assertEquals(false, titleTable.createtitle("9781442668584", "By the grace of God"));
	}
	
	@Test
	public void testLookup() {
		assertEquals(true, titleTable.lookup("100967"));
		assertEquals(false, titleTable.lookup("7"));
	}

}

