package com.inits.expenseapp.exceptions;


public class InvalidCredentialException extends FintrackException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InvalidCredentialException() {
	super("Invalid data");
    }

    public InvalidCredentialException(String message) {
	super(message);
    }

}
