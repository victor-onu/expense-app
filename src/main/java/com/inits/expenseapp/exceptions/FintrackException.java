package com.inits.expenseapp.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FintrackException extends RuntimeException{

    protected String message;
    
    public FintrackException(String message) {
        this.message=message;
    }
}
