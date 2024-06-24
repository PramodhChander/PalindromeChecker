package com.cme.assignment.palindromechecker.exception;

import com.cme.assignment.palindromechecker.dto.PalindromeCheckerResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PalindromeCheckerExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        PalindromeCheckerResponseDTO response = new PalindromeCheckerResponseDTO();
        response.setStatus(PalindromeCheckerResponseDTO.Status.FAILED);
        StringBuffer errorMessage = new StringBuffer();
        errorMessage.append("Validation Error:");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errorMessage.append(" ").append(error.getDefaultMessage());
        });
        response.setMessage(errorMessage.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
