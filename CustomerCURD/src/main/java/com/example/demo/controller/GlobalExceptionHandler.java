package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.InvalidAddressException;
import com.example.demo.exception.InvalidIdException;
import com.example.demo.exception.InvalidMobileNumber;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidMobileNumber.class)
	public ResponseEntity<?> invalidMobileNumber(InvalidMobileNumber e){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());
	}
    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<String> handleInvalidAddress(InvalidAddressException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<String> handleInvalidId(InvalidIdException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
