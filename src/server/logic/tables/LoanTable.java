package server.logic.tables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Loan;

public class LoanTable {
	List<Loan> loanList=new ArrayList<Loan>();
	
    private static class LoanListHolder {
        private static final LoanTable INSTANCE = new LoanTable();
    }
    
    private LoanTable(){
    	//set up the default list with some instances
    	Loan loan = new Loan(0,"9781442668584","1",new Date(),"0", "10009");
    	Loan loan2 = new Loan(1,"1000098","2",new Date(),"1", "77777");
    	Loan loan3 = new Loan(2,"1000777","3",new Date(),"1", "88888");
    	loanList.add(loan);
    	loanList.add(loan2);
    	loanList.add(loan3);
    };
    
    public static final LoanTable getInstance() {
        return LoanListHolder.INSTANCE;
    }
    
    public List<Loan> getLoanTable() {
		return loanList;
	}
    
    public boolean looklimit(int j) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int userid=(loanList.get(i)).getUserid();
			if(userid==j){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
    
    public boolean checkUser(int j) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int userid=(loanList.get(i)).getUserid();
			if(userid==j){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
    
    public boolean lookup(int j, String string, String string2) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			String copynumber=(loanList.get(i)).getCopynumber();
			if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
    
}
