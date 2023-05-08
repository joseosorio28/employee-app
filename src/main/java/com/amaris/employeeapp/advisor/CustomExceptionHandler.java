package com.amaris.employeeapp.advisor;

import com.amaris.employeeapp.advisor.customexceptions.DefaultException;
import com.amaris.employeeapp.advisor.customexceptions.EmployeeNotFoundException;
import com.amaris.employeeapp.advisor.customexceptions.RequestException;
import com.amaris.employeeapp.advisor.customexceptions.ServerException;
import com.amaris.employeeapp.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.concurrent.ConcurrentHashMap;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String FATAL_ERROR_CONTACT_ADMIN
            = "The application got a critical error, please contact the administrator.";
    private static final ConcurrentHashMap<String, Integer> STATUS_CODES = new ConcurrentHashMap<>();

    public CustomExceptionHandler() {
        STATUS_CODES.put(DefaultException.class.getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        STATUS_CODES.put(ServerException.class.getSimpleName(), HttpStatus.SERVICE_UNAVAILABLE.value());
        STATUS_CODES.put(RequestException.class.getSimpleName(), HttpStatus.TOO_MANY_REQUESTS.value());
        STATUS_CODES.put(EmployeeNotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
        STATUS_CODES.put(ConstraintViolationException.class.getSimpleName(), HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorResponse> handleAllExceptions(Exception exception) {

        ResponseEntity<ErrorResponse> response;
        String exceptionName = exception.getClass().getSimpleName();
        String message = exception.getMessage();
        Integer code = STATUS_CODES.get(exceptionName);

        ErrorResponse errorResponse;
        if (code != null) {
            errorResponse = new ErrorResponse(exceptionName, message, HttpStatus.valueOf(code));
        } else {
            errorResponse = new ErrorResponse(exceptionName, FATAL_ERROR_CONTACT_ADMIN, HttpStatus.INTERNAL_SERVER_ERROR);
            code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(code));
    }
}
