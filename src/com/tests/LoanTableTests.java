package com.tests;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Loan;
import server.logic.tables.LoanTable;

public class LoanTableTests {
	LoanTable loanTable = null;
	
	@Before
	public void setup() {
		loanTable = loanTable.getInstance();
	}
	
	@Test
	public void testGetLoanTable() {	
		List<Loan> loanList = loanTable.getLoanTable();
		
		assertEquals(1, loanList.get(1).getUserid());
		assertEquals("1000098", loanList.get(1).getIsbn());
		assertEquals("2", loanList.get(1).getCopynumber());
		assertEquals("1", loanList.get(1).getRenewstate());
		assertEquals("77777", loanList.get(1).getLoanID());
		
		assertEquals("88888", loanList.get(2).getLoanID());
		
		assertEquals("9781442668584", loanList.get(0).getIsbn());
	}
	
	@Test
	public void passLookLimit() {
		assertEquals(true, loanTable.looklimit(4));
		assertEquals(true, loanTable.looklimit(5));
	}
	
	@Test
	public void failLookLimit() {
		assertEquals(false, loanTable.looklimit(0));
		assertEquals(false, loanTable.looklimit(1));
	}
	
	@Test
	public void passCheckUser() {
		assertEquals(true, loanTable.checkUser(3));
		assertEquals(true, loanTable.checkUser(4));
	}
	
	@Test
	public void failCheckUser() {
		assertEquals(false, loanTable.checkUser(0));
		assertEquals(false, loanTable.checkUser(1));
	}
	
}
