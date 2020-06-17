package com.inits.expenseapp.exceptions;


public class CustomUniqueDataViolationException extends FintrackException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CustomUniqueDataViolationException() {
	super("Data is not unique");
    }

    public CustomUniqueDataViolationException(String message) {
	super(message);
    }

}
