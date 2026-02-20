package me.satyaki.TicketingApi.Exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import me.satyaki.TicketingApi.DTO.ErrorResponse;

/**
 * Global exception handler for all REST API endpoints
 * Handles custom and standard exceptions with consistent error responses
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle ResourceNotFoundException
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                "Resource Not Found",
                LocalDateTime.now());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle InvalidRequestException
     */
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(
            InvalidRequestException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "Invalid Request",
                LocalDateTime.now());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle UnauthorizedException
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(
            UnauthorizedException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                "Unauthorized Access",
                LocalDateTime.now());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle BusinessException
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                "Business Logic Violation",
                LocalDateTime.now());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handle MethodArgumentNotValidException for validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors()
                .forEach(error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                "Invalid Input",
                LocalDateTime.now());
        errorResponse.setDetails(errors);
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle 404 Not Found when endpoint doesn't exist
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Endpoint not found: " + ex.getRequestURL(),
                "Endpoint Not Found",
                LocalDateTime.now());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle all other generic exceptions
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                "Internal Server Error",
                LocalDateTime.now());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));

        // Add detailed message in development mode
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        errorResponse.setDetails(details);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
