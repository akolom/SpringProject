package edu.mum.ezstore.exception;


@SuppressWarnings("serial")
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {
        super();
    }

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

}
