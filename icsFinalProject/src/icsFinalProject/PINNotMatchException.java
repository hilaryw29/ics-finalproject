package icsFinalProject;

//Class description: the class inherits the Exception class and throws an exception
//message when the PIN entered by the user is not matched to the correct PIN.
public class PINNotMatchException extends Exception{
	private String message;
	private String enteredPIN;
	
	//constructs an exception with the PIN entered and the message
	public PINNotMatchException(String message, String PIN) {
		this.message = message;
		this.enteredPIN = PIN;
	}
	
	//Accessors
	public String getEnteredPIN() {
		return enteredPIN;
	}
	
	public String getMessage() {
		return message;
	}
}
