package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.model.ErrorResponseDTO;

import customexception.FieldRequiredException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> handleArithmeticException(
            ArithmeticException ex, WebRequest request) {
		
		ErrorResponseDTO errorResonseDTO=new ErrorResponseDTO();
		errorResonseDTO.setError(ex.getMessage());
		List<String> details=new ArrayList<>();
		details.add("So nguyen lam sao chia dc cho khong");
		errorResonseDTO.setDetail(details);
        return new ResponseEntity<>(errorResonseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<Object> handleFieldRequiredException(
            FieldRequiredException ex, WebRequest request) {
		
		ErrorResponseDTO errorResonseDTO=new ErrorResponseDTO();
		errorResonseDTO.setError(ex.getMessage());
		List<String> details=new ArrayList<>();
		details.add("Check lai nam hoăc numberofbasement di boi vi dang bi null cho do");
		errorResonseDTO.setDetail(details);
        return new ResponseEntity<>(errorResonseDTO, HttpStatus.BAD_GATEWAY);
    }


}

