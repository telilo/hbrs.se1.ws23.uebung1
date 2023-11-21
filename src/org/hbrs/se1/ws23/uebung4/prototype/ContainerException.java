package org.hbrs.se1.ws23.uebung4.prototype;

public class ContainerException extends Exception {
	private String message;
	private String type;

	public ContainerException(String type, String message) {
		super(message);
		this.message = message;
		this.type= type;
	}

	public ContainerException( String message  ) {
		super(message);
		this.message = message;
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		System.out.println( this.message );
	}
}
