package com.egen.exhandle.exception;


@SuppressWarnings("serial")
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {
        super();
    }

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

}
