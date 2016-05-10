package edu.mum.ezstore.exception;


@SuppressWarnings("serial")
public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
        super();
    }

	public AuthorizationException(String msg) {
		super(msg);
	}
}
