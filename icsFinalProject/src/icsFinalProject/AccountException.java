package icsFinalProject;

public class AccountEception extends Exception {
	private boolean isAccountExisted;
	private String name;
	public AccountException(boolean isAccountExisted, String name) {
		this.isAccountExisted = isAccountExisted;
		this.name = name;
	}
	
	public boolean isAccountExisted() {
		return isAccountExisted;
	}
	
	public String getName(){
		return name;
	}
}