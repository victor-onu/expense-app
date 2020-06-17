package com.inits.expenseapp.config;

import com.inits.expenseapp.apiresponse.ApiResponse;
import com.inits.expenseapp.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

	ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST);
	apiResponse.setError("Validation Failed");
	apiResponse.addValidationErrors(ex.getBindingResult().getFieldErrors());
	return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiResponseException.class)
    public ResponseEntity<Object> handleCustomException(ApiResponseException e) {

	ApiResponse<?> ar = new ApiResponse<>(e.getStatus());
	ar.setError(e.getMessage());
	return new ResponseEntity<>(ar, ar.getStatus());

    }

    @ExceptionHandler(CustomUniqueDataViolationException.class)
    public ResponseEntity<Object> handleUniqueDataConstraintViolationException(CustomUniqueDataViolationException e) {

	ApiResponse<?> ar = new ApiResponse<>(HttpStatus.CONFLICT);
	ar.setError(e.getMessage());
	return new ResponseEntity<>(ar, ar.getStatus());

    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<Object> handleInvalidDataException(InvalidCredentialException e) {

	ApiResponse<?> ar = new ApiResponse<>(HttpStatus.BAD_REQUEST);
	ar.setError(e.getMessage());
	return new ResponseEntity<>(ar, ar.getStatus());

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {

	ApiResponse<?> ar = new ApiResponse<>(HttpStatus.NOT_FOUND);
	ar.setError(e.getMessage());
	return new ResponseEntity<>(ar, ar.getStatus());

    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<Object> handleInvalidOperationException(InvalidOperationException e) {

	ApiResponse<?> ar = new ApiResponse<>(HttpStatus.UNAUTHORIZED);
	ar.setError(e.getMessage());
	return new ResponseEntity<>(ar, ar.getStatus());

    }

}
