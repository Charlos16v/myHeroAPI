package com.example.myHero.exception;

import com.example.myHero.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HeroExceptionHandler {

    @ExceptionHandler(HeroNotFoundException.class)
    public static ResponseEntity<ErrorResponse> handleHeroNotFoundException(HeroNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(404);
        errorResponse.setMessage("Hero not found");

        return ResponseEntity.status(errorResponse.getCode()).body(errorResponse);
    }
}
