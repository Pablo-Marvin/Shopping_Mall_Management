package com.sm.im;


public class InvalidItemException extends  RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidItemException(String message) {
        super(message);
    }
}
