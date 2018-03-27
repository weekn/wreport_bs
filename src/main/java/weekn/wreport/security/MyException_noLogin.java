package weekn.wreport.security;

public class MyException_noLogin extends Exception{

	public MyException_noLogin(String message, Throwable cause) {
		super(message,cause);
	}
	public MyException_noLogin(String message) {
		super(message);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
