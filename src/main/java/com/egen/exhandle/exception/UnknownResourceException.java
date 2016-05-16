package com.egen.exhandle.exception;


@SuppressWarnings("serial")
public class UnknownResourceException extends RuntimeException {

    public UnknownResourceException() {
        super();
    }

	public UnknownResourceException(String msg) {
		super(msg);
	}

}
