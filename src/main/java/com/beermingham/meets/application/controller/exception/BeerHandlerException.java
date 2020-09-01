package com.beermingham.meets.application.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.beermingham.meets.application.exception.DuplicateEntityException;
import com.beermingham.meets.application.exception.EntityNotFoundException;
import com.beermingham.meets.application.exception.ForbiddenException;
import com.beermingham.meets.application.exception.ServiceException;
import com.beermingham.meets.application.exception.ValidationException;

@ControllerAdvice
public class BeerHandlerException {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeerHandlerException.class);

    /**
     * Handle 500 Exception
     */
    @ExceptionHandler({ Exception.class})
    public ResponseEntity<HttpErrorResponse> handleGenericException(Exception exception) {
        HttpErrorResponse errorResponse = new HttpErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle 403 Exception
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<HttpErrorResponse> handleGenericException(HttpRequestMethodNotSupportedException exception) {
        HttpErrorResponse errorResponse = new HttpErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(),HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Handle 409 Conflict
     */
    @ExceptionHandler({ DuplicateEntityException.class})
    public ResponseEntity<HttpErrorResponse> handleConflictException(ServiceException exception) {
        HttpErrorResponse errorResponse = new HttpErrorResponse(HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT.getReasonPhrase(),exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handle 403 Forbidden
     */
    @ExceptionHandler({ ForbiddenException.class})
    public ResponseEntity<HttpErrorResponse> handleForbiddenException(ServiceException exception) {
        HttpErrorResponse errorResponse = new HttpErrorResponse(HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN.getReasonPhrase(),exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Handle 404 Not Found
     */
    @ExceptionHandler({ EntityNotFoundException.class})
    public ResponseEntity<HttpErrorResponse> handleNotFoundException(ServiceException exception) {
        HttpErrorResponse errorResponse = new HttpErrorResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle 400 BadRequest
     */
    @ExceptionHandler({ ValidationException.class})
    public ResponseEntity<HttpErrorResponse> handleValidationException(ServiceException exception) {
        HttpErrorResponse errorResponse = new HttpErrorResponse(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
