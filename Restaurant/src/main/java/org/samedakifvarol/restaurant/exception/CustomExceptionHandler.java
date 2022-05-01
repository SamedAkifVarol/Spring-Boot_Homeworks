package org.samedakifvarol.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RestaurantNotNullException.class)
    public ResponseEntity<?> restaurantNotNull(RestaurantNotNullException restaurantNotNullException) {
        List<String> detail = new ArrayList<>();
        detail.add(restaurantNotNullException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Restaurant not Null",detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
