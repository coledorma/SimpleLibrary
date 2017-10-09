package server.logic.handler;

import java.util.Date;

import server.logic.handler.model.Output;
import server.logic.tables.FeeTable;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;
import utilities.Config;

public class OutputHandler {
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
    public static final int CLERK = 2;
    public static final int USER = 3;
    public static final int CREATEUSER=4;
    public static final int CREATETITLE=5;
    public static final int CREATEITEM=6;
    public static final int DELETEUSER=7;
    public static final int DELETETITLE=8;
    public static final int DELETEITEM=9;
    public static final int BORROW=10;
    public static final int RENEW=11;
    public static final int RETURN=12;
    public static final int PAYFINE=13;
    public static final int CLERKLOGIN=14;
    public static final int USERLOGIN=15;
    public static final int MONITOR=16;

	public Output createUser(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        Object result="";
        if(strArray.length!=2 || email!=true){
        		output.setOutput("Your input should in this format:'username,password'");
        		output.setState(CREATEUSER);
        }else{
        		result=UserTable.getInstance().createuser(strArray[0], strArray[1]);
        		if(result.equals(true)){
        			output.setOutput("Success!");
        		}else{
        			output.setOutput("The User Already Exists!");
        		}
        		output.setState(CLERK);
        }
		return output;
	}
	
	public static boolean isInteger(String value) {
		char[] ch = value.toCharArray();
		boolean isNumber=true;
		if(value.length()==13){
			for (int i = 0; i < ch.length; i++) {
				isNumber = Character.isDigit(ch[i]);
			}
		}else{
			isNumber=false;
		}
		return isNumber;
	}
	
	public static boolean isNumber(String value) {
		char[] ch = value.toCharArray();
		boolean isNumber=true;
			for (int i = 0; i < ch.length; i++) {
				isNumber = Character.isDigit(ch[i]);
			}
		return isNumber;
	}
	
	public Output createTitle(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=2 || number!=true){
        		output.setOutput("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number");
        		output.setState(CREATETITLE);
        }else{
        		result=TitleTable.getInstance().createtitle(strArray[0], strArray[1]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Title Already Exists!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	
	public Output createItem(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=1 || number!=true){
        		output.setOutput("Your input should in this format:'ISBN',ISBN should be a 13-digit number");
        		output.setState(CREATEITEM);
        }else{
        	result=ItemTable.getInstance().createitem(strArray[0]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
            output.setState(CLERK);
        	}else{
        		output.setOutput("The Title Does Not Exist! Please create a title first:");
        		output.setState(CREATETITLE);
        	}
        }
		return output;
	}
	
}
