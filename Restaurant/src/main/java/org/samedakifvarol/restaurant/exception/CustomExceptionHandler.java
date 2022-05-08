package org.samedakifvarol.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<?> restaurantNotFound(RestaurantNotFoundException restaurantNotFoundException) {
        List<String> detail = new ArrayList<>();
        detail.add(restaurantNotFoundException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Restaurant Not Found",detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MenuNotFoundException.class)
    public ResponseEntity<?> menuNotFound(MenuNotFoundException menuNotFoundException) {
        List<String> detail = new ArrayList<>();
        detail.add(menuNotFoundException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Menu Not Found", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
