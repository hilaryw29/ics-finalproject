package icsFinalProject;

//Class Description: Inherits the Exception class and throws an exception message 
//when the entered account does not exist or when the entered account does not 
//belong to the specific family member or when the account holder does not exist.
public class AccountException extends Exception {
	private boolean isAccountExisted;
	private String name;
	
	//The method takes in a boolean suggesting whether the account exists and a name
	//of the member and throws an exception when account does not exist or person 
	//identity does not match
	public AccountException(boolean isAccountExisted, String name) {
		this.isAccountExisted = isAccountExisted;
		this.name = name;
	}
	
	//The method takes in no parameters and return a boolean suggesting whether 
	//the account exists
	public boolean isAccountExisted() {
		return isAccountExisted;
	}
	
	//The method get the name of the account holder
	public String getName(){
		return name;
	}
}
