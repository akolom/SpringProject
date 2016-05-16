package com.egen.exhandle.exception;


@SuppressWarnings("serial")
public class ObjectStateChangedSinceLastRequestException extends RuntimeException {

    public ObjectStateChangedSinceLastRequestException() {
        super();
    }

	public ObjectStateChangedSinceLastRequestException(String msg) {
		super(msg);
	}

}
