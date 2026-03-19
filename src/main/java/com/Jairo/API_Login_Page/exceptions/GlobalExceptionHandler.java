package com.Jairo.API_Login_Page.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(ResoruceNotFoundExption.class)


public ResponseEntity<String> HandleResourceNotFound(ResoruceNotFoundExption e){
   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());


}



}
