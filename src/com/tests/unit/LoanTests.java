package com.tests.unit;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Fee;
import server.logic.model.Loan;

public class LoanTests {
	Loan loan1 = null;
	Loan loan2 = null;
	Date date = null;
	Date date2 = null;
	
	@Before
	public void setup() {
		date = new Date();
		date2 = new Date();
		
		//Loan for getter
		loan1 = new Loan(1001, "7888N7", "0", date,"0","189");
		
		//Fee for setter
		loan2 = new Loan(2002, "101018", "1", date,"1","222");
		loan2.setUserid(2009);
		loan2.setIsbn("77777");
		loan2.setCopynumber("8");
		loan2.setDate(date2);
		loan2.setRenewstate("2");
		loan2.setLoanID("888");
	}
	
	@Test
	public void testGetters() {
		assertEquals(1001, loan1.getUserid());
		assertEquals("7888N7", loan1.getIsbn());
		assertEquals("0", loan1.getCopynumber());
		assertEquals(date, loan1.getDate());
		assertEquals("0", loan1.getRenewstate());
		assertEquals("189", loan1.getLoanID());
		
	}
	
	@Test
	public void testSetters() {
		assertEquals(2009, loan2.getUserid());
		assertEquals("77777", loan2.getIsbn());
		assertEquals("8", loan2.getCopynumber());
		assertEquals(date2, loan2.getDate());
		assertEquals("2", loan2.getRenewstate());
		assertEquals("888", loan2.getLoanID());

	}
	
	@Test
	public void testToString() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		assertEquals("[1001,7888N7,0,"+format1.format(date)+",0,189]", loan1.toString());
	}

}
