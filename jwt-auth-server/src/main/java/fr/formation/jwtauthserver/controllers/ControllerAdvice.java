package fr.formation.jwtauthserver.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * A controller advice to handle API exceptions in one place and override
 * default behavior.
 */
@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Handles validation errors. Encapsulates field and global errors into a
     * single collection of {@code ValidationError}s.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	    MethodArgumentNotValidException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
	BindingResult result = ex.getBindingResult();
	// Convert each FieldError to ValidationError
	List<FieldError> fieldErrors = result.getFieldErrors();
	List<ValidationError> errors = fieldErrors.stream()
		.map(e -> ValidationError.ofFieldType(e.getObjectName(),
			e.getField(), e.getDefaultMessage()))
		.collect(Collectors.toList());
	// Convert each ObjectError to ValidationError
	List<ObjectError> globalErrors = result.getGlobalErrors();
	globalErrors.stream()
		.map(e -> ValidationError.ofGlobalType(e.getObjectName(),
			e.getCode(), e.getDefaultMessage()))
		.forEach(errors::add);
	// Encapsulate ValidationError into ApiErrors
	ApiErrors<ValidationError> apiErrors = new ApiErrors<>(errors,
		status.value(), getMethod(), getRequestURI());
	return new ResponseEntity<>(apiErrors, status);
    }

    private static String getRequestURI() {
	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
		.currentRequestAttributes();
	return attr.getRequest().getRequestURI();
    }

    private static String getMethod() {
	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
		.currentRequestAttributes();
	return attr.getRequest().getMethod();
    }
}
