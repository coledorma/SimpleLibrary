package com.tests;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Fee;
import server.logic.tables.FeeTable;

public class FeeTableTests {
	FeeTable feeTable = null;
	
	@Before
	public void setup() {
		feeTable = FeeTable.getInstance();
	}
	
	@Test
	public void testGetFeeTable() {	
		List<Fee> feeList = feeTable.getFeeTable();
		
		assertEquals(0, feeList.get(0).getUserid());
		
		assertEquals(1, feeList.get(1).getUserid());
		assertEquals(100, feeList.get(1).getFee());
		
		assertEquals(2, feeList.get(2).getUserid());
		
	}
	
	@Test
	public void testApplyFee() {
		List<Fee> feeList = feeTable.getFeeTable();
		
		feeTable.applyfee(3, 360000);
		assertEquals(1, feeList.get(3).getFee());
		assertEquals(3, feeList.get(3).getUserid());
		
		feeTable.applyfee(4, 2000);
		assertEquals(0, feeList.get(4).getFee());
		assertEquals(4, feeList.get(4).getUserid());
		
		feeTable.applyfee(0, 360000);
		assertEquals(6, feeList.get(0).getFee());
		assertEquals(0, feeList.get(0).getUserid());
		
		feeTable.applyfee(1, 50000);
		assertEquals(100, feeList.get(1).getFee());
		assertEquals(1, feeList.get(1).getUserid());
		
	}
	
	@Test
	public void passCheckUser() {
		assertEquals(true, feeTable.checkuser(0));
		assertEquals(true, feeTable.checkuser(1));
		assertEquals(true, feeTable.checkuser(3));
	}
	
	@Test
	public void failCheckUser() {
		assertEquals(false, feeTable.checkuser(8));
		assertEquals(false, feeTable.checkuser(4));
		
	}
	
}
