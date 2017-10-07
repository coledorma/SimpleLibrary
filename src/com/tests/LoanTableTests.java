package com.tests;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Item;
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
		assertEquals(true, loanTable.looklimit(10));
		assertEquals(true, loanTable.looklimit(11));
	}
	
	@Test
	public void failLookLimit() {
		assertEquals(false, loanTable.looklimit(0));
		assertEquals(false, loanTable.looklimit(1));
	}
	
	@Test
	public void passCheckUser() {
		assertEquals(true, loanTable.checkUser(10));
		assertEquals(true, loanTable.checkUser(11));
	}
	
	@Test
	public void failCheckUser() {
		assertEquals(false, loanTable.checkUser(0));
		assertEquals(false, loanTable.checkUser(1));
	}
	
	@Test
	public void passLookup() {
		assertEquals(true, loanTable.lookup(0, "hey not here", "nope"));
		assertEquals(true, loanTable.lookup(0, "9781442667181", "1"));
	}
	
	@Test
	public void failLookup() {
		assertEquals(false, loanTable.lookup(0, "1000098", "2"));
	}
	
	@Test
	public void passCheckLimit() {
		assertEquals(true, loanTable.checkLimit(0));
	}
	
	@Test
	public void failCheckLimit() {
		assertEquals(false, loanTable.checkLimit(4));
	}
	
	@Test
	public void testCreateLoan() {
		assertEquals("User Invalid", loanTable.createloan(87, "", "", new Date(), ""));
		
		assertEquals("ISBN Invalid", loanTable.createloan(0, "Non-existant ISBN", "", new Date(), ""));
		
		assertEquals("Copynumber Invalid", loanTable.createloan(0, "9781442667181", "Copynumber not existing", new Date(), ""));
		
		assertEquals("success", loanTable.createloan(3, "9781442667181", "1", new Date(), ""));
		
		assertEquals("The Maximun Number of Items is Reached", loanTable.createloan(4, "9781611687910", "1", new Date(), ""));
		
		assertEquals("Outstanding Fee Exists", loanTable.createloan(0, "9781611687910", "1", new Date(), ""));
		
		assertEquals("The Item is Not Available", loanTable.createloan(0, "9781442668584", "1", new Date(), ""));
	}
	
	@Test
	public void testRenewal() {
		loanTable.createloan(3, "9781442667181", "1", new Date(), "");
		
		assertEquals("success", loanTable.renewal(3, "9781442667181", "1", new Date()));
		
		assertEquals("Renewed Item More Than Once for the Same Loan", loanTable.renewal(3, "9781442667181", "1", new Date()));
		
		assertEquals("The loan does not exist", loanTable.renewal(3, "9781442668584","1", new Date()));
		
		assertEquals("The Maximun Number of Items is Reached", loanTable.renewal(4, "9781611687910", "1", new Date()));
		
		assertEquals("Outstanding Fee Exists", loanTable.renewal(0, "9781611687910", "1", new Date()));
	}
	
	@Test
	public void testReturnItem() {
		loanTable.createloan(3, "9781442667181", "1", new Date(), "");
		
		assertEquals("success", loanTable.returnItem(3, "9781442667181", "1", new Date()));
		
		assertEquals("The Loan Does Not Exist", loanTable.returnItem(1, "9781442667181", "1", new Date()));
	}
	
	@Test
	public void passCheckLoan() {
		assertEquals(true, loanTable.checkLoan("Not a loan",""));
		
	}
	
	@Test
	public void failCheckLoan() {
		assertEquals(false, loanTable.checkLoan("1000098","2"));
	}

	
}
