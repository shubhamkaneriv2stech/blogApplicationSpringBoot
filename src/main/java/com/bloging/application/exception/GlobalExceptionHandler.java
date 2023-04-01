package com.bloging.application.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException exception) {
		ApiResponse apiResponse = new ApiResponse(null, null, null, exception.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			if (errors.containsKey(error.getField())) {
				errors.put(error.getField(),
						String.format("%s, %s", errors.get(error.getField()), error.getDefaultMessage()));
			} else {
				errors.put(error.getField(), error.getDefaultMessage());
			}
		});
		return new ApiResponse(null, null, null, errors);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResponse> customException(CustomException exception) {
		ApiResponse apiResponse = new ApiResponse(null, null, null, exception.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse> illegalArgumentException(IllegalArgumentException exception) {
		ApiResponse apiResponse = new ApiResponse(null, null, null, exception.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<ApiResponse> expiredJwtException(ExpiredJwtException exception) {
		ApiResponse apiResponse = new ApiResponse(null, null, null, exception.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<ApiResponse> malformedJwtException(MalformedJwtException exception) {
		ApiResponse apiResponse = new ApiResponse(null, null, null, exception.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<ApiResponse> signatureException(SignatureException exception) {
		ApiResponse apiResponse = new ApiResponse(null, null, null, exception.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}
	

}
