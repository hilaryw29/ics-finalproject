package icsFinalProject;

public class PINNotMatchException extends Exception {
	private String message;
	private String enteredPIN;
	public PINNotMatchException(String message, String PIN) {
		this.message = message;
		this.enteredPIN = PIN;
	}
	
	public String getEnteredPIN() {
		return enteredPIN;
	}
	
	public String getMessage() {
		return message;
	}
}
