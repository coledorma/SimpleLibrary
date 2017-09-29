package com.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Fee;
import server.logic.model.Item;

public class FeeTests {
	Fee fee1 = null;
	Fee fee2 = null;
	
	@Before
	public void setup() {
		//Fee for getter
		fee1 = new Fee(1001, 5);
		
		//Fee for setter
		fee2 = new Fee(2002, 3);
		fee2.setUserid(2007);
		fee2.setFee(18);
	}
	
	@Test
	public void testGetters() {
		assertEquals(1001, fee1.getUserid());
		assertEquals(5, fee1.getFee());
		
	}
	
	@Test
	public void testSetters() {
		assertEquals(2007, fee2.getUserid());
		assertEquals(18, fee2.getFee());

	}
	
	@Test
	public void testToString() {
		assertEquals("[1001,5]", fee1.toString());
	}

}
