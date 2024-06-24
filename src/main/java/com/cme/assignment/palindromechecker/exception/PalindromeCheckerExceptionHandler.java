package com.cme.assignment.palindromechecker.exception;

import com.cme.assignment.palindromechecker.dto.PalindromeCheckerResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PalindromeCheckerExceptionHandler {

    Logger logger = LoggerFactory.getLogger(PalindromeCheckerExceptionHandler.class);

    /**
     * Exception handling method for any field validation fails
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        logger.error("MethodArgumentNotValidException occurred", ex);
        StringBuffer errorMessage = new StringBuffer();
        errorMessage.append("Validation Error:");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errorMessage.append(" ").append(error.getDefaultMessage());
        });
        PalindromeCheckerResponseDTO response = PalindromeCheckerResponseDTO.builder()
                .status(PalindromeCheckerResponseDTO.Status.FAILED)
                .message(errorMessage.toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handling method for empty request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleInvalidRequestExceptions(
            HttpMessageNotReadableException ex) {
        logger.error("HttpMessageNotReadableException occurred", ex);
        PalindromeCheckerResponseDTO response = PalindromeCheckerResponseDTO.builder()
                .status(PalindromeCheckerResponseDTO.Status.FAILED)
                .message("Error: Invalid Request")
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handling method for generic exceptions
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralExceptions(
            Exception ex) {
        logger.error("Exception occurred", ex);
        StringBuffer errorMessage = new StringBuffer().append("Error: ").append(ex.getLocalizedMessage());
        PalindromeCheckerResponseDTO response = PalindromeCheckerResponseDTO.builder()
                .status(PalindromeCheckerResponseDTO.Status.ERROR)
                .message(errorMessage.toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
