package com.phonepe.cabbooking.exceptions.exceptionhandler;

import com.phonepe.cabbooking.exceptions.InvalidCabException;
import com.phonepe.cabbooking.exceptions.InvalidCityException;
import com.phonepe.cabbooking.exceptions.NoCabsAvailableException;
import com.phonepe.cabbooking.exceptions.TripNotFoundException;
import com.phonepe.cabbooking.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    private String NOT_ACCEPTABLE = "NOT_ACCEPTABLE";
    private String BAD_REQUEST = "BAD_REQUEST";

    @ExceptionHandler({NoCabsAvailableException.class})
    public final ResponseEntity<ErrorResponse> handleCabNotFoundException
            (NoCabsAvailableException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(NOT_ACCEPTABLE, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({InvalidCityException.class})
    public final ResponseEntity<ErrorResponse> handleInvalidCityException
            (InvalidCityException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidCabException.class})
    public final ResponseEntity<ErrorResponse> handleInvalidCabException
            (InvalidCabException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TripNotFoundException.class})
    public final ResponseEntity<ErrorResponse> handleTripNotFoundException
            (TripNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

