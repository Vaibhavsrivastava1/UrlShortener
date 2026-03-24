package com.urlshorterner.urlshorterner.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.urlshorterner.urlshorterner.DT0.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> ValidationError(MethodArgumentNotValidException ex){

        Map<String , String>  errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        ErrorResponse response = ErrorResponse.builder()
                                 .success(false)
                                 .message("unsuccessful")
                                 .errors(errors)
                                 .build();
        return  ResponseEntity.badRequest().body(response);


    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> RuntimeError(RuntimeException ex){
         Map<String , String>  errors = new HashMap<>();
         errors.put("Runtime Error", ex.getMessage());
           ErrorResponse response = ErrorResponse.builder()
                                 .success(false)
                                 .message("unsuccessful")
                                 .errors(errors)
                                 .build();
        return  ResponseEntity.badRequest().body(response);



    }

}
